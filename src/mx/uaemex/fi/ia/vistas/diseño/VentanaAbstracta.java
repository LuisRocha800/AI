/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.vistas.diseño;

import javax.swing.JFrame;
import mx.uaemex.fi.ia.control.Control;

/**
 *
 * @author angel
 */
public abstract class VentanaAbstracta extends JFrame {
    protected Control control;
    public void setControl(Control control){
        this.control = control;
    }
    
}
