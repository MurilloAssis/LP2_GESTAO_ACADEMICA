
package com.example.gerenciadornotas.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ExcelReportService {

  public byte[] planilhaNotasCurso(String codigoCurso, List<LinhaNotaCurso> linhas) {
    try (var wb = new XSSFWorkbook(); var bos = new ByteArrayOutputStream()) {
      var sheet = wb.createSheet("Notas " + codigoCurso);
      var header = sheet.createRow(0);
      header.createCell(0).setCellValue("RA");
      header.createCell(1).setCellValue("Aluno");
      header.createCell(2).setCellValue("Média");

      int i=1;
      for (var r : linhas) {
        var row = sheet.createRow(i++);
        row.createCell(0).setCellValue(r.registro());
        row.createCell(1).setCellValue(r.estudante());
        row.createCell(2).setCellValue(r.media().doubleValue());
      }
      wb.write(bos);
      return bos.toByteArray();
    } catch (IOException e) { throw new RuntimeException(e); }
  }
  public record LinhaNotaCurso(String registro, String estudante, BigDecimal media) {}
}
