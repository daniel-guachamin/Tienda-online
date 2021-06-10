
package com.danielta.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "chat")
public class Mensaje implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremental
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "codigo_persona", nullable = false)
    private Persona persona;

    @Column(name = "mensaje")
    private String mensaje;
    
    @Column(name = "mensajeAdmin")
    private String mensajeAdmin;

    public String getMensajeAdmin() {
        return mensajeAdmin;
    }

    public void setMensajeAdmin(String mensajeAdmin) {
        this.mensajeAdmin = mensajeAdmin;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensaje other = (Mensaje) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "codigo=" + codigo + '}';
    }


    

    

}
