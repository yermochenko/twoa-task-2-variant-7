package by.vsu.twoa.web;

import by.vsu.twoa.domain.User;
import by.vsu.twoa.web.exception.AccessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebFilter({"/task/list.html"})
public class AuthorizationFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			if(session == null) throw new AccessException();
			User user = (User) session.getAttribute("authenticatedUser");
			if(user == null) throw new AccessException();
			chain.doFilter(req, resp);
		} catch(AccessException e) {
			resp.sendRedirect(req.getContextPath() + "/login.html?warn=" + URLEncoder.encode("Доступ к запрошенной странице запрещён", StandardCharsets.UTF_8));
		}
	}
}
