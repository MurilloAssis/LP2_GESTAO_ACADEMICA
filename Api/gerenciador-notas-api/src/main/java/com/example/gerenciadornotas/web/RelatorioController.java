
package com.example.gerenciadornotas.web;

import com.example.gerenciadornotas.service.ExcelReportService;
import com.example.gerenciadornotas.service.PdfReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class RelatorioController {

  private final PdfReportService pdfReportService;
  private final ExcelReportService excelReportService;

  @GetMapping(value = "/relatorio/{estudanteId}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<byte[]> relatorio(@PathVariable Long estudanteId, @RequestParam(required=false) Long periodoId) {
    var linhas = List.of(
      new PdfReportService.RelatorioLinha("DW101","Desenvolvimento Web", new BigDecimal("8.50")),
      new PdfReportService.RelatorioLinha("DB200","Banco de Dados", new BigDecimal("7.40"))
    );
    var bytes = pdfReportService.relatorio("Aluno " + estudanteId,
        periodoId==null? "Geral" : ("Termo " + periodoId), linhas, new BigDecimal("7.95"));
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=transcript-"+estudanteId+".pdf")
      .body(bytes);
  }

  @GetMapping(value = "/notas.xlsx", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public ResponseEntity<byte[]> notasCurso(@RequestParam String codigoCurso) {
    var linhas = List.of(
      new ExcelReportService.LinhaNotaCurso("RA001","Helena", new BigDecimal("8.0")),
      new ExcelReportService.LinhaNotaCurso("RA002","Caio", new BigDecimal("7.0"))
    );
    var bytes = excelReportService.planilhaNotasCurso(codigoCurso, linhas);
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=notas-"+codigoCurso+".xlsx")
      .body(bytes);
  }
}
