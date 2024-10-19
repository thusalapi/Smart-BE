package org.trash.smartbe.service;

import org.trash.smartbe.payload.ResponseEntityDto;
import org.trash.smartbe.dto.AuthenticationRequest;

public interface AuthService {
    ResponseEntityDto<String> login(AuthenticationRequest authenticationRequest);
}