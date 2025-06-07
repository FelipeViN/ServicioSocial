/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matsu
 */
@Entity
@Table(name = "vacante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacante.findAll", query = "SELECT v FROM Vacante v"),
    @NamedQuery(name = "Vacante.findByIdVacante", query = "SELECT v FROM Vacante v WHERE v.idVacante = :idVacante"),
    @NamedQuery(name = "Vacante.findByClasificacionDependencia", query = "SELECT v FROM Vacante v WHERE v.clasificacionDependencia = :clasificacionDependencia"),
    @NamedQuery(name = "Vacante.findByDependencia", query = "SELECT v FROM Vacante v WHERE v.dependencia = :dependencia"),
    @NamedQuery(name = "Vacante.findByDescripcionVacante", query = "SELECT v FROM Vacante v WHERE v.descripcionVacante = :descripcionVacante"),
    @NamedQuery(name = "Vacante.findByEstado", query = "SELECT v FROM Vacante v WHERE v.estado = :estado"),
    @NamedQuery(name = "Vacante.findByHorarioVacante", query = "SELECT v FROM Vacante v WHERE v.horarioVacante = :horarioVacante"),
    @NamedQuery(name = "Vacante.findByNombreVacante", query = "SELECT v FROM Vacante v WHERE v.nombreVacante = :nombreVacante"),
    @NamedQuery(name = "Vacante.findByTipoDependentica", query = "SELECT v FROM Vacante v WHERE v.tipoDependentica = :tipoDependentica")})
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vacante")
    private Integer idVacante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "clasificacion_dependencia")
    private String clasificacionDependencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "dependencia")
    private String dependencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion_vacante")
    private String descripcionVacante;
    @Size(max = 255)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "horario_vacante")
    private String horarioVacante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre_vacante")
    private String nombreVacante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo_dependentica")
    private String tipoDependentica;
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion")
    @ManyToOne
    private Institucion idInstitucion;

    public Vacante() {
    }

    public Vacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Vacante(Integer idVacante, String clasificacionDependencia, String dependencia, String descripcionVacante, String horarioVacante, String nombreVacante, String tipoDependentica) {
        this.idVacante = idVacante;
        this.clasificacionDependencia = clasificacionDependencia;
        this.dependencia = dependencia;
        this.descripcionVacante = descripcionVacante;
        this.horarioVacante = horarioVacante;
        this.nombreVacante = nombreVacante;
        this.tipoDependentica = tipoDependentica;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getClasificacionDependencia() {
        return clasificacionDependencia;
    }

    public void setClasificacionDependencia(String clasificacionDependencia) {
        this.clasificacionDependencia = clasificacionDependencia;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getDescripcionVacante() {
        return descripcionVacante;
    }

    public void setDescripcionVacante(String descripcionVacante) {
        this.descripcionVacante = descripcionVacante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHorarioVacante() {
        return horarioVacante;
    }

    public void setHorarioVacante(String horarioVacante) {
        this.horarioVacante = horarioVacante;
    }

    public String getNombreVacante() {
        return nombreVacante;
    }

    public void setNombreVacante(String nombreVacante) {
        this.nombreVacante = nombreVacante;
    }

    public String getTipoDependentica() {
        return tipoDependentica;
    }

    public void setTipoDependentica(String tipoDependentica) {
        this.tipoDependentica = tipoDependentica;
    }

    public Institucion getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Institucion idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacante != null ? idVacante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacante)) {
            return false;
        }
        Vacante other = (Vacante) object;
        if ((this.idVacante == null && other.idVacante != null) || (this.idVacante != null && !this.idVacante.equals(other.idVacante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Vacante[ idVacante=" + idVacante + " ]";
    }
    
}
