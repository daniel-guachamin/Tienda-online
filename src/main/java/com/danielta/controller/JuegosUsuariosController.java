package com.danielta.controller;

import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.model.JuegosUsuarios;
import com.danielta.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class JuegosUsuariosController implements Serializable {

    @EJB
    private JuegosUsuariosFacadeLocal juegosUsuariosEJB; //

    private List<JuegosUsuarios> misJuegosComprados; //variable que me permitira mostrar los juegos comprados del usuario
    private JuegosUsuarios juegos;

    public List<JuegosUsuarios> getMisJuegosComprados() {
        return misJuegosComprados;
    }

    public void setMisJuegosComprados(List<JuegosUsuarios> misJuegosComprados) {
        this.misJuegosComprados = misJuegosComprados;
    }

    public JuegosUsuarios getJuegos() {
        return juegos;
    }

    public void setJuegos(JuegosUsuarios juegos) {
        this.juegos = juegos;
    }
    Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    @PostConstruct
    public void init() {

        misJuegosComprados = juegosUsuariosEJB.encuentraJuegosUsuarioComprado(us.getCodigo()); //Utilizo mi metodo encuentraJuegosUsuario guardar los juegos de mi usuario en sesion en un array List
    }

}
