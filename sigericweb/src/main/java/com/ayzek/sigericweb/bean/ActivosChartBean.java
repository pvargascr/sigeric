/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.bean;

import com.ayzek.sigericweb.ejb.ActivoFacade;
import com.ayzek.sigericweb.model.Activo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Varsandrey
 */

@Named(value = "beanGraficos")
@ViewScoped
public class ActivosChartBean implements Serializable{
    
@EJB
private ActivoFacade activoFacade;
private List <Activo> listado;
private PieChartModel chart;

public ActivosChartBean() {     
}
    
public void listar(){
    listado = activoFacade.listar();
    graficar();
  
}
@PostConstruct
public void graficar(){
    chart = new PieChartModel();
    for(Activo act: activoFacade.listar()){
            chart.set(act.getNombre(), act.getValorCompra());
        }

        chart.setLegendPosition("e");
        chart.setFill(true);
        chart.setShowDataLabels(true);
        chart.setDiameter(200);
}

    public List<Activo> getListado() {
        return listado;
    }

    public void setListado(List<Activo> listado) {
        this.listado = listado;
    }

    public PieChartModel getChart() {
        return chart;
    }

    public void setChart(PieChartModel chart) {
        this.chart = chart;
    }




}

