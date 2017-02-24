package service.impl;

import java.io.File;
import java.util.Map;

import bean.User;
import constant.Constants;
import dao.DAOFactory;
import dao.UserDAO;
import dao.exception.DAOException;
import service.UserService;
import service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public User singInUser(String login, String password) throws ServiceException {
		User user = null;

		if (login != "" && password != "") {
			DAOFactory factory = DAOFactory.getInstance();
			UserDAO userDAO = factory.getUserDAO();

			try {
				user = userDAO.singInUser(login, password);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		} else {
			throw new ServiceException(Constants.EMPTY_LOGIN_OR_PASSWORD);
		}

		return user;
	}

	@Override
	public String changePhoto(Map<String, String> inputs, String oldPhoto, String path, int id) throws ServiceException {
		String newPhoto = null;
		String oldFilePath = path + oldPhoto;
		String newFilePath = path + inputs.get(Constants.PARAM_USER_PHOTO);

		newPhoto = inputs.get(Constants.PARAM_USER_PHOTO);

		// when user already has a photo on the server and download new one to
		// replace
		if (newPhoto != null && !oldPhoto.equals(Constants.PARAM_USER_NO_PHOTO)) {

			boolean isPhotoChanged = false;
			isPhotoChanged = new File(newFilePath).renameTo(new File(oldFilePath));

			if (isPhotoChanged) {
				return oldPhoto;
			} else
				throw new ServiceException(Constants.CANT_RENAME_PHOTO_FILE);
		}

		// when user didn't have photo on the server before or didn't really
		// download it
		if (newPhoto == null) {
			newPhoto = Constants.PARAM_USER_NO_PHOTO;
			File photo = new File(oldFilePath);
			photo.delete();
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			userDAO.updatePhotoField(newPhoto, id);
		} catch (DAOException e) {
			// //logger
			e.printStackTrace();
			throw new ServiceException(e);
		}

		return newPhoto;
	}
}
