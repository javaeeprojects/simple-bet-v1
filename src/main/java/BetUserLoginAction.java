import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class BetUserLoginAction extends HttpServlet {

    @Inject
    BetUserBean betUserBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BetUser user = betUserBean.checkLoginData(req.getParameter("username"), req.getParameter("password"));
        if (user != null) {
            req.getSession(true).setAttribute("user", user);
            resp.sendRedirect("http://localhost:8080/simple-bet-v1-1.0-SNAPSHOT/login.html");
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{ \"error\" : \"Invalid username or password\"}");
    }
}
