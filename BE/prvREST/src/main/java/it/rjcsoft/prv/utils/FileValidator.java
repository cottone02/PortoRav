package it.rjcsoft.prv.utils;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.rjcsoft.prv.constant.PrvFileConstant;

public class FileValidator {

	protected static final Logger log = LoggerFactory.getLogger(FileValidator.class);

	public static void validaFile(File file) throws FileFormatException {
		MDC.put(PrvFileConstant.PRV_TID, PrvUtils.generateTID());
		log.debug("START - Validazione file : ", file.getName());
		if (!file.exists()) {
			log.error("File inesistente");
			log.trace("END - File inesistente");
			throw new FileFormatException();
		}
		String fileName = file.getName();
		String fe = FilenameUtils.getExtension(fileName);
		if (StringUtils.equalsIgnoreCase(fe, PrvFileConstant.CSV_FORMAT)
				|| StringUtils.equalsIgnoreCase(fe, PrvFileConstant.XLS_FORMAT)
				|| StringUtils.equalsIgnoreCase(fe, PrvFileConstant.XLSX_FORMAT)) {
			log.info("inizio processazione file : ", fileName);
			log.trace("END - File processato : ", fileName);

		} else {
			if (StringUtils.equalsIgnoreCase(fe, PrvFileConstant.PDF_FORMAT)) {
				log.warn("Estensione pdf, file non processabile : ", fileName);
				log.trace("END - File non processabile : ", fileName);
			}
			log.error("Estensione file non accetata, file non processabile : ", fileName);
			log.trace("END - File non processato : ", fileName);
			throw new FileFormatException();

		}
	}

}
