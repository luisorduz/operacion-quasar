package com.quasar.orduz.ports.service;

import com.quasar.orduz.domain.model.Satellite;

import java.util.List;

public interface SatelliteManager {

    Satellite saveSatellite(Satellite satellite);

    List<Satellite> getAllSatellites();

    Satellite getSatelliteById(Long id);

    Satellite findByName(String name) throws Exception;

    Satellite updateDistanceAndMessageByName(double distance, List<String> message, String name) throws Exception;
}
