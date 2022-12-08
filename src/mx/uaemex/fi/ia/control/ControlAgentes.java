/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uaemex.fi.ia.control.data.Data;
import mx.uaemex.fi.ia.data.BooleanData;
import mx.uaemex.fi.ia.data.CuentaBancaria;
import mx.uaemex.fi.ia.data.RespuestaAgente;
import mx.uaemex.fi.ia.error.AgenteException;
import mx.uaemex.fi.ia.vistas.diseño.Cancelar;
import mx.uaemex.fi.ia.vistas.diseño.CrearCuenta;
import mx.uaemex.fi.ia.vistas.diseño.Monto;
import mx.uaemex.fi.ia.vistas.diseño.Retirar;
import mx.uaemex.fi.ia.vistas.diseño.Transferir;


public class ControlAgentes implements Control {
    public static final String CUENTA_CREADA = "Realizado exitosamente";
public static final String CUENTA_CANCELADA = "Cuenta cancelada exitosamente";
public static final String TRANSFERENCIA_E = "Transferencia exitosa";
public static final String RETIRO_E = "Retiro exitoso";


//***********COMANDOS***********
    public static final String CREAR_CUENTA_AHORRO = "Crear Cuenta de Ahorro";
    public static final String CREAR_CUENTA_DEBITO = "Crear Cuenta de Débito";
    public static final String CANCELAR_CUENTA = "Cancelar Cuenta";
    public static final String TRANSFERIR_CUENTAS = "Transferir Cuentas";
    public static final String RETIRAR_CUENTAS = "Retirar Cuentas";
    //***********RESPUESTAS***********
    //************TIPOS************
    public static final String RESPUESTA_CORRECTA = "Proceso Correcto";
    public static final String CAMPOS_VACIOS_TIPO = "Hay Campos Vacios";
    public static final String TELEFONO_INVALIDO_TIPO = "Teléfono Invalido";
    public static final String MINIMO_EDAD_TIPO = "Edad Inválida";
    public static final String NO_NUMERO_TIPO = "Hay números Inválidos";
    public static final String NO_CONFIRMACION_TIPO = "No se Confirmo la cancelación";
    public static final String MONTO_MINIMO_TIPO = "Monto Minimo";
    public static final String MONTO_INSUFICIENTE_TIPO = "Monto insuficiente";
    public static final String CUENTA_NO_EXISTENTE_TIPO = "Cuenta No Existe";
    public static final String CUENTA_REPETIDA_TIPO = "Cuenta Repetida";
    public static final String RESPUESTA_DESCONOCIDA_TIPO = "Respuesta Desconocida";
    public static final String NO_SELECCION = "No se selecciono";
    
    //************MENSAJES************
    public static final String CAMPOS_VACIOS_MENSAJE = "Debes llenar todos los Campos";
    public static final String TELEFONO_INVALIDO_MENSAJE = "El Teléfono es Inválido, debe ser de 10 caráctreres";
    public static final String MINIMO_EDAD_MENSAJE = "La Edad debe ser mayor a 10 años";
    public static final String NO_NUMERO_MENSAJE = "Hay que solo aceptan números";
    public static final String NO_CONFIRMACION_MENSAJE = "Debes Confirmar la cancelación";
    public static final String MONTO_MINIMO_MENSAJE = "El Monto Ingresado es menor al Monto Mínimo";
    public static final String MONTO_INSUFICIENTE_MENSAJE = "Saldo Insuficiente";
    public static final String CUENTA_REPETIDA_MENSAJE = "Ya existe una Cuenta con este Cliente";
    public static final String CUENTA_NO_EXISTENTE_MENSAJE = "La Cuenta destino no existe";
    public static final String RESPUESTA_DESCONOCIDA_MENSAJE = "Error: No hay sistema";
    public static final String NO_SELECCION_MENSAJE = "Error: No se selecciono la confirmacion de cancelar";
    
