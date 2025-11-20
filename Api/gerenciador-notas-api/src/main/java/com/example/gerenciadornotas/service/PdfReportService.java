
package com.example.gerenciadornotas.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfReportService {

  public byte[] relatorio(String nomeEstudante, String periodo, List<RelatorioLinha> linhas, BigDecimal gpa) {
    try (var baos = new ByteArrayOutputStream()) {
      Document doc = new Document();
      PdfWriter.getInstance(doc, baos);
      doc.open();
      doc.add(new Paragraph("Histórico Acadêmico"));
      doc.add(new Paragraph("Aluno: " + nomeEstudante));
      doc.add(new Paragraph("Período: " + periodo));
      doc.add(new Paragraph(" "));

      PdfPTable table = new PdfPTable(4);
      Stream.of("Código","Disciplina","Média","Situação").forEach(h -> table.addCell(new PdfPCell(new Phrase(h))));
      for (var r : linhas) {
        table.addCell(r.codigoCurso());
        table.addCell(r.nomeCurso());
        table.addCell(r.media().toPlainString());
        table.addCell(r.media().compareTo(new BigDecimal("6.00"))>=0 ? "Aprovado" : "Reprovado");
      }
      doc.add(table);
      doc.add(new Paragraph("GPA: " + gpa.toPlainString()));
      doc.close();
      return baos.toByteArray();
    } catch (DocumentException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public record RelatorioLinha(String codigoCurso, String nomeCurso, java.math.BigDecimal media) {}
}
