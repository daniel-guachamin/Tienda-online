package com.danielta.controller;

import com.danielta.ejb.DetallesCompraFacadeLocal;
import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.ejb.PersonaFacadeLocal;
import com.danielta.model.DetallesCompra;
import com.danielta.model.Juegos;
import com.danielta.model.JuegosUsuarios;
import com.danielta.model.Persona;
import com.danielta.model.Usuario;
import com.danielta.model.pedidos;
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
public class JuegosController implements Serializable {

    @EJB
    private JuegosUsuariosFacadeLocal juegosEJB;
    @EJB
    private DetallesCompraFacadeLocal detallesEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;

    @Inject
    private Persona persona;
    @Inject
    private JuegosUsuarios juegos;
    @Inject
    private DetallesCompra detalles;

    private Juegos juego = new Juegos();

    private pedidos pedido = new pedidos();

    //Array que guardara los juegos que quiera el usuario en un arrayList para poder eliminarlos antes de enviarlos a la bbdd
    private int codigo_persona;//para poder introducir el codigo de la persona que esta comprando cada juego
    //Array que guardara los juegos que quiera el usuario en la base de datos
    static List<JuegosUsuarios> misJuegos = new ArrayList();

    static List<DetallesCompra> misPedidos = new ArrayList();

    static List<Juegos> juegosList = new ArrayList();

    static List<pedidos> pedidosList = new ArrayList();

    private List<Persona> datosPersona;

    public List<DetallesCompra> getMisPedidos() {
        return misPedidos;
    }

    public List<pedidos> getPedidosList() {
        return pedidosList;
    }

    public pedidos getPedido() {
        return pedido;
    }

    public void setPedido(pedidos pedido) {
        this.pedido = pedido;
    }

    public void setPedidosList(List<pedidos> pedidosList) {
        JuegosController.pedidosList = pedidosList;
    }

    public void setMisPedidos(List<DetallesCompra> misPedidos) {
        JuegosController.misPedidos = misPedidos;
    }

    public List<Persona> getDatosPersona() {
        return datosPersona;
    }

    public void setDatosPersona(List<Persona> datosPersona) {
        this.datosPersona = datosPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Juegos> getJuegosList() {
        return juegosList;
    }

    public void setJuegosList(List<Juegos> juegosList) {
        JuegosController.juegosList = juegosList;
    }

    public List<JuegosUsuarios> getMisJuegos() {
        return misJuegos;
    }

    public DetallesCompra getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallesCompra detalles) {
        this.detalles = detalles;
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
        datosPersona = personaEJB.encuentraDatosPersona(codigo_persona);
    }

    public int totalPrecio() {
        int total = 0;
        for (Juegos juegoPrecio : juegosList) {
            total = total + juegoPrecio.getPrecio();
        }
        for (pedidos pedidoPrecio : pedidosList) {
            if (pedidoPrecio.getCantidad() > 1) {
                total = total + (pedidoPrecio.getPrecio() * pedidoPrecio.getCantidad());
            } else {
                total = total + pedidoPrecio.getPrecio();
            }

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

    public void eliminarPedido(pedidos borrar) {

        try {

            JuegosController.pedidosList.remove(borrar);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego eliminado de tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al eliminar!"));
        }
    }

    public void finalizarCompra() {

        try {

            if (juegosList.size() > 0 || pedidosList.size() > 0) { //solo entrara si el array listaJuegos no esta vacio
                for (Juegos listaJuegos : juegosList) { //añado los juegos no eliminados por el usuario en mi array que guardara los juegos a la bbdd

                    juegos = new JuegosUsuarios();//necesario para guardar mis datos en un objeto nuevo durante la itracion del bucle
                    this.juegos.setPersona(codigo_persona);
                    this.juegos.setNombre(listaJuegos.getNombre());
                    this.juegos.setImagen(listaJuegos.getImagen());
                    this.juegos.setPrecio(listaJuegos.getPrecio());
                    JuegosController.misJuegos.add(juegos);

                }

                for (pedidos listaPedidos : pedidosList) { //añado los juegos no eliminados por el usuario en mi array que guardara los juegos a la bbdd

                    detalles = new DetallesCompra();//necesario para guardar mis datos en un objeto nuevo durante la itracion del bucle
                    this.detalles.setPersona(codigo_persona);
                    this.detalles.setProducto(listaPedidos.getProducto());
                    this.detalles.setImagen(listaPedidos.getImagen());
                    this.detalles.setPrecio(listaPedidos.getPrecio());
                    this.detalles.setCantidad(listaPedidos.getCantidad());
                    JuegosController.misPedidos.add(detalles);
                }
                //introdusco la cuenta de banco que el usuario usa para comprar un juego
                for (Persona datos : datosPersona) {
                    this.persona.setCodigo(codigo_persona);
                    this.persona.setNombres(datos.getNombres());
                    this.persona.setApellidos(datos.getApellidos());
                    this.persona.setSexo(datos.getSexo());
                    this.persona.setFechaNacimiento(datos.getFechaNacimiento());
                }
                personaEJB.edit(persona);

                //Introducciendo datos a mi tabla juegosUsuarios
                for (JuegosUsuarios juegosCarrito : misJuegos) {
                    juegosEJB.create(juegosCarrito);//guarda mis juegos en mi bbdd
                }

                //Introducciendo datos a mi tabla detallesCompra
                for (DetallesCompra pedidosVarios : misPedidos) {
                    detallesEJB.create(pedidosVarios);//guarda mis pedidos en mi bbdd
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Compra realizada correctamente")); //para mostrar mensaje de registro exitoso
                //reinicio mis arrays una vez que el usuario haya pulsado el boton finalizar compra
                JuegosController.misJuegos.clear();
                JuegosController.pedidosList.clear();
                JuegosController.juegosList.clear();
                JuegosController.misPedidos.clear();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No has añadido ningun juego a tu cesta!"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al realizar la compra!"));
        }

    }

    public void agregarJuegoAlCarrito(String nombre, String imagen, int precio) {
        try {
            this.juego.setNombre(nombre);
            this.juego.setImagen(imagen);
            this.juego.setPrecio(precio);
            JuegosController.juegosList.add(this.juego);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego añadido a tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al añadir a tu cesta!"));
        }

    }

    public void agregarPedidoAlCarrito(String nombre, String imagen, int precio) {
        try {

            this.pedido.setProducto(nombre);
            this.pedido.setImagen(imagen);
            this.pedido.setPrecio(precio);
            

            JuegosController.pedidosList.add(this.pedido);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego añadido a tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al añadir a tu cesta!"));
        }

    }
    
    public void sumarUnProducto() {
        try {

            this.pedido.setCantidad(this.pedido.getCantidad()+1);
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "+1 producto")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al sumar!"));
        }

    }
    
    public void restarUnProducto() {
        try {
            if(this.pedido.getCantidad() <=1){
                this.pedido.setCantidad(1);
            }else{
                this.pedido.setCantidad(this.pedido.getCantidad()-1);
            }
            
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "-1 producto")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al restar"));
        }

    }

}
