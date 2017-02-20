package command.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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

public class SignUpTrainer implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		HttpSession session = request.getSession();
		String page = null;
		HashMap<String, String> inputs = null;

		inputs = (HashMap<String, String>) request.getAttribute("requestParatemers");

		Trainer trainer = fillTrainer(inputs);

		String confPassword = inputs.get(Constants.PARAM_PASSWORD_CONFIRMATION);

		ServiceFactory factory = ServiceFactory.getInstance();
		TrainerService service = factory.getTrainerService();

		try {
			trainer = service.singUpTrainer(trainer, confPassword);

			if (trainer != null) {
				session.setAttribute(Constants.PARAM_USER, trainer);
				session.setAttribute(Constants.PARAM_IS_LOGGED, true);
				page = Constants.PAGE_All_PUPILS;
			} else {
				page = Constants.PAGE_SIGN_UP_TRAINER;

			}
		} catch (ServiceException e) {
			page = Constants.PAGE_SIGN_UP_TRAINER;
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}

		return page;
	}

	private Trainer fillTrainer(Map<String, String> inputs) {
		Trainer trainer = new Trainer();

		String login = inputs.get(Constants.PARAM_LOGIN);
		String password = inputs.get(Constants.PARAM_PASSWORD);
		String name = inputs.get(Constants.PARAM_NAME);
		String secondName = inputs.get(Constants.PARAM_SECOND_NAME);

		String photo = inputs.get(Constants.PARAM_USER_PHOTO);
		if (photo == null) {
			photo = Constants.PARAM_USER_NO_PHOTO;
		}

		String age = inputs.get(Constants.PARAM_AGE);
		boolean isTrainer = true;
		boolean isMale = Boolean.parseBoolean(inputs.get(Constants.PARAM_IS_MALE));
		String experience_years = inputs.get(Constants.PARAM_EXPERIENCE);
		String specialization = inputs.get(Constants.PARAM_SPECIALIZATION);
		String price = inputs.get(Constants.PARAM_PRICE);
		String about = inputs.get(Constants.PARAM_ABOUT);

		trainer.setLogin(login);
		trainer.setPassword(password);
		trainer.setName(name);
		trainer.setSecondName(secondName);
		trainer.setBirthday(Date.valueOf(age));
		trainer.setPhoto(photo);
		trainer.setTrainer(isTrainer);
		trainer.setMale(isMale);
		trainer.setExperience_years(experience_years);
		trainer.setSpecialization(specialization);
		trainer.setPrice(price);
		trainer.setAbout(about);

		return trainer;
	}

}
