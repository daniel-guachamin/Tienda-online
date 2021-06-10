
package com.danielta.ejb;

import com.danielta.model.Mensaje;
import com.danielta.model.Persona;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MensajeFacadeLocal {

    void create(Mensaje mensaje);

    void edit(Mensaje mensaje);

    void remove(Mensaje mensaje);

    Mensaje find(Object id);

    List<Mensaje> findAll();
    
    List<Mensaje> encuentraMensaje(Persona entra_codigo_persona);

    List<Mensaje> findRange(int[] range);

    int count();
    
}
