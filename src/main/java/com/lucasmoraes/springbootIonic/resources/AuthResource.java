package com.lucasmoraes.springbootIonic.resources;

import com.lucasmoraes.springbootIonic.dto.EmailDto;
import com.lucasmoraes.springbootIonic.security.JwtUtil;
import com.lucasmoraes.springbootIonic.security.UserSS;
import com.lucasmoraes.springbootIonic.services.AuthService;
import com.lucasmoraes.springbootIonic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource
{
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value="/refresh_token", method= RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/forgot", method= RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto objDto) {
        authService.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
