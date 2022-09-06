
package me.pocan.libraryx.modules;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.pocan.libraryx.interfaces.IDatabase;


public class DatabaseModule implements IDatabase{
    
    private String hostname,username,password,database;
    private int port;
    private Connection con = null;
    private PreparedStatement preparedStatement = null;
    private Boolean result;
    
    public DatabaseModule(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;    
    }

    @Override
    public Connection connectDatabase(String database, String username, String password) {
        String request = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(request, username, password);
            return con;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseModule.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Boolean loginCheck(String username, String password, Connection con) {
        String request = "Select * from users where username = ? and password = ?";
        try {
            preparedStatement = con.prepareStatement(request);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            result = rs.next();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseModule.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        

    }
    
}
