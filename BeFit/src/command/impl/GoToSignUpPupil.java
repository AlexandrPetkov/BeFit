package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;

public class GoToSignUpPupil implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		return Constants.PAGE_SIGN_UP_PUPIL;
	}

}
