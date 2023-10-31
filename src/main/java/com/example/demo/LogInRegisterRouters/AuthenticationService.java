package com.example.demo.LogInRegisterRouters;

import com.example.demo.Accounts.Accounts;
import com.example.demo.Accounts.AccountsRepo;
import com.example.demo.Accounts.roleEnum.Rola;
import com.example.demo.LogInRegisterRouters.AuthenticationSchemas.AthenticationRequest;
import com.example.demo.LogInRegisterRouters.AuthenticationSchemas.AuthenticationResponse;
import com.example.demo.LogInRegisterRouters.AuthenticationSchemas.LoginRequest;
import com.example.demo.config.jwtServices.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountsRepo accountsRepo;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(AthenticationRequest req) {

            var user = Accounts.builder()
                    .username(req.getUsername())
                    .password(passwordEncoder.encode(req.getPassword()))
                    .email(req.getEmail())
                    .role(req.getRole())
                    .build();
        System.out.println(req.getUsername() +"\n");
        System.out.println(req.getEmail() +"\n");

            accountsRepo.save(user);
            var jwtToken=jwtService.generateToken(user);
        return new AuthenticationResponse().builder().token(jwtToken).role(user.getRole()).username(user.getUsername()).build();



    }

    public AuthenticationResponse authenticate(LoginRequest req) {
        System.out.println(passwordEncoder.encode(req.getPassword()));
        System.out.println(req.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )

        );

        var user=accountsRepo.findAccountsByUsername(req.getUsername())

                .orElseThrow();
        if (passwordEncoder.matches(req.getPassword(), user.getPassword())){
            var jwtToken=jwtService.generateToken(user);
            return new AuthenticationResponse().builder().token(jwtToken).role(user.getRole()).username(user.getUsername()).build();
        }
        return null
                ;


    }
}
