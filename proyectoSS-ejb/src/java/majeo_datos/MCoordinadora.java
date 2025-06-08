/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package majeo_datos;

import acceso_datos.CoordinadoraFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Coordinadora;

/**
 *
 * @author Matsu
 */
@Stateless
@LocalBean
public class MCoordinadora {

    @EJB
    private CoordinadoraFacade coordinadoraFacade;

    public List<Coordinadora>coordinadoras(){
    return coordinadoraFacade.findAll();
    }
    public void registrar(Coordinadora coordinadora){
    coordinadoraFacade.create(coordinadora);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
