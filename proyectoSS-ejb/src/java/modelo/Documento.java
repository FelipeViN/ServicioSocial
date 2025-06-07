/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matsu
 */
@Entity
@Table(name = "documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByEstadoValidacion", query = "SELECT d FROM Documento d WHERE d.estadoValidacion = :estadoValidacion"),
    @NamedQuery(name = "Documento.findByIdDocumento", query = "SELECT d FROM Documento d WHERE d.idDocumento = :idDocumento"),
    @NamedQuery(name = "Documento.findByFechaLimiteSubida", query = "SELECT d FROM Documento d WHERE d.fechaLimiteSubida = :fechaLimiteSubida"),
    @NamedQuery(name = "Documento.findByFechaSubidaDocumento", query = "SELECT d FROM Documento d WHERE d.fechaSubidaDocumento = :fechaSubidaDocumento"),
    @NamedQuery(name = "Documento.findByNombreDocumento", query = "SELECT d FROM Documento d WHERE d.nombreDocumento = :nombreDocumento"),
    @NamedQuery(name = "Documento.findByRutaDocumento", query = "SELECT d FROM Documento d WHERE d.rutaDocumento = :rutaDocumento")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_validacion")
    private int estadoValidacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_limite_subida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteSubida;
    @Column(name = "fecha_subida_documento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubidaDocumento;
    @Size(max = 255)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    @Size(max = 255)
    @Column(name = "ruta_documento")
    private String rutaDocumento;
    @JoinColumn(name = "id_calificacion", referencedColumnName = "id_calificacion")
    @OneToOne
    private Calificacion idCalificacion;
    @JoinColumn(name = "id_corrdinadora", referencedColumnName = "id_coordinadora")
    @ManyToOne
    private Coordinadora idCorrdinadora;
    @JoinColumn(name = "id_prestador", referencedColumnName = "id_prestador")
    @ManyToOne
    private PrestadorServicio idPrestador;

    public Documento() {
    }

    public Documento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Documento(Integer idDocumento, int estadoValidacion, Date fechaLimiteSubida) {
        this.idDocumento = idDocumento;
        this.estadoValidacion = estadoValidacion;
        this.fechaLimiteSubida = fechaLimiteSubida;
    }

    public int getEstadoValidacion() {
        return estadoValidacion;
    }

    public void setEstadoValidacion(int estadoValidacion) {
        this.estadoValidacion = estadoValidacion;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Date getFechaLimiteSubida() {
        return fechaLimiteSubida;
    }

    public void setFechaLimiteSubida(Date fechaLimiteSubida) {
        this.fechaLimiteSubida = fechaLimiteSubida;
    }

    public Date getFechaSubidaDocumento() {
        return fechaSubidaDocumento;
    }

    public void setFechaSubidaDocumento(Date fechaSubidaDocumento) {
        this.fechaSubidaDocumento = fechaSubidaDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public Calificacion getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Calificacion idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Coordinadora getIdCorrdinadora() {
        return idCorrdinadora;
    }

    public void setIdCorrdinadora(Coordinadora idCorrdinadora) {
        this.idCorrdinadora = idCorrdinadora;
    }

    public PrestadorServicio getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(PrestadorServicio idPrestador) {
        this.idPrestador = idPrestador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Documento[ idDocumento=" + idDocumento + " ]";
    }
    
}
