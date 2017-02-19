package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Constants;

public class ChangeLocaleController extends HttpServlet {

	private static final long serialVersionUID = 5014565495139093861L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(Constants.PARAM_ENCODING);
		resp.setCharacterEncoding(Constants.PARAM_ENCODING);
		changeLocale(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(Constants.PARAM_ENCODING);
		resp.setCharacterEncoding(Constants.PARAM_ENCODING);
		changeLocale(req, resp);
	}

	private void changeLocale(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(true);
		String locale = Constants.PARAM_LOCALE_RU;

		// System.out.println("Loc: " + req.getHeader(Constants.PARAM_REFERER));

		locale = (String) session.getAttribute(Constants.PARAM_LOCALE);

		if (locale == null) {
			locale = Constants.PARAM_LOCALE_RU;
		} else if (locale.equals(Constants.PARAM_LOCALE_RU)) {
			locale = Constants.PARAM_LOCALE_EN;
		} else {
			locale = Constants.PARAM_LOCALE_RU;
		}

		session.setAttribute(Constants.PARAM_LOCALE, locale);

		resp.sendRedirect(req.getHeader(Constants.PARAM_REFERER));

	}

}
