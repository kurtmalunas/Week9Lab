/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author kurtm
 */
public class UserService {
    
    public List<User> getAll(List<Role> roles) throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll(roles);
        return users;
    }

    public void del(User user) throws Exception{
        UserDB userDB = new UserDB();
        userDB.del(user);
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public void update(User user) throws Exception {
        
        UserDB userDB = new UserDB();
        User user2 = userDB.get(user.getEmail());
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setPassword(user.getPassword());
        user2.setRole(user.getRole());
        
        userDB.update(user2);
    }

    public void addUser(User user, int roleId) throws Exception {
        UserDB userDB = new UserDB();
        userDB.addUser(user, roleId);
    }
}
