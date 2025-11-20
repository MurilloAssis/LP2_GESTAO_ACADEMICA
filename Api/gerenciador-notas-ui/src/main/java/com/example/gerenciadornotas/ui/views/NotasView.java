
package com.example.gerenciadornotas.ui.views;

import com.example.gerenciadornotas.ui.ApiClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.numberfield.NumberField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Route("")
@PageTitle("Notas")
@RequiredArgsConstructor
public class NotasView extends VerticalLayout {

  private final ApiClient api;

  public NotasView(ApiClient api) {
    this.api = api;
    NumberField matriculaId = new NumberField("Matricula Id");
    NumberField tarefaId = new NumberField("Tarefa Id");
    NumberField value = new NumberField("Nota");

    Button salvar = new Button("Salvar", e -> {
      api.salvaNota(matriculaId.getValue().longValue(), tarefaId.getValue().longValue(),
          new BigDecimal(value.getValue().toString()))
        .subscribe(v -> Notification.show("Nota salva"),
                   err -> Notification.show("Erro ao salvar"));
    });

    Button media = new Button("Ver Média", e -> {
      api.media(matriculaId.getValue().longValue())
        .subscribe(avg -> Notification.show("Média: " + avg),
                   err -> Notification.show("Erro ao calcular"));
    });

    add(matriculaId, tarefaId, value, salvar, media);
  }
}
