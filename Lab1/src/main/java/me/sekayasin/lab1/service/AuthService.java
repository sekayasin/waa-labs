package me.sekayasin.lab1.service;

import me.sekayasin.lab1.domain.dto.request.LoginRequest;
import me.sekayasin.lab1.domain.dto.request.RefreshTokenRequest;
import me.sekayasin.lab1.domain.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
