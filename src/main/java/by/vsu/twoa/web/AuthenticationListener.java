package by.vsu.twoa.web;

import by.vsu.twoa.domain.User;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class AuthenticationListener implements HttpSessionListener, HttpSessionAttributeListener {
	private static final Logger log = LogManager.getLogger(AuthenticationListener.class);

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if("authenticatedUser".equals(event.getName())) {
			User user = (User) event.getValue();
			log.debug("User {} logged in", user.getLogin());
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		User user = (User) event.getSession().getAttribute("authenticatedUser");
		log.debug("User {} logged out", user.getLogin());
	}
}
