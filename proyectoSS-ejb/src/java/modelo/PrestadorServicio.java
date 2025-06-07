/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matsu
 */
@Entity
@Table(name = "prestador_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestadorServicio.findAll", query = "SELECT p FROM PrestadorServicio p"),
    @NamedQuery(name = "PrestadorServicio.findByFechaNac", query = "SELECT p FROM PrestadorServicio p WHERE p.fechaNac = :fechaNac"),
    @NamedQuery(name = "PrestadorServicio.findByIdPrestador", query = "SELECT p FROM PrestadorServicio p WHERE p.idPrestador = :idPrestador"),
    @NamedQuery(name = "PrestadorServicio.findByNumeroControl", query = "SELECT p FROM PrestadorServicio p WHERE p.numeroControl = :numeroControl"),
    @NamedQuery(name = "PrestadorServicio.findByPromedioFinal", query = "SELECT p FROM PrestadorServicio p WHERE p.promedioFinal = :promedioFinal"),
    @NamedQuery(name = "PrestadorServicio.findByApellidoMaterno", query = "SELECT p FROM PrestadorServicio p WHERE p.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "PrestadorServicio.findByApellidoPaterno", query = "SELECT p FROM PrestadorServicio p WHERE p.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "PrestadorServicio.findByCarrera", query = "SELECT p FROM PrestadorServicio p WHERE p.carrera = :carrera"),
    @NamedQuery(name = "PrestadorServicio.findByDireccion", query = "SELECT p FROM PrestadorServicio p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PrestadorServicio.findByEmail", query = "SELECT p FROM PrestadorServicio p WHERE p.email = :email"),
    @NamedQuery(name = "PrestadorServicio.findByNombre", query = "SELECT p FROM PrestadorServicio p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PrestadorServicio.findByPassword", query = "SELECT p FROM PrestadorServicio p WHERE p.password = :password"),
    @NamedQuery(name = "PrestadorServicio.findByTelefono", query = "SELECT p FROM PrestadorServicio p WHERE p.telefono = :telefono")})
public class PrestadorServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prestador")
    private Integer idPrestador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_control")
    private int numeroControl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "promedio_final")
    private double promedioFinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "carrera")
    private String carrera;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "direccion")
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(mappedBy = "idPrestador")
    private Collection<Calificacion> calificacionCollection;
    @OneToMany(mappedBy = "idPrestador")
    private Collection<AgendaCita> agendaCitaCollection;
    @OneToMany(mappedBy = "idPrestador")
    private Collection<Documento> documentoCollection;
    @JoinColumn(name = "id_coordinadora", referencedColumnName = "id_coordinadora")
    @ManyToOne
    private Coordinadora idCoordinadora;

    public PrestadorServicio() {
    }

    public PrestadorServicio(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public PrestadorServicio(Integer idPrestador, Date fechaNac, int numeroControl, double promedioFinal, String apellidoMaterno, String apellidoPaterno, String carrera, String direccion, String email, String nombre, String password, String telefono) {
        this.idPrestador = idPrestador;
        this.fechaNac = fechaNac;
        this.numeroControl = numeroControl;
        this.promedioFinal = promedioFinal;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.carrera = carrera;
        this.direccion = direccion;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Integer getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public int getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(int numeroControl) {
        this.numeroControl = numeroControl;
    }

    public double getPromedioFinal() {
        return promedioFinal;
    }

    public void setPromedioFinal(double promedioFinal) {
        this.promedioFinal = promedioFinal;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public Collection<Calificacion> getCalificacionCollection() {
        return calificacionCollection;
    }

    public void setCalificacionCollection(Collection<Calificacion> calificacionCollection) {
        this.calificacionCollection = calificacionCollection;
    }

    @XmlTransient
    public Collection<AgendaCita> getAgendaCitaCollection() {
        return agendaCitaCollection;
    }

    public void setAgendaCitaCollection(Collection<AgendaCita> agendaCitaCollection) {
        this.agendaCitaCollection = agendaCitaCollection;
    }

    @XmlTransient
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    public Coordinadora getIdCoordinadora() {
        return idCoordinadora;
    }

    public void setIdCoordinadora(Coordinadora idCoordinadora) {
        this.idCoordinadora = idCoordinadora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrestador != null ? idPrestador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestadorServicio)) {
            return false;
        }
        PrestadorServicio other = (PrestadorServicio) object;
        if ((this.idPrestador == null && other.idPrestador != null) || (this.idPrestador != null && !this.idPrestador.equals(other.idPrestador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PrestadorServicio[ idPrestador=" + idPrestador + " ]";
    }
    
}
