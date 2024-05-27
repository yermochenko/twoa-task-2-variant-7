package by.vsu.twoa.web;

import by.vsu.twoa.config.ServiceFactory;
import by.vsu.twoa.domain.Task;
import by.vsu.twoa.service.TaskService;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.service.exception.TaskNotExistsException;
import by.vsu.twoa.service.exception.UserNotExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/task/edit.html")
public class TaskEditServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(TaskEditServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ServiceFactory factory = new ServiceFactory()) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				TaskService service = factory.getTaskService();
				Integer id = Integer.valueOf(req.getParameter("id"));
				Task task = service.findById(id);
				req.setAttribute("task", task);
			}
			req.getRequestDispatcher("/WEB-INF/jsp/task/edit.jsp").forward(req, resp);
		} catch(NumberFormatException | TaskNotExistsException | UserNotExistsException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} catch(ServiceException e) {
			throw new ServletException(e);
		}
	}
}
