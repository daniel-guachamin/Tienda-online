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
    private int codigo_persona;

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

    @PostConstruct
    public void init() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        codigo_persona=us.getCodigo().getCodigo();//guardo una variable 
        misJuegosComprados = juegosUsuariosEJB.encuentraJuegosUsuarioComprado(codigo_persona); //Utilizo mi metodo encuentraJuegosUsuario guardar los juegos de mi usuario en sesion en un array List
    }

}
