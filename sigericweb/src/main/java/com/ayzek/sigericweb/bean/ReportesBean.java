/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.bean;

import com.ayzek.sigericweb.ejb.ActivoFacade;
import com.ayzek.sigericweb.ejb.BeanAutenticacionFacade;
import com.ayzek.sigericweb.model.Activo;
import com.ayzek.sigericweb.model.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Varsandrey
 */
@Named(value = "beanReportes")
@ViewScoped
public class ReportesBean implements Serializable{
    
private ActivoFacade activoFacade;
private List <Activo> listado;
private BeanAutenticacionFacade BAF;
HttpSession session = Util.getSession();



public ReportesBean() { }

   public List<Activo> getListado() {
        return listado;
    }

    public void setListado(List<Activo> listado) {
        this.listado = listado;
    }
public void exportarPDF() throws JRException, IOException{
    Map<String,Object> parametros= new HashMap<String, Object>();
    parametros.put("txtUsuario", session.getAttribute("usuario_nombre").toString());
    File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("ActRep.jasper"));
    JasperPrint jasperprint;
    jasperprint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.getListado()));

    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.addHeader("Content-Disposition", "attachment; filename=Reporte_Activos.pdf");
    ServletOutputStream stream = response.getOutputStream();
    
    JasperExportManager.exportReportToPdfStream(jasperprint, stream);
    stream.flush();
    stream.close();
    FacesContext.getCurrentInstance().responseComplete();

}

}
    
    

