package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constant.Constants;
import bean.Pupil;
import bean.Trainer;
import command.Command;
import command.exception.CommandNotFoundException;
import service.PupilService;
import service.ServiceFactory;
import service.TrainerService;
import service.exception.ServiceException;

public class GoToUserCard implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		String page = Constants.PAGE_SIGN_IN;
		Trainer trainer = null;
		Pupil pupil = null;
		int id = 0;
		boolean isTrainer;

		id = Integer.parseInt(request.getParameter(Constants.PARAM_ID));
		isTrainer = Boolean.parseBoolean(request.getParameter(Constants.PARAM_IS_TRAINER));

		setUrlToSession(request);

		ServiceFactory factory = ServiceFactory.getInstance();

		try {

			if (isTrainer) {
				TrainerService service = factory.getTrainerService();
				trainer = service.getTrainer(id);
				request.setAttribute(Constants.PARAM_USER, trainer);
				page = Constants.PAGE_TRAINERS_CARD;

			} else {
				PupilService service = factory.getPupilService();
				pupil = service.getPupil(id);
				request.setAttribute(Constants.PARAM_USER, pupil);
				page = Constants.PAGE_PUPILS_CARD;

			}

		} catch (ServiceException e) {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}

		return page;
	}

	private void setUrlToSession(HttpServletRequest request) {
		// HttpSession session = request.getSession();
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURL());

		if (request.getQueryString() != null) {
			url.append("?" + request.getQueryString());
		}

		request.getSession().setAttribute("url", url.toString());
	}
}
