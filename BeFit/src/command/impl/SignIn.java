package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constant.Constants;
import bean.Pupil;
import bean.Trainer;
import bean.User;
import command.Command;
import command.exception.CommandNotFoundException;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

public class SignIn implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		User user = null;
		Pupil pupil = null;
		Trainer trainer = null;
		String page = null;
		HttpSession session = request.getSession();

		// if someone has 2 or more accounts and wants to signIn twice, destroy
		// previous session and create new
		Boolean isLogged = (Boolean) session.getAttribute(Constants.PARAM_IS_LOGGED);
		if (isLogged != null) {
			session.invalidate();
			session = request.getSession(true);
		}

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService service = factory.getUserService();

		String login = request.getParameter(Constants.PARAM_LOGIN);
		String password = request.getParameter(Constants.PARAM_PASSWORD);

		try {
			user = service.singInUser(login, password);

			if (user != null && user.getId() != 0) {

				if (user.isTrainer()) {
					trainer = (Trainer) user;
					session.setAttribute(Constants.PARAM_USER, trainer);
				} else {
					pupil = (Pupil) user;
					session.setAttribute(Constants.PARAM_USER, pupil);
				}

				session.setAttribute(Constants.PARAM_IS_LOGGED, true);
				session.setAttribute(Constants.PARAM_IS_TRAINER, user.isTrainer());
				page = Constants.PAGE_INDEX;

			} else {

				request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.NO_SUCH_USER);
				page = Constants.PAGE_SIGN_IN;

			}
		} catch (ServiceException e) {

			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
			page = Constants.PAGE_SIGN_IN;

		}

		return page;
	}

}
