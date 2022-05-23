/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ubp.doo.main;

import com.formdev.flatlaf.FlatLightLaf;
import edu.ubp.doo.controlador.AlumnoControlador;
import edu.ubp.doo.controlador.Controlador;
import edu.ubp.doo.modelo.Alumno;
import edu.ubp.doo.modelo.Modelo;
import edu.ubp.doo.vista.FrmAbmAlumno;
import edu.ubp.doo.vista.InterfazVista;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author agustin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } catch (UnsupportedLookAndFeelException ex) {
                    System.err.println("Failed to initialize LaF");
                }
                //modelo:
                Modelo modelo = new Alumno();
                //vista:
                InterfazVista vista = FrmAbmAlumno.getInstancia();

                //controlador:
                Controlador control = new AlumnoControlador(vista, modelo);

                //configuramos la vista para que pueda enviar las acciones del usuario como eventos al controlador
                vista.setControlador(control);

                //y arrancamos la interfaz:
                vista.iniciaVista();
            }
        });
    }

}
