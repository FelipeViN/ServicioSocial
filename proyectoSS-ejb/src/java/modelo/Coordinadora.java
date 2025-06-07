/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
 * @author Matsu
 */
@Entity
@Table(name = "coordinadora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coordinadora.findAll", query = "SELECT c FROM Coordinadora c"),
    @NamedQuery(name = "Coordinadora.findByIdCoordinadora", query = "SELECT c FROM Coordinadora c WHERE c.idCoordinadora = :idCoordinadora"),
    @NamedQuery(name = "Coordinadora.findByCorreo", query = "SELECT c FROM Coordinadora c WHERE c.correo = :correo"),
    @NamedQuery(name = "Coordinadora.findByDireccion", query = "SELECT c FROM Coordinadora c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Coordinadora.findByNombreCompleto", query = "SELECT c FROM Coordinadora c WHERE c.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "Coordinadora.findByPassword", query = "SELECT c FROM Coordinadora c WHERE c.password = :password"),
    @NamedQuery(name = "Coordinadora.findByTelefono", query = "SELECT c FROM Coordinadora c WHERE c.telefono = :telefono")})
public class Coordinadora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_coordinadora")
    private Integer idCoordinadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "idCorrdinadora")
    private Collection<Documento> documentoCollection;
    @OneToMany(mappedBy = "idCoordinadora")
    private Collection<PrestadorServicio> prestadorServicioCollection;

    public Coordinadora() {
    }

    public Coordinadora(Integer idCoordinadora) {
        this.idCoordinadora = idCoordinadora;
    }

    public Coordinadora(Integer idCoordinadora, String correo, String direccion, String nombreCompleto, String password, String telefono) {
        this.idCoordinadora = idCoordinadora;
        this.correo = correo;
        this.direccion = direccion;
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.telefono = telefono;
    }

    public Integer getIdCoordinadora() {
        return idCoordinadora;
    }

    public void setIdCoordinadora(Integer idCoordinadora) {
        this.idCoordinadora = idCoordinadora;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @XmlTransient
    public Collection<PrestadorServicio> getPrestadorServicioCollection() {
        return prestadorServicioCollection;
    }

    public void setPrestadorServicioCollection(Collection<PrestadorServicio> prestadorServicioCollection) {
        this.prestadorServicioCollection = prestadorServicioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoordinadora != null ? idCoordinadora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coordinadora)) {
            return false;
        }
        Coordinadora other = (Coordinadora) object;
        if ((this.idCoordinadora == null && other.idCoordinadora != null) || (this.idCoordinadora != null && !this.idCoordinadora.equals(other.idCoordinadora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Coordinadora[ idCoordinadora=" + idCoordinadora + " ]";
    }
    
}
