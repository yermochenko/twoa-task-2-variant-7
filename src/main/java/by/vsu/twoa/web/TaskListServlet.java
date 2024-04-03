package by.vsu.twoa.web;

import by.vsu.twoa.config.ServiceFactory;
import by.vsu.twoa.domain.Task;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet("/task/list.html")
public class TaskListServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(TaskListServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ServiceFactory factory = new ServiceFactory()) {
			TaskService service = factory.getTaskService();
			User user = (User) req.getSession().getAttribute("authenticatedUser");
			log.debug("Current user {} with ID={} open start page", user.getLogin(), user.getId());
			List<Task> tasks = service.findByOwner(1);
			req.setAttribute("tasks", tasks);
			req.getRequestDispatcher("/WEB-INF/jsp/task/list.jsp").forward(req, resp);
		} catch(ServiceException e) {
			throw new ServletException(e);
		}
	}
}
