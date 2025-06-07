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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matsu
 */
@Entity
@Table(name = "agenda_cita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgendaCita.findAll", query = "SELECT a FROM AgendaCita a"),
    @NamedQuery(name = "AgendaCita.findByEstadoCita", query = "SELECT a FROM AgendaCita a WHERE a.estadoCita = :estadoCita"),
    @NamedQuery(name = "AgendaCita.findByFechaCita", query = "SELECT a FROM AgendaCita a WHERE a.fechaCita = :fechaCita"),
    @NamedQuery(name = "AgendaCita.findByHoraCita", query = "SELECT a FROM AgendaCita a WHERE a.horaCita = :horaCita"),
    @NamedQuery(name = "AgendaCita.findByIdCita", query = "SELECT a FROM AgendaCita a WHERE a.idCita = :idCita")})
public class AgendaCita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_cita")
    private int estadoCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cita")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_cita")
    @Temporal(TemporalType.TIME)
    private Date horaCita;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cita")
    private Integer idCita;
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion")
    @ManyToOne
    private Institucion idInstitucion;
    @JoinColumn(name = "id_prestador", referencedColumnName = "id_prestador")
    @ManyToOne
    private PrestadorServicio idPrestador;

    public AgendaCita() {
    }

    public AgendaCita(Integer idCita) {
        this.idCita = idCita;
    }

    public AgendaCita(Integer idCita, int estadoCita, Date fechaCita, Date horaCita) {
        this.idCita = idCita;
        this.estadoCita = estadoCita;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
    }

    public int getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(int estadoCita) {
        this.estadoCita = estadoCita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(Date horaCita) {
        this.horaCita = horaCita;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public Institucion getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Institucion idInstitucion) {
        this.idInstitucion = idInstitucion;
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
        hash += (idCita != null ? idCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgendaCita)) {
            return false;
        }
        AgendaCita other = (AgendaCita) object;
        if ((this.idCita == null && other.idCita != null) || (this.idCita != null && !this.idCita.equals(other.idCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AgendaCita[ idCita=" + idCita + " ]";
    }
    
}
