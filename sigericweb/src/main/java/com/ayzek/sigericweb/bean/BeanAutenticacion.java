/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.bean;
import com.ayzek.sigericweb.ejb.BeanAutenticacionFacade;
import com.ayzek.sigericweb.ejb.BeanAutenticacionFacade;
import com.ayzek.sigericweb.model.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ViewScoped
@Named("beanAutenticacion")
public class BeanAutenticacion implements Serializable{
    @EJB
    private BeanAutenticacionFacade facade;
    
    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;
    private String userType;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String loginProject() {
        ArrayList<String> result_1 = facade.validateUser(uname, password);
        if (result_1.get(0).equals("1") && result_1.get(4).equals("administrador")) {
            this.saveUserSession(result_1);
            return this.redirectUser("Info","Bien!","Autenticado correctamente!", "/Dashboard/index.xhtml?faces-redirect=true");
        } else if (result_1.get(0).equals("1") && result_1.get(4).equals("usuario")){
        this.saveUserSession(result_1);
            return this.redirectUser("Info","Bien!","Autenticado correctamente!", "/Dashboard/dashboardUsuario.xhtml?faces-redirect=true");
        }else {
            return this.redirectUser("Warn","Incorrecto!","Credenciales invalidas!", "/landing.xhtml?faces-redirect=true");
        }
    }
    
        public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return this.redirectUser("Info","Bien!", "Ha cerrado la sesión exitosamente", "/landing.xhtml?faces-redirect=true");
    }
        
        public void saveUserSession(ArrayList<String> result) {
        HttpSession session = Util.getSession();
        session.setAttribute("usuario_usuario", result.get(1));
        session.setAttribute("usuario_nombre", result.get(2));
        session.setAttribute("usuario_correo", result.get(3));
        session.setAttribute("usuario_tipo", result.get(4));
    }
        
        public String redirectUser(String severity, String message, String detail, String return_page) {
        switch (severity) {
            case "Info":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message,
                        detail));
                break;
            case "Warn":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        message,
                        detail));
                break;
            case "Error":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        message,
                        detail));
                break;
            case "Fatal":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        message,
                        detail));
                break;
        }
        return return_page;
    }

    public boolean haySessionActiva(){
        boolean respuesta = false;
        try{
            HttpSession session = Util.getSession();
            if(!session.getAttribute("usuario_id").toString().isEmpty()){
                respuesta = true;
            }
        }catch (Exception e){
            System.out.println("EXCEPTION FH4RHG => "+e.getMessage());
        }

        return respuesta;
    }
}
