
package me.pocan.libraryx.interfaces;

import java.sql.Connection;


public interface IDatabase {
    public Connection connectDatabase(String database, String username, String password);
    public Boolean loginCheck(String username, String password, Connection con);
    
}
