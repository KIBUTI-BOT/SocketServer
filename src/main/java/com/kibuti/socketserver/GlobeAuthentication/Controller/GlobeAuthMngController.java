package com.kibuti.socketserver.GlobeAuthentication.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.kibuti.socketserver.GlobeAdvice.Exceptions.RandomExceptions;
import com.kibuti.socketserver.GlobeAdvice.Exceptions.TokenInvalidException;
import com.kibuti.socketserver.GlobeAdvice.Exceptions.UserExistException;
import com.kibuti.socketserver.GlobeAdvice.Exceptions.VerificationException;
import com.kibuti.socketserver.GlobeAuthentication.DTOs.RefreshTokenDTO;
import com.kibuti.socketserver.GlobeAuthentication.DTOs.UserLoginDTO;
import com.kibuti.socketserver.GlobeAuthentication.DTOs.UserRegisterDTO;
import com.kibuti.socketserver.GlobeAuthentication.Service.UserManagementService;
import com.kibuti.socketserver.GlobeResponseBody.GlobalJsonResponseBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/v2/auth")
public class GlobeAuthMngController {

    private final UserManagementService userManagementService;


    @PostMapping("/register")
    public ResponseEntity<GlobalJsonResponseBody> userRegistration(@Valid @RequestBody UserRegisterDTO userManagementDTO) throws UserExistException, RandomExceptions, JsonProcessingException {
        return new ResponseEntity<>(userManagementService.registerUser(userManagementDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalJsonResponseBody> userLogin(@Valid @RequestBody UserLoginDTO userLoginDTO) throws UserExistException, VerificationException {
        return new ResponseEntity<>(userManagementService.loginUser(userLoginDTO), HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<GlobalJsonResponseBody> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) throws RandomExceptions, TokenInvalidException {
        return new ResponseEntity<>(userManagementService.refreshToken(refreshTokenDTO.getRefreshToken()), HttpStatus.ACCEPTED);
    }



    @GetMapping("/all-users")
    public ResponseEntity<List<GlobalJsonResponseBody>> getAllUsers() {
        return new ResponseEntity<>(userManagementService.getAllUser(), HttpStatus.CREATED);
    }


    @GetMapping("/single-user/{userId}")
    public ResponseEntity<GlobalJsonResponseBody> getSingleUser(@PathVariable UUID userId) throws UserExistException, VerificationException {
        return new ResponseEntity<>(userManagementService.getSingleUser(userId), HttpStatus.OK);
    }


}
