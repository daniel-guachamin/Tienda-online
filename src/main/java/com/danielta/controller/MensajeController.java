package com.danielta.controller;

import com.danielta.ejb.MensajeFacadeLocal;
import com.danielta.model.Mensaje;
import com.danielta.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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

    @PostConstruct
    public void init() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        codigo_persona = us.getCodigo().getCodigo();//guardo una variable 
        nombre_usuario= us.getUsuario();
        miMensaje = mensajeEJB.encuentraMensaje(codigo_persona);
    }

    public void enviarMensaje() {

        try {
            if (miMensaje.size() == 0) { //cuando se envie un mensaje por primera vez 
                this.mensaje.setPersona(codigo_persona);
                mensajeEJB.create(mensaje);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Mensaje enviado correctamente")); //para mostrar mensaje de registro exitoso
            }else{
                for(Mensaje men : miMensaje){ //entra cuando el usuario ya ha escrito un mensaje en el pasado
                    this.mensaje.setCodigo(men.getCodigo());
                    this.mensaje.setPersona(men.getPersona());
                    this.mensaje.setMensaje(men.getMensaje() + "  " + mensaje.getMensaje());
                }
                mensajeEJB.edit(mensaje);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Mensaje2 enviado correctamente")); //para mostrar mensaje de registro exitoso
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al enviar el mensaje!"));
        }

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
