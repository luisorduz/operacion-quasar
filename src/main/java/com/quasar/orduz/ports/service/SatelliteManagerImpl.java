package com.quasar.orduz.ports.service;

import com.quasar.orduz.adapters.rest.exceptions.GenericException;
import com.quasar.orduz.adapters.springdata.entity.SatelliteEntity;
import com.quasar.orduz.adapters.springdata.mapper.SatelliteMapperDbo;
import com.quasar.orduz.adapters.springdata.repository.SatelliteRepository;
import com.quasar.orduz.domain.model.Satellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatelliteManagerImpl implements SatelliteManager {

    @Autowired
    SatelliteMapperDbo satelliteMapperDbo;

    private final SatelliteRepository satelliteRepository;

    @Autowired
    public SatelliteManagerImpl(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }
    @Override
    public Satellite saveSatellite(Satellite satellite) {
         return satelliteMapperDbo.toDomain(satelliteRepository.save(satelliteMapperDbo.toDbo(satellite)));
    }
    @Override
    public List<Satellite> getAllSatellites() {
        return satelliteMapperDbo.toDomains(satelliteRepository.findAll());
    }
    @Override
    public Satellite getSatelliteById(Long id) {
        return satelliteMapperDbo.toDomain(satelliteRepository.findById(id).orElse(null));
    }
    @Override
    public Satellite findByName(String name) {
        SatelliteEntity satelliteEntity = satelliteRepository.findByName(name);
        if (satelliteEntity == null) {
            throw new GenericException("Satellite con nombre '" + name + "' no existe o no hay suficiente información.");
        }
        return satelliteMapperDbo.toDomain(satelliteEntity);
    }
    @Override
    public Satellite updateDistanceAndMessageByName(double distance, List<String> message, String name) {
        SatelliteEntity satelliteEntity = satelliteRepository.findByName(name);
        if (satelliteEntity == null) {
            throw new GenericException("Satellite con nombre '" + name + "' no existe o no hay suficiente información.");
        }

        String joinedMessage = String.join(",", message);
        satelliteRepository.updateDistanceAndMessageByName(distance, joinedMessage, name);

        Satellite satellite = satelliteMapperDbo.toDomain(satelliteRepository.findByName(name));
        return satellite;

    }


}