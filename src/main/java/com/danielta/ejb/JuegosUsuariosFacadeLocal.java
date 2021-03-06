
package com.danielta.ejb;

import com.danielta.model.JuegosUsuarios;
import com.danielta.model.Persona;
import java.util.List;
import javax.ejb.Local;


@Local
public interface JuegosUsuariosFacadeLocal {

    void create(JuegosUsuarios juegosUsuarios);

    void edit(JuegosUsuarios juegosUsuarios);

    void remove(JuegosUsuarios juegosUsuarios);

    JuegosUsuarios find(Object id);

    List<JuegosUsuarios> findAll();
    
    List<JuegosUsuarios> encuentraJuegosUsuarioComprado(Persona entra_codigo_persona);
    
    List<JuegosUsuarios> findRange(int[] range);

    int count();
    
}
