/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import majeo_datos.MCoordinadora;
import majeo_datos.MDocumento;
import majeo_datos.MInstitucion;
import majeo_datos.MPrestadorServicio;
import majeo_datos.MVacante;
import modelo.Coordinadora;
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
    private MCoordinadora mCoordinadora;

    @EJB
    private MDocumento mDocumento;

    @EJB
    private MInstitucion mInstitucion;

    @EJB
    private MVacante mVacante;

    @EJB
    private MPrestadorServicio mPrestadorServicio;

    private List<PrestadorServicio> prestadoresServicio;
    private List<PrestadorServicio> prestadoresServicioPropios;
    private List<Documento> documentosDelPrestador;
    private List<Vacante> vacantes;
    private List<Vacante> vacantesPropias;
    private List<Institucion> instituciones;
    private List<Coordinadora> coordinadoras;
    private PrestadorServicio sesion;
    private PrestadorServicio psn;
    private String validatePassword;
    private PrestadorServicio loginData;
    private Institucion institucion;
    private Documento documento;
    private Vacante vacante;
    private Coordinadora coordinadora;

    public Acceso() {
        psn = new PrestadorServicio();
        sesion = new PrestadorServicio();
        loginData = new PrestadorServicio();
        institucion = new Institucion();
        coordinadora = new Coordinadora();
        vacantes = new ArrayList<>();
        vacantesPropias = new ArrayList<>();
        prestadoresServicio = new ArrayList<>();
        prestadoresServicioPropios = new ArrayList<>();
        vacante = new Vacante();
        instituciones = new ArrayList<>();
    }
    //Metodos genericos

    public List<PrestadorServicio> getPrestadoresServicio() {
        return prestadoresServicio;
    }

    public void setPrestadoresServicio(List<PrestadorServicio> prestadoresServicio) {
        this.prestadoresServicio = prestadoresServicio;
    }

    public Coordinadora getCoordinadora() {
        return coordinadora;
    }

    public void setCoordinadora(Coordinadora coordinadora) {
        this.coordinadora = coordinadora;
    }

    public List<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

    public List<Coordinadora> getCoordinadoras() {
        return coordinadoras;
    }

    public void setCoordinadoras(List<Coordinadora> coordinadoras) {
        this.coordinadoras = coordinadoras;
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

    public List<PrestadorServicio> getPrestadoresServicioPropios() {
        return prestadoresServicioPropios;
    }

    public void setPrestadoresServicioPropios(List<PrestadorServicio> prestadoresServicioPropios) {
        this.prestadoresServicioPropios = prestadoresServicioPropios;
    }

    public List<Vacante> getVacantesPropias() {
        return vacantesPropias;
    }

    public void setVacantesPropias(List<Vacante> vacantesPropias) {
        this.vacantesPropias = vacantesPropias;
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
        List<Institucion> listaInsituciones = mInstitucion.institutciones();
        List<Coordinadora> listaCoordinadoras = mCoordinadora.coordinadoras();
        List<Vacante> todasVacantes = mVacante.vacantes();
        List<Institucion> instit = mInstitucion.institutciones();
        try {

        for (PrestadorServicio ps : lista) {
            if (ps.getEmail().equals(loginData.getEmail())
                    && ps.getPassword().equals(loginData.getPassword())) {
                sesion = ps;

                todasVacantes = mVacante.vacantes();
                for (Vacante v : todasVacantes) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Entro a buscar vacantes, ps", null));
                    if (v.getIdPrestador().getIdPrestador() == sesion.getIdPrestador()) {
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Asigno la vacante, ps", null));
                        vacante = v;
                        instit = mInstitucion.institutciones();
                        for (Institucion ins : listaInsituciones) {
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                            "Entro a buscar institucion", null));
                            if (v.getIdInstitucion().getIdInstitucion() == ins.getIdInstitucion()) {
                                FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "asigno la institucion", null));
                                institucion = ins;
                                return redirectUsuario();
                               
                            }
                        }
                    }
                }
                
            }
        }

        for (Institucion ps : listaInsituciones) {
            if (ps.getEmailInstitucion().equals(loginData.getEmail())
                    && ps.getPassword().equals(loginData.getPassword())) {
                institucion = ps;
                // Buscar la vacante del prestador
                vacantes = mVacante.vacantes();
                for (Vacante v : vacantes) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Entro a vacantes, insti", null));
                    if (v.getIdInstitucion().getIdInstitucion() == institucion.getIdInstitucion()) {
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Agrego vacantes, insti", null));
                        vacantesPropias.add(v);
                    }
                }
               

                for (Vacante v : vacantesPropias) {
                    PrestadorServicio prestador = v.getIdPrestador();
                    if (prestador != null && !prestadoresServicio.contains(prestador) ) {
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "agrego prestadores, insti", null));
                        prestadoresServicioPropios.add(prestador);
                    }
                }
                return redirectInstitucion();
            }
        }
        for (Coordinadora ps : listaCoordinadoras) {
            if (ps.getCorreo().equals(loginData.getEmail())
                    && ps.getPassword().equals(loginData.getPassword())) {
                coordinadora = ps;
                prestadoresServicio = mPrestadorServicio.prestadororesServicio();
                vacantes = mVacante.vacantes();
                return redirectCoordinadora();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "El email o la contraseña son incorrectas", null));
    } catch (Exception e) {
        e.printStackTrace(); 
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage()+ " Vacantes"+todasVacantes+" Institutos"+instit+ "vacante "+sesion.getIdPrestador(), null));
        return null;
    }
        return null;
    }

    public void registrarDocumento(Documento documento, String nombre) {
        documento.setEstadoValidacion(1);

        documento.setIdPrestador(sesion);

        documento.setNombreDocumento(nombre);
        documento.setRutaDocumento("C:\\Documentos\\" + nombre);

        documento.setFechaLimiteSubida(new Date());

        documento.setFechaSubidaDocumento(new Date());

        mDocumento.registrar(documento);
    }

    public void registrarVacante() {
        vacante.setIdInstitucion(institucion);
        vacante.setDisponible(1);
        vacante.setIdVacante(mVacante.obtenerSiguienteId());
        vacante.setEstado("disponible");
        mVacante.registrar(vacante);
        vacantes.add(vacante);
        vacante = new Vacante();
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

    public String redirectCoordinadora() {
        return "usaurioCoordinadora?faces-redirect=true";
    }

    public String redirectInstitucion() {
        return "usuarioInstitucion?faces-redirect=true";
    }

    public String redirectVacantes() {
        return "vacantes?faces-redirect=true";
    }

    public String redirectPrestadores() {
        return "prestadores?faces-redirect=true";
    }

    public String redirectVacantesCordi() {
        return "vacantesCordi?faces-redirect=true";
    }

}
