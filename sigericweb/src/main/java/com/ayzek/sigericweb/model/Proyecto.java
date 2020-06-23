/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Varsandrey
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto")
    , @NamedQuery(name = "Proyecto.findByNombre", query = "SELECT p FROM Proyecto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Proyecto.findByUrl", query = "SELECT p FROM Proyecto p WHERE p.url = :url")
    , @NamedQuery(name = "Proyecto.findByEstado", query = "SELECT p FROM Proyecto p WHERE p.estado = :estado")
    , @NamedQuery(name = "Proyecto.findByPrioridad", query = "SELECT p FROM Proyecto p WHERE p.prioridad = :prioridad")
    , @NamedQuery(name = "Proyecto.findByCosto", query = "SELECT p FROM Proyecto p WHERE p.costo = :costo")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prioridad")
    private int prioridad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private double costo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<ProyectoSesion> proyectoSesionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<UsuarioProyecto> usuarioProyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<ProyectoGenero> proyectoGeneroCollection;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyecto(Integer idProyecto, String nombre, String url, int estado, int prioridad, double costo) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.url = url;
        this.estado = estado;
        this.prioridad = prioridad;
        this.costo = costo;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @XmlTransient
    public Collection<ProyectoSesion> getProyectoSesionCollection() {
        return proyectoSesionCollection;
    }

    public void setProyectoSesionCollection(Collection<ProyectoSesion> proyectoSesionCollection) {
        this.proyectoSesionCollection = proyectoSesionCollection;
    }

    @XmlTransient
    public Collection<UsuarioProyecto> getUsuarioProyectoCollection() {
        return usuarioProyectoCollection;
    }

    public void setUsuarioProyectoCollection(Collection<UsuarioProyecto> usuarioProyectoCollection) {
        this.usuarioProyectoCollection = usuarioProyectoCollection;
    }

    @XmlTransient
    public Collection<ProyectoGenero> getProyectoGeneroCollection() {
        return proyectoGeneroCollection;
    }

    public void setProyectoGeneroCollection(Collection<ProyectoGenero> proyectoGeneroCollection) {
        this.proyectoGeneroCollection = proyectoGeneroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.Proyecto[ idProyecto=" + idProyecto + " ]";
    }
    
}
