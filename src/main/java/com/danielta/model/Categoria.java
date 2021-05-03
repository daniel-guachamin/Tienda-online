package com.danielta.model;
//Las anotaciones JPA nos permitiran tener un reflejo de nuestra tabla 
//en entidades o clases en  JAVA
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Enntidad manejada por JPA
@Table(name = "categoria")
//Importante llamar a la interfaz Serializable para que mi codigo funcione bien
public class Categoria implements Serializable {

    @Id //hay que indicar cual es mi llave o primary key de mi tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
//llamamos la variable nombre que representa a la columna nombre de mi BBDD
    @Column(name = "nombre")
    private String nombre; 

    @Column(name = "estado")
    private boolean estado = true;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.codigo;
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
        final Categoria other = (Categoria) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoria{" + "codigo=" + codigo + '}';
    }
}
