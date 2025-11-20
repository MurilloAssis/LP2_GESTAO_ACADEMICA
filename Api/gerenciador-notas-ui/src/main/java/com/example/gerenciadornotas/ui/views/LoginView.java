
package com.example.gerenciadornotas.ui.views;

import com.example.gerenciadornotas.ui.ApiClient;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.passwordfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Route("login")
@PageTitle("Login")
@RequiredArgsConstructor
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

  private final ApiClient apiClient;

  public LoginView(ApiClient apiClient) {
    this.apiClient = apiClient;
    setSizeFull();
    setAlignItems(Alignment.CENTER);
    setJustifyContentMode(JustifyContentMode.CENTER);

    TextField email = new TextField("Email");
    PasswordField senha = new PasswordField("Senha");
    Button btn = new Button("Entrar", e -> {
      Mono<Void> mono = apiClient.login(email.getValue(), senha.getValue());
      mono.subscribe(
        ok -> UI.getCurrent().navigate(""),
        err -> Notification.show("Falha no login")
      );
    });
    add(email, senha, btn);
  }
}
