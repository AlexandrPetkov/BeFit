package command.impl;

import java.util.List;

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
import service.TrainerService;
import service.exception.ServiceException;

public class HireTrainer implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		String page = Constants.PAGE_All_TRAINERS;
		List<Trainer> trainers = null;
		User user = null;
		Pupil pupil = null;
		boolean isHired = false;
		int idTrainers;

		HttpSession session = request.getSession();
		user = (User) session.getAttribute(Constants.PARAM_USER);

		if (user instanceof Pupil) {
			pupil = (Pupil) user;
		} else {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.NOT_A_PUPIL);

			return page;
		}

		idTrainers = Integer.parseInt(request.getParameter(Constants.PARAM_ID_TRAINER));

		ServiceFactory factory = ServiceFactory.getInstance();
		TrainerService service = factory.getTrainerService();

		try {
			isHired = service.hireTrainer(pupil.getId(), idTrainers);

			if (!isHired) {
				request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.NEED_TO_LOGIN);
				page = Constants.PAGE_SIGN_IN;

				return page;
			}

			trainers = service.getAllTrainers();
			pupil.setIdTrainers(idTrainers);
			session.setAttribute(Constants.PARAM_USER, pupil);

			request.setAttribute(Constants.PARAM_USERS, trainers);

		} catch (ServiceException e) {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}

		return page;
	}
}
