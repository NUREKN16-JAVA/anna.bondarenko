package ua.nure.kn.bondarenko.web;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailsServlet extends EditServlet {

   
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            doOk(req, resp);
      
        }
        showPage(req, resp);
    }
    @Override
   
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/details.jsp").forward(req, resp);

    }
    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse").forward(req, resp);
        
    }
}
