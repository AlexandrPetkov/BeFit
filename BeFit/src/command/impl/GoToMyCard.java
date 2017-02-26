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

public class GoToMyCard implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		HttpSession session = request.getSession(false);

		// if session is over, go to signIn page
		if (session == null) {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.SESSION_IS_OVER);
			return Constants.PAGE_SIGN_IN;
		}

		String page = Constants.PAGE_PREVIOUS;
		Pupil pupil = null;
		Trainer trainer = null;
		User user = null;
		int id = 0;
		boolean isTrainer;
		ServiceFactory factory = ServiceFactory.getInstance();

		user = (User) session.getAttribute(Constants.PARAM_USER);
		id = user.getId();
		isTrainer = user.isTrainer();

		// get user
		try {

			if (isTrainer) {
				// getting trainer info
				TrainerService trainerService = factory.getTrainerService();
				trainer = trainerService.getTrainer(id);
				request.setAttribute(Constants.PARAM_USER, trainer);

				// getting all pupils info, related with current trainer
				List<Pupil> pupils = null;
				PupilService service = factory.getPupilService();
				pupils = service.getPupilsByIdTrainers(trainer.getId());
				request.setAttribute(Constants.PARAM_USERS, pupils);

				page = Constants.PAGE_TRAINERS_CARD;

			} else {
				// getting pupil info
				PupilService service = factory.getPupilService();
				pupil = service.getPupil(id);
				request.setAttribute(Constants.PARAM_USER, pupil);

				// getting pupil's trainer info
				TrainerService trainerService = factory.getTrainerService();
				trainer = trainerService.getTrainer(pupil.getIdTrainers());
				request.setAttribute(Constants.PARAM_TRAINER, trainer);

				page = Constants.PAGE_PUPILS_CARD;

			}

		} catch (ServiceException e) {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}

		return page;
	}
}
