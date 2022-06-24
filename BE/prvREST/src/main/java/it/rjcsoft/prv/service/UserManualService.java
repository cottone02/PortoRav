package it.rjcsoft.prv.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import it.rjcsoft.prv.config.PrvRestConfig;

@Service
public class UserManualService extends BaseService implements IUserManualService {
	
	@Autowired
	private PrvRestConfig prvRestConfig;
	
	@Override
	public File getManual() {
		File manual = new File(prvRestConfig.getUserManualPath());
		if (!manual.exists()) {
			manual = null;
		}
		return manual;
	}

}
