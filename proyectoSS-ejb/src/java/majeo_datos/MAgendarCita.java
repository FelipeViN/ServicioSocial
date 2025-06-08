/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package majeo_datos;

import acceso_datos.AgendaCitaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.AgendaCita;

/**
 *
 * @author Matsu
 */
@Stateless
@LocalBean
public class MAgendarCita {

    @EJB
    private AgendaCitaFacade agendaCitaFacade;

    public List<AgendaCita> citasAgendadas(){
    return agendaCitaFacade.findAll();
    }
    public void registrar(AgendaCita agendaCita){
    agendaCitaFacade.create(agendaCita);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
