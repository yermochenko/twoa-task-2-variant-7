package by.vsu.twoa.web;

import by.vsu.twoa.config.ServiceFactory;
import by.vsu.twoa.domain.Task;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.geometry.Point;
import by.vsu.twoa.geometry.Triangle;
import by.vsu.twoa.service.TaskService;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.web.exception.InvalidFormDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/task/save.html")
public class TaskSaveServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(TaskSaveServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Task task = new Task();

			HttpSession session = req.getSession();
			task.setOwner((User)session.getAttribute("authenticatedUser"));

			String id = req.getParameter("id");
			if(id != null) {
				task.setId(Integer.valueOf(id));
			}

			String name = req.getParameter("name");
			if(name == null || name.isEmpty()) throw new InvalidFormDataException();
			task.setName(name);

			task.setTriangle(
				new Triangle(
					new Point(
						Double.parseDouble(req.getParameter("x1")),
						Double.parseDouble(req.getParameter("y1"))
					),
					new Point(
						Double.parseDouble(req.getParameter("x2")),
						Double.parseDouble(req.getParameter("y2"))
					),
					new Point(
						Double.parseDouble(req.getParameter("x3")),
						Double.parseDouble(req.getParameter("y3"))
					)
				)
			);

			try(ServiceFactory factory = new ServiceFactory()) {
				TaskService service = factory.getTaskService();
				Integer taskId = service.save(task);
				resp.sendRedirect(req.getContextPath() + "/task/edit.html?id=" + taskId + "&info=" + URLEncoder.encode("Задача успешно сохранена", StandardCharsets.UTF_8));
			} catch(ServiceException e) {
				throw new ServletException(e);
			}

		} catch(InvalidFormDataException | NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
