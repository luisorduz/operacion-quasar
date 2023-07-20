package com.quasar.orduz.ports.service;

import com.quasar.orduz.adapters.rest.dto.TopSecretRequest;
import com.quasar.orduz.adapters.rest.dto.TopSecretResponse;
import com.quasar.orduz.domain.model.Point;
import com.quasar.orduz.domain.model.Satellite;

import java.util.List;

public interface CommunicationManager {

    TopSecretResponse getLocationAndMessage(TopSecretRequest topSecretRequest) throws Exception;

    // Método para obtener la ubicación del emisor del mensaje
    Point getLocation(List<Satellite> satellites) throws Exception;

    // Método para obtener el mensaje original que emite el emisor
    String getMessage(List<Satellite> satellites) throws Exception;

}
