
package com.danielta.ejb;

import com.danielta.model.Mensaje;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MensajeFacadeLocal {

    void create(Mensaje mensaje);

    void edit(Mensaje mensaje);

    void remove(Mensaje mensaje);

    Mensaje find(Object id);

    List<Mensaje> findAll();
    
    List<Mensaje> encuentraMensaje(int entra_codigo_persona);

    List<Mensaje> findRange(int[] range);

    int count();
    
}
