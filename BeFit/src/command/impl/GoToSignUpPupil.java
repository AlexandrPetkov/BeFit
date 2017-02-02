package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Constant.Constants;
import command.Command;
import command.exception.CommandNotFoundException;

public class GoToSignUpPupil implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException {
		return Constants.PAGE_SIGN_UP_PUPIL;
	}

}
