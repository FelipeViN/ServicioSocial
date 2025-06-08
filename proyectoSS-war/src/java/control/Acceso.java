/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package control;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import majeo_datos.MInstitucion;
import majeo_datos.MPrestadorServicio;
import majeo_datos.MVacante;
import modelo.Documento;
import modelo.Institucion;
import modelo.PrestadorServicio;
import modelo.Vacante;

/**
 *
 * @author Matsu
 */
@Named(value = "acceso")
@SessionScoped
public class Acceso implements Serializable {

    @EJB
    private MInstitucion mInstitucion;

    @EJB
    private MVacante mVacante;

    @EJB
    private MPrestadorServicio mPrestadorServicio;

    private List<PrestadorServicio> prestadoresServicio;
    private List<Documento> documentosDelPrestador;
    private List<Vacante> vacantes;
    private List<Institucion> instituciones;
    private PrestadorServicio sesion;
    private PrestadorServicio psn;
    private String validatePassword;
    private PrestadorServicio loginData;
    private Institucion institucion;
    private Documento documento;
    private Vacante vacante;

    public Acceso() {
        psn = new PrestadorServicio();
        sesion = new PrestadorServicio();
        loginData = new PrestadorServicio();
    }
    //Metodos genericos

    public List<PrestadorServicio> getPrestadoresServicio() {
        return prestadoresServicio;
    }

    public void setPrestadoresServicio(List<PrestadorServicio> prestadoresServicio) {
        this.prestadoresServicio = prestadoresServicio;
    }

    public List<Documento> getDocumentosDelPrestador() {
        return documentosDelPrestador;
    }

    public void setDocumentosDelPrestador(List<Documento> documentosDelPrestador) {
        this.documentosDelPrestador = documentosDelPrestador;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public PrestadorServicio getSesion() {
        return sesion;
    }

    public void setSesion(PrestadorServicio sesion) {
        this.sesion = sesion;
    }

    public PrestadorServicio getPsn() {
        return psn;
    }

    public void setPsn(PrestadorServicio psn) {
        this.psn = psn;
    }

    public String getValidatePassword() {
        return validatePassword;
    }

    public void setValidatePassword(String validatePassword) {
        this.validatePassword = validatePassword;
    }

    public PrestadorServicio getLoginData() {
        return loginData;
    }

    public void setLoginData(PrestadorServicio loginData) {
        this.loginData = loginData;
    }

    // Registros
    public String registrarPrestador() {
        if (!psn.getPassword().equals(validatePassword)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", null));
            return null;
        }
        if (!psn.getEmail().startsWith(String.valueOf(psn.getNumeroControl()))) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "El email debe comenzar con el número de control", null));
            return null;
        }
        psn.setIdPrestador(mPrestadorServicio.obtenerSiguienteId());
        mPrestadorServicio.registrar(psn);
        psn = new PrestadorServicio();
        validatePassword = "";
        return "login?faces-redirect=true";
    }

    public String iniciarSesion() {
        List<PrestadorServicio> lista = mPrestadorServicio.prestadororesServicio();

        for (PrestadorServicio ps : lista) {
            if (ps.getEmail().equals(loginData.getEmail())
                    && ps.getPassword().equals(loginData.getPassword())) {
                sesion = ps;
                // Buscar la vacante del prestador
                List<Vacante> todasVacantes = mVacante.vacantes(); // Método que retorna todas las vacantes
                for (Vacante v : todasVacantes) {
                    if (v.getIdPrestador().getIdPrestador() == sesion.getIdPrestador()) {
                        vacante = v;
                        List<Institucion> instit = mInstitucion.institutciones();
                        for (Institucion ins : instit) {
                            if (v.getIdInstitucion().getIdInstitucion() == ins.getIdInstitucion()) {
                                // Obtener la institución asociada a esa vacante
                                institucion = ins;
                                break;
                            }
                        }
                    }
                }
                return redirectUsuario();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El email o la contraseña son incorrectas", null));
        return null;
    }

    // Redirecciones
    public String redirectHome() {
        return "index?faces-redirect=true";
    }

    public String redirectUsuario() {
        return "usuario?faces-redirect=true";
    }

    public String redirectLogIn() {
        return "login?faces-redirect=true";
    }

    public String redirectSignUp() {
        return "sign up?faces-redirect=true";
    }

    public String redirectInformacion() {
        return "informacion?faces-redirect=true";
    }

    public String redirectSerivicioSocial() {
        return "servicioSocial?faces-redirect=true";
    }

    public String redirectSignOut() {
        return "index?faces-redirect=true";
    }

}
