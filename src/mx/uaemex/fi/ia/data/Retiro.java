/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import jade.content.Predicate;


public class Retiro implements Predicate{
    private Retiracion retiracion;

    public Retiracion getRetiracion() {
        return retiracion;
    }

    public void setRetiracion(Retiracion retiracion) {
        this.retiracion = retiracion;
    }
    
}
