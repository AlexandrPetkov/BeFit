package command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Pupil;
import bean.Trainer;
import bean.User;
import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;
import service.PupilService;
import service.ServiceFactory;
import service.TrainerService;
import service.exception.ServiceException;

public class MakeOffer implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		String page = Constants.PAGE_All_PUPILS;
		List<Pupil> pupils = null;
		User user = null;
		Trainer trainer = null;
		boolean isHired = false;
		int idUsers;

		HttpSession session = request.getSession();
		user = (User) session.getAttribute(Constants.PARAM_USER);

		if (user instanceof Trainer) {
			trainer = (Trainer) user;
		} else {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.NOT_A_TRAINER);
			return page;
		}

		idUsers = Integer.parseInt(request.getParameter(Constants.PARAM_ID_PUPIL));

		ServiceFactory factory = ServiceFactory.getInstance();
		TrainerService trainerService = factory.getTrainerService();
		PupilService pupilService = factory.getPupilService();

		try {
			isHired = trainerService.hireTrainer(idUsers, trainer.getId());

			if (!isHired) {
				request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.NEED_TO_LOGIN);
				page = Constants.PAGE_SIGN_IN;

				return page;
			}

			pupils = pupilService.getAllPupils();
			request.setAttribute(Constants.PARAM_USERS, pupils);

		} catch (ServiceException e) {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}

		return page;
	}

}
