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
@Table(name = "proyecto_genero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyectoGenero.findAll", query = "SELECT p FROM ProyectoGenero p")
    , @NamedQuery(name = "ProyectoGenero.findByIdProyecto", query = "SELECT p FROM ProyectoGenero p WHERE p.proyectoGeneroPK.idProyecto = :idProyecto")
    , @NamedQuery(name = "ProyectoGenero.findByIdGenero", query = "SELECT p FROM ProyectoGenero p WHERE p.proyectoGeneroPK.idGenero = :idGenero")})
public class ProyectoGenero implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProyectoGeneroPK proyectoGeneroPK;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genero genero;

    public ProyectoGenero() {
    }

    public ProyectoGenero(ProyectoGeneroPK proyectoGeneroPK) {
        this.proyectoGeneroPK = proyectoGeneroPK;
    }

    public ProyectoGenero(int idProyecto, int idGenero) {
        this.proyectoGeneroPK = new ProyectoGeneroPK(idProyecto, idGenero);
    }

    public ProyectoGeneroPK getProyectoGeneroPK() {
        return proyectoGeneroPK;
    }

    public void setProyectoGeneroPK(ProyectoGeneroPK proyectoGeneroPK) {
        this.proyectoGeneroPK = proyectoGeneroPK;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyectoGeneroPK != null ? proyectoGeneroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoGenero)) {
            return false;
        }
        ProyectoGenero other = (ProyectoGenero) object;
        if ((this.proyectoGeneroPK == null && other.proyectoGeneroPK != null) || (this.proyectoGeneroPK != null && !this.proyectoGeneroPK.equals(other.proyectoGeneroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.ProyectoGenero[ proyectoGeneroPK=" + proyectoGeneroPK + " ]";
    }
    
}
