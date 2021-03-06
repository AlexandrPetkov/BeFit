package command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Pupil;
import bean.Trainer;
import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;
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

		ServiceFactory factory = ServiceFactory.getInstance();

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
