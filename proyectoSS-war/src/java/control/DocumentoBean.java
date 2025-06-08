package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part; 
import modelo.Documento;
import modelo.Institucion;
import modelo.PrestadorServicio;
import modelo.Vacante;

@Named(value = "documentoBean")
@SessionScoped
public class DocumentoBean implements Serializable {

    private Part reporteBimestralUno;
    private Part reporteBimestralDos;
    private Part reporteBimestralTres;
    private Part evaluacionUno;
    private Part evaluacionDos;
    private Part evaluacionTres;
    private Part autoevaluacionUno;
    private Part autoevaluacionDos;
    private Part autoevaluacionTres;
    private Part cualitativaUno;
    private Part cualitativaDos;
    private Part cualitativaTres;

    @Inject
    private Acceso acceso;

    private final PDFBoxReader pdfReader = new PDFBoxReader();

    public void subirDocumento(String tipo) {
        Part documentoSubido = null;

        switch (tipo) {
            case "reporteBimestralUno":
                documentoSubido = reporteBimestralUno;
                break;
            case "reporteBimestralDos":
                documentoSubido = reporteBimestralDos;
                break;
            case "reporteBimestralTres":
                documentoSubido = reporteBimestralTres;
                break;
            case "evaluacionUno":
                documentoSubido = evaluacionUno;
                break;
            case "evaluacionDos":
                documentoSubido = evaluacionDos;
                break;
            case "evaluacionTres":
                documentoSubido = evaluacionTres;
                break;
            case "autoevaluacionUno":
                documentoSubido = autoevaluacionUno;
                break;
            case "autoevaluacionDos":
                documentoSubido = autoevaluacionDos;
                break;
            case "autoevaluacionTres":
                documentoSubido = autoevaluacionTres;
                break;
            case "cualitativaUno":
                documentoSubido = cualitativaUno;
                break;
            case "cualitativaDos":
                documentoSubido = cualitativaDos;
                break;
            case "cualitativaTres":
                documentoSubido = cualitativaTres;
                break;
            default:
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de documento no reconocido", null));
                return;
        }

        if (documentoSubido == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se subió ningún documento", null));
            return;
        }

        try {
            String filename = Paths.get(documentoSubido.getSubmittedFileName()).getFileName().toString();
            File destino = new File("C:/Documentos/" + filename);
            Files.copy(documentoSubido.getInputStream(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String texto = pdfReader.getText(destino.getAbsolutePath());

            List<String> criterios = obtenerCriteriosPorTipo(tipo, acceso.getVacante(), acceso.getSesion(), acceso.getInstitucion());
            List<String> faltantes = pdfReader.validateContent(texto, criterios, filename);

            if (faltantes.isEmpty()) {
                Documento documento = new Documento();
                
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento válido", filename));
                
               acceso.registrarDocumento(documento, filename);
            } else {
                StringBuilder mensaje = new StringBuilder("Documento incompleto. Faltan: ");
                for (String f : faltantes) {
                    mensaje.append(f).append(", ");
                }
                
                if (mensaje.length() > 0) {
                    mensaje.setLength(mensaje.length() - 2);
                }

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje.toString(), filename));
            }

        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir el documento: " + e.getMessage(), null));
            e.printStackTrace();
        }
    }

