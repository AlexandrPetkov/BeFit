package service;

import service.impl.PupilServiceImpl;
import service.impl.SourceServiceImpl;
import service.impl.TrainerServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
	private static ServiceFactory instance = null;

	private final UserService userService = new UserServiceImpl();
	private final PupilService pupilService = new PupilServiceImpl();
	private final TrainerService trainerService = new TrainerServiceImpl();
	private final SourceService sourceService = new SourceServiceImpl();

	private ServiceFactory() {
	}

	public SourceService getSourceService() {
		return sourceService;
	}

	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public PupilService getPupilService() {
		return pupilService;
	}

	public TrainerService getTrainerService() {
		return trainerService;
	}

	public UserService getUserService() {
		return userService;
	}
}
