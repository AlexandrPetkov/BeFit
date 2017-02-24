package command.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

public class EditUserPhoto implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		HttpSession session = request.getSession();
		String page = Constants.PAGE_PREVIOUS;
		HashMap<String, String> inputs = null;
		User user = null;
		int id = 0;
		String photo = null;

		user = (User) session.getAttribute(Constants.PARAM_USER);
		inputs = (HashMap<String, String>) request.getAttribute(Constants.PARAM_REQUEST_PARAMETER);
		id = Integer.parseInt(inputs.get(Constants.PARAM_ID));

		if (user.getId() == id) {
			ServiceFactory factory = ServiceFactory.getInstance();
			UserService service = factory.getUserService();

			try {
				photo = service.changePhoto(inputs, user.getPhoto(), request.getServletContext().getRealPath("/"), id);

				user.setPhoto(photo);
			} catch (ServiceException e) {
				// logger
				// react on serviceException
				e.printStackTrace();
			}
		} else {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.CANT_EDIT_PUPIL_DATA);
		}

		return page;
	}

}
