/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package majeo_datos;

import acceso_datos.CalificacionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Calificacion;

/**
 *
 * @author Matsu
 */
@Stateless
@LocalBean
public class MCalificacion {

    @EJB
    private CalificacionFacade calificacionFacade;
    
    public List<Calificacion> calificaciones(){
    return calificacionFacade.findAll();
    }
    public void registrar(Calificacion calificacion){
    calificacionFacade.create(calificacion);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
