
package com.danielta.controller;

import com.danielta.ejb.DetallesCompraFacadeLocal;
import com.danielta.model.DetallesCompra;
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
public class PedidosController  implements Serializable{
    @EJB
    private DetallesCompraFacadeLocal detallesEJB;
    
    @Inject
    private DetallesCompra detalles;
    
    static List<DetallesCompra> misPedidos = new ArrayList();

    public List<DetallesCompra> getMisPedidos() {
        return misPedidos;
    }
    
    private int codigo_persona;

    public void setMisPedidos(List<DetallesCompra> misPedidos) {
        PedidosController.misPedidos = misPedidos;
    }
    
    @PostConstruct
    public void init() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        codigo_persona = us.getCodigo().getCodigo();//guardo una variable 
    }
    
    public void eliminarJuego(DetallesCompra borrar) {

        try {

            PedidosController.misPedidos.remove(borrar);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Juego eliminado de tu cesta")); //para mostrar mensaje de registro exitoso
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al eliminar!"));
        }
    }
    
    
    
}
