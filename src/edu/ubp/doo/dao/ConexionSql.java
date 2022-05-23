/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ubp.doo.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author agustin
 */
public class ConexionSql {
    private String URL = "jdbc:sqlite:";
    private Connection connection = null;
    private static ConexionSql instancia = null;
    
    
    private ConexionSql() {
        if (connection == null) {
            try {
                URL += getClass().getResource("ejemploMVCDAO.db").toURI().getPath();
                connection = DriverManager.getConnection(URL);
                if (connection != null) {
                    System.out.println("Conexión OK");
                }
            } catch (SQLException | URISyntaxException e) {
                System.out.println(e);
            }
        }
    }
    
    public static ConexionSql getInstancia() {
        if(instancia == null) {
            instancia = new ConexionSql();
        }
        return instancia;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    protected void desconectar() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        connection = null;
    }
    
}
