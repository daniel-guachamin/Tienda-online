package com.danielta.model;

import com.danielta.model.Persona;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-05T19:58:45")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Persona> codigo;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, String> tipo;
    public static volatile SingularAttribute<Usuario, String> usuario;

}