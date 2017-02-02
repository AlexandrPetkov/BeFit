package dao;

import dao.impl.DbInitImpl;
import dao.impl.PupilDAOImpl;
import dao.impl.TrainerDAOImpl;
import dao.impl.UserDAOImpl;

public class DAOFactory {
	private static DAOFactory instance = null;
	private final UserDAO userDAO = new UserDAOImpl();
	private final PupilDAO pupilDAO = new PupilDAOImpl();
	private final TrainerDAO trainerDAO = new TrainerDAOImpl();
	private final SourceInit sourceInit = new DbInitImpl();

	private DAOFactory() {
	}

	public SourceInit getSourceInit() {
		return sourceInit;
	}

	public TrainerDAO getTrainerDAO() {
		return trainerDAO;
	}

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public PupilDAO getPupilDAO() {
		return pupilDAO;
	}

}
