package command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Pupil;
import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;
import service.PupilService;
import service.ServiceFactory;
import service.exception.ServiceException;

public class ShowAllPupils implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		String page = null;
		List<Pupil> pupils = null;

		setUrlToSession(request);

		ServiceFactory factory = ServiceFactory.getInstance();
		PupilService service = factory.getPupilService();

		try {
			pupils = service.getPupilsByIdTrainers(0);

			if (pupils != null) {
				request.setAttribute(Constants.PARAM_USERS, pupils);
				page = Constants.PAGE_All_PUPILS;
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
			url.append("?" + request.getQueryString().toString());
		}

		request.getSession().setAttribute("url", url.toString());
	}

}
