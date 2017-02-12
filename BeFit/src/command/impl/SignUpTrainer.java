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

public class SignUpTrainer implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		String page = null;
		Trainer trainer = new Trainer();
		String confPassword = request.getParameter(Constants.PARAM_PASSWORD_CONFIRMATION);
		HttpSession session = request.getSession();

		trainer = fillTrainer(request, response);

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

	private Trainer fillTrainer(HttpServletRequest request, HttpServletResponse response) {
		Trainer trainer = new Trainer();

		String login = request.getParameter(Constants.PARAM_LOGIN);
		String password = request.getParameter(Constants.PARAM_PASSWORD);
		String name = request.getParameter(Constants.PARAM_NAME);
		String secondName = request.getParameter(Constants.PARAM_SECOND_NAME);

		// доделать полноценную загрузку аватаров в систему
		String photo = Constants.PARAM_PHOTO;

		String age = request.getParameter(Constants.PARAM_AGE);
		boolean isTrainer = true;
		boolean isMale = Boolean.parseBoolean(request.getParameter(Constants.PARAM_IS_MALE));
		String experience_years = request.getParameter(Constants.PARAM_EXPERIENCE);
		String specialization = request.getParameter(Constants.PARAM_SPECIALIZATION);
		String price = request.getParameter(Constants.PARAM_PRICE);
		String about = request.getParameter(Constants.PARAM_ABOUT);

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
