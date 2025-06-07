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
import majeo_datos.MPrestadorServicio;
import modelo.PrestadorServicio;

/**
 *
 * @author Matsu
 */
@Named(value = "acceso")
@SessionScoped
public class Acceso implements Serializable{

    @EJB
    private MPrestadorServicio mPrestadorServicio;
    
    private List<PrestadorServicio> prestadoresServicio;
    private PrestadorServicio sesion;
    private PrestadorServicio psn;
    
    public Acceso() {
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
    
    // Redirecciones
        public String redirectHome() {
        return "index?faces-redirect=true";
    }
            public String redirectLogIn() {
        return "login?faces-redirect=true";
    }
                public String redirectSignUp() {
        return "sign up?faces-redirect=true";
    }
                
    
}
