package com.danielta.ejb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
//Los EJB van a llamar a los entity class para poder hacer las llamadas a los metodos
//de persistir en la BBDD
//Patron fachada me permitira acceder a los metodos de una clase 
//a traves de una interfaz mi interfaz en este caso es CategoriaFacadeLocal
//y los metodos que voy a llamar estan en CategoriaFacade
public abstract class AbstractFacade<T> { //utiliza datos de tipo generico para que me permita cualquier tipo de objeto y me permitira poder utilizar los metodos definidos en la parte inferior de ese tipo de objeto
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<T> encuentraJuegosUsuarioComprado(int codigo_persona) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(cb.equal(c.get("persona"), codigo_persona)); //consulta al campo codigo_persona dependiendo del tipo de usuario que este logeado
        return getEntityManager().createQuery(cq).getResultList();
    }
 
    public List<T> encuentraMensaje(int codigo_persona) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(cb.equal(c.get("persona"), codigo_persona)); //consulta al campo codigo_persona dependiendo del tipo de usuario que este logeado
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<T> encuentraDatosPersona(int codigo_persona) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        cq.where(cb.equal(c.get("codigo"), codigo_persona)); 
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
