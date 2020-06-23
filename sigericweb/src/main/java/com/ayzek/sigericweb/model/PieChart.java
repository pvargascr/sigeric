/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.model;
 
import javax.annotation.PostConstruct;  
import java.io.Serializable;  
import javax.faces.bean.ManagedBean;  
import org.primefaces.model.chart.PieChartModel;  
@ManagedBean  
public class PieChart implements Serializable {  
private PieChartModel pieModel;  
@PostConstruct  
public void init() {  
createPieModels();  
}  
public PieChartModel getPieModel() {  
return pieModel;  
}  
private void createPieModels() {  
createPieModel();  
}  
private void createPieModel() {  
pieModel = new PieChartModel();  
pieModel.set("Mercedess", 700);  
pieModel.set("BMW", 300);  
pieModel.set("Volvo", 400);  
pieModel.setTitle("Car Brands");  
pieModel.setLegendPosition("c");  
}  
}  