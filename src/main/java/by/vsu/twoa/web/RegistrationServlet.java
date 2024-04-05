package by.vsu.twoa.web;

import by.vsu.twoa.config.ServiceFactory;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.service.UserService;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.web.exception.InvalidFormDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/register.html")
public class RegistrationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			User user = new User();

			String firstName = req.getParameter("first-name");
			if(firstName == null || firstName.isEmpty()) throw new InvalidFormDataException();
			user.setFirstName(firstName);

			String middleName = req.getParameter("middle-name");
			if(middleName != null && !middleName.isEmpty()) {
				user.setMiddleName(middleName);
			}

			String lastName = req.getParameter("last-name");
			if(lastName == null || lastName.isEmpty()) throw new InvalidFormDataException();
			user.setLastName(lastName);

			String birthday = req.getParameter("birthday");
			user.setBirthday(format.parse(birthday));

			String login = req.getParameter("login");
			if(login == null || login.isEmpty()) throw new InvalidFormDataException();
			user.setLogin(login);

			String password = req.getParameter("password");
			if(password == null || password.isEmpty()) throw new InvalidFormDataException();
			user.setPassword(password);

			try(ServiceFactory factory = new ServiceFactory()) {
				UserService userService = factory.getUserService();
				userService.register(user);
				HttpSession session = req.getSession();
				session.setAttribute("authenticatedUser", user);
				resp.sendRedirect(req.getContextPath());
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} catch(InvalidFormDataException | ParseException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
