/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.agentes;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import mx.uaemex.fi.ia.data.OntologiaBancaria;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uaemex.fi.ia.data.ActivacionAhorro;
import mx.uaemex.fi.ia.data.ActivacionDebito;
import mx.uaemex.fi.ia.data.Cancelacion;
import mx.uaemex.fi.ia.data.CuentaAhorro;
import mx.uaemex.fi.ia.data.CuentaBancaria;
import mx.uaemex.fi.ia.data.CuentaDebito;
import mx.uaemex.fi.ia.data.Retiracion;
import mx.uaemex.fi.ia.data.Transaccion;
import mx.uaemex.fi.ia.data.Transferencia;

public class Usuario extends Agent {
    
    public static final float SALDO_MINIMO = 200;
    public static final float COMISION = 1000;
    public static final float DESCUENTO_TRANSACCION = 50;
    public static final float IMPUESTO_ANUAL = 2500;
    public static final float INTERESES = 5;
    
    private Codec codec = new SLCodec();
    private Ontology ontologia = OntologiaBancaria.getInstance();
    private ArrayList<CuentaBancaria> cuentas;
    
    private MessageTemplate mt;
    
    
    class ComportamientoUsuario extends SimpleBehaviour {
        private boolean finished = false;
        private boolean saldo_suficiente = true;
        private boolean cuenta_inexistente = true;
        public ComportamientoUsuario(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            //COMUNICACION CON OTRO AGENTE
            
            
            System.out.println("\nEsperando accion de Agentes");
 
            mt = MessageTemplate.and(
            MessageTemplate.MatchLanguage(codec.getName()),
            MessageTemplate.MatchOntology(ontologia.getName()));
            ACLMessage  msg = blockingReceive(mt);
            //System.out.println(msg.getOntology());
            try{
                if(msg != null){
                    if(msg.getPerformative()== ACLMessage.INFORM){
                        //System.out.println(msg.getOntology());
                        ContentElement ec = getContentManager().extractContent(msg);
                        //System.out.println(msg.getOntology());
                        //CASO DE ACTIVACIÓN
                        if (ec instanceof ActivacionAhorro){
                            ActivacionAhorro crea = (ActivacionAhorro) ec;
                            CuentaAhorro cuenta_recibida = crea.getCuenta_bancaria_ahorro();
                            
                            if(this.saldo_suficiente){
                                for(CuentaBancaria cuenta : cuentas){
                                    if(cuenta.getCliente().getNombre().equals(cuenta_recibida.getCliente().getNombre())){
                                        cuenta_inexistente = false;
                                        break;
                                    }
                                }
                                if(this.cuenta_inexistente){
                                    ActivacionAhorro a = new ActivacionAhorro();
                                    ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                                    msg2.setLanguage(codec.getName());
                                    msg2.setOntology(ontologia.getName());
                                    msg2.setSender(getAID());
                                    msg2.addReceiver(msg.getSender());


        
                                    cuenta_recibida.setClabe(crearClabe());
                                    cuenta_recibida.setNumero_tarjeta(crearTarjeta());
                                    

               
                                    cuenta_recibida.setDescuento_transaccion(DESCUENTO_TRANSACCION);
                                    cuenta_recibida.setImpuesto_anual(IMPUESTO_ANUAL);
                                    cuenta_recibida.setInteres(INTERESES);
                                    a.setCuenta_bancaria_ahorro(cuenta_recibida);
                   
                                    cuentas.add(cuenta_recibida);
                                    
                                    getContentManager().fillContent(msg2,a);
                                    send(msg2);
                                }else{
                                    ACLMessage reply = msg.createReply();
                                    reply.setPerformative(ACLMessage.CANCEL);
                                    reply.setLanguage(codec.getName());
                                    reply.setOntology(ontologia.getName());

                                    ActivacionAhorro a = new ActivacionAhorro();
                                    a.setCuenta_bancaria_ahorro(cuenta_recibida);
                                    getContentManager().fillContent(reply,a);
                                    send(reply);
                                }
                                
                            }
                            

                        }
                        if (ec instanceof ActivacionDebito){
                            ActivacionDebito crea = (ActivacionDebito) ec;
                            CuentaDebito cuenta_recibida = crea.getCuenta_bancaria_debito();
                            if(cuenta_recibida.getMonto()<SALDO_MINIMO){
                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.REFUSE);
                                reply.setLanguage(codec.getName());
                                reply.setOntology(ontologia.getName());
      
                                ActivacionDebito a = new ActivacionDebito();
                                a.setCuenta_bancaria_debito(cuenta_recibida);
                                getContentManager().fillContent(reply,a);
                                send(reply);
                            }else{
                                if(this.saldo_suficiente){
                                    for(CuentaBancaria cuenta : cuentas){
                                        if(cuenta.getCliente().getNombre().equals(cuenta_recibida.getCliente().getNombre())){
                                            cuenta_inexistente = false;
                                            break;
                                        }
                                    }
                                    if(this.cuenta_inexistente){
                                        ActivacionDebito a = new ActivacionDebito();
                                        ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                                        msg2.setLanguage(codec.getName());
                                        msg2.setOntology(ontologia.getName());
                                        msg2.setSender(getAID());
                                        msg2.addReceiver(msg.getSender());



                                        cuenta_recibida.setClabe(crearClabe());
                                        cuenta_recibida.setNumero_tarjeta(crearTarjeta());



                                        cuenta_recibida.setComision(COMISION);
                                        cuenta_recibida.setSaldo_minimo(SALDO_MINIMO);

                                        a.setCuenta_bancaria_debito(cuenta_recibida);
                                        
                                        cuentas.add(cuenta_recibida);
                                        
                                        getContentManager().fillContent(msg2,a);
                                        send(msg2);
                                    }else{
                                        ACLMessage reply = msg.createReply();
                                        reply.setPerformative(ACLMessage.CANCEL);
                                        reply.setLanguage(codec.getName());
                                        reply.setOntology(ontologia.getName());

                                        ActivacionDebito a = new ActivacionDebito();
                                        a.setCuenta_bancaria_debito(cuenta_recibida);
                                        getContentManager().fillContent(reply,a);
                                        send(reply);
                                    }

                                }
                            }
                            
                            

                        }
                        //CASO DE CANCELACIÓN
                        if(ec instanceof Transferencia){
                            Transferencia trans = (Transferencia) ec;
                            Transaccion transaccion = trans.getTransaccion();
                            CuentaBancaria cuenta_destino = null;
                            CuentaBancaria cuenta_origen = null;
                            for(CuentaBancaria cuenta : cuentas){
                                
                                if(cuenta.getClabe().equals(transaccion.getClabe_origen())){
                                    cuenta_origen = cuenta;
                                    
                                    break;
                                }
                            }
                            for(CuentaBancaria cuenta : cuentas){

                                if(cuenta.getClabe().equals(transaccion.getClabe_destino())){
                                    cuenta_destino = cuenta;
                                    
                                    break;
                                }
                            }
                            
                            if(cuenta_destino!=null){
                                Transferencia t = new Transferencia();
                                ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                                msg2.setLanguage(codec.getName());
                                msg2.setOntology(ontologia.getName());
                                msg2.setSender(getAID());
                                msg2.addReceiver(msg.getSender());
                                //ESTO ES EN REALIDAD LO QUE CAMBIA DE LA LOGICA DE TRANSFERIR Y DEPOSITAR, SOLO QUE CON OTRO PREDICADO
                                cuenta_destino.setMonto(cuenta_destino.getMonto()+transaccion.getMonto());
                                
                                cuenta_origen.setMonto(cuenta_origen.getMonto()-transaccion.getMonto());
                                System.out.println("");
                                System.out.println("---- TRAFERENCIA ÉXITOSA ----");
                                System.out.println("Su saldo se actualizó a  = " + cuenta_origen.getMonto());
                                    System.out.println("Saldo de la cuenta destino = " + cuenta_destino.getMonto());
                                transaccion.setMonto(0);
                                t.setTransaccion(transaccion);
                                getContentManager().fillContent(msg2,t);
                                send(msg2);
                            }else{
                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.REFUSE);
                                reply.setLanguage(codec.getName());
                                reply.setOntology(ontologia.getName());
                                Transferencia t = new Transferencia();
                                t.setTransaccion(transaccion);
                                getContentManager().fillContent(reply,t);
                                send(reply);
                            }
                        } //retirar
                        if(ec instanceof Retiracion){
                            Retiracion trans = (Retiracion) ec;
                            Transaccion transaccion = trans.getTransaccion();
                            
                            CuentaBancaria cuenta_origen = null;
                            for(CuentaBancaria cuenta : cuentas){
                                
                                if(cuenta.getClabe().equals(transaccion.getClabe_origen())){
                                    cuenta_origen = cuenta;
                                    
                                    break;
                                }
                            }
                            
                            
                            if(cuenta_origen!=null){
                                Retiracion t = new Retiracion();
                                ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                                msg2.setLanguage(codec.getName());
                                msg2.setOntology(ontologia.getName());
                                msg2.setSender(getAID());
                                msg2.addReceiver(msg.getSender());
                                //ESTO ES EN REALIDAD LO QUE CAMBIA DE LA LOGICA DE TRANSFERIR Y DEPOSITAR, SOLO QUE CON OTRO PREDICADO
                               
                                cuenta_origen.setMonto(cuenta_origen.getMonto()-transaccion.getMonto());
                                System.out.println("");
                                System.out.println("---- RETIRO EXITOSO ----");
                                System.out.println("Su saldo se actualizó a = " + cuenta_origen.getMonto());
                                //System.out.println("De LA CUENTA DESTINO EL MONTO NUEVO:" + cuenta_destino.getMonto());
                                transaccion.setMonto(0);
                                t.setTransaccion(transaccion);
                                getContentManager().fillContent(msg2,t);
                                send(msg2);
                            }else{
                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.REFUSE);
                                reply.setLanguage(codec.getName());
                                reply.setOntology(ontologia.getName());
                                Transferencia t = new Transferencia();
                                t.setTransaccion(transaccion);
                                getContentManager().fillContent(reply,t);
                                send(reply);
                            }
                        }
                        //cancelacion
                        if(ec instanceof Cancelacion){
                            Cancelacion trans = (Cancelacion) ec;
                            Transaccion transaccion = trans.getTransaccion();
                            
                            CuentaBancaria cuenta_origen = null;
                            for(CuentaBancaria cuenta : cuentas){
                                
                                if(cuenta.getClabe().equals(transaccion.getClabe_origen())){
                                    cuenta_origen = cuenta;
                                    
                                    break;
                                }
                            }
                            
                            
                            if(cuenta_origen!=null){
                                Cancelacion t = new Cancelacion();
                                ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                                msg2.setLanguage(codec.getName());
                                msg2.setOntology(ontologia.getName());
                                msg2.setSender(getAID());
                                msg2.addReceiver(msg.getSender());
                                //ESTO ES EN REALIDAD LO QUE CAMBIA DE LA LOGICA DE TRANSFERIR Y DEPOSITAR, SOLO QUE CON OTRO PREDICADO
                               
                               cuentas.remove(cuenta_origen);
                                System.out.println("Se canceló la cuenta = " + cuenta_origen.getClabe());
                                System.out.println("---- BUEN DÍA ----");
                                //System.out.println("De LA CUENTA DESTINO EL MONTO NUEVO:" + cuenta_destino.getMonto());
                                
                                t.setTransaccion(transaccion);
                                getContentManager().fillContent(msg2,t);
                                send(msg2);
                            }else{
                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.REFUSE);
                                reply.setLanguage(codec.getName());
                                reply.setOntology(ontologia.getName());
                                Transferencia t = new Transferencia();
                                t.setTransaccion(transaccion);
                                getContentManager().fillContent(reply,t);
                                send(reply);
                            }
                        }
                        
                        
                        
                        
                        cuenta_inexistente = true;
                    }else{
                        // Recibida una performativa incorrecta
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                        reply.setContent("Error:_No hay Sistema para acceder a los Datos");
                        send(reply);
                    }
                    
                }
            } catch (Codec.CodecException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OntologyException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

           
    
            /*try {
                MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontologia.getName()));
                Scanner s = new Scanner(System.in);
                AID idReceptor = new AID();
                idReceptor.setLocalName("Ejecutivo");
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.setSender(getAID());
                msg.addReceiver(idReceptor);
                msg.setLanguage(codec.getName());
                msg.setOntology(ontologia.getName());
                
                
                Cliente cliente = new Cliente();
                cliente.setNombre("Angel");
                cliente.setCorreo("angel@gmail.com");
                cliente.setDomicilio("Toluca, Estado de México");
                cliente.setEdad(25);
                cliente.setEstado_laboral("Trabajador");
                cliente.setTelefono("7224567892");

                CuentaAhorro cd = new CuentaAhorro();
                cd.setCliente(cliente);

                Crear crea = new Crear();
                crea.setCuenta_bancaria(cd);

                String r = "asf";

                getContentManager().fillContent(msg,crea);

                send(msg);

                ACLMessage msg2 = blockingReceive(mt);
                
                if(msg2 != null){
                    if(msg2.getPerformative() == ACLMessage.NOT_UNDERSTOOD){
                        System.out.println("Mensaje no Reconocido");
                    }else{
                        if(msg2.getPerformative()== ACLMessage.INFORM){
                            ContentElement ec = getContentManager().extractContent(msg);
                            if (ec instanceof Activacion){
                                Activacion ac = (Activacion) ec;
                                if(msg2.getContent().equals("Cuenta Activada")){
                                    System.out.println(msg2.getContent());
                                }else{
                                    System.out.println("Cuenta Creada con exito");
                                    System.out.println(ac.getCuenta_bancaria().getClabe());
                                    System.out.println(ac.getCuenta_bancaria().getMonto());
                                    System.out.println(ac.getCuenta_bancaria().getNumero_tarjeta());
                                }
                            }else{
                                // Recibido un INFORM con contenido incorrecto
                                ACLMessage reply = msg.createReply();
                                reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                                reply.setContent("No se reconoce el contenido del mensaje");
                                send(reply);
                            }
                        }else{
                            // Recibida una performativa incorrecta
                            ACLMessage reply = msg.createReply();
                            reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                            reply.setContent("Se espera un mensaje INFORM");
                            send(reply);
                        }
                    }
                }
            } catch (Codec.CodecException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OntologyException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
*/
        }

        @Override
        public boolean done() {
          return finished;
        }

        private String crearClabe(){
            StringBuilder clabe = new StringBuilder();
            Random numAleatorio = new Random();
            // Constructor pasadole una semilla
            Random numAleatorio1 = new Random(375);
            for(int i=0; i<18; i++){
                clabe.append(Integer.toString(numAleatorio.nextInt(9-1+1) + 1));
            }
            return clabe.toString();
        }
        private String crearTarjeta(){
            StringBuilder tarjeta = new StringBuilder();
            Random numAleatorio = new Random();
            // Constructor pasadole una semilla
            Random numAleatorio1 = new Random(375);
      
            for(int i=1; i<=16; i++){
                tarjeta.append(Integer.toString(numAleatorio.nextInt(9-1+1) + 1));
                if(i%4 == 0){
                    tarjeta.append("-");
                }
                
            }
            tarjeta.deleteCharAt(tarjeta.length()-1);
            return tarjeta.toString();
        }
        
        

    } //Fin de la clase ComportamientoUsuario
    
    @Override
    protected void setup() {
        System.out.println("INICIE USUARIO");
        this.cuentas = (ArrayList<CuentaBancaria>) this.getArguments()[0];
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);

        

        ComportamientoUsuario PingBehaviour;
        PingBehaviour = new  ComportamientoUsuario(this);
        addBehaviour(PingBehaviour);
    }

    @Override
    protected void takeDown() {
        System.out.println("Agente Terminado");
        
    }
    
    
    
}
