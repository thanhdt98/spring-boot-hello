package com.thanhxv.service;

import com.thanhxv.exception.BlogapiException;
import com.thanhxv.model.user.User;
import com.thanhxv.payload.request.SignUpRequest;

public interface AuthService {
    public void checkCredentials(SignUpRequest signUpRequest) throws BlogapiException;
    public User registerUser(SignUpRequest signUpRequest);

}
