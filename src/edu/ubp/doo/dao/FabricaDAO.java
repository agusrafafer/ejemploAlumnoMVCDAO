/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ubp.doo.dao;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author agustin
 */
public abstract class FabricaDAO {

    public abstract AlumnoDAO getAlumnoDao();

    public static FabricaDAO getFactory(String nombreClase){
        try {            
            return (FabricaDAO) Class.forName(FabricaDAO.class.getPackageName() + "." + nombreClase).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println(e);
            throw new IllegalArgumentException();
        }
    }
}
