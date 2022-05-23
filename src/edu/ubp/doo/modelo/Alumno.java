/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ubp.doo.modelo;

import edu.ubp.doo.dao.AlumnoDAO;
import edu.ubp.doo.dao.FabricaDAO;
import edu.ubp.doo.dto.AlumnoDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author agustin
 */
public class Alumno extends Modelo{
     
    private final FabricaDAO fabricaDao;
    private final AlumnoDAO alumnoDao;

    public Alumno() {
        fabricaDao = FabricaDAO.getFactory("SqlFabricaDAO");
        alumnoDao = fabricaDao.getAlumnoDao();
    }

    public AlumnoDTO buscarAlumno(String nombre, String apellido) {
        AlumnoDTO alumno = alumnoDao.buscarAlumno(nombre, apellido);
        return alumno;
    }
    
    public AlumnoDTO buscarAlumno(int legajo) {
        AlumnoDTO alumno = alumnoDao.buscarAlumno(legajo);
        return alumno;
    }
    
    public int mayorLegajo() {
        return alumnoDao.mayorLegajo();
    }

    public List<AlumnoDTO> listarAlumnos() {
        List<AlumnoDTO> listadoAlumnos = alumnoDao.listarAlumnos();
        return listadoAlumnos;
    }

    public boolean insertarAlumno(String apellido, String nombre, Date fechaNacimiento, String sexo) {
        return alumnoDao.insertarAlumno(apellido, nombre, fechaNacimiento, sexo);
    }

    public boolean modificarAlumno(int legajo, String apellido, String nombre, Date fechaNacimiento, String sexo) {
        return alumnoDao.modificarAlumno(legajo, apellido, nombre, fechaNacimiento, sexo);
    }

    public boolean borrarAlumno(int legajo) {
        return alumnoDao.borrarAlumno(legajo);
    }
    
    @Override
    protected void finalize() throws Throwable {
        alumnoDao.cerrarConexion();
    }
    
    
}
