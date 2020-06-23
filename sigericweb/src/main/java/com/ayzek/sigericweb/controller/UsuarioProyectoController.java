package com.ayzek.sigericweb.controller;

import com.ayzek.sigericweb.model.util.JsfUtil;
import com.ayzek.sigericweb.model.util.JsfUtil.PersistAction;
import com.ayzek.sigericweb.ejb.UsuarioProyectoFacade;
import com.ayzek.sigericweb.model.UsuarioProyecto;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("usuarioProyectoController")
@SessionScoped
public class UsuarioProyectoController implements Serializable {

    @EJB
    private com.ayzek.sigericweb.ejb.UsuarioProyectoFacade ejbFacade;
    private List<UsuarioProyecto> items = null;
    private UsuarioProyecto selected;

    public UsuarioProyectoController() {
    }

    public UsuarioProyecto getSelected() {
        return selected;
    }

    public void setSelected(UsuarioProyecto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getUsuarioProyectoPK().setIdProyecto(selected.getProyecto().getIdProyecto());
        selected.getUsuarioProyectoPK().setUsuario(selected.getUsuario1().getUsuario());
    }

    protected void initializeEmbeddableKey() {
        selected.setUsuarioProyectoPK(new com.ayzek.sigericweb.model.UsuarioProyectoPK());
    }

    private UsuarioProyectoFacade getFacade() {
        return ejbFacade;
    }

    public UsuarioProyecto prepareCreate() {
        selected = new UsuarioProyecto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioProyectoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioProyectoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioProyectoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsuarioProyecto> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UsuarioProyecto getUsuarioProyecto(com.ayzek.sigericweb.model.UsuarioProyectoPK id) {
        return getFacade().find(id);
    }

    public List<UsuarioProyecto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsuarioProyecto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsuarioProyecto.class)
    public static class UsuarioProyectoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioProyectoController controller = (UsuarioProyectoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioProyectoController");
            return controller.getUsuarioProyecto(getKey(value));
        }

        com.ayzek.sigericweb.model.UsuarioProyectoPK getKey(String value) {
            com.ayzek.sigericweb.model.UsuarioProyectoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.ayzek.sigericweb.model.UsuarioProyectoPK();
            key.setUsuario(values[0]);
            key.setIdProyecto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.ayzek.sigericweb.model.UsuarioProyectoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsuario());
            sb.append(SEPARATOR);
            sb.append(value.getIdProyecto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsuarioProyecto) {
                UsuarioProyecto o = (UsuarioProyecto) object;
                return getStringKey(o.getUsuarioProyectoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuarioProyecto.class.getName()});
                return null;
            }
        }

    }

}
