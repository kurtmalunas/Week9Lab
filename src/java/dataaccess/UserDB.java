package dataaccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

public class UserDB {
    
    public List<User> getAll(List<Role> roles) throws Exception {

        List<User> users = new ArrayList<>();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
 
        try {
            users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
        
    }

    public void del(User user) throws SQLException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Role role = user.getRole();
            role.getUserList().remove(user);
            trans.begin();
            em.remove(em.merge(user));
            em.merge(role);
            trans.commit();
        } catch(Exception ex) {
            trans.rollback();
        }finally {
            em.close();
        }
    }

    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch(Exception ex) {
            trans.rollback();
        }finally {
            em.close();
        }
    }

    public void addUser(User user, int roleId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Role role = user.getRole();
            role.getUserList().add(user);
            trans.begin();
            em.persist(user);
            em.merge(role);
            trans.commit();
        } catch(Exception ex) {
            trans.rollback();
        }finally {
            em.close();
        }
    }
}
