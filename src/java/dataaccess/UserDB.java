package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

public class UserDB {
    
    public List<User> getAll(List<Role> roles) throws Exception {

        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user";
        Role role = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
                int roleId = rs.getInt(5);
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i).getRoleId() == roleId){
                        role = roles.get(i);
                    }
                }
                User user = new User(email, firstName, lastName, password, role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }

    public void del(String email) throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE email=?";
        
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
            System.out.println(email + "Works here");
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public User get(String email, List<Role> roles) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        String sql = "SELECT * FROM user WHERE email=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
                int roleId = rs.getInt(5);
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i).getRoleId() == roleId){
                        role = roles.get(i);
                    }
                }
                user = new User(email, firstName, lastName, password, role); 
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return user;
    }
}
