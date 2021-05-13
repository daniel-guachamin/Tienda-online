package com.danielta.controller;

import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.model.Juego;
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
    //Array que guardara los juegos que quiera el usuario en el carrito
    private static List<Juego> listaJuegos = new ArrayList();
    //Array que guardara los juegos que quiera el usuario en la base de datos
    private static List<JuegosUsuarios> misJuegos = new ArrayList();

    private Juego juego = new Juego();

    public List<JuegosUsuarios> getMisJuegos() {
        return misJuegos;
    }

    public void setMisJuegos(List<JuegosUsuarios> misJuegos) {
        this.misJuegos = misJuegos;
    }

    public List<Juego> getListaJuegos() {
        return listaJuegos;
    }

    public void setListaJuegos(List<Juego> listaJuegos) {
        JuegosController.listaJuegos = listaJuegos;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
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

    public void eliminarJuego(Juego borrar) {

        try {
            JuegosController.listaJuegos.remove(borrar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego eliminado de tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al eliminar!"));
        }
    }

    public void agregarCompraJuego1() {
        try {
            this.juego.setNombre("Detroit Become Human");
            this.juego.setEstado("Comprar");
            this.juego.setImagen("Detroit.png");
            this.juego.setPrecio(40);
            JuegosController.listaJuegos.add(juego);

            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre(juego.getNombre());
            this.juegos.setEstado(juego.getEstado());
            this.juegos.setImagen(juego.getImagen());
            this.juegos.setPrecio(juego.getPrecio());
            JuegosController.misJuegos.add(juegos);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego añadido a tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al añadir a tu cesta!"));
        }

    }

    public void agregarAlquilarJuego1() {
        try {
            this.juego.setNombre("Detroit Become Human");
            this.juego.setEstado("Alquilar");
            this.juego.setImagen("Detroit.png");
            this.juego.setPrecio(40);
            JuegosController.listaJuegos.add(juego);

            this.juegos.setPersona(codigo_persona);
            this.juegos.setNombre(juego.getNombre());
            this.juegos.setEstado(juego.getEstado());
            this.juegos.setImagen(juego.getImagen());
            this.juegos.setPrecio(juego.getPrecio());
            JuegosController.misJuegos.add(juegos);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego añadido a tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al añadir a tu cesta!"));
        }

    }

    public void finalizarCompra() {
        try {
            if (misJuegos.size() > 0) {
                for (JuegosUsuarios juegosCarrito : misJuegos) {
                    juegosEJB.create(juegosCarrito);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Compra realizada correctamente")); //para mostrar mensaje de registro exitoso
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Nos añadido ningun juego a tu cesta!"));
            }

            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al realizar la compra!"));
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
