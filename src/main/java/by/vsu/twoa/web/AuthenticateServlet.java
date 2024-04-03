package by.vsu.twoa.web;

import by.vsu.twoa.config.ServiceFactory;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.service.UserService;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.service.exception.UserNotAuthenticatedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/login.html")
public class AuthenticateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		if(login != null && password != null && !login.isBlank() && !password.isBlank()) {
			try(ServiceFactory factory = new ServiceFactory()) {
				UserService userService = factory.getUserService();
				User user = userService.authenticate(login, password);
				HttpSession session = req.getSession();
				session.setAttribute("authenticatedUser", user);
				resp.sendRedirect(req.getContextPath());
			} catch(UserNotAuthenticatedException e) {
				resp.sendRedirect(req.getContextPath() + "/login.html?error=" + URLEncoder.encode("Имя пользователя или пароль не опознаны", StandardCharsets.UTF_8));
			} catch(ServiceException e) {
				throw new ServletException(e);
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
