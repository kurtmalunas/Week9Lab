
package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
        session.setAttribute("message", "Welcome");
        
        session.setAttribute("edit", false);
        session.setAttribute("adding", true);
        if (action != null && action.equals("delete")) {
            String email = (String) request.getParameter("emailSel");
            //cheated cuz of plus sign
            if(email.contains("cprg352")) {
                email = email.substring(0, 7) + "+" + email.substring(8);
            }
            session.setAttribute("message", "Deleted user: " + email);
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
        
        try {
            List<Role> roles = rs.getRoles();
            List<User> users = us.getAll(roles);
            session.setAttribute("roles", roles);
            session.setAttribute("users", users);
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Sorry. Something went wrong");
        }
        
        if (action != null && action.equals("edit")) {
            session.setAttribute("Manage", "Edit");
            session.setAttribute("message", "");
            session.setAttribute("edit", true);
            session.setAttribute("adding", false);
            String email = (String) request.getParameter("emailSel");
            //cheated cuz of plus sign
            if(email.contains("cprg352")) {
                email = email.substring(0, 7) + "+" + email.substring(8);
            }
            
            try {
                List<Role> roles = rs.getRoles();
                List<Role> roles2 = new ArrayList<>();
                User user = us.get(email, roles);
                session.setAttribute("email", user.getEmail());
                session.setAttribute("firstName", user.getFirstName());
                session.setAttribute("lastName", user.getLastName());
                session.setAttribute("password", user.getPassword());
                session.setAttribute("selectedRole", user.getRole().getRoleId());
                for (int i = 0; i < roles.size(); i++){
                    if(i == 0){
                    for(int j = 0; j < roles.size(); j++){
                        if(user.getRole().getRoleId() == roles.get(j).getRoleId()) {
                            roles2.add(roles.get(j));
                        }
                    }
                    }
                    if(user.getRole().getRoleId() != roles.get(i).getRoleId()) {
                            roles2.add(roles.get(i));
                        }
                }
                session.setAttribute("roles", roles2);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Sorry. Something went wrong upon editing");
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        RoleService rs = new RoleService();
        
        String action = request.getParameter("action");
        String email = (String) request.getParameter("email");
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");
        String password = (String) request.getParameter("password");
        System.out.println(action);
        
        
        if(action != null && action.equals("Add User")) {
                session.setAttribute("Manage", "Add");
                session.setAttribute(request.getParameter("action"), "addUser");
//                String email = (String) session.getAttribute("email");
//                String firstName = (String) session.getAttribute("firstName");
//                String lastName = (String) session.getAttribute("lastName");
//                String password = (String) session.getAttribute("password");
//                String roleString = (String) session.getAttribute("role");
//                
//                
//                //String vehicle = request.getParameter("vehicle");
//                
//                System.out.println(email);
//            try {
//                Role role = rs.getRole(roleString);
//                User user = new User(email, firstName, lastName, password, role);
//            } catch (Exception ex) {
//                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
//                request.setAttribute("message", "Sorry. Something went wrong upon adding");
//            }
        }
        
        if(action != null && action.equals("Update")) {
            int roleSel = Integer.parseInt(request.getParameter("role"));
            try {
                Role role = rs.getRole(roleSel);
                User user = new User(email, firstName, lastName, password, role);
                us.update(user, roleSel);
                session.setAttribute("message", "Updated User: " + email);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Sorry. Something went wrong upon updating");
            } 
            
            
        }
        
        if(action != null && action.equals("Cancel")) {
            session.setAttribute("Manage", "Add");
            session.setAttribute("edit", false);
            session.setAttribute("adding", true);
        }
        
        
        try {
            List<Role> roles = rs.getRoles();
            List<User> users = us.getAll(roles);
            session.setAttribute("users", users);
            session.setAttribute("roles", roles);
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Sorry. Something went wrong");
        } finally {
            request.setAttribute("email", "");
            request.setAttribute("firstName", "");
            request.setAttribute("lastName", "");
            request.setAttribute("password", "");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
    
}
