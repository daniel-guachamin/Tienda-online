package com.danielta.controller;

import com.danielta.ejb.MenuFacadeLocal;
import com.danielta.model.Menu;
import com.danielta.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@Named
@SessionScoped //los datos se mantendrán vivos hasta que el cliente cierre la sesión.
public class MenuController implements Serializable {

    @EJB
    private MenuFacadeLocal EJBMenu;
    private List<Menu> lista;
    private MenuModel model;

    //traemos la lista de menu
    @PostConstruct
    public void init() {
        this.listarMenus();
        model = new DefaultMenuModel();
        this.establecerPermisos();
    }

    public void listarMenus() {
        try {
            lista = EJBMenu.findAll();
        } catch (Exception e) {
            //mensaje jsf
        }
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void establecerPermisos() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        for (Menu m : lista) {
            if (m.getTipoUsuario().equals(us.getTipo())) {
                DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                item.setUrl(m.getUrl());
                model.addElement(item);
            }
        }
    }

    public void cerrarSesion() {
        JuegosController.misJuegos.clear();
        JuegosController.juegosList.clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); //cierro sesion
    }

    public String mostrarUsuarioLogeado() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getUsuario();
    }
}
