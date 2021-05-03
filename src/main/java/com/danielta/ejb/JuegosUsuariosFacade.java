
package com.danielta.ejb;

import com.danielta.model.JuegosUsuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class JuegosUsuariosFacade extends AbstractFacade<JuegosUsuarios> implements JuegosUsuariosFacadeLocal {

    @PersistenceContext(unitName = "drivergames")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JuegosUsuariosFacade() {
        super(JuegosUsuarios.class);
    }
    
}
