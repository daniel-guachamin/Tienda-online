package com.danielta.controller;

import com.danielta.ejb.MensajeFacadeLocal;
import com.danielta.model.Mensaje;
import com.danielta.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class MensajeController implements Serializable {

    @EJB
    private MensajeFacadeLocal mensajeEJB;

    @Inject
    private Mensaje mensaje;

    private List<Mensaje> miMensaje; //variable que me permitira mostrar los juegos disponibles del usuario

    private int codigo_persona;//para poder introducir el codigo de la persona que esta comprando cada juego
    private String nombre_usuario;
    Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    @PostConstruct
    public void init() {

        nombre_usuario = us.getUsuario();
        miMensaje = mensajeEJB.encuentraMensaje(us.getCodigo());
    }

    public void enviarMensaje() {

        try {
        
            this.mensaje.setPersona(us.getCodigo());
            this.mensaje.setMensajeAdmin("");
            mensajeEJB.create(mensaje);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Mensaje enviado correctamente")); //para mostrar mensaje de registro exitoso

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al enviar el mensaje!"));
        }

    }

    public void enviarMensajeAdmin(Mensaje men) {

        try {

            mensajeEJB.edit(men);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Mensaje enviado correctamente")); //para mostrar mensaje de registro exitoso

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al enviar el mensaje!"));
        }

    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public List<Mensaje> getMiMensaje() {
        return miMensaje;
    }

    public void setMiMensaje(List<Mensaje> miMensaje) {
        this.miMensaje = miMensaje;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo_persona() {
        return codigo_persona;
    }

    public void setCodigo_persona(int codigo_persona) {
        this.codigo_persona = codigo_persona;
    }

}
