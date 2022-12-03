/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.control;

import javax.swing.JOptionPane;
import mx.uaemex.fi.ia.control.data.Data;
import mx.uaemex.fi.ia.data.BooleanData;
import mx.uaemex.fi.ia.vistas.diseño.CuentaPrincipal;
import mx.uaemex.fi.ia.vistas.diseño.Cancelar;
import mx.uaemex.fi.ia.vistas.diseño.Login;
import mx.uaemex.fi.ia.vistas.diseño.CrearCuenta;
import mx.uaemex.fi.ia.vistas.diseño.Espera;
import mx.uaemex.fi.ia.vistas.diseño.Monto;
import mx.uaemex.fi.ia.vistas.diseño.Productos;
import mx.uaemex.fi.ia.vistas.diseño.Retirar;
import mx.uaemex.fi.ia.vistas.diseño.Transferir;


public class ControlFlujos implements Control {
   
    public static final String MOSTRAR_ESPERA = "Muestra Ventana de Procesando";
    public static final String CERRAR_ESPERA = "Cerrar Ventana de Procesando";
    public static final String MOSTRAR_VENTANA_PRINCIPAL = "Muestra Ventana Principal";
    public static final String MOSTRAR_PRODUCTOS = "Muestra Productos";
    public static final String MOSTRAR_CREAR_CUENTA = "Muestra Crear Cuenta";
    public static final String MOSTRAR_MONTO = "Muestra Monto";
    public static final String MOSTRAR_RETIRAR = "Muestra Retirar";
    public static final String MOSTRAR_TRANSFERIR = "Muestraa Transferir";
    public static final String MOSTRAR_CANCELAR = "Muestra Cancelar";
    public static final String REGRESAR_PRODUCTOS_LOGIN = "Regresar de Productos a Login";
    public static final String REGRESAR_MONTO_LOGIN = "Regresar de Monto a Login";
    public static final String REGRESAR_CREAR_CUENTA_LOGIN = "Regresar de Crear Cuenta a Login";
    public static final String REGRESAR_RETIRAR_VENTANA_PRINCIPAL = "Regresar de Retirar a Ventaana Principal";
    public static final String REGRESAR_TRANSFERIR_VENTANA_PRINCIPAL = "Regresar de Transferir a Ventaana Principal";
    public static final String REGRESAR_CANCELAR_VENTANA_PRINCIPAL = "Regresar de Cancelar a Ventaana Principal";
    public static final String REGRESAR_VENTANA_PRINCIPAL_LOGIN = "Cerrar Sesión";
    public static final String REGRESAR_CREAR_CUENTA_PRODUCTOS = "Regresar de Crear Cuenta a Productos";
    public static final String CASO_LOGIN_DESCONOCIDO = "Caso no reconocido de Login";
    public static final String CASO_AGENTES_DESCONOCIDO = "Caso no reconocido de Agentes";
    public static final String CASO_VENTANA_PRINCIPAL_DESCONOCIDO = "Caso no reconocido de Ventana Principal";
    
    private Login login;
    private Productos productos;
    private CrearCuenta crearCuenta;
    private Monto monto;
    private CuentaPrincipal cuentaPrincipal;
    private Retirar retirar;
    private Transferir trasferir;
    private Cancelar cancelar;
    private Espera espera;
    
