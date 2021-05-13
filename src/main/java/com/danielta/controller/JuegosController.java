package com.danielta.controller;

import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.model.JuegosUsuarios;
import com.danielta.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
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

    //Array que guardara los juegos que quiera el usuario en la base de datos
    private static List<JuegosUsuarios> misJuegos = new ArrayList();

    public List<JuegosUsuarios> getMisJuegos() {
        return misJuegos;
    }

    public void setMisJuegos(List<JuegosUsuarios> misJuegos) {
        this.misJuegos = misJuegos;
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
        codigo_persona = us.getCodigo().getCodigo();//guardo una variable 
    }

    public void eliminarJuego(JuegosUsuarios borrar) {

        try {

            JuegosController.misJuegos.remove(borrar);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego eliminado de tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al eliminar!"));
        }
    }
    
    public void finalizarCompra() {
        try {
            if (misJuegos.size() > 0) { //solo entrara si el array no esta vacio
                for (JuegosUsuarios juegosCarrito : misJuegos) {
                    juegosEJB.create(juegosCarrito);//guarda mis juegos en mi bbdd
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Compra realizada correctamente")); //para mostrar mensaje de registro exitoso
                JuegosController.misJuegos.clear();//reinicio el array una vez que el usuario haya pulsado el boton finalizar compra
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No has añadido ningun juego a tu cesta!"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al realizar la compra!"));
        }

    }

    public void agregarCompraJuego1() {
        try {
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Detroit Become Human");
            this.juegos.setEstado("Comprar");
            this.juegos.setImagen("Detroit.png");
            this.juegos.setPrecio(40);
            JuegosController.misJuegos.add(juegos);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego añadido a tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al añadir a tu cesta!"));
        }

    }

    public void agregarAlquilarJuego1() {
        try {
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Detroit Become Human");
            this.juegos.setEstado("Alquilar");
            this.juegos.setImagen("Detroit.png");
            this.juegos.setPrecio(5);
            JuegosController.misJuegos.add(juegos);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego añadido a tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al añadir a tu cesta!"));
        }

    }

    public void comprarJuego2() {
        try {
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Hitman 3");
            this.juegos.setEstado("Comprado");
            this.juegos.setImagen("Hitman3.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego comprado correctamente")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al comprar el juego!"));
        }
    }

    public void alquilarJuego2() {
        try {
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Hitman 3");
            this.juegos.setEstado("Alquilado disponible 3 días");
            this.juegos.setImagen("Hitman3.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego alquilado correctamente")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al alquilar el juego!"));
        }
    }

    public void comprarJuego3() {
        try {
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Los Sims 4");
            this.juegos.setEstado("Comprado");
            this.juegos.setImagen("Sims4.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego comprado correctamente")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al comprar el juego!"));
        }
    }

    public void alquilarJuego3() {
        try {
            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre("Los Sims 4");
            this.juegos.setEstado("Alquilado disponible 3 días");
            this.juegos.setImagen("Sims4.png");
            juegosEJB.create(juegos);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego alquilado correctamente")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al alquilar el juego!"));
        }
    }
}
