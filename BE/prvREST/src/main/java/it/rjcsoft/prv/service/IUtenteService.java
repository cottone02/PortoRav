
package it.rjcsoft.prv.service;

import java.util.Calendar;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.rjcsoft.prv.dto.utente.UtenteDTO;
import it.rjcsoft.prv.dto.utente.UtenteFullDTO;
import it.rjcsoft.prv.dto.utente.UtenteUpdateDTO;
import it.rjcsoft.prv.enums.ResponseEnum;
import it.rjcsoft.prv.enums.Status;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;

public interface IUtenteService {

	public Page<UtenteDTO> getUsers(Predicate predicate, Pageable pageable);

	public boolean delete(int id) throws BaseEx;

	public UtenteFullDTO createUser(UtenteFullDTO userDTO) throws BaseEx;

	public boolean sendEmailToAdmin(String mailId, String... email);

	public UtenteDTO loadUserByUsernameOrEmail(String username) throws BaseEx;

	public void update(UtenteUpdateDTO userDTO) throws BaseEx;

	public Boolean sendEmail(String email);

	public Boolean checkOtpValidity(String email, String otp);

	public Boolean changePasswordByOtp(String email, String otp, String password);

	public Boolean changepwd(String username, String password) throws InternalError;

	public Boolean sendEmailActivateAccount(String email);

	public Boolean activateAccount(String email, String otp);

	public Boolean setInactiveUser(int id);

	public boolean sendMailToDev(String subject, String text);

	public ResponseEnum sendEmailRoleChanged(String email, String newRole);

	public boolean setOtpByEmail(String email, String otp, Calendar calendar);
        
        public Boolean changeAccountStatus(int id, Status status);
}
