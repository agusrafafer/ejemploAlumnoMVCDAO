/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ubp.doo.dao;

/**
 *
 * @author agustin
 */
public class SqlFabricaDAO extends FabricaDAO {

    @Override
    public AlumnoDAO getAlumnoDao() {
        return new AlumnoDAOImplSql();
    }
    
}
