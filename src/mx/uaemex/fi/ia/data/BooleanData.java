/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import mx.uaemex.fi.ia.control.data.Data;


public class BooleanData implements Data {
    
    private boolean bandera;
    
    public void setBandera(boolean bandera){
        this.bandera = bandera;
    }
    
    public boolean getBandera(){
        return this.bandera;
    }
    
}
