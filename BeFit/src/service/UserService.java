package service;

import bean.User;
import service.exception.ServiceException;

public interface UserService {
	User singInUser(String login, String password) throws ServiceException;
}
