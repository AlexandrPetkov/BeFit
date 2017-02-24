package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import command.Command;
import command.exception.CommandNotFoundException;
import constant.Constants;

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
		String commandName = null;

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			commandName = getRequestParameters(request);
		} else {
			commandName = request.getParameter(Constants.PARAM_COMMAND);
		}

		try {
			command = provider.getCommand(commandName);
			page = command.execute(request, response);
		} catch (CommandNotFoundException e) {
			// logger
		}

		if (page == null) {
			page = Constants.PAGE_INDEX;
		}

		if (page.equals(Constants.PAGE_PREVIOUS)) {

			response.sendRedirect(request.getHeader(Constants.PARAM_REFERER));
		} else
			request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * Get reuqest parameters with encoding type multipart.
	 * 
	 * @param item
	 * @throws Exception
	 * @return String commandName
	 */
	private String getRequestParameters(HttpServletRequest request) {
		Map<String, String> inputs = new HashMap<>();
		Random random = new Random();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 20);

		// creating temporary file
		File tempDir = (File) request.getServletContext().getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(tempDir);

		// creating uploader
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 30);

		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			File file = null;

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					// if is input field
					inputs.put(item.getFieldName(), item.getString());
				} else if (item.getSize() != 0) {
					// if is file
					String fullPath = null;
					String path = null;
					do {
						path = Constants.PARAM_USER_AVATAR + random.nextInt() + ".jpg";
						fullPath = getServletContext().getRealPath("/") + path;
						file = new File(fullPath);
					} while (file.exists());

					file.createNewFile();
					item.write(file);
					inputs.put(item.getFieldName(), path);
				}
			}
		} catch (Exception e) {
			// logger
			e.printStackTrace();
		}

		request.setAttribute(Constants.PARAM_REQUEST_PARAMETER, (Object) inputs);

		return inputs.get(Constants.PARAM_COMMAND);
	}

}
