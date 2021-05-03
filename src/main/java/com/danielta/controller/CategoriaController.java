package com.danielta.controller;

import com.danielta.ejb.CategoriaFacadeLocal;
import com.danielta.model.Categoria;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
//Se esta utilizando el modelo vista controlador MVC (ojo)
//fluyo de ejecucion CategoriaController,CategoriaFacadeLocal,CategoriaFacede,AbstractFacede 
//llamo al metodo create del EJB que acabo de inyectar 
//nuestra interfaz donde este create busca a la clase CategoriaFacade y invoca el contructor
//como es una herencia busca al contructor padre de nuestra clase AbstractFacade
//Esta clase se queda con en Enity class de tipo categoria y por lo tanto usa el metodo create definido ahi
//y persiste a nuestra base de datos y registra ....

@Named //valida a partir de JSF 2.2 para inyecciones de dependencias
@ViewScoped
public class CategoriaController implements Serializable{
    
    @EJB //indicamos que esto es un EJB
    private CategoriaFacadeLocal categoriaEJB;
    @Inject //para usar el CDI
    private Categoria categoria; //variable para poder utilizar los datos

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }        
    
    @PostConstruct 
    public void init() {
        //categoria = new Categoria(); //ya no hace falta instanciar gracias al CDI
    }
    
    public void registrar() {
        try {
            categoriaEJB.create(categoria); //llamo mi metodo create de mi interfaz CategoriaFacadeLocal
        } catch (Exception e) {
            //mensaje prime grwol
        }
    }
}
