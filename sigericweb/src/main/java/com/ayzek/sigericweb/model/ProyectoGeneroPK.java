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
public class ProyectoGeneroPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_genero")
    private int idGenero;

    public ProyectoGeneroPK() {
    }

    public ProyectoGeneroPK(int idProyecto, int idGenero) {
        this.idProyecto = idProyecto;
        this.idGenero = idGenero;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProyecto;
        hash += (int) idGenero;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoGeneroPK)) {
            return false;
        }
        ProyectoGeneroPK other = (ProyectoGeneroPK) object;
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        if (this.idGenero != other.idGenero) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.ProyectoGeneroPK[ idProyecto=" + idProyecto + ", idGenero=" + idGenero + " ]";
    }
    
}
