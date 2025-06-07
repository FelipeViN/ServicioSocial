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
import majeo_datos.MPrestadorServicio;
import modelo.PrestadorServicio;

/**
 *
 * @author Matsu
 */
@Named(value = "acceso")
@SessionScoped
public class Acceso implements Serializable {

    @EJB
    private MPrestadorServicio mPrestadorServicio;

    private List<PrestadorServicio> prestadoresServicio;
    private PrestadorServicio sesion;
    private PrestadorServicio psn;
    private String validatePassword;
    private PrestadorServicio loginData;

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
                sesion = loginData;
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

}
