package it.rjcsoft.prv.taskexecutor;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.rjcsoft.prv.dto.geotiff.GeotiffAllDTO;
import it.rjcsoft.prv.service.IGeotiffStyleService;

public class TaskExecutorToLoadGeotiffStyles {

	private static TaskExecutorToLoadGeotiffStyles instance;
	private ThreadPoolTaskExecutor executor;

	protected final Logger log = LoggerFactory.getLogger(TaskExecutorToLoadGeotiffStyles.class);

		
	public static TaskExecutorToLoadGeotiffStyles getInstance() {
		if (instance == null) {
			instance = new TaskExecutorToLoadGeotiffStyles();
			instance.executor = new ThreadPoolTaskExecutor();
			instance.executor.setCorePoolSize(10);
			instance.executor.setMaxPoolSize(100);
			instance.executor.setQueueCapacity(1);
			instance.executor.initialize();
		}
		return instance;
	}

	public void laod(GeotiffAllDTO dto, IGeotiffStyleService service) {
		log.debug("START - start saving geotiffStyles={}", dto.toString());
		instance.executor.execute(new ToLoadGeotiffStyles(dto, service, MDC.get("PRV_TID")));
		log.info("END -geotiffStyles={} succesfully saveda", dto.toString());
	}

}
