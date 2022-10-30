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

    public void del(String email) throws Exception{
        UserDB userDB = new UserDB();
        userDB.del(email);
    }
}
