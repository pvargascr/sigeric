package com.ayzek.sigericweb.controller;

import com.ayzek.sigericweb.model.util.JsfUtil;
import com.ayzek.sigericweb.model.util.JsfUtil.PersistAction;
import com.ayzek.sigericweb.ejb.ProyectoGeneroFacade;
import com.ayzek.sigericweb.model.ProyectoGenero;

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

@Named("proyectoGeneroController")
@SessionScoped
public class ProyectoGeneroController implements Serializable {

    @EJB
    private com.ayzek.sigericweb.ejb.ProyectoGeneroFacade ejbFacade;
    private List<ProyectoGenero> items = null;
    private ProyectoGenero selected;

    public ProyectoGeneroController() {
    }

    public ProyectoGenero getSelected() {
        return selected;
    }

    public void setSelected(ProyectoGenero selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getProyectoGeneroPK().setIdProyecto(selected.getProyecto().getIdProyecto());
        selected.getProyectoGeneroPK().setIdGenero(selected.getGenero().getIdGenero());
    }

    protected void initializeEmbeddableKey() {
        selected.setProyectoGeneroPK(new com.ayzek.sigericweb.model.ProyectoGeneroPK());
    }

    private ProyectoGeneroFacade getFacade() {
        return ejbFacade;
    }

    public ProyectoGenero prepareCreate() {
        selected = new ProyectoGenero();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProyectoGeneroCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProyectoGeneroUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProyectoGeneroDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ProyectoGenero> getItems() {
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

    public ProyectoGenero getProyectoGenero(com.ayzek.sigericweb.model.ProyectoGeneroPK id) {
        return getFacade().find(id);
    }

    public List<ProyectoGenero> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ProyectoGenero> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ProyectoGenero.class)
    public static class ProyectoGeneroControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProyectoGeneroController controller = (ProyectoGeneroController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proyectoGeneroController");
            return controller.getProyectoGenero(getKey(value));
        }

        com.ayzek.sigericweb.model.ProyectoGeneroPK getKey(String value) {
            com.ayzek.sigericweb.model.ProyectoGeneroPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.ayzek.sigericweb.model.ProyectoGeneroPK();
            key.setIdProyecto(Integer.parseInt(values[0]));
            key.setIdGenero(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.ayzek.sigericweb.model.ProyectoGeneroPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdProyecto());
            sb.append(SEPARATOR);
            sb.append(value.getIdGenero());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProyectoGenero) {
                ProyectoGenero o = (ProyectoGenero) object;
                return getStringKey(o.getProyectoGeneroPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProyectoGenero.class.getName()});
                return null;
            }
        }

    }

}
