package servlet;

import controller.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by YR on 2018/11/30.
 */

public class RegisterServlet extends HttpServlet {
    UserService service = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter ("phone");

        boolean flag = service.register(userName, password, phone);
        if (flag == false) {
            request.getRequestDispatcher("/register.html").forward(request, response);
        } else {
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

