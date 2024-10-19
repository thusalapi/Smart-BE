package org.trash.smartbe.service;

import org.trash.smartbe.common.payload.response.ResponseEntityDto;
import org.trash.smartbe.dto.UserRegistrationDto;

public interface UserService {
    ResponseEntityDto registerUser(UserRegistrationDto registrationDto);
}