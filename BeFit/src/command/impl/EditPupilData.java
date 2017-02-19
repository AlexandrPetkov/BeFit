package command.impl;

import java.sql.Date;

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

public class EditPupilData implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		HttpSession session = request.getSession();
		String page = Constants.PAGE_PREVIOUS;
		Pupil pupil = null;
		int id = Integer.parseInt(request.getParameter(Constants.PARAM_ID));

		pupil = (Pupil) session.getAttribute(Constants.PARAM_USER);

		ServiceFactory factory = ServiceFactory.getInstance();
		PupilService service = factory.getPupilService();

		if (pupil.getId() != id) {

			request.setAttribute(Constants.PARAM_ERROR_TEXT, Constants.CANT_EDIT_PUPIL_DATA);

			return page;
		}

		fillPupil(pupil, request);

		try {
			service.editProfile(pupil);
		} catch (ServiceException e) {
			// logger
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}

		return page;
	}

	private void fillPupil(Pupil pupil, HttpServletRequest request) {
		String name = request.getParameter(Constants.PARAM_NAME);
		String secondName = request.getParameter(Constants.PARAM_SECOND_NAME);

		String age = request.getParameter(Constants.PARAM_AGE);
		boolean isTrainer = false;
		String height_sm = request.getParameter(Constants.PARAM_HEIGHT);
		String weight = request.getParameter(Constants.PARAM_WEIGHT);
		String goal = request.getParameter(Constants.PARAM_GOAL);

		pupil.setName(name);
		pupil.setSecondName(secondName);
		pupil.setBirthday(Date.valueOf(age));
		pupil.setTrainer(isTrainer);
		pupil.setHeight_sm(height_sm);
		pupil.setWeight(weight);
		pupil.setGoal(goal);
	}

}
