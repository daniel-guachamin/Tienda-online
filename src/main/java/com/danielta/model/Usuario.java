package com.danielta.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//La persistencia nos permite manejar nuestras consultas por medio de objetos
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
//Relacionamos por medio de anotacion las llaves foraneas
    @Id
    @OneToOne(cascade = CascadeType.PERSIST) //relacion uno a uno BBDD,  no es autoincremental,
            //cuando hacemos una inserciona a la tabla persona va persistir tambien a la tabla usuario
    @JoinColumn(name = "codigo", nullable = false) //este campo no puede presentar nulo
    private Persona codigo;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "clave")
    private String clave;

    @Column(name = "tipo")
    private String tipo="O";

    public Persona getCodigo() {
        return codigo;
    }

    public void setCodigo(Persona codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    //equals, hashCode estos metodos son importantes cuando trabajas con conexiones
    //nos permite comparar si un objeto es identico a otro,poder ordenarlos
    //es necesario sobrescribirlos 
    //equals() hashCode() boton derecho para realizar esta accion
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigo);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + '}';
    }

}