    private List<String> obtenerCriteriosPorTipo(String tipo, Vacante vacante, PrestadorServicio ps, Institucion ins) {
        switch (tipo) {
            case "reporteBimestralUno":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "UNO", String.valueOf(ps.getNumeroControl()), ins.getNombreInstitucion(),
                        vacante.getDescripcionVacante()
                );
            case "reporteBimestralDos":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "DOS", String.valueOf(ps.getNumeroControl()), ins.getNombreInstitucion(),
                        vacante.getDescripcionVacante()
                );
            case "reporteBimestralTres":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "TRES", String.valueOf(ps.getNumeroControl()), ins.getNombreInstitucion(),
                        vacante.getDescripcionVacante()
                );
            case "evaluacionTres":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "X", String.valueOf(ps.getNumeroControl()),
                        vacante.getDescripcionVacante()
                );
            case "evaluacionDos":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "2", String.valueOf(ps.getNumeroControl()),
                        vacante.getDescripcionVacante()
                );
            case "evaluacionUno":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "1", String.valueOf(ps.getNumeroControl()),
                        vacante.getDescripcionVacante()
                );
            case "autoevaluacionTres":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "X", String.valueOf(ps.getNumeroControl()),
                        vacante.getDescripcionVacante()
                );
            case "autoevaluacionDos":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "2", String.valueOf(ps.getNumeroControl()),
                        vacante.getDescripcionVacante()
                );
            case "autoevaluacionUno":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "1", String.valueOf(ps.getNumeroControl()),
                        vacante.getDescripcionVacante()
                );
            case "cualitativaTres":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "X", 
                        vacante.getDescripcionVacante()
                );
            case "cualitativaDos":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "2",
                        vacante.getDescripcionVacante()
                );
            case "cualitativaUno":
                return List.of(ps.getApellidoMaterno(), ps.getApellidoPaterno(), ps.getNombre(),
                        "1",
                        vacante.getDescripcionVacante()
                );
            default:
                return List.of();
        }
    }

    // Getters y setters
    public Part getReporteBimestralUno() {
        return reporteBimestralUno;
    }

    public void setReporteBimestralUno(Part reporteBimestralUno) {
        this.reporteBimestralUno = reporteBimestralUno;
    }

    public Part getReporteBimestralDos() {
        return reporteBimestralDos;
    }

    public void setReporteBimestralDos(Part reporteBimestralDos) {
        this.reporteBimestralDos = reporteBimestralDos;
    }

    public Part getReporteBimestralTres() {
        return reporteBimestralTres;
    }

    public void setReporteBimestralTres(Part reporteBimestralTres) {
        this.reporteBimestralTres = reporteBimestralTres;
    }

    public Part getEvaluacionUno() {
        return evaluacionUno;
    }

    public void setEvaluacionUno(Part evaluacionUno) {
        this.evaluacionUno = evaluacionUno;
    }

    public Part getEvaluacionDos() {
        return evaluacionDos;
    }

    public void setEvaluacionDos(Part evaluacionDos) {
        this.evaluacionDos = evaluacionDos;
    }

    public Part getEvaluacionTres() {
        return evaluacionTres;
    }

    public void setEvaluacionTres(Part evaluacionTres) {
        this.evaluacionTres = evaluacionTres;
    }

    public Part getAutoevaluacionUno() {
        return autoevaluacionUno;
    }

    public void setAutoevaluacionUno(Part autoevaluacionUno) {
        this.autoevaluacionUno = autoevaluacionUno;
    }

    public Part getAutoevaluacionDos() {
        return autoevaluacionDos;
    }

    public void setAutoevaluacionDos(Part autoevaluacionDos) {
        this.autoevaluacionDos = autoevaluacionDos;
    }

    public Part getAutoevaluacionTres() {
        return autoevaluacionTres;
    }

    public void setAutoevaluacionTres(Part autoevaluacionTres) {
        this.autoevaluacionTres = autoevaluacionTres;
    }

    public Part getCualitativaUno() {
        return cualitativaUno;
    }

    public void setCualitativaUno(Part cualitativaUno) {
        this.cualitativaUno = cualitativaUno;
    }

    public Part getCualitativaDos() {
        return cualitativaDos;
    }

    public void setCualitativaDos(Part cualitativaDos) {
        this.cualitativaDos = cualitativaDos;
    }

    public Part getCualitativaTres() {
        return cualitativaTres;
    }

    public void setCualitativaTres(Part cualitativaTres) {
        this.cualitativaTres = cualitativaTres;
    }

}
