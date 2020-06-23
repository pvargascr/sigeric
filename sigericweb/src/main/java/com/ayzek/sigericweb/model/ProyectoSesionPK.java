/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Varsandrey
 */
@Embeddable
public class ProyectoSesionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sesion")
    private int idSesion;

    public ProyectoSesionPK() {
    }

    public ProyectoSesionPK(int idProyecto, int idSesion) {
        this.idProyecto = idProyecto;
        this.idSesion = idSesion;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProyecto;
        hash += (int) idSesion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoSesionPK)) {
            return false;
        }
        ProyectoSesionPK other = (ProyectoSesionPK) object;
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        if (this.idSesion != other.idSesion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.ProyectoSesionPK[ idProyecto=" + idProyecto + ", idSesion=" + idSesion + " ]";
    }
    
}
