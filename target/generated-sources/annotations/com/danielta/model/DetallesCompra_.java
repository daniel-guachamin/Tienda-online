package com.danielta.model;

import com.danielta.model.Persona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-14T17:13:58")
@StaticMetamodel(DetallesCompra.class)
public class DetallesCompra_ { 

    public static volatile SingularAttribute<DetallesCompra, Date> fecha;
    public static volatile SingularAttribute<DetallesCompra, Integer> codigo;
    public static volatile SingularAttribute<DetallesCompra, Integer> precio;
    public static volatile SingularAttribute<DetallesCompra, Persona> persona;
    public static volatile SingularAttribute<DetallesCompra, String> imagen;
    public static volatile SingularAttribute<DetallesCompra, String> producto;
    public static volatile SingularAttribute<DetallesCompra, Integer> cantidad;

}