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
import javax.validation.constraints.Size;

/**
 *
 * @author Varsandrey
 */
@Embeddable
public class UsuarioSesionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sesion")
    private int idSesion;

    public UsuarioSesionPK() {
    }

    public UsuarioSesionPK(String usuario, int idSesion) {
        this.usuario = usuario;
        this.idSesion = idSesion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        hash += (usuario != null ? usuario.hashCode() : 0);
        hash += (int) idSesion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioSesionPK)) {
            return false;
        }
        UsuarioSesionPK other = (UsuarioSesionPK) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        if (this.idSesion != other.idSesion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.UsuarioSesionPK[ usuario=" + usuario + ", idSesion=" + idSesion + " ]";
    }
    
}
