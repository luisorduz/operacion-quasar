package com.quasar.orduz.adapters.rest.controller;

import com.quasar.orduz.adapters.rest.dto.SatelliteDto;
import com.quasar.orduz.adapters.rest.exceptions.ApiErrorResponse;
import com.quasar.orduz.adapters.rest.dto.TopSecretRequest;
import com.quasar.orduz.adapters.rest.dto.TopSecretResponse;
import com.quasar.orduz.domain.model.Satellite;
import com.quasar.orduz.ports.service.CommunicationManager;
import com.quasar.orduz.ports.service.SatelliteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class CommunicationController {

    @Autowired
    CommunicationManager communicationManager;
    @Autowired
    SatelliteManager satelliteManager;

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

    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<Object> topSecretSplit(@PathVariable("satellite_name") String satelliteName,
                                                       @RequestBody SatelliteDto satelliteDto) {
        try {
            Satellite satellite = satelliteManager.updateDistanceAndMessageByName(satelliteDto.getDistance(),
                    satelliteDto.getMessage(),satelliteName);

            return ResponseEntity.ok(satellite);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND,
                    e.getMessage(), e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/topsecret_split_by_name/{satellite_name}")
    public ResponseEntity<Object> topSecretSplitByName(@PathVariable("satellite_name") String satelliteName) {
        try {
            Satellite satellite = satelliteManager.findByName(satelliteName);
            return ResponseEntity.ok(satellite);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND,
                    e.getMessage(), e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_all_topsecret/")
    public ResponseEntity<Object> getAllTopSecret() {
        try {
            List<Satellite> satellites = satelliteManager.getAllSatellites();
            TopSecretRequest topSecretRequest = new TopSecretRequest();
            topSecretRequest.setSatellites(satellites);
            return ResponseEntity.ok(topSecretRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND,
                    e.getMessage(), e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
        }
    }
}


