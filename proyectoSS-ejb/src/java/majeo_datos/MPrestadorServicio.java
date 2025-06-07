/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package majeo_datos;

import acceso_datos.PrestadorServicioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.PrestadorServicio;

/**
 *
 * @author Matsu
 */
@Stateless
@LocalBean
public class MPrestadorServicio {

    @EJB
    private PrestadorServicioFacade prestadorServicioFacade;
    
    public List<PrestadorServicio> prestadororesServicio(){
    return prestadorServicioFacade.findAll();
    }
    
    public void registrar(PrestadorServicio ps){
    prestadorServicioFacade.create(ps);
    }
    
    public int obtenerSiguienteId(){
    List<PrestadorServicio> lista = prestadorServicioFacade.findAll();
            int maxId = lista.stream()
                .mapToInt(PrestadorServicio::getIdPrestador)
                .max()
                .orElse(0);
        return maxId + 1;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
