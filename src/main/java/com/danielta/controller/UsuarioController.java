package com.danielta.controller;

import com.danielta.ejb.UsuarioFacadeLocal;
import com.danielta.model.Persona;
import com.danielta.model.Usuario;
import java.io.Serializable;
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
public class UsuarioController implements Serializable{

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @Inject
    private Usuario usuario;
    @Inject
    private Persona persona;
    
    private List<Usuario> misUsuarios;

    @PostConstruct
    public void init() {
        misUsuarios=usuarioEJB.findAll();
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
    
    public void registrar(){
        try{
            this.usuario.setCodigo(persona);
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario registrado correctamente")); //para mostrar mensaje de registro exitoso
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al registrar!"));            
        }
    }
    
    public String verMensajes(int codigo) {
        misUsuarios.clear();
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Persona lol=new Persona();
        lol.setCodigo(codigo);
        Usuario tu=new Usuario();
        tu.setCodigo(lol);
        us.setCodigo(tu.getCodigo());
        
        
        String redireccion = "chatAdmin?faces-redirect=true";          
        
        return redireccion;
    }
    
    public String mostrarUsuarioLogeado() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getUsuario();
    }

}
