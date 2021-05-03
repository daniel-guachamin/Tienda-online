package com.danielta.controller;

import com.danielta.model.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PlantillaController implements Serializable{ //añadir Serializable para evitar tener problemas
    //metodo que verifica si mi sesion existe
    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario"); //acedo a mi variable que he guardado en session 

            if (us == null) { //si la session no existe me rederidije a permisos
                context.getExternalContext().redirect("./../permisos.xhtml");
            }
        } catch (Exception e) {
            //log para guardar algun registro de un error
        }
    }
}
