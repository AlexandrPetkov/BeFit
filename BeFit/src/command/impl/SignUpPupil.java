package command.impl;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constant.Constants;
import bean.Pupil;
import command.Command;
import command.exception.CommandNotFoundException;
import service.PupilService;
import service.ServiceFactory;
import service.exception.ServiceException;

public class SignUpPupil implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		HttpSession session = request.getSession();
		String page = null;
		Pupil pupil = fillPupil(request, response);
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

	private Pupil fillPupil(HttpServletRequest request, HttpServletResponse response) {
		Pupil pupil = new Pupil();

		String login = request.getParameter(Constants.PARAM_LOGIN);
		String password = request.getParameter(Constants.PARAM_PASSWORD);
		String name = request.getParameter(Constants.PARAM_NAME);
		String secondName = request.getParameter(Constants.PARAM_SECOND_NAME);

		// доделать полноценную загрузку аватаров в систему
		String photo = Constants.PARAM_PHOTO;

		String age = request.getParameter(Constants.PARAM_AGE);
		boolean isTrainer = false;
		boolean isMale = Boolean.parseBoolean(request.getParameter(Constants.PARAM_IS_MALE));
		String height_sm = request.getParameter(Constants.PARAM_HEIGHT);
		String weight = request.getParameter(Constants.PARAM_WEIGHT);
		int idTrainers = 0;
		String goal = request.getParameter(Constants.PARAM_WEIGHT);

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
