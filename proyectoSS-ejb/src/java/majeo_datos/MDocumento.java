/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package majeo_datos;

import acceso_datos.DocumentoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Documento;

/**
 *
 * @author Matsu
 */
@Stateless
@LocalBean
public class MDocumento {

    @EJB
    private DocumentoFacade documentoFacade;
    
    public List<Documento>documentos(){
    return documentoFacade.findAll();
    }
    public void registrar(Documento documento){
    documentoFacade.create(documento);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
