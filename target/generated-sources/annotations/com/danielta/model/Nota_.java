package com.danielta.model;

import com.danielta.model.Categoria;
import com.danielta.model.Persona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-05T19:58:45")
@StaticMetamodel(Nota.class)
public class Nota_ { 

    public static volatile SingularAttribute<Nota, Date> fecha;
    public static volatile SingularAttribute<Nota, String> comentarioAdmin;
    public static volatile SingularAttribute<Nota, Integer> codigo;
    public static volatile SingularAttribute<Nota, Persona> persona;
    public static volatile SingularAttribute<Nota, Categoria> categoria;
    public static volatile SingularAttribute<Nota, String> encabezado;
    public static volatile SingularAttribute<Nota, Integer> valorizacion;
    public static volatile SingularAttribute<Nota, String> cuerpo;

}