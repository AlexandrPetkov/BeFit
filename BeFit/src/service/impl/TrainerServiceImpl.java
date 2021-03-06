package service.impl;

import java.util.List;

import bean.Trainer;
import constant.Constants;
import dao.DAOFactory;
import dao.TrainerDAO;
import dao.exception.DAOException;
import service.TrainerService;
import service.exception.ServiceException;

public class TrainerServiceImpl implements TrainerService {

	@Override
	public Trainer singUpTrainer(Trainer trainer, String confPassword) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		TrainerDAO trainerDAO = daoFactory.getTrainerDAO();

		// тут будет валидация данных с формы регистрации ученика (дописать)
		if (false) {
			trainer = null;
			throw new ServiceException(Constants.INCORRECT_REGISTRATION_DATA);
		}

		try {
			trainer = trainerDAO.singUpTrainer(trainer);
		} catch (DAOException e) {
			throw new ServiceException(e);

		}
		return trainer;
	}

	@Override
	public void editProfile(Trainer trainer) throws ServiceException {
		// тут будет валидация данных с формы регистрации ученика (дописать)
		if (false) {
			throw new ServiceException(Constants.INCORRECT_EDIT_DATA);
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		TrainerDAO trainerDAO = daoFactory.getTrainerDAO();

		try {
			trainerDAO.editProfile(trainer);
		} catch (DAOException e) {
			// logger
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Trainer> getAllTrainers() throws ServiceException {
		List<Trainer> trainers = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		TrainerDAO trainerDAO = daoFactory.getTrainerDAO();

		try {
			trainers = trainerDAO.getAllTrainers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return trainers;
	}

	@Override
	public boolean hireTrainer(int idUsers, int idTrainers) throws ServiceException {
		if (idUsers > 0) {
			if (idTrainers > 0) {
				DAOFactory factory = DAOFactory.getInstance();
				TrainerDAO trainerDAO = factory.getTrainerDAO();

				try {
					trainerDAO.hireTrainer(idUsers, idTrainers);
					return true;

				} catch (DAOException e) {
					throw new ServiceException(e.getMessage());
				}

			} else {
				throw new ServiceException(Constants.CANT_HIRE_TRAINER);
			}

		} else {
			return false;
		}
	}

	@Override
	public boolean fireTrainer(int idUsers) throws ServiceException {
		if (idUsers > 0) {
			DAOFactory factory = DAOFactory.getInstance();
			TrainerDAO trainerDAO = factory.getTrainerDAO();

			try {
				trainerDAO.fireTrainer(idUsers);
				return true;

			} catch (DAOException e) {
				throw new ServiceException(e.getMessage());
			}
		} else {
			return false;
		}
	}

	@Override
	public Trainer getTrainer(int id) throws ServiceException {
		Trainer trainer = null;

		if (id > 0) {
			DAOFactory daoFactory = DAOFactory.getInstance();
			TrainerDAO trainerDAO = daoFactory.getTrainerDAO();

			try {
				trainer = trainerDAO.getTrainer(id);
			} catch (DAOException e) {
				throw new ServiceException(e.getMessage());
			}
		}

		return trainer;
	}

}