    private Control padre;
    private RespuestaAgente respuesta;
    private StringBuilder comandoEjecutivo;
    private StringBuilder comandoVentanilla;
    private CuentaBancaria login;
    
    
    @Override
    public void ejecutaComando(String comando, Data d) throws AgenteException {
        switch(comando){
            case Retirar.RETIRAR:
                //AQUI VA LO DE AGENTES
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_ESPERA, null);
                this.comandoVentanilla.append(ControlAgentes.RETIRAR_CUENTAS);

                while(!this.comandoEjecutivo.toString().isEmpty()){

                }
                this.padre.ejecutaComando(ControlFlujos.CERRAR_ESPERA, null);
                if(respuesta.getTipo() != RESPUESTA_CORRECTA){
                    throw new AgenteException(respuesta.getMensaje());
                }
                //FALTAN VALIDACAOINES
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_RETIRAR_VENTANA_PRINCIPAL, null);
                break;
            case Retirar.REGRESAR:
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_RETIRAR_VENTANA_PRINCIPAL, null);
                break;
            case Transferir.TRANSFERIR:
                //AQUI VA LO DE AGENTES
                //AQUI VA LO DE CREAR LA CUENTA DE AHORRO
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_ESPERA, null);
                this.comandoVentanilla.append(ControlAgentes.TRANSFERIR_CUENTAS);

                while(!this.comandoEjecutivo.toString().isEmpty()){

                }
                this.padre.ejecutaComando(ControlFlujos.CERRAR_ESPERA, null);
                if(respuesta.getTipo() != RESPUESTA_CORRECTA){
                    throw new AgenteException(respuesta.getMensaje());
                }
           
                //FALTAN VALIDACAOINES
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_TRANSFERIR_VENTANA_PRINCIPAL, null);
                break;
            case Transferir.REGRESAR:
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_TRANSFERIR_VENTANA_PRINCIPAL, null);
                break;
            case Cancelar.CANCELAR:
                //AQUI VA LO DE AGENTES
                //FALTAN VALIDACAOINES
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_ESPERA, null);
                this.comandoEjecutivo.append(ControlAgentes.CANCELAR_CUENTA);

                while(!this.comandoEjecutivo.toString().isEmpty()){

                }
                this.padre.ejecutaComando(ControlFlujos.CERRAR_ESPERA, null);
                if(respuesta.getTipo() != RESPUESTA_CORRECTA){
                    throw new AgenteException(respuesta.getMensaje());
                }
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_CANCELAR_VENTANA_PRINCIPAL, null);

                break;
            case Cancelar.REGRESAR:
                
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_CANCELAR_VENTANA_PRINCIPAL, null);
                break;
            case CrearCuenta.CREAR_CUENTA_DATOS:
                //AQUI VA LO DE AGENTES
                //FALTAN VALIDACAOINES
                BooleanData es_cuenta_debito = (BooleanData) d;
                if(es_cuenta_debito.getBandera()){
                    this.padre.ejecutaComando(ControlFlujos.MOSTRAR_MONTO, null);
                    
                }else{
                    //AQUI VA LO DE CREAR LA CUENTA DE AHORRO
                    this.padre.ejecutaComando(ControlFlujos.MOSTRAR_ESPERA, null);
                    this.comandoEjecutivo.append(ControlAgentes.CREAR_CUENTA_AHORRO);
                   
                    while(!this.comandoEjecutivo.toString().isEmpty()){

                    }
                    this.padre.ejecutaComando(ControlFlujos.CERRAR_ESPERA, null);
                    if(respuesta.getTipo() != RESPUESTA_CORRECTA){
                        throw new AgenteException(respuesta.getMensaje());
                    }
                    this.padre.ejecutaComando(ControlFlujos.REGRESAR_CREAR_CUENTA_LOGIN, null);
                }
                break;
            case CrearCuenta.REGRESAR:
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_CREAR_CUENTA_PRODUCTOS, null);
                break;
            case Monto.CREAR_CUENTA_DEBITO_MONTO:
                //AQUI VA LO DE AGENTES
                //FALTAN VALIDACAOINES
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_ESPERA, null);
                
                this.comandoEjecutivo.append(ControlAgentes.CREAR_CUENTA_DEBITO);
                
                while(!this.comandoEjecutivo.toString().isEmpty()){
           
                }
                this.padre.ejecutaComando(ControlFlujos.CERRAR_ESPERA, null);
                if(respuesta.getTipo() != RESPUESTA_CORRECTA){
                    throw new AgenteException(respuesta.getMensaje());
                }
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_MONTO_LOGIN, null);
                break;
            default:
                this.padre.ejecutaComando(ControlFlujos.CASO_AGENTES_DESCONOCIDO, null);
        }
    }

    public void setRespuesta(RespuestaAgente respuesta) {
        this.respuesta = respuesta;
    }

    public void setComandoEjecutivo(StringBuilder comandoEjecutivo) {
        this.comandoEjecutivo = comandoEjecutivo;
    }

    public void setComandoVentanilla(StringBuilder comandoVentanilla) {
        this.comandoVentanilla = comandoVentanilla;
    }

    public void setLogin(CuentaBancaria login) {
        this.login = login;
    }
    
    
    
    @Override
    public void setPadre(Control control) {
        this.padre = control;
    }
}
