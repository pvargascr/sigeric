/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Varsandrey
 */
@Entity
@Table(name = "proyecto_sesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyectoSesion.findAll", query = "SELECT p FROM ProyectoSesion p")
    , @NamedQuery(name = "ProyectoSesion.findByIdProyecto", query = "SELECT p FROM ProyectoSesion p WHERE p.proyectoSesionPK.idProyecto = :idProyecto")
    , @NamedQuery(name = "ProyectoSesion.findByIdSesion", query = "SELECT p FROM ProyectoSesion p WHERE p.proyectoSesionPK.idSesion = :idSesion")})
public class ProyectoSesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProyectoSesionPK proyectoSesionPK;
    @JoinColumn(name = "id_sesion", referencedColumnName = "id_sesion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sesion sesion;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public ProyectoSesion() {
    }

    public ProyectoSesion(ProyectoSesionPK proyectoSesionPK) {
        this.proyectoSesionPK = proyectoSesionPK;
    }

    public ProyectoSesion(int idProyecto, int idSesion) {
        this.proyectoSesionPK = new ProyectoSesionPK(idProyecto, idSesion);
    }

    public ProyectoSesionPK getProyectoSesionPK() {
        return proyectoSesionPK;
    }

    public void setProyectoSesionPK(ProyectoSesionPK proyectoSesionPK) {
        this.proyectoSesionPK = proyectoSesionPK;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyectoSesionPK != null ? proyectoSesionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoSesion)) {
            return false;
        }
        ProyectoSesion other = (ProyectoSesion) object;
        if ((this.proyectoSesionPK == null && other.proyectoSesionPK != null) || (this.proyectoSesionPK != null && !this.proyectoSesionPK.equals(other.proyectoSesionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.ProyectoSesion[ proyectoSesionPK=" + proyectoSesionPK + " ]";
    }
    
}
