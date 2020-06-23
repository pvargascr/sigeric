/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayzek.sigericweb.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Varsandrey
 */
@Entity
@Table(name = "activo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activo.findAll", query = "SELECT a FROM Activo a")
    , @NamedQuery(name = "Activo.findByIdActivo", query = "SELECT a FROM Activo a WHERE a.idActivo = :idActivo")
    , @NamedQuery(name = "Activo.findByNombre", query = "SELECT a FROM Activo a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Activo.findByDetalle", query = "SELECT a FROM Activo a WHERE a.detalle = :detalle")
    , @NamedQuery(name = "Activo.findByFechaCompra", query = "SELECT a FROM Activo a WHERE a.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "Activo.findByValorCompra", query = "SELECT a FROM Activo a WHERE a.valorCompra = :valorCompra")
    , @NamedQuery(name = "Activo.findByValorResidual", query = "SELECT a FROM Activo a WHERE a.valorResidual = :valorResidual")
    , @NamedQuery(name = "Activo.findByVidaUtil", query = "SELECT a FROM Activo a WHERE a.vidaUtil = :vidaUtil")
    , @NamedQuery(name = "Activo.findByEstado", query = "SELECT a FROM Activo a WHERE a.estado = :estado")})
public class Activo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activo")
    private Integer idActivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_compra")
    private double valorCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_residual")
    private double valorResidual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vida_util")
    private int vidaUtil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    @ManyToOne(optional = false)
    private Marca idMarca;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;

    public Activo() {
    }

    public Activo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    public Activo(Integer idActivo, String nombre, String detalle, Date fechaCompra, double valorCompra, double valorResidual, int vidaUtil, String estado) {
        this.idActivo = idActivo;
        this.nombre = nombre;
        this.detalle = detalle;
        this.fechaCompra = fechaCompra;
        this.valorCompra = valorCompra;
        this.valorResidual = valorResidual;
        this.vidaUtil = vidaUtil;
        this.estado = estado;
    }

    public Integer getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(double valorResidual) {
        this.valorResidual = valorResidual;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivo != null ? idActivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activo)) {
            return false;
        }
        Activo other = (Activo) object;
        if ((this.idActivo == null && other.idActivo != null) || (this.idActivo != null && !this.idActivo.equals(other.idActivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ayzek.sigericweb.model.Activo[ idActivo=" + idActivo + " ]";
    }
    
}
