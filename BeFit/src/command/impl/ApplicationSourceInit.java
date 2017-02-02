package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import service.ServiceFactory;
import service.SourceService;

public class ApplicationSourceInit implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceFactory factory = ServiceFactory.getInstance();
		SourceService service = factory.getSourceService();

		service.sourceInit();

		return null;
	}

}
