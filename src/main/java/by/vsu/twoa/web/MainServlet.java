package by.vsu.twoa.web;

import by.vsu.twoa.domain.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/index.html")
public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		if(session != null) {
			User user = (User) session.getAttribute("authenticatedUser");
			if(user != null) {
				resp.sendRedirect(req.getContextPath() + "/task/list.html");
				return;
			}
		}
		resp.sendRedirect(req.getContextPath() + "/login.html");
	}
}
