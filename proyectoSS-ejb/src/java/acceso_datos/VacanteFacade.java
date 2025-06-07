/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso_datos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Vacante;

/**
 *
 * @author Matsu
 */
@Stateless
public class VacanteFacade extends AbstractFacade<Vacante> {

    @PersistenceContext(unitName = "proyectoSS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VacanteFacade() {
        super(Vacante.class);
    }
    
}
