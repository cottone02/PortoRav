#!/bin/sh

###########################################
#     Script di attivazione PrvApplication     #
#         Versione script 1.0.0           #
###########################################

#set -x 

# System Commands
EGREP="/bin/egrep -E"
AWK="/bin/awk"
RM="/bin/rm -f"
TOUCH="/bin/touch"
CAT="/bin/cat"
SED="/bin/sed"
RM="/bin/rm -f"

if [ `uname -s` = "AIX" ]; then
  HEAD="/bin/head"
  PS="/bin/ps auxww"
elif [ `uname -s` = "Linux" ]; then
  HEAD="/usr/bin/head"
  PS="/bin/ps auxww"
else
  HEAD="/bin/head"
  PS="/usr/ucb/ps -auxww"
fi


EXITCODE=0

# Funzione RealPath (toglie "." e ".." da un path assoluto)
RealPath_RET=""
RealPath()
{
  in_path=$1

  is_abs_path=`echo "$in_path" | $AWK '{
    if( index($0, "/") == 1 ) print "1"; else print "0";
  }'`

  if [ "$is_abs_path" = "0" ]; then
    in_path=`pwd`/$in_path
  fi

  RealPath_RET=`echo "$in_path" | $AWK '{
    if( $0 == "/" ) {
      print $0; exit; # Root
    }
    if( substr($0, 1, 1) != "/" ) {
      print $0; exit;  # Relative path
    }
    nr = split($0, subs, "/");
    path = "";
    for( i = 2; i <= nr; i++ ) {
      if( subs[i] == "." ) {
        if( path == "" )
          path = "/";
      } else if( subs[i] == ".." ) {
        if( path == "/" ) {
          print $0; exit; # Errore
        }
        nr2 = split(path, subs2, "/");
        if( nr2 == 2 ) {
           path = "/"; continue;
        }
        path = "";
        for( j = 2; j < nr2; j++ )
          path = path "/" subs2[j];
      } else if( substr(path, length(path), 1) == "/" )
        path = path subs[i];
      else
        path = path "/" subs[i];
    }
    print path;
  }'`
}

# Input script path
NAME="$0"

# Absolute directory
WORKDIR=`dirname "$NAME"`
RealPath "$WORKDIR"
WORKDIR="$RealPath_RET"

PWD=$WORKDIR
ROOT=`dirname $PWD`

# FlagFile
FLAGFILE=$WORKDIR/log/cluster.prvApplication

# the name of this script
SCRIPT=`basename $0`

# the name of the executable
DAEMON="it.rjcsoft.prv.PrvApplication"

#JAVA_HOME=/usr/local/smb/jdk
#export JAVA_HOME

STARTCMD="$JAVA_HOME/bin/java -Xmx1024m -Duser.timezone=UTC -classpath .:$WORKDIR/conf:$WORKDIR/lib/* it.rjcsoft.prv.PrvApplication"

COMMANDPS="$DAEMON"

PSHEAD=`$PS | $HEAD -n 1`

# Tempo di attesa in secondi per la terminazione del processo
SLEEPTIME=20

# File di Stop configurato in conf/application.properties
APPL_STOP_FILE=`$CAT $WORKDIR/conf/application.properties | $EGREP stopFile`
STOP_FILE=`echo $APPL_STOP_FILE | $AWK -F "=" '{print $2}'`
UNIX_STOP_FILE=`echo $STOP_FILE | $SED 's/\r//'`


case "$1" in
  start)

#
# check if the daemon is already running
#
    apid=`$PS | $EGREP -e "$COMMANDPS" | $EGREP -e ":$WORKDIR/conf:" | $EGREP -v -e "$EGREP -e $COMMANDPS"`
    if [ -n "$apid" ]; then
      pid=`echo "$apid" | $AWK '{ printf "%s ", $2; }'`
      echo "PrvApplication is already running with pid [$pid]"
      echo " "
      echo "$PSHEAD"
      echo "$apid"
      $RM $FLAGFILE
      EXITCODE=0
      exit $EXITCODE
    fi

    echo -n "Starting PrvApplication: ... "
     $STARTCMD > $WORKDIR/log/prvREST.out 2>&1 &

    sleep 1
    apid=`$PS | $EGREP -e "$COMMANDPS" | $EGREP -e ":$WORKDIR/conf:" | $EGREP -v -e "$EGREP -e $COMMANDPS"`
    if [ -n "$apid" ]; then
        pid=`echo "$apid" | $AWK '{ printf "%s ", $2; }'`
        echo "Ok! now running with pid [$pid]"
        $RM $FLAGFILE
        EXITCODE=0
    else
        echo "Failed!"
        if [ -r $WORKDIR/log/prvREST.out ]; then
        echo
        echo "Check the reasons in:"
        echo "====================="
        cat $WORKDIR/log/prvREST.out 
        #$HEAD -10 /tmp/start.$$
        fi
        EXITCODE=1
    fi
    ;;

  stop)
    apid=`$PS | $EGREP -e "$COMMANDPS" | $EGREP -e ":$WORKDIR/conf:" | $EGREP -v -e "$EGREP -e $COMMANDPS"`
    if [ -z "$apid" ]; then
      touch $FLAGFILE
      echo "PrvApplication is NOT running!"
      EXITCODE=0
      exit $EXITCODE
    fi

    pid=`echo "$apid" | $AWK '{ printf "%s ", $2; }'`
    echo -n "Stopping PrvApplication: [pid=$pid] ... "
    echo " "
    kill $pid

   # Aspetto per SLEEPTIME secondi
    sleeptime=0
    while [ $sleeptime -le $SLEEPTIME ]; do

      apid=`$PS | $EGREP -e "$COMMANDPS" | $EGREP -e ":$WORKDIR/conf:" | $EGREP -v -e "$EGREP -e $COMMANDPS"`
      if [ -z "$apid" ]; then

        touch $FLAGFILE
        echo "Ok"
        EXITCODE=0
        exit $EXITCODE
      fi

      sleep 1

      printf "."
      sleeptime=$((sleeptime + 1))

    done

    echo "Unable to stop PrvApplication [pid=$pid]!"
    EXITCODE=1
  ;;

  status)
    apid=`$PS | $EGREP -e "$COMMANDPS" | $EGREP -e ":$WORKDIR/conf:" | $EGREP -v -e "$EGREP -e $COMMANDPS"`
    if [ -z "$apid" ]; then
      echo "PrvApplication is NOT running"
      EXITCODE=1
    else
      pid=`echo "$apid" | $AWK '{ printf "%s ", $2; }'`
      echo "PrvApplication is currently running with pid [$pid]"
      echo " "
      echo "$PSHEAD"
      echo "$apid"
      EXITCODE=0
    fi
  ;;

  *)
    echo "Usage: $ROOT/bin/$SCRIPT {start|stop|status}" >&2
    EXITCODE=1
  ;;
esac

exit $EXITCODE
