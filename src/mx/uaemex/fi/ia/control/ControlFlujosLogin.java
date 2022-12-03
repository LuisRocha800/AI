/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.control;

import mx.uaemex.fi.ia.control.data.Data;
import mx.uaemex.fi.ia.data.BooleanData;
import mx.uaemex.fi.ia.error.AgenteException;
import mx.uaemex.fi.ia.vistas.diseño.Login;
import mx.uaemex.fi.ia.vistas.diseño.Productos;


public class ControlFlujosLogin implements Control{

    private Control padre;
    private BooleanData banderaCrearCuenta = new BooleanData();
   
    
    @Override
    public void ejecutaComando(String comando, Data d) throws AgenteException {
        switch(comando){
            case Login.INGRESAR: 
                //FALTAN VALIDACAOINES
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_VENTANA_PRINCIPAL, null);
                break;
            case Login.CREAR_CUENTA:
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_PRODUCTOS, null);
                break;
            case Productos.CREAR_CUENTA_DEBITO:
                banderaCrearCuenta.setBandera(true);
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_CREAR_CUENTA, banderaCrearCuenta);
                break;
            case Productos.CREAR_CUENTA_AHORRO:
                banderaCrearCuenta.setBandera(false);
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_CREAR_CUENTA, banderaCrearCuenta);
                break;
            case Productos.REGRESAR:
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_PRODUCTOS_LOGIN, null);
                break;
            default:
                this.padre.ejecutaComando(ControlFlujos.CASO_LOGIN_DESCONOCIDO, null);
        }
    }
    
    @Override
    public void setPadre(Control control) {
        this.padre = control;
    }
    
}
