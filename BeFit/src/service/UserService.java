package service;

import java.util.Map;

import bean.User;
import service.exception.ServiceException;

public interface UserService {
	User singInUser(String login, String password) throws ServiceException;

	String changePhoto(Map<String, String> inputs, String oldPhoto, String path, int id) throws ServiceException;
}
