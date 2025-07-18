package com.example.controller;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import com.example.model.User;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class Auth {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PatchMapping("/login")
    public ResponseEntity<AuthResponse> login(AuthRequest request)
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword())
    );

    User user = (User) authentication.getPrincipal();
    String accessToken = jwtTokenUtil.generateAccessToken(user);
        return ResponseEntity.ok(new AuthResponse(user.getEmail(), accessToken));
   }

   public ResponseEntity<User> register(
         String name,
         String surname,
         String email,
         String password,
         String accountType,{

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setAccountType(accountType);
            if (user.getAvatar() != null && !avatar.isEmpty()) {
           user.setAvatar(UserService(avatar));
       }

       return ResponseEntity.ok(UserService.createUser(user));
   }}

}
