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
@Table(name = "usuario_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioProyecto.findAll", query = "SELECT u FROM UsuarioProyecto u")
    , @NamedQuery(name = "UsuarioProyecto.findByUsuario", query = "SELECT u FROM UsuarioProyecto u WHERE u.usuarioProyectoPK.usuario = :usuario")
    , @NamedQuery(name = "UsuarioProyecto.findByIdProyecto", query = "SELECT u FROM UsuarioProyecto u WHERE u.usuarioProyectoPK.idProyecto = :idProyecto")})
public class UsuarioProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioProyectoPK usuarioProyectoPK;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public UsuarioProyecto() {
    }

    public UsuarioProyecto(UsuarioProyectoPK usuarioProyectoPK) {
        this.usuarioProyectoPK = usuarioProyectoPK;
    }

    public UsuarioProyecto(String usuario, int idProyecto) {
        this.usuarioProyectoPK = new UsuarioProyectoPK(usuario, idProyecto);
    }

    public UsuarioProyectoPK getUsuarioProyectoPK() {
        return usuarioProyectoPK;
    }

    public void setUsuarioProyectoPK(UsuarioProyectoPK usuarioProyectoPK) {
        this.usuarioProyectoPK = usuarioProyectoPK;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
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
        hash += (usuarioProyectoPK != null ? usuarioProyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioProyecto)) {
            return false;
        }
        UsuarioProyecto other = (UsuarioProyecto) object;
        if ((this.usuarioProyectoPK == null && other.usuarioProyectoPK != null) || (this.usuarioProyectoPK != null && !this.usuarioProyectoPK.equals(other.usuarioProyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.UsuarioProyecto[ usuarioProyectoPK=" + usuarioProyectoPK + " ]";
    }
    
}
