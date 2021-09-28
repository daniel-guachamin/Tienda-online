package com.danielta.model;

import com.danielta.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-14T17:13:58")
@StaticMetamodel(Mensaje.class)
public class Mensaje_ { 

    public static volatile SingularAttribute<Mensaje, Integer> codigo;
    public static volatile SingularAttribute<Mensaje, Persona> persona;
    public static volatile SingularAttribute<Mensaje, String> mensaje;
    public static volatile SingularAttribute<Mensaje, String> mensajeAdmin;

}