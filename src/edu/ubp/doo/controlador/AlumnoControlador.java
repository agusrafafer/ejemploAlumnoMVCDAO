/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ubp.doo.controlador;

import edu.ubp.doo.dto.AlumnoDTO;
import edu.ubp.doo.modelo.Alumno;
import edu.ubp.doo.modelo.Modelo;
import edu.ubp.doo.vista.FrmAbmAlumno;
import edu.ubp.doo.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author agustin
 */
public class AlumnoControlador extends Controlador {

    public AlumnoControlador(InterfazVista vista, Modelo modelo) {
        VISTA = vista;
        MODELO = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel modeloTabla = (DefaultTableModel) ((FrmAbmAlumno) this.VISTA).getModeloTblAlumnos();
        boolean resultado = false;
        String nombre;
        String apellido;
        AlumnoDTO alum;
        try {

            switch (InterfazVista.Operacion.valueOf(e.getActionCommand())) {
                case CARGAR:
                    modeloTabla.setRowCount(0);
                    modeloTabla.fireTableDataChanged();
                    List<AlumnoDTO> listadoAlumnos = ((Alumno) this.MODELO).listarAlumnos();
                    for (AlumnoDTO alu : listadoAlumnos) {
                        modeloTabla.addRow(new Object[]{alu.getLegajo(), alu.getNombre(), alu.getApellido()});
                    }
                    break;
                case GUARDAR:
                    nombre = ((FrmAbmAlumno) this.VISTA).getTxtNombre().getText();
                    apellido = ((FrmAbmAlumno) this.VISTA).getTxtApellido().getText();
                    if (!nombre.equals("") && !apellido.equals("")) {
                        resultado = ((Alumno) this.MODELO).insertarAlumno(apellido.toUpperCase(), nombre.toUpperCase(), new Date(System.currentTimeMillis()), "");
                        if (resultado) {
                            VISTA.actualizaTabla(this);
                        }
                        VISTA.limpiaVista();
                    } else {
                        VISTA.imprimeMensaje(new Exception("Todos los campos son obligatorios"));
                    }
                    break;
                case MODIFICAR:
                    int legAnterior = Integer.parseInt(modeloTabla.getValueAt(((FrmAbmAlumno) this.VISTA).getTblAlumnos().getSelectedRow(), 0).toString());
                    nombre = ((FrmAbmAlumno) this.VISTA).getTxtNombre().getText();
                    apellido = ((FrmAbmAlumno) this.VISTA).getTxtApellido().getText();
                    if (!nombre.equals("") && !apellido.equals("")) {
                        resultado = ((Alumno) this.MODELO).modificarAlumno(legAnterior, apellido.toUpperCase(), nombre.toUpperCase(), new Date(System.currentTimeMillis()), "");
                        if (resultado) {
                            VISTA.actualizaTabla(this);
                        }
                        VISTA.limpiaVista();
                    } else {
                        VISTA.imprimeMensaje(new Exception("Todos los campos son obligatorios"));
                    }
                    break;
                case ELIMINAR:
                    if(((FrmAbmAlumno) this.VISTA).getTblAlumnos().getSelectedRow() == -1) {
                        VISTA.imprimeMensaje(new Exception("Debe seleccionar una fila"));
                        return;
                    }
                    int respuesta = JOptionPane.showConfirmDialog(((FrmAbmAlumno) this.VISTA), "¿Seguro desea eliminar ese alumno?", "Información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.OK_OPTION) {
                        int legajo = Integer.parseInt(modeloTabla.getValueAt(((FrmAbmAlumno) this.VISTA).getTblAlumnos().getSelectedRow(), 0).toString());
                        resultado = ((Alumno) this.MODELO).borrarAlumno(legajo);
                        if (resultado) {
                            VISTA.actualizaTabla(this);
                        } else {
                            VISTA.imprimeMensaje(new Exception("Ocurrio un error al eliminar el alumno"));
                        }
                        VISTA.limpiaVista();
                    }
                    break;
                case LIMPIAR:
                    ((FrmAbmAlumno) this.VISTA).limpiaVista();
                    break;
            }
        } catch (RuntimeException ex) {
            VISTA.imprimeMensaje(ex);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        verificarInputTxt(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        verificarInputTxt(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        verificarInputTxt(e);
    }

    public void verificarInputTxt(KeyEvent e) {
        char c = e.getKeyChar();

        if (!((c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
                || (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_TAB)
                || (Character.isDigit(c)))) {
            e.consume();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTable) {
            DefaultTableModel modeloTabla = (DefaultTableModel) ((FrmAbmAlumno) this.VISTA).getModeloTblAlumnos();
            ((FrmAbmAlumno) this.VISTA).getTxtLegajo().setValue(modeloTabla.getValueAt(((FrmAbmAlumno) this.VISTA).getTblAlumnos().getSelectedRow(), 0));
            ((FrmAbmAlumno) this.VISTA).getTxtNombre().setText((String) modeloTabla.getValueAt(((FrmAbmAlumno) this.VISTA).getTblAlumnos().getSelectedRow(), 1));
            ((FrmAbmAlumno) this.VISTA).getTxtApellido().setText((String) modeloTabla.getValueAt(((FrmAbmAlumno) this.VISTA).getTblAlumnos().getSelectedRow(), 2));
        } else if(e.getSource() instanceof JButton){
            Object legajo = ((FrmAbmAlumno) this.VISTA).getTxtLegajo().getValue();
            if (legajo != null) {
                ((FrmAbmAlumno) this.VISTA).getmItemGuardarAlumno().setEnabled(false);
                ((FrmAbmAlumno) this.VISTA).getmItemModificarAlumno().setEnabled(true);
            } else {
                ((FrmAbmAlumno) this.VISTA).getmItemGuardarAlumno().setEnabled(true);
                ((FrmAbmAlumno) this.VISTA).getmItemModificarAlumno().setEnabled(false);
            }
            ((FrmAbmAlumno) this.VISTA).getPopUpEdicion().show(e.getComponent(), e.getX(), e.getY());
        }
    }

}
