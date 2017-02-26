package service;

import java.util.List;

import bean.Trainer;
import service.exception.ServiceException;

public interface TrainerService {
	Trainer singUpTrainer(Trainer trainer, String confPassword) throws ServiceException;

	Trainer getTrainer(int id) throws ServiceException;

	List<Trainer> getAllTrainers() throws ServiceException;

	void editProfile(Trainer trainer) throws ServiceException;

	boolean hireTrainer(int idUsers, int idTrainers) throws ServiceException;

	boolean fireTrainer(int idUsers) throws ServiceException;

}
