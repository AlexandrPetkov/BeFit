package dao;

import java.util.List;

import bean.Trainer;
import dao.exception.DAOException;

public interface TrainerDAO {
	public Trainer singUpTrainer(Trainer trainer) throws DAOException;

	List<Trainer> getAllTrainers() throws DAOException;

	void hireTrainer(int idUsers, int idTrainers) throws DAOException;

	void fireTrainer(int idUsers) throws DAOException;

	void editProfile(Trainer trainer) throws DAOException;

	Trainer getTrainer(int id) throws DAOException;
}
