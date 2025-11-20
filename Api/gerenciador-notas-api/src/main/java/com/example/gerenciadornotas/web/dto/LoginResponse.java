
package com.example.gerenciadornotas.web.dto;

import java.util.List;
public record LoginResponse(String token, String fullName, List<String> roles) {}
