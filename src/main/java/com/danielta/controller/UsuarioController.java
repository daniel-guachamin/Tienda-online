package com.danielta.controller;

import com.danielta.ejb.DetallesCompraFacadeLocal;
import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.ejb.MensajeFacadeLocal;
import com.danielta.ejb.PersonaFacadeLocal;
import com.danielta.ejb.UsuarioFacadeLocal;
import com.danielta.model.DetallesCompra;
import com.danielta.model.JuegosUsuarios;
import com.danielta.model.Mensaje;
import com.danielta.model.Persona;
import com.danielta.model.Usuario;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;
    @EJB
    private JuegosUsuariosFacadeLocal juegosEJB;
    @EJB
    private DetallesCompraFacadeLocal detalleEJB;
    @EJB
    private MensajeFacadeLocal mensajeEJB;

    private List<JuegosUsuarios> listJuegos;
    
    private List<DetallesCompra> listDetalles;
    
    private List<Mensaje> listMensajes;
    
    private List<Persona> listPersonas;

    @Inject
    private Usuario usuario;
    @Inject
    private Persona persona;

    private List<Usuario> misUsuarios;

    @PostConstruct
    public void init() {
        misUsuarios = usuarioEJB.findAll();
        listJuegos = juegosEJB.findAll();
        listDetalles=detalleEJB.findAll();
        listMensajes=mensajeEJB.findAll();
        listPersonas=personaEJB.findAll();
    }

    public List<Usuario> getMisUsuarios() {
        return misUsuarios;
    }

    public void setMisUsuarios(List<Usuario> misUsuarios) {
        this.misUsuarios = misUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void registrar() {
        try {
            this.usuario.setCodigo(persona);
            this.usuario.setClave(encriptar(usuario.getClave()));
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario registrado correctamente")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al registrar!"));
        }
    }
    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    public String verMensajes(Persona codigo) {

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        us.setCodigo(codigo);

        String redireccion = "chatAdmin?faces-redirect=true";

        return redireccion;
    }

    public String mostrarUsuarioLogeado() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getUsuario();
    }

    public void eliminarUsuario(Usuario borrar, Persona codigo) {

        try {
            
            usuarioEJB.remove(borrar);
            //elimino los juegos digitales del usuario que tenia comprados
            for (JuegosUsuarios lista : listJuegos) {
                if(lista.getPersona().getCodigo()== codigo.getCodigo()){
                    juegosEJB.remove(lista);
                }
            }
            //elimino los pedidos del usuario que tenia
            for (DetallesCompra lista2 : listDetalles) {
                if(lista2.getPersona().getCodigo()== codigo.getCodigo()){
                    detalleEJB.remove(lista2);
                }
            }
            //elimino los mensajes del usuario que tenia
            for (Mensaje lista3 : listMensajes) {
                if(lista3.getPersona().getCodigo()== codigo.getCodigo()){
                    mensajeEJB.remove(lista3);
                }
            }
            //elimino los datos personales del usuario 
            for (Persona lista4 : listPersonas) {
                if(lista4.getCodigo()== codigo.getCodigo()){
                    personaEJB.remove(lista4);
                }
            }
      
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario eliminado")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al eliminar!"));
        }
    }

}
