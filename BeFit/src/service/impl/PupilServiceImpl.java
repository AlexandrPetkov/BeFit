package service.impl;

import java.util.List;

import bean.Pupil;
import constant.Constants;
import dao.DAOFactory;
import dao.PupilDAO;
import dao.exception.DAOException;
import service.PupilService;
import service.exception.ServiceException;

public class PupilServiceImpl implements PupilService {

	@Override
	public Pupil singUpPupil(Pupil pupil, String confPassword) throws ServiceException {

		// тут будет валидация данных с формы регистрации ученика (дописать)
		if (false) {
			pupil = null;
			throw new ServiceException(Constants.INCORRECT_REGISTRATION_DATA);
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		PupilDAO pupilDAO = daoFactory.getPupilDAO();

		try {
			pupil = pupilDAO.singUpPupil(pupil);
		} catch (DAOException e) {
			// logger
			throw new ServiceException(e);
		}

		return pupil;
	}

	@Override
	public List<Pupil> getPupilsByIdTrainers(int idTrainers) throws ServiceException {
		List<Pupil> pupils = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		PupilDAO pupilDAO = daoFactory.getPupilDAO();

		try {
			pupils = pupilDAO.getPupilsByIdTrainers(idTrainers);
		} catch (DAOException e) {
			// logger
			throw new ServiceException(e);
		}

		return pupils;
	}

	@Override
	public void editProfile(Pupil pupil) throws ServiceException {

		// тут будет валидация данных с формы регистрации ученика (дописать)
		if (false) {
			throw new ServiceException(Constants.INCORRECT_EDIT_DATA);
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		PupilDAO pupilDAO = daoFactory.getPupilDAO();

		try {
			pupilDAO.editProfile(pupil);
		} catch (DAOException e) {
			// logger
			throw new ServiceException(e);
		}
	}

	@Override
	public Pupil getPupil(int id) throws ServiceException {
		Pupil pupil = null;
		if (id > 0) {
			DAOFactory daoFactory = DAOFactory.getInstance();
			PupilDAO pupilDAO = daoFactory.getPupilDAO();

			try {
				pupil = pupilDAO.getPupil(id);
			} catch (DAOException e) {
				// logger
				throw new ServiceException(e);
			}
		} else {
			// logger
			System.out.println("Else place is services");
			throw new ServiceException(Constants.CANT_GO_TO_CABINET);
		}
		return pupil;
	}

}
