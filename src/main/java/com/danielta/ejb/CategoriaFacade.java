package com.danielta.ejb;

import com.danielta.model.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless //sin estado recomendable en el uso de EJB
//Esta clase invoca a la unidad de persistencia que he creado antes primePU
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {
    @PersistenceContext(unitName = "drivergames") //se conecta a mi unidad de persistencia
    private EntityManager em; //me permite manejar las operaciones crud

    @Override //sobre escribe el metodo( polimorfismo)
    protected EntityManager getEntityManager() { //de mi clase AbstractaFacede
        return em;
    }   
    //contructor que envia el tipo de dato que utilizaremos en abstract Facade
    public CategoriaFacade() {
        super(Categoria.class);
    }
    
}
