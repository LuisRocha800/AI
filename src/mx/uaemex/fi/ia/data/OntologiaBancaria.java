/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

/**
 *
 * 
 * Definición: Clase que define la Ontologia para el uso de Cuentas Bancarias
 *             En este caso, de 2 tipos, Cuenta de Ahorro y Cuenta de Debito
 */
public class OntologiaBancaria extends Ontology {
    //Nombre de la Ontologia
    public static final String ONTOLOGY_NAME = "Ontologia-bancaria";
    
    //Concepto Cliente
    public static final String CLIENTE = "Cliente";
    public static final String CLIENTE_NOMBRE = "nombre";
    public static final String CLIENTE_DOMICILIO = "domicilio";
    public static final String CLIENTE_TELEFONO = "telefono";
    public static final String CLIENTE_CORREO = "correo";
    public static final String CLIENTE_EDAD = "edad";
    public static final String CLIENTE_ESTADO_LABORAL = "estado_laboral";
    
    //Concepto Cuenta Bancaria
    public static final String CUENTA_BANCARIA = "CuentaBancaria";
    public static final String CUENTA_BANCARIA_CLIENTE = "cliente";
    public static final String CUENTA_BANCARIA_MONTO = "monto";
    public static final String CUENTA_BANCARIA_CLABE = "clabe";
    public static final String CUENTA_BANCARIA_NUMERO_TARJETA = "numero_tarjeta";
    
    //Concepto de Cuenta de Debito
    public static final String CUENTA_DEBITO = "CuentaDebito";
    public static final String CUENTA_DEBITO_SALDO_MINIMO = "saldo_minimo";
    public static final String CUENTA_DEBITO_COMISION = "comision";
    
    //Concepto de Cuenta de Ahorro
    public static final String CUENTA_AHORRO = "CuentaAhorro";
    public static final String CUENTA_AHORRO_INTERES = "interes";
    public static final String CUENTA_AHORRO_IMPUESTO_ANUAL = "impuesto_anual";
    public static final String CUENTA_AHORRO_DESCUENTO_TRANSACCION = "descuento_transaccion";
    
    //Concepto de Transaccion
    public static final String TRANSACCION = "Transaccion";
    public static final String TRANSACCION_CLABE_ORIGEN = "clabe_origen";
    public static final String TRANSACCION_CLABE_DESTINO = "clabe_destino";
    public static final String TRANSACCION_MONTO = "monto";
            
    //Predicado de Activacion
    public static final String ACTIVACION_AHORRO = "ActivacionAhorro";
    public static final String ACTIVACION_CUENTA_AHORRO = "cuenta_bancaria_ahorro";
    //Predicado de Cancelacion
    public static final String CANCELACION_AHORRO = "CancelacionAhorro";
    public static final String CANCELACION_CUENTA_AHORRO = "cuenta_bancaria_ahorro";
    
    //Predicado de Activacion
    public static final String ACTIVACION_DEBITO = "ActivacionDebito";
    public static final String ACTIVACION_CUENTA_DEBITO = "cuenta_bancaria_debito";
    //Predicado de Cancelacion
    public static final String CANCELACION = "Cancelacion";
    public static final String CANCELACION_CUENTA = "transaccion";
    
    //Predicado de Retiro
    public static final String RETIRO = "Retiro";
    public static final String RETIRO_CUENTA_BANCARIA = "cuenta_bancaria";
    //Predicado de Transaccion
    public static final String TRANSFERENCIA = "Transferencia";
    public static final String TRANSFERENCIA_CUENTA_BANCARIA = "cuenta_bancaria";
    public static final String TRANSFERENCIA_TRANSACCION = "transaccion";
    
    //Accion de Crear
    public static final String CREAR_AHORRO = "CrearAhorro";
    public static final String CREAR_CUENTA_AHORRO = "cuenta_bancaria_ahorro";
    //Accion de Borrar
    public static final String BORRAR_AHORRO = "BorrrarAhorro";
    public static final String BORRAR_CUENTA_AHORRO = "cuenta_bancaria_ahorro";
    
    //Accion de Crear
    public static final String CREAR_DEBITO = "CrearDebito";
    public static final String CREAR_CUENTA_DEBITO = "cuenta_bancaria_debito";
    //Accion de Borrar
    public static final String BORRAR_DEBITO = "BorrrarDebito";
    public static final String BORRAR_CUENTA_DEBITO = "cuenta_bancaria_debito";
    
    //Accion de Retirar
    public static final String RETIRAR = "Retirar";
    public static final String RETIRAR_CUENTA_BANCARIA = "cuenta_bancaria";
    
 //Accion de Retirar
    public static final String RETIRACION = "Retiracion";
    public static final String RETIRACION_TRANSACCION = "transaccion";


//Accion de Transferir
    public static final String TRANSFERIR = "Transferir";
    public static final String TRANSFERIR_CUENTA_BANCARIA = "cuenta_bancaria";
    
    //Instancia de la Ontologia misma (Privada)
    private static Ontology instance = new OntologiaBancaria();
    
    //GET para conseguir la Instancia de la Ontologia
    public static Ontology getInstance(){
        return instance;
    }
    
