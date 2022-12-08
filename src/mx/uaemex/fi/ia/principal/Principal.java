/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.principal;

import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uaemex.fi.ia.control.ControlAgentes;
import mx.uaemex.fi.ia.control.ControlFlujos;
import mx.uaemex.fi.ia.control.ControlFlujosLogin;
import mx.uaemex.fi.ia.control.ControlVentanaPrincipal;
import mx.uaemex.fi.ia.data.Cliente;
import mx.uaemex.fi.ia.data.CuentaBancaria;
import mx.uaemex.fi.ia.data.RespuestaAgente;
import mx.uaemex.fi.ia.vistas.diseño.Cancelar;
import mx.uaemex.fi.ia.vistas.diseño.CrearCuenta;
import mx.uaemex.fi.ia.vistas.diseño.CuentaPrincipal;
import mx.uaemex.fi.ia.vistas.diseño.Espera;
import mx.uaemex.fi.ia.vistas.diseño.Login;
import mx.uaemex.fi.ia.vistas.diseño.Monto;
import mx.uaemex.fi.ia.vistas.diseño.Productos;
import mx.uaemex.fi.ia.vistas.diseño.Retirar;
import mx.uaemex.fi.ia.vistas.diseño.Transferir;


/*
    VALORES DEL PARAMS-EJECUTIVO:
        0. COMANDO
        1. RESPUESTA
        2. VENTANA CREAR CUENTA
        3. VENTANA MONTO
        4. VENTANA CANCELAR
        
*/


public class Principal {
    public static void main(String args[]){
        //CREANDO VARIABLES PARA COMUNICACIÓN ENTRE EL CONTROLADOR DE AGENTES Y LOS AGENTES
        Cliente c = new Cliente();
        c.setNombre("Luis Angel Rocha Ronquillo");
        c.setCorreo("rochaLuis000@gmail.com");
        c.setDomicilio("Toluca");
        c.setEdad(30);
        c.setEstado_laboral("Estudiante");
        c.setTelefono("7224566789");
        CuentaBancaria loginCuenta = new CuentaBancaria();
        loginCuenta.setClabe("564678535678975357");
        loginCuenta.setMonto(1000000);
        loginCuenta.setNumero_tarjeta("4915-8079-5951-0904");
        loginCuenta.setCliente(c);
        ArrayList<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
        cuentas.add(loginCuenta);
        StringBuilder comandoEjecutivo = new StringBuilder("");
        StringBuilder comandoVentanilla = new StringBuilder("");
        RespuestaAgente respuesta = new RespuestaAgente();
        //VENTANAS
        Login login = new Login();
        CuentaPrincipal cuentaPrincipal = new CuentaPrincipal();
        Productos productos = new Productos();
        CrearCuenta crearCuenta = new CrearCuenta();
        Monto monto = new Monto();
        Retirar retirar = new Retirar();
        Transferir transferir = new Transferir();
        Cancelar cancelar = new Cancelar();
        Espera espera = new Espera();
        //CONTROLADORES
        ControlFlujos controlFlujos = new ControlFlujos();
        ControlFlujosLogin controlFlujosLog = new ControlFlujosLogin();
        ControlVentanaPrincipal controlVentanaPrin = new ControlVentanaPrincipal();
        ControlAgentes controlAgentes = new ControlAgentes();
        //CONTROLADORES EN VENTANAS
        login.setControl(controlFlujosLog);
        cuentaPrincipal.setControl(controlVentanaPrin);
        productos.setControl(controlFlujosLog);
        crearCuenta.setControl(controlAgentes);
        monto.setControl(controlAgentes);
        retirar.setControl(controlAgentes);
        transferir.setControl(controlAgentes);
        cancelar.setControl(controlAgentes);
        espera.setControl(controlAgentes);
        //VENTANAS EN CONTROL DE FLUJOS
        controlFlujos.setLogin(login);
        controlFlujos.setCuentaPrincipal(cuentaPrincipal);
        controlFlujos.setProductos(productos);
        controlFlujos.setCrearCuenta(crearCuenta);
        controlFlujos.setMonto(monto);
        controlFlujos.setRetirar(retirar);
        controlFlujos.setTrasferir(transferir);
        controlFlujos.setCancelar(cancelar);
        controlFlujos.setEspera(espera);
        //CONTROL DE FLUJOS COMO PADRE DE LOS DEMÁS CONTROLES
        controlFlujosLog.setPadre(controlFlujos);
        controlVentanaPrin.setPadre(controlFlujos);
        controlAgentes.setPadre(controlFlujos);
        
        //VARIABLES DE COMUNICACIÓN EN EL CONTROLADOR DE AGENTES
        controlAgentes.setComandoEjecutivo(comandoEjecutivo);
        controlAgentes.setComandoVentanilla(comandoVentanilla);
        controlAgentes.setRespuesta(respuesta);
        controlAgentes.setLogin(loginCuenta);
        
        
        //INICIALIZACIÓN Y ARRANQUE DE LOS AGENTES
        String[] parametros = new String[2];
        parametros[0] = "-port";
        parametros[1] = "1099";
        Boot.main(parametros);
        Runtime rt = Runtime.instance();
        Profile p=new ProfileImpl(); //define propiedades (en qué contenedor, host, Puerto se va a crear)
       
        p.setParameter(Profile.CONTAINER_NAME, "Principal");//se le asigna un nombre al contenedor
        ContainerController cc = rt.createMainContainer(p); //se crea un contenedor en la plataforma jade
        if(cc != null){
            try {
                Object[] params = {comandoEjecutivo,respuesta,crearCuenta,monto,cancelar,loginCuenta};
                AgentController e = cc.createNewAgent("ejecutivo", "mx.uaemex.fi.ia.agentes.Ejecutivo",params);
                e.start();
                Object[] params2 = {cuentas};
                AgentController u = cc.createNewAgent("usuario", "mx.uaemex.fi.ia.agentes.Usuario", params2);
                u.start();
                Object[] params3 = {comandoVentanilla,respuesta,loginCuenta,transferir,retirar};
                AgentController v = cc.createNewAgent("ventanilla", "mx.uaemex.fi.ia.agentes.Ventanilla", params3);
                v.start();
            } catch (StaleProxyException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //MOSTRAR LOGIN
        login.setVisible(true);

    }
}
