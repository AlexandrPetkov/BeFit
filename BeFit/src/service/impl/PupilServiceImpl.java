package service.impl;

import java.util.List;

import Constant.Constants;
import bean.Pupil;
import dao.DAOFactory;
import dao.PupilDAO;
import dao.exception.DAOException;
import service.PupilService;
import service.exception.ServiceException;

public class PupilServiceImpl implements PupilService {

	@Override
	public Pupil singUpPupil(Pupil pupil, String confPassword) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		PupilDAO pupilDAO = daoFactory.getPupilDAO();

		// тут будет валидация данных с формы регистрации ученика (дописать)
		if (false) {
			pupil = null;
			throw new ServiceException(Constants.INCORRECT_REGISTRATION_DATA);
		}

		try {
			pupil = pupilDAO.singUpPupil(pupil);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return pupil;
	}

	@Override
	public List<Pupil> getAllPupils() throws ServiceException {
		List<Pupil> pupils = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		PupilDAO pupilDAO = daoFactory.getPupilDAO();

		try {
			pupils = pupilDAO.getAllPupils();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return pupils;
	}

	@Override
	public Pupil editProfile(Pupil pupil) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException(Constants.CANT_GO_TO_CABINET);
		}
		return pupil;
	}

}
