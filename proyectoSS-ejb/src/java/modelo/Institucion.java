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
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i"),
    @NamedQuery(name = "Institucion.findByIdInstitucion", query = "SELECT i FROM Institucion i WHERE i.idInstitucion = :idInstitucion"),
    @NamedQuery(name = "Institucion.findByDireccion", query = "SELECT i FROM Institucion i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "Institucion.findByEmailInstitucion", query = "SELECT i FROM Institucion i WHERE i.emailInstitucion = :emailInstitucion"),
    @NamedQuery(name = "Institucion.findByNombreInstitucion", query = "SELECT i FROM Institucion i WHERE i.nombreInstitucion = :nombreInstitucion"),
    @NamedQuery(name = "Institucion.findByPassword", query = "SELECT i FROM Institucion i WHERE i.password = :password"),
    @NamedQuery(name = "Institucion.findByTelefono", query = "SELECT i FROM Institucion i WHERE i.telefono = :telefono"),
    @NamedQuery(name = "Institucion.findByUsuarioInstitucion", query = "SELECT i FROM Institucion i WHERE i.usuarioInstitucion = :usuarioInstitucion")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_institucion")
    private Integer idInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email_institucion")
    private String emailInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usuario_institucion")
    private String usuarioInstitucion;
    @OneToMany(mappedBy = "idInstitucion")
    private Collection<Vacante> vacanteCollection;
    @OneToMany(mappedBy = "idInstitucion")
    private Collection<AgendaCita> agendaCitaCollection;

    public Institucion() {
    }

    public Institucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Institucion(Integer idInstitucion, String direccion, String emailInstitucion, String nombreInstitucion, String password, String telefono, String usuarioInstitucion) {
        this.idInstitucion = idInstitucion;
        this.direccion = direccion;
        this.emailInstitucion = emailInstitucion;
        this.nombreInstitucion = nombreInstitucion;
        this.password = password;
        this.telefono = telefono;
        this.usuarioInstitucion = usuarioInstitucion;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailInstitucion() {
        return emailInstitucion;
    }

    public void setEmailInstitucion(String emailInstitucion) {
        this.emailInstitucion = emailInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
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

    public String getUsuarioInstitucion() {
        return usuarioInstitucion;
    }

    public void setUsuarioInstitucion(String usuarioInstitucion) {
        this.usuarioInstitucion = usuarioInstitucion;
    }

    @XmlTransient
    public Collection<Vacante> getVacanteCollection() {
        return vacanteCollection;
    }

    public void setVacanteCollection(Collection<Vacante> vacanteCollection) {
        this.vacanteCollection = vacanteCollection;
    }

    @XmlTransient
    public Collection<AgendaCita> getAgendaCitaCollection() {
        return agendaCitaCollection;
    }

    public void setAgendaCitaCollection(Collection<AgendaCita> agendaCitaCollection) {
        this.agendaCitaCollection = agendaCitaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstitucion != null ? idInstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idInstitucion == null && other.idInstitucion != null) || (this.idInstitucion != null && !this.idInstitucion.equals(other.idInstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Institucion[ idInstitucion=" + idInstitucion + " ]";
    }
    
}
