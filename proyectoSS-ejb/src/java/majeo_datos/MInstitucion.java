/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package majeo_datos;

import acceso_datos.InstitucionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Institucion;

/**
 *
 * @author Matsu
 */
@Stateless
@LocalBean
public class MInstitucion {

    @EJB
    private InstitucionFacade institucionFacade;
    
    public List<Institucion> institutciones(){
    return institucionFacade.findAll();
    }
    public void registrar(Institucion institucion){
    institucionFacade.create(institucion);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
