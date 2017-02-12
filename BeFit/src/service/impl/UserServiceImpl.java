package service.impl;

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
}
