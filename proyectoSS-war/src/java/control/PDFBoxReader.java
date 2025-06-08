/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import modelo.PrestadorServicio;

/**
 *
 * @author Matsu
 */
public class PDFBoxReader {

    public PDFBoxReader() {
    }

    public void extractText(String documentPath, PrestadorServicio ps, String nombre) throws IOException {
        try (PDDocument document = PDDocument.load(new File(documentPath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            FileWriter writer = new FileWriter("Documento " + nombre + " de " + ps.getNumeroControl() + ".txt");
            writer.write(text);
            writer.close();
        }
    }

    public String getText(String documentPath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(documentPath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

public List<String> validateContent(String textoExtraido, List<String> elementosEsperados, String nombreDocumento) {
    String textoNormalizado = normalizarTexto(textoExtraido);
    List<String> faltantes = new ArrayList<>();

    for (String esperado : elementosEsperados) {
        String esperadoNormalizado = normalizarTexto(esperado);
        if (!textoNormalizado.contains(esperadoNormalizado)) {
            faltantes.add(esperado);
        }
    }

    return faltantes;
}



 public static String normalizarTexto(String texto) {
    // Eliminar espacios no estándar y múltiples espacios
    String textoLimpio = texto
            .replace("\u00A0", " ")         // Espacio no separable
            .replaceAll("\\s+", " ")        // Reemplazar múltiples espacios con uno solo
            .trim();                        // Eliminar espacios al inicio y final

    // Convertir a minúsculas
    textoLimpio = textoLimpio.toLowerCase();

    // Normalizar para eliminar acentos y caracteres especiales
    textoLimpio = Normalizer.normalize(textoLimpio, Normalizer.Form.NFD);
    textoLimpio = textoLimpio.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

    // Eliminar caracteres no ASCII si aún quedan
    textoLimpio = textoLimpio.replaceAll("[^\\p{ASCII}]", "");

    return textoLimpio;
}
}
