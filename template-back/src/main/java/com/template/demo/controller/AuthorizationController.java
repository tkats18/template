package com.template.demo.controller;

import com.template.demo.model.authorization.LoginRequest;
import com.template.demo.model.authorization.RegisterRequest;
import com.template.demo.model.authorization.UserResponse;
import com.template.demo.model.template.UserRepresentationModel;
import com.template.demo.service.UserManagementService;
import com.template.demo.service.impl.JwtTokenService;
import com.template.demo.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1")
public class AuthorizationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    private UserManagementService userManagementService;


    @PostMapping("login")
    public UserResponse login(@RequestBody LoginRequest loginRequest){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
        }catch (Exception e){
            throw new RuntimeException("invalid_credentials");
        }

        User user = userManagementService.getUser(loginRequest.getUsername());
        return new UserResponse(user.getUserName(),user.getFullName(),jwtTokenService.generateToken(loginRequest.getUsername()));
    }

    @PostMapping("register")
    public UserResponse register(@RequestBody RegisterRequest registerRequest){
        String prevPassword = registerRequest.getPassword();
        registerRequest.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        userManagementService.addUser(registerRequest);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getUsername(),prevPassword)
        );

        User user = userManagementService.getUser(registerRequest.getUsername());
        return new UserResponse(user.getUserName(),user.getFullName(),jwtTokenService.generateToken(registerRequest.getUsername()));
    }

    @GetMapping("logout")
    public void logout(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }else {
            throw new RuntimeException("not_logged_in");
        }
    }

    @GetMapping("session")
    public UserResponse session(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth==null){
            throw new RuntimeException("session_not_found");
        }
        UserRepresentationModel model = (UserRepresentationModel) auth.getPrincipal();
        return new UserResponse(model.getUserName(),model.getFullName(),request.getHeader("Authorization").substring(7));
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserManagementService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Autowired
    public void setJwtTokenService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

}
