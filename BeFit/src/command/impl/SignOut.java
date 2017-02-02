package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Constant.Constants;
import command.Command;
import command.exception.CommandNotFoundException;

public class SignOut implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		String page = null;
		HttpSession session = request.getSession();
		session.invalidate();
		page = Constants.PAGE_INDEX;

		return page;
	}

}
