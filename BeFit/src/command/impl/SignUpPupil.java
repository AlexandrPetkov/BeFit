package command.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Pupil;
import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;
import service.PupilService;
import service.ServiceFactory;
import service.exception.ServiceException;

public class SignUpPupil implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		HttpSession session = request.getSession();
		String page = null;
		HashMap<String, String> inputs = null;

		inputs = (HashMap<String, String>) request.getAttribute("requestParatemers");

		Pupil pupil = fillPupil(inputs);

		String confPassword = request.getParameter(Constants.PARAM_PASSWORD_CONFIRMATION);

		ServiceFactory factory = ServiceFactory.getInstance();
		PupilService service = factory.getPupilService();

		try {
			pupil = service.singUpPupil(pupil, confPassword);

			if (pupil != null) {
				session.setAttribute(Constants.PARAM_USER, pupil);
				session.setAttribute(Constants.PARAM_IS_LOGGED, true);
				page = Constants.PAGE_All_TRAINERS;
			} else {
				page = Constants.PAGE_SIGN_UP_PUPIL;
			}
		} catch (ServiceException e) {
			page = Constants.PAGE_SIGN_UP_PUPIL;
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}
		return page;

	}

	private Pupil fillPupil(Map<String, String> inputs) {
		Pupil pupil = new Pupil();

		String login = inputs.get(Constants.PARAM_LOGIN); // request.getParameter(Constants.PARAM_LOGIN);
		String password = inputs.get(Constants.PARAM_PASSWORD);// request.getParameter(Constants.PARAM_PASSWORD);
		String name = inputs.get(Constants.PARAM_NAME);// request.getParameter(Constants.PARAM_NAME);
		String secondName = inputs.get(Constants.PARAM_SECOND_NAME);// request.getParameter(Constants.PARAM_SECOND_NAME);

		String photo = inputs.get(Constants.PARAM_PHOTO);

		String age = inputs.get(Constants.PARAM_AGE);// request.getParameter(Constants.PARAM_AGE);
		boolean isTrainer = false;
		boolean isMale = Boolean.parseBoolean(inputs.get(Constants.PARAM_IS_MALE));// request.getParameter(Constants.PARAM_IS_MALE));
		String height_sm = inputs.get(Constants.PARAM_HEIGHT);// request.getParameter(Constants.PARAM_HEIGHT);
		String weight = inputs.get(Constants.PARAM_WEIGHT);// request.getParameter(Constants.PARAM_WEIGHT);
		int idTrainers = 0;
		String goal = inputs.get(Constants.PARAM_GOAL);// request.getParameter(Constants.PARAM_GOAL);

		System.out.println(login);
		System.out.println(password);
		System.out.println(name);
		System.out.println(secondName);
		System.out.println(photo);
		System.out.println(age);
		System.out.println(isTrainer);
		System.out.println(isMale);
		System.out.println(height_sm);
		System.out.println(weight);
		System.out.println(idTrainers);
		System.out.println(goal);

		pupil.setLogin(login);
		pupil.setPassword(password);
		pupil.setName(name);
		pupil.setSecondName(secondName);
		pupil.setBirthday(Date.valueOf(age));
		pupil.setPhoto(photo);
		pupil.setTrainer(isTrainer);
		pupil.setHeight_sm(height_sm);
		pupil.setWeight(weight);
		pupil.setIdTrainers(idTrainers);
		pupil.setMale(isMale);
		pupil.setGoal(goal);

		return pupil;
	}
}
