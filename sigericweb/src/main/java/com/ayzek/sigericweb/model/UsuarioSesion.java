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
@Table(name = "usuario_sesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioSesion.findAll", query = "SELECT u FROM UsuarioSesion u")
    , @NamedQuery(name = "UsuarioSesion.findByUsuario", query = "SELECT u FROM UsuarioSesion u WHERE u.usuarioSesionPK.usuario = :usuario")
    , @NamedQuery(name = "UsuarioSesion.findByIdSesion", query = "SELECT u FROM UsuarioSesion u WHERE u.usuarioSesionPK.idSesion = :idSesion")})
public class UsuarioSesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioSesionPK usuarioSesionPK;
    @JoinColumn(name = "id_sesion", referencedColumnName = "id_sesion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sesion sesion;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public UsuarioSesion() {
    }

    public UsuarioSesion(UsuarioSesionPK usuarioSesionPK) {
        this.usuarioSesionPK = usuarioSesionPK;
    }

    public UsuarioSesion(String usuario, int idSesion) {
        this.usuarioSesionPK = new UsuarioSesionPK(usuario, idSesion);
    }

    public UsuarioSesionPK getUsuarioSesionPK() {
        return usuarioSesionPK;
    }

    public void setUsuarioSesionPK(UsuarioSesionPK usuarioSesionPK) {
        this.usuarioSesionPK = usuarioSesionPK;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioSesionPK != null ? usuarioSesionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSesion)) {
            return false;
        }
        UsuarioSesion other = (UsuarioSesion) object;
        if ((this.usuarioSesionPK == null && other.usuarioSesionPK != null) || (this.usuarioSesionPK != null && !this.usuarioSesionPK.equals(other.usuarioSesionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.UsuarioSesion[ usuarioSesionPK=" + usuarioSesionPK + " ]";
    }
    
}
