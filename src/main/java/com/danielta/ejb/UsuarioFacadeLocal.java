
package com.danielta.ejb;

import com.danielta.model.Usuario;
import java.util.List;
import javax.ejb.Local;


@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    //indicamos el nuevo metodo para que el controlador lo reconosca
    Usuario iniciarSesion(Usuario us) throws Exception;

}
