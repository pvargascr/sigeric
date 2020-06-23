package com.ayzek.sigericweb.controller;

import com.ayzek.sigericweb.model.util.JsfUtil;
import com.ayzek.sigericweb.model.util.JsfUtil.PersistAction;
import com.ayzek.sigericweb.ejb.ProyectoSesionFacade;
import com.ayzek.sigericweb.model.ProyectoSesion;

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

@Named("proyectoSesionController")
@SessionScoped
public class ProyectoSesionController implements Serializable {

    @EJB
    private com.ayzek.sigericweb.ejb.ProyectoSesionFacade ejbFacade;
    private List<ProyectoSesion> items = null;
    private ProyectoSesion selected;

    public ProyectoSesionController() {
    }

    public ProyectoSesion getSelected() {
        return selected;
    }

    public void setSelected(ProyectoSesion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getProyectoSesionPK().setIdProyecto(selected.getProyecto().getIdProyecto());
        selected.getProyectoSesionPK().setIdSesion(selected.getSesion().getIdSesion());
    }

    protected void initializeEmbeddableKey() {
        selected.setProyectoSesionPK(new com.ayzek.sigericweb.model.ProyectoSesionPK());
    }

    private ProyectoSesionFacade getFacade() {
        return ejbFacade;
    }

    public ProyectoSesion prepareCreate() {
        selected = new ProyectoSesion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProyectoSesionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProyectoSesionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProyectoSesionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ProyectoSesion> getItems() {
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

    public ProyectoSesion getProyectoSesion(com.ayzek.sigericweb.model.ProyectoSesionPK id) {
        return getFacade().find(id);
    }

    public List<ProyectoSesion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ProyectoSesion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ProyectoSesion.class)
    public static class ProyectoSesionControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProyectoSesionController controller = (ProyectoSesionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proyectoSesionController");
            return controller.getProyectoSesion(getKey(value));
        }

        com.ayzek.sigericweb.model.ProyectoSesionPK getKey(String value) {
            com.ayzek.sigericweb.model.ProyectoSesionPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.ayzek.sigericweb.model.ProyectoSesionPK();
            key.setIdProyecto(Integer.parseInt(values[0]));
            key.setIdSesion(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.ayzek.sigericweb.model.ProyectoSesionPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdProyecto());
            sb.append(SEPARATOR);
            sb.append(value.getIdSesion());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProyectoSesion) {
                ProyectoSesion o = (ProyectoSesion) object;
                return getStringKey(o.getProyectoSesionPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProyectoSesion.class.getName()});
                return null;
            }
        }

    }

}