    //Constructor de la Ontologia (Privado)
    private OntologiaBancaria() {
        super(ONTOLOGY_NAME,BasicOntology.getInstance());
        try{
            add(new ConceptSchema(CLIENTE),Cliente.class);
            add(new ConceptSchema(CUENTA_BANCARIA),CuentaDebito.class);
            add(new ConceptSchema(CUENTA_DEBITO),CuentaDebito.class);
            add(new ConceptSchema(CUENTA_AHORRO),CuentaAhorro.class);
            add(new ConceptSchema(TRANSACCION),Transaccion.class);
            add(new PredicateSchema(ACTIVACION_AHORRO), ActivacionAhorro.class);
            add(new PredicateSchema(ACTIVACION_DEBITO), ActivacionDebito.class);
            add(new PredicateSchema(CANCELACION_AHORRO), CancelacionAhorro.class);
            add(new PredicateSchema(TRANSFERENCIA),Transferencia.class);
            add(new AgentActionSchema(CREAR_AHORRO),CrearAhorro.class);
            add(new AgentActionSchema(CREAR_DEBITO),CrearAhorro.class);
            add(new AgentActionSchema(BORRAR_AHORRO),BorrarAhorro.class);
            add(new AgentActionSchema(TRANSFERIR),Transferir.class);
            add(new PredicateSchema(RETIRACION),Retiracion.class);
            add(new PredicateSchema(CANCELACION),Cancelacion.class);
            
            
            ConceptSchema cs = (ConceptSchema) getSchema(CLIENTE);
            cs.add(CLIENTE_NOMBRE,(PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(CLIENTE_DOMICILIO, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(CLIENTE_TELEFONO, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(CLIENTE_CORREO, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(CLIENTE_EDAD, (PrimitiveSchema) getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);
            cs.add(CLIENTE_ESTADO_LABORAL, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            
            cs = (ConceptSchema) getSchema(CUENTA_BANCARIA);
            cs.add(CUENTA_BANCARIA_CLIENTE, (ConceptSchema) getSchema(CLIENTE),ObjectSchema.OPTIONAL);
            cs.add(CUENTA_BANCARIA_MONTO, (PrimitiveSchema) getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(CUENTA_BANCARIA_CLABE, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(CUENTA_BANCARIA_NUMERO_TARJETA, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            
            cs = (ConceptSchema) getSchema(CUENTA_AHORRO);
            cs.addSuperSchema((ConceptSchema) getSchema(CUENTA_BANCARIA));
            cs.add(CUENTA_AHORRO_INTERES, (PrimitiveSchema) getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(CUENTA_AHORRO_IMPUESTO_ANUAL, (PrimitiveSchema) getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(CUENTA_AHORRO_DESCUENTO_TRANSACCION, (PrimitiveSchema) getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            
            cs = (ConceptSchema) getSchema(CUENTA_DEBITO);
            cs.addSuperSchema((ConceptSchema) getSchema(CUENTA_BANCARIA));
            
            cs.add(CUENTA_DEBITO_SALDO_MINIMO, (PrimitiveSchema) getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
            cs.add(CUENTA_DEBITO_COMISION, (PrimitiveSchema) getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);
        
            cs = (ConceptSchema) getSchema(TRANSACCION);
            cs.add(TRANSACCION_CLABE_ORIGEN, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(TRANSACCION_CLABE_DESTINO, (PrimitiveSchema) getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
            cs.add(TRANSACCION_MONTO, (PrimitiveSchema) getSchema(BasicOntology.FLOAT),ObjectSchema.OPTIONAL);

            
            PredicateSchema ps = (PredicateSchema) getSchema(ACTIVACION_AHORRO);
            ps.add(ACTIVACION_CUENTA_AHORRO, (ConceptSchema) getSchema(CUENTA_AHORRO),ObjectSchema.OPTIONAL);
            ps = (PredicateSchema) getSchema(ACTIVACION_DEBITO);
            ps.add(ACTIVACION_CUENTA_DEBITO, (ConceptSchema) getSchema(CUENTA_DEBITO),ObjectSchema.OPTIONAL);
            
            ps = (PredicateSchema) getSchema(CANCELACION_AHORRO);
            ps.add(CANCELACION_CUENTA_AHORRO, (ConceptSchema) getSchema(CUENTA_AHORRO),ObjectSchema.OPTIONAL);
            
            ps = (PredicateSchema) getSchema(TRANSFERENCIA);
            ps.add(TRANSFERENCIA_TRANSACCION, (ConceptSchema) getSchema(TRANSACCION),ObjectSchema.OPTIONAL);
            
            AgentActionSchema as = (AgentActionSchema) getSchema(CREAR_AHORRO);
            as.add(CREAR_CUENTA_AHORRO, (ConceptSchema) getSchema(CUENTA_AHORRO),ObjectSchema.OPTIONAL);
            as = (AgentActionSchema) getSchema(CREAR_DEBITO);
            as.add(CREAR_CUENTA_DEBITO, (ConceptSchema) getSchema(CUENTA_DEBITO),ObjectSchema.OPTIONAL);
            
            as = (AgentActionSchema) getSchema(BORRAR_AHORRO);
            as.add(BORRAR_CUENTA_AHORRO, (ConceptSchema) getSchema(CUENTA_AHORRO),ObjectSchema.OPTIONAL);
            
            ps = (PredicateSchema) getSchema(RETIRACION);
            ps.add(RETIRACION_TRANSACCION, (ConceptSchema) getSchema(TRANSACCION),ObjectSchema.OPTIONAL);
            
            ps = (PredicateSchema) getSchema(CANCELACION);
            ps.add(CANCELACION_CUENTA, (ConceptSchema) getSchema(TRANSACCION),ObjectSchema.OPTIONAL);
            
        }catch(OntologyException oe){
            oe.printStackTrace();
        }
    }
    
}
