/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielta.ejb;

import com.danielta.model.DetallesCompra;
import com.danielta.model.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author danie
 */
@Local
public interface DetallesCompraFacadeLocal {

    void create(DetallesCompra detallesCompra);

    void edit(DetallesCompra detallesCompra);

    void remove(DetallesCompra detallesCompra);

    DetallesCompra find(Object id);

    List<DetallesCompra> findAll();
    
    List<DetallesCompra> encuentraPedidosUsuario(Persona entra_codigo_persona);

    List<DetallesCompra> findRange(int[] range);

    int count();
    
}
