/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import jade.content.Concept;


public class Transaccion implements Concept {
    private String clabe_origen;
    private String clabe_destino;
    private float monto;

    public String getClabe_origen() {
        return clabe_origen;
    }

    public void setClabe_origen(String clabe_origen) {
        this.clabe_origen = clabe_origen;
    }

    public String getClabe_destino() {
        return clabe_destino;
    }

    public void setClabe_destino(String clabe_destino) {
        this.clabe_destino = clabe_destino;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
}