    public void setLogin(Login login) {
        this.login = login;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public void setCrearCuenta(CrearCuenta crearCuenta) {
        this.crearCuenta = crearCuenta;
    }

    public void setMonto(Monto monto) {
        this.monto = monto;
    }

    public void setCuentaPrincipal(CuentaPrincipal cuentaPrincipal) {
        this.cuentaPrincipal = cuentaPrincipal;
    }

    public void setRetirar(Retirar retirar) {
        this.retirar = retirar;
    }

    public void setTrasferir(Transferir trasferir) {
        this.trasferir = trasferir;
    }

    public void setCancelar(Cancelar cancelar) {
        this.cancelar = cancelar;
    }

    public void setEspera(Espera espera) {
        this.espera = espera;
    }
    
    
    
    private Control padre;
    
    @Override
    public void ejecutaComando(String comando, Data d) {
        switch(comando){
            case ControlFlujos.MOSTRAR_VENTANA_PRINCIPAL:
                this.login.setVisible(false);
                this.cuentaPrincipal.setVisible(true);
                break;
            case ControlFlujos.MOSTRAR_PRODUCTOS:
                this.login.setVisible(false);
                this.productos.setVisible(true);
                break;
            case ControlFlujos.MOSTRAR_CREAR_CUENTA:
                this.crearCuenta.setEs_cuenta_debito((BooleanData) d);
                this.productos.setVisible(false);
                this.crearCuenta.setVisible(true);
                break;
            case ControlFlujos.MOSTRAR_MONTO:
                this.monto.setVisible(true);
                break;
            case ControlFlujos.MOSTRAR_RETIRAR:
                this.trasferir.setVisible(false);
                this.cancelar.setVisible(false);
                this.retirar.setVisible(true);
                break;
            case ControlFlujos.MOSTRAR_TRANSFERIR:
                this.retirar.setVisible(false);
                this.cancelar.setVisible(false);
                this.trasferir.setVisible(true);
                break;
            case ControlFlujos.MOSTRAR_CANCELAR:
                this.retirar.setVisible(false);
                this.trasferir.setVisible(false);
                this.cancelar.setVisible(true);
                break;
            case ControlFlujos.REGRESAR_PRODUCTOS_LOGIN:
                this.productos.setVisible(false);
                this.login.setVisible(true);
                break;
            case ControlFlujos.REGRESAR_MONTO_LOGIN:
                this.monto.setVisible(false);
                this.crearCuenta.setVisible(false);
                this.login.setVisible(true);
                break;
            case ControlFlujos.REGRESAR_CREAR_CUENTA_LOGIN:
                this.crearCuenta.setVisible(false);
                this.login.setVisible(true);
                break;
            case ControlFlujos.REGRESAR_RETIRAR_VENTANA_PRINCIPAL:
                this.retirar.setVisible(false);
                break;
            case ControlFlujos.REGRESAR_TRANSFERIR_VENTANA_PRINCIPAL:
                this.trasferir.setVisible(false);
                break;
            case ControlFlujos.REGRESAR_CANCELAR_VENTANA_PRINCIPAL:                
                
                
                this.cancelar.setVisible(false);
                this.trasferir.setVisible(false);
                this.retirar.setVisible(false);
                this.cuentaPrincipal.setVisible(false);
                this.login.setVisible(true);
                this.login.setUsuario("fer");
                this.login.setContraseña("fer");
                break;
            case ControlFlujos.REGRESAR_VENTANA_PRINCIPAL_LOGIN:
                this.cuentaPrincipal.setVisible(false);
                this.login.setVisible(true);
                break;
            case ControlFlujos.REGRESAR_CREAR_CUENTA_PRODUCTOS:
                this.crearCuenta.setVisible(false);
                this.monto.setVisible(false);
                this.productos.setVisible(true);
                break;
            case ControlFlujos.MOSTRAR_ESPERA:
                this.espera.setVisible(true);
                
                break;
            case ControlFlujos.CERRAR_ESPERA:
                this.espera.setVisible(false);
                break;
            case ControlFlujos.CASO_LOGIN_DESCONOCIDO:
                this.productos.setVisible(false);
                this.crearCuenta.setVisible(false);
                this.monto.setVisible(false);
                this.login.setVisible(true);
                JOptionPane.showConfirmDialog(null, "Accion desconocida, comando desconocido","Comando Desconocido", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION);
                break;
            case ControlFlujos.CASO_AGENTES_DESCONOCIDO:
                this.retirar.setVisible(false);
                this.trasferir.setVisible(false);
                this.cancelar.setVisible(false);
                JOptionPane.showConfirmDialog(null, "Accion desconocida, comando desconocido de agentes","Comando Desconocido", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION);
                break;
            case ControlFlujos.CASO_VENTANA_PRINCIPAL_DESCONOCIDO:
                this.retirar.setVisible(false);
                this.trasferir.setVisible(false);
                this.cancelar.setVisible(false);
                this.cuentaPrincipal.setVisible(false);
                this.login.setVisible(true);
                JOptionPane.showConfirmDialog(null, "Accion desconocida, comando desconocido de la ventana principal","Comando Desconocido", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION);
                break;
        }
    }

    @Override
    public void setPadre(Control control) {
        this.padre = control;
    }
}
