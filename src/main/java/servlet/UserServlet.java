package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pojo.User;
import DAO.UserDao;
/**
 * Created by YR on 2018/12/1.
 */

public class UserServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        int start = 0;
        int count = 5;

        try {
            start = Integer.parseInt(request.getParameter("start"));
        } catch (NumberFormatException e) {}

        int next = start + count;
        int pre = start - count;

        int total = new UserDao().getTotal();
        int last;

        if (0 == total % count)
            last = total - count;
        else
            last = total - total % count;

        pre = pre < 0 ? 0 : pre;
        next = next > last ? last : next;

        request.setAttribute("next", next);
        request.setAttribute("pre", pre);
        request.setAttribute("last", last);

        List<User> users = new UserDao().list(start, count);
        JSONArray data = JSONArray.fromObject(users);
        response.setCharacterEncoding ("utf-8");
        PrintWriter re = response.getWriter();
        re.append (data.toString());
    }
}
