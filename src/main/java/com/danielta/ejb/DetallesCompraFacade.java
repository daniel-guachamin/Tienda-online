/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielta.ejb;

import com.danielta.model.DetallesCompra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author danie
 */
@Stateless
public class DetallesCompraFacade extends AbstractFacade<DetallesCompra> implements DetallesCompraFacadeLocal {

    @PersistenceContext(unitName = "drivergames")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallesCompraFacade() {
        super(DetallesCompra.class);
    }
    
}
