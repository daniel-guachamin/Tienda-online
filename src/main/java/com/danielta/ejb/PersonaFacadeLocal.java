
package com.danielta.ejb;

import com.danielta.model.Persona;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PersonaFacadeLocal {

    void create(Persona persona);

    void edit(Persona persona);

    void remove(Persona persona);

    Persona find(Object id);

    List<Persona> findAll(); //imporatantre para traer todos los datos necesrios del menu que tenemos
    
    List<Persona> encuentraDatosPersona(int entra_codigo_persona);

    List<Persona> findRange(int[] range);

    int count();
    
}
