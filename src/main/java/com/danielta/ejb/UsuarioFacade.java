package com.danielta.ejb;

import com.danielta.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "drivergames")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    //Metodo que me permitira iniciar sesion
    @Override
    public Usuario iniciarSesion(Usuario us) throws Exception{
        Usuario usuario = null;
        String consulta;
        try {
            //lenguaje propio de consultas cuando manejas JPA y EJB (JPQL)
            consulta = "FROM Usuario u WHERE u.usuario = ?1 and u.clave = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsuario());
            query.setParameter(2, encriptar(us.getClave()));

            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) { //si la lista es diferente de vacia,es decir hay un elemento, usuario es igual a el primer elemento
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }       
        return usuario;
    }
    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
}
