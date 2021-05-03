package com.danielta.controller;

import com.danielta.ejb.UsuarioFacadeLocal;
import com.danielta.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class IndexController implements Serializable { //Serializable ponerlo para evitar problemas al desplegar

    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        Usuario us;
        String redireccion = null;
        try {
            us = EJBUsuario.iniciarSesion(usuario); //patron fachado puedo llamar a mi metodo iniciar sesion ya que lo he definido en la interfaz UsuarioFacadeLocal
            if (us != null) {     //si existe el usuario entra
                //Almacenar en la sesión de JSF,es lo mismo que en los servlet cuando uso session y guardo
                //un objeto de tipo session y ese atributo podia utilizarlos en toda la aplicacion pero ahora con JSF
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us); //añado mi variable al apodo usuario
                redireccion = "/protegido/principal?faces-redirect=true"; //?faces-redirect=true me permite ver la ruta de navegacion en la url
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
        }
        return redireccion;
    }
}
