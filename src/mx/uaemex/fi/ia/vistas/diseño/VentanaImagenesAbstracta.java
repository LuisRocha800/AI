/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.vistas.diseño;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author angel
 */
public abstract class VentanaImagenesAbstracta extends VentanaAbstracta {
    private ImageIcon imagen;
    private Icon icono;
    public void pintarImagen(JLabel label, String rutaImagen){
        this.imagen = new ImageIcon(rutaImagen);
        this.icono = new ImageIcon(this.imagen.getImage().getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_DEFAULT));
        label.setIcon(this.icono);
    }
}
