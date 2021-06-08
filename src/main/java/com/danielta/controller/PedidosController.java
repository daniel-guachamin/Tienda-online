
package com.danielta.controller;

import com.danielta.ejb.DetallesCompraFacadeLocal;
import com.danielta.ejb.JuegosUsuariosFacadeLocal;
import com.danielta.model.DetallesCompra;
import com.danielta.model.JuegosUsuarios;
import com.danielta.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class PedidosController  implements Serializable{
    @EJB
    private DetallesCompraFacadeLocal detallesUsuariosEJB; //
    
    private List<DetallesCompra> misPedidos; //variable que me permitira mostrar los juegos comprados del usuario
    private DetallesCompra detalles;
    private int codigo_persona;

    public List<DetallesCompra> getMisPedidos() {
        return misPedidos;
    }

    public void setMisPedidos(List<DetallesCompra> misPedidos) {
        this.misPedidos = misPedidos;
    }

    public DetallesCompra getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallesCompra detalles) {
        this.detalles = detalles;
    }
    
    @PostConstruct
    public void init() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        codigo_persona=us.getCodigo().getCodigo();//guardo una variable 
        misPedidos = detallesUsuariosEJB.encuentraPedidosUsuario(codigo_persona); //Utilizo mi metodo encuentraPedidosUsuario para guardar los pedidos de mi usuario en un array List
    }

}
