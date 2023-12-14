package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.dto.request.NewUserDTO;
import com.vorstu.DeliveryServiceBackend.mappers.BaseUserMapper;
import com.vorstu.DeliveryServiceBackend.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    BaseUserMapper baseUserMapper;

    @GetMapping("/auth")
    @ResponseBody
    public Principal user(Principal user){
        log.warn("getUserAuth: " + (user != null ? user.getName() : "null") );
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity register(@RequestBody NewUserDTO user){
        authService.register(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/logout", consumes = "application/json", produces = "applicaton/json")
    @ResponseBody
    public void logout(Principal user, HttpServletRequest request, HttpServletResponse response){
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
    }
}
