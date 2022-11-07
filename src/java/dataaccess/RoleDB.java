/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import models.Role;

/**
 *
 * @author kurtm
 */
public class RoleDB {
    
    public List<Role> getRoles() throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Role> roles = new ArrayList<>();
        try {
            roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        } finally {
            em.close();
        }
    }

    public Role getRole(int roleId) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Role role = em.find(Role.class, roleId);
            return role;
        } finally {
            em.close();
        }
    }
}
