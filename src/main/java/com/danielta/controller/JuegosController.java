
package com.danielta.controller;

import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.model.JuegosUsuarios;
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
public class JuegosController {
    @EJB
    private JuegosUsuariosFacadeLocal juegosEJB;
    @Inject
    private JuegosUsuarios juegos;
    private int codigo_persona;

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
    }
    
    public void comprarJuego1(){
        try{
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Detroit");
            this.juegos.setEstado("Comprado");
            this.juegos.setImagen("Detroit.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego comprado correctamente")); //para mostrar mensaje de registro exitoso
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al comprar el juego!"));            
        }
    }
    public void alquilarJuego1(){
        try{
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Detroit");
            this.juegos.setEstado("Alquilado disponible 3 días");
            this.juegos.setImagen("Detroit.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego alquilado correctamente")); //para mostrar mensaje de registro exitoso
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al alquilar el juego!"));            
        }
    }
    public void comprarJuego2(){
        try{
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Hitman 3");
            this.juegos.setEstado("Comprado");
            this.juegos.setImagen("Hitman3.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego comprado correctamente")); //para mostrar mensaje de registro exitoso
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al comprar el juego!"));            
        }
    }
    public void alquilarJuego2(){
        try{
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Hitman 3");
            this.juegos.setEstado("Alquilado disponible 3 días");
            this.juegos.setImagen("Hitman3.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego alquilado correctamente")); //para mostrar mensaje de registro exitoso
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al alquilar el juego!"));            
        }
    }
    public void comprarJuego3(){
        try{
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Los Sims 4");
            this.juegos.setEstado("Comprado");
            this.juegos.setImagen("Sims4.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego comprado correctamente")); //para mostrar mensaje de registro exitoso
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al comprar el juego!"));            
        }
    }
    public void alquilarJuego3(){
        try{
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Los Sims 4");
            this.juegos.setEstado("Alquilado disponible 3 días");
            this.juegos.setImagen("Sims4.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego alquilado correctamente")); //para mostrar mensaje de registro exitoso
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al alquilar el juego!"));            
        }
    }
}
