/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author kurtm
 */
public class RoleService {
    public List<Role> getRoles() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getRoles();
        return roles;
    }

    public Role getRole(int roleId) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(roleId);
        return role;
    }
}