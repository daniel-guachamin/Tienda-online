
package com.danielta.controller;

import com.danielta.ejb.DetallesCompraFacadeLocal;
import com.danielta.model.DetallesCompra;
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
    Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    @PostConstruct
    public void init() {
        misPedidos = detallesUsuariosEJB.encuentraPedidosUsuario(us.getCodigo()); //Utilizo mi metodo encuentraPedidosUsuario para guardar los pedidos de mi usuario en un array List
    }

}
