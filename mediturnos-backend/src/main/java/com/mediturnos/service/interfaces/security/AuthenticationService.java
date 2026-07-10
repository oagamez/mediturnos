package com.mediturnos.service.interfaces.security;

import com.mediturnos.dto.security.request.LoginRequest;
import com.mediturnos.dto.security.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

}