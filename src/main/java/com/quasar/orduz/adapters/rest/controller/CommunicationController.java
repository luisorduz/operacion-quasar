package com.quasar.orduz.adapters.rest.controller;

import com.quasar.orduz.adapters.rest.exceptions.ApiErrorResponse;
import com.quasar.orduz.adapters.rest.dto.TopSecretRequest;
import com.quasar.orduz.adapters.rest.dto.TopSecretResponse;
import com.quasar.orduz.ports.service.CommunicationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class CommunicationController {

    @Autowired
    CommunicationManager communicationManager;

    @PostMapping("/topsecret")
    public ResponseEntity<Object> getTopSecretData(@Valid @RequestBody TopSecretRequest topSecretRequest) {
        try {
            TopSecretResponse response = communicationManager.getLocationAndMessage(topSecretRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND,
                    e.getMessage(), e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

}


