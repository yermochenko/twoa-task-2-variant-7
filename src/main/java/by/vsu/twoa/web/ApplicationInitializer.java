package by.vsu.twoa.web;

import by.vsu.twoa.config.ServiceFactory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ApplicationInitializer implements ServletContextListener {
	private static final Logger log = LogManager.getLogger(ApplicationInitializer.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String jdbcDriver   = context.getInitParameter("jdbc-driver");
		String jdbcUrl      = context.getInitParameter("jdbc-url");
		String jdbcUser     = context.getInitParameter("jdbc-user");
		String jdbcPassword = context.getInitParameter("jdbc-password");
		try {
			ServiceFactory.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
		} catch(ClassNotFoundException e) {
			log.fatal("Can't load JDBC driver", e);
		}
	}
}
