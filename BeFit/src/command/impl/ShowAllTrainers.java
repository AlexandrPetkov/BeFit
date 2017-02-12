package command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Trainer;
import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;
import service.ServiceFactory;
import service.TrainerService;
import service.exception.ServiceException;

public class ShowAllTrainers implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		String page = null;
		List<Trainer> trainers = null;

		setUrlToSession(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		TrainerService service = factory.getTrainerService();

		try {
			trainers = service.getAllTrainers();

			if (trainers != null) {
				request.setAttribute(Constants.PARAM_USERS, trainers);
				page = Constants.PAGE_All_TRAINERS;
			}
		} catch (ServiceException e) {
			page = Constants.PAGE_ERROR;
			request.setAttribute(Constants.PARAM_ERROR_TEXT, e.getMessage());
		}
		return page;
	}

	private void setUrlToSession(HttpServletRequest request) {
		// HttpSession session = request.getSession();
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURL());

		if (request.getQueryString() != null) {
			url.append("?" + request.getQueryString());
		}

		request.getSession().setAttribute("url", url.toString());
	}

}
