
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        UserService us = new UserService();
        RoleService rs = new RoleService();
        session.setAttribute("Manage", "Add");
        
        if (action != null && action.equals("delete")) {
            String email = (String) request.getParameter("emailSel");
            //cheated cuz of plus sign
            if(email.contains("cprg352")) {
                email = email.substring(0, 7) + "+" + email.substring(8);
            }
            session.setAttribute("message", "deleted " + email);
            try {
                us.del(email);
                session.setAttribute("Manage", "Add");
                session.setAttribute("email", "");
                session.setAttribute("firstName", "");
                session.setAttribute("lastName", "");
                session.setAttribute("password", "");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Sorry. Something went wrong upon deletion");
            }
        }
        
        if (action != null && action.equals("edit")) {
            session.setAttribute("Manage", "Edit");
            String email = (String) request.getParameter("emailSel");
            //cheated cuz of plus sign
            if(email.contains("cprg352")) {
                email = email.substring(0, 7) + "+" + email.substring(8);
            }
            try {
                List<Role> roles = rs.getRoles();
                User user = us.get(email, roles);
                session.setAttribute("message", "went here " + user.getEmail());
                
                session.setAttribute("email", user.getEmail());
                
                session.setAttribute("firstName", user.getFirstName());
                session.setAttribute("lastName", user.getLastName());
                session.setAttribute("password", user.getPassword());
                session.setAttribute("regularUser", false);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Sorry. Something went wrong upon deletion");
            }
        }
        
        try {

            List<Role> roles = rs.getRoles();
            List<User> users = us.getAll(roles);
            request.setAttribute("users", users);
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Sorry. Something went wrong");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        RoleService rs = new RoleService();
        
        
        try {
            List<Role> roles = rs.getRoles();
            List<User> users = us.getAll(roles);
            request.setAttribute("users", users);
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Sorry. Something went wrong");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
    
}
