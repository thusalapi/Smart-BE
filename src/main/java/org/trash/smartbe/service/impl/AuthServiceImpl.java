// AuthServiceImpl.java
package org.trash.smartbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.trash.smartbe.dto.AuthenticationRequest;
import org.trash.smartbe.model.User;
import org.trash.smartbe.payload.ResponseEntityDto;
import org.trash.smartbe.service.AuthService;
import org.trash.smartbe.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntityDto<String> login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntityDto<>(HttpStatus.UNAUTHORIZED, "Incorrect username or password", null);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Long userId = ((User) userDetails).getId();
        final String jwt = jwtUtil.generateToken(userDetails, userId);

        return new ResponseEntityDto<>(HttpStatus.OK, "Login successful", jwt);
    }

}