package com.kibuti.socketserver.GlobeAuthentication.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kibuti.socketserver.GlobeAdvice.Exceptions.RandomExceptions;
import com.kibuti.socketserver.GlobeAdvice.Exceptions.TokenInvalidException;
import com.kibuti.socketserver.GlobeAdvice.Exceptions.UserExistException;
import com.kibuti.socketserver.GlobeAdvice.Exceptions.VerificationException;
import com.kibuti.socketserver.GlobeAuthentication.DTOs.UserLoginDTO;
import com.kibuti.socketserver.GlobeAuthentication.DTOs.UserRegisterDTO;
import com.kibuti.socketserver.GlobeResponseBody.GlobalJsonResponseBody;

import java.util.List;
import java.util.UUID;

public interface UserManagementService {
    GlobalJsonResponseBody registerUser(UserRegisterDTO userManagementDTO) throws UserExistException, RandomExceptions, JsonProcessingException;
    //Login
    GlobalJsonResponseBody loginUser(UserLoginDTO userLoginDTO) throws UserExistException, VerificationException;

    GlobalJsonResponseBody refreshToken(String refreshToken) throws RandomExceptions, TokenInvalidException;

   // GlobalJsonResponseBody oAuthLoginOrRegister(String authorizationCode, String provider) throws Exception;
    //Edit
    //View all
    List<GlobalJsonResponseBody> getAllUser();
    //View single
    GlobalJsonResponseBody getSingleUser(UUID uuid);
    //Approval
    //SetUserAccount
    //Send OTP

    GlobalJsonResponseBody resetPassword(String phoneNumber, String newPassword);
}
