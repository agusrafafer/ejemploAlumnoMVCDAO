/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ubp.doo.controlador;

import edu.ubp.doo.modelo.Modelo;
import edu.ubp.doo.vista.InterfazVista;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author agustin
 */
public abstract class Controlador extends MouseAdapter implements ActionListener, KeyListener{
    InterfazVista VISTA = null;
    Modelo MODELO = null;
}
