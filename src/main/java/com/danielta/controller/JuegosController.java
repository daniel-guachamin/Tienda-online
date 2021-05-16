package com.danielta.controller;

import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.model.Juegos;
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
    
    private int codigo_persona;//para poder introducir el codigo de la persona que esta comprando cada juego
    //Array que guardara los juegos que quiera el usuario en la base de datos
    private static List<JuegosUsuarios> misJuegos = new ArrayList();

    private Juegos juego = new Juegos();
    //Array que guardara los juegos que quiera el usuario en un arrayList para poder eliminarlos antes de enviarlos a la bbdd
    private static List<Juegos> juegosList = new ArrayList();

    public List<Juegos> getJuegosList() {
        return juegosList;
    }

    public void setJuegosList(List<Juegos> juegosList) {
        JuegosController.juegosList = juegosList;
    }

    public List<JuegosUsuarios> getMisJuegos() {
        return misJuegos;
    }

    public Juegos getJuego() {
        return juego;
    }

    public void setJuego(Juegos juego) {
        this.juego = juego;
    }

    public void setMisJuegos(List<JuegosUsuarios> misJuegos) {
        JuegosController.misJuegos = misJuegos;
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

    public int totalPrecio() {
        int total = 0;
        for (Juegos juegoPrecio : juegosList) {
            total = total + juegoPrecio.getPrecio();
        }
        return total;
    }

    public void eliminarJuego(Juegos borrar) {

        try {

            JuegosController.juegosList.remove(borrar);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego eliminado de tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al eliminar!"));
        }
    }

    public void finalizarCompra() {
        try {
            if (juegosList.size() > 0) { //solo entrara si el array listaJuegos no esta vacio
                for (Juegos listaJuegos : juegosList) { //añado los juegos no eliminados por el usuario en mi array que guardara los juegos a la bbdd
                    juegos=new JuegosUsuarios();//necesario para guardar mis datos en un objeto nuevo durante la itracion del bucle
                    this.juegos.setPersona(codigo_persona);
                    this.juegos.setNombre(listaJuegos.getNombre());
                    if(listaJuegos.getEstado().equals("Alquilar")){
                        this.juegos.setEstado("Alquilado");
                    }else{
                        this.juegos.setEstado("Comprado");
                    } 
                    this.juegos.setImagen(listaJuegos.getImagen());
                    this.juegos.setPrecio(listaJuegos.getPrecio());
                    JuegosController.misJuegos.add(juegos);
                }
                
                for (JuegosUsuarios juegosCarrito : misJuegos) {
                    juegosEJB.create(juegosCarrito);//guarda mis juegos en mi bbdd
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Compra realizada correctamente")); //para mostrar mensaje de registro exitoso
                //reinicio mis arrays una vez que el usuario haya pulsado el boton finalizar compra
                JuegosController.misJuegos.clear();
                JuegosController.juegosList.clear();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No has añadido ningun juego a tu cesta!"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al realizar la compra!"));
        }

    }

    public void agregarCompraJuego1() {
        try {
            this.juego.setNombre("Detroit Become Human");
            this.juego.setEstado("Comprar");
            this.juego.setImagen("Detroit.png");
            this.juego.setPrecio(40);
            JuegosController.juegosList.add(this.juego);

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
            this.juego.setPrecio(5);
            JuegosController.juegosList.add(this.juego);

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
