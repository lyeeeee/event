package com.rcd.iotsubsys.web.rest;

import com.alibaba.fastjson.JSONObject;
import com.rcd.iotsubsys.security.jwt.JWTFilter;
import com.rcd.iotsubsys.security.jwt.TokenProvider;
import com.rcd.iotsubsys.util.VerifyIdentity;
import com.rcd.iotsubsys.web.rest.vm.LoginVM;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.Map;

/**
 * Controller to authenticate users.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private static Logger logger = LoggerFactory.getLogger(UserJWTController.class);

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    @Timed
//    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM, @RequestBody) {
    public ResponseEntity<JWTToken> authorize(@RequestParam String username, @RequestParam String password
        , @RequestBody MultipartFile file) {
        LoginVM loginVM = new LoginVM();
        loginVM.setUsername(username);
        loginVM.setPassword(password);
        InputStream in = null;
        FileOutputStream out = null;
        int code = 0;
        try {
            in = file.getInputStream();
            logger.info("begin ca verify identity");
            File cafile = new File("./admin.pem");
            if (cafile.exists()) {
                cafile.delete();
                cafile = new File("./admin.pem");
            }
            out = new FileOutputStream(cafile);
            IOUtils.copy(in, out);
            File file1 = new File("./admin.pem");
            String resp = VerifyIdentity.VerifyIdentity("admin", file1.getAbsolutePath());
            JSONObject parse = (JSONObject) JSONObject.parse(resp);
            String status = parse.getString("code");
            code = Integer.parseInt(status);
            logger.info("end ca verify identity, code:{}", code);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            code = -1;
        }
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYyMzg0NDk1Mn0.ae12K0hOur6g7bbxGN1VQM95xCdxvEEhaIlKDzSA_AJJCXYRdJYvjgzOZ6YXmzqYAERwTn6J74_zPY7o4y95mg";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        if (code == -1) {
            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        }
    }


    @PostMapping("/validSSO")
    @Timed
    public ResponseEntity<JWTToken> validSSO(@RequestBody Map<String, String> map) throws Exception {
        String token = map.get("token");
        logger.info("receive token:{}", token);
        boolean valid = tokenProvider.validateToken(token);
        if (valid) {
            logger.info("token valid...");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + token);
            return new ResponseEntity<>(new JWTToken(token), httpHeaders, HttpStatus.OK);
        } else {
            logger.info("token invalid...");
            throw new Exception();
        }
//        LoginVM loginVM = new LoginVM();
//        loginVM.setUsername(map.get("username"));
//        loginVM.setPassword(map.get("password"));
//        UsernamePasswordAuthenticationToken authenticationToken =
//            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
//
//        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
//        String jwt = tokenProvider.createToken(authentication, rememberMe);
//        jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTYyMzg0NDk1Mn0.ae12K0hOur6g7bbxGN1VQM95xCdxvEEhaIlKDzSA_AJJCXYRdJYvjgzOZ6YXmzqYAERwTn6J74_zPY7o4y95mg"

    }
    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
