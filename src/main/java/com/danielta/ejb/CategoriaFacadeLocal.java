package com.danielta.ejb;

import com.danielta.model.Categoria;
import java.util.List;
import javax.ejb.Local;

//interfaz que me sirve para poder acceder a los metodos creados aqui
//pero definidos en AbstractFacede

//Primero crear los metodos aqui en la interfaz ,luego definir el contenido de
//estos metodos en AbstractFacede (recordar deficion de lo que hace una interfaz del año pasado...)


@Local
public interface CategoriaFacadeLocal {

    void create(Categoria categoria);

    void edit(Categoria categoria);

    void remove(Categoria categoria);

    Categoria find(Object id);

    List<Categoria> findAll();

    List<Categoria> findRange(int[] range);

    int count();
    
}
