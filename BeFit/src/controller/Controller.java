package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constant.Constants;
import command.Command;
import command.exception.CommandNotFoundException;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constants.PARAM_ENCODING);
		response.setCharacterEncoding(Constants.PARAM_ENCODING);
		proccessRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constants.PARAM_ENCODING);
		response.setCharacterEncoding(Constants.PARAM_ENCODING);
		proccessRequest(request, response);
	}

	private void proccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandHelper provider = CommandHelper.getInstatnce();
		Command command = null;
		String page = null;
		String commandName = request.getParameter(Constants.PARAM_COMMAND);

		try {
			command = provider.getCommand(commandName);
			page = command.execute(request, response);
		} catch (CommandNotFoundException e) {
			// logger
		}

		if (page == null) {
			page = Constants.PAGE_INDEX;
		}

		request.getRequestDispatcher(page).forward(request, response);

	}
}
