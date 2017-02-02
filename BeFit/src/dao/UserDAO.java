package dao;

import bean.User;
import dao.exception.DAOException;

public interface UserDAO {
	User singInUser(String login, String password) throws DAOException;
}
