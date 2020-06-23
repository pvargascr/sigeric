/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Varsandrey
 */
@Entity
@Table(name = "sesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sesion.findAll", query = "SELECT s FROM Sesion s")
    , @NamedQuery(name = "Sesion.findByIdSesion", query = "SELECT s FROM Sesion s WHERE s.idSesion = :idSesion")
    , @NamedQuery(name = "Sesion.findByFecha", query = "SELECT s FROM Sesion s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "Sesion.findByDuracion", query = "SELECT s FROM Sesion s WHERE s.duracion = :duracion")})
public class Sesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sesion")
    private Integer idSesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private int duracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sesion")
    private Collection<ProyectoSesion> proyectoSesionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sesion")
    private Collection<UsuarioSesion> usuarioSesionCollection;

    public Sesion() {
    }

    public Sesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public Sesion(Integer idSesion, Date fecha, int duracion) {
        this.idSesion = idSesion;
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @XmlTransient
    public Collection<ProyectoSesion> getProyectoSesionCollection() {
        return proyectoSesionCollection;
    }

    public void setProyectoSesionCollection(Collection<ProyectoSesion> proyectoSesionCollection) {
        this.proyectoSesionCollection = proyectoSesionCollection;
    }

    @XmlTransient
    public Collection<UsuarioSesion> getUsuarioSesionCollection() {
        return usuarioSesionCollection;
    }

    public void setUsuarioSesionCollection(Collection<UsuarioSesion> usuarioSesionCollection) {
        this.usuarioSesionCollection = usuarioSesionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSesion != null ? idSesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sesion)) {
            return false;
        }
        Sesion other = (Sesion) object;
        if ((this.idSesion == null && other.idSesion != null) || (this.idSesion != null && !this.idSesion.equals(other.idSesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.Sesion[ idSesion=" + idSesion + " ]";
    }
    
}
