
package com.danielta.ejb;

import com.danielta.model.DetallesJuegos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DetallesJuegosFacade extends AbstractFacade<DetallesJuegos> implements DetallesJuegosFacadeLocal {

    @PersistenceContext(unitName = "drivergames")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallesJuegosFacade() {
        super(DetallesJuegos.class);
    }
    
}
