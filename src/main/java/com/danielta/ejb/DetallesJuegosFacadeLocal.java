
package com.danielta.ejb;

import com.danielta.model.DetallesJuegos;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DetallesJuegosFacadeLocal {

    void create(DetallesJuegos detallesJuegos);

    void edit(DetallesJuegos detallesJuegos);

    void remove(DetallesJuegos detallesJuegos);

    DetallesJuegos find(Object id);

    List<DetallesJuegos> findAll();

    List<DetallesJuegos> findRange(int[] range);

    int count();
    
}
