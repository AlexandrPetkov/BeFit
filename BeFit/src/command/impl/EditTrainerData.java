package command.impl;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Trainer;
import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;
import service.ServiceFactory;
import service.TrainerService;
import service.exception.ServiceException;

public class EditTrainerData implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		HttpSession session = request.getSession(false);

		// if session is over, go to signIn page
		if (session == null) {
			request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.SESSION_IS_OVER);
			return Constants.PAGE_SIGN_IN;
		}

		String page = Constants.PAGE_PREVIOUS;
		Trainer trainer = null;
		Trainer oldTrainer = null;

		int id = Integer.parseInt(request.getParameter(Constants.PARAM_ID));
		trainer = (Trainer) session.getAttribute(Constants.PARAM_USER);
		oldTrainer = new Trainer(trainer);

		// if the request is hacked
		// and a hacker is trying to change another trainer's data
		if (trainer.getId() != id) {

			request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.CANT_EDIT_PUPIL_DATA);

			return page;
		}

		// executing request
		ServiceFactory factory = ServiceFactory.getInstance();
		TrainerService service = factory.getTrainerService();

		fillTrainer(trainer, request);

		try {
			service.editProfile(trainer);

		} catch (ServiceException e) {
			// logger
			session.setAttribute(Constants.PARAM_USER, oldTrainer);
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}

		return page;
	}

	private void fillTrainer(Trainer trainer, HttpServletRequest request) {
		String name = request.getParameter(Constants.PARAM_NAME);
		String secondName = request.getParameter(Constants.PARAM_SECOND_NAME);

		String age = request.getParameter(Constants.PARAM_AGE);
		String price = request.getParameter(Constants.PARAM_PRICE);
		String experience = request.getParameter(Constants.PARAM_EXPERIENCE);
		String specialization = request.getParameter(Constants.PARAM_SPECIALIZATION);
		String about = request.getParameter(Constants.PARAM_ABOUT);

		trainer.setName(name);
		trainer.setSecondName(secondName);
		trainer.setBirthday(Date.valueOf(age));
		trainer.setPrice(price);
		trainer.setExperience_years(experience);
		trainer.setSpecialization(specialization);
		trainer.setAbout(about);

	}
}
