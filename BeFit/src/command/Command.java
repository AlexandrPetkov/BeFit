package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.exception.CommandNotFoundException;

public interface Command {
	
	String execute(HttpServletRequest request, HttpServletResponse response) throws CommandNotFoundException;
	
}
