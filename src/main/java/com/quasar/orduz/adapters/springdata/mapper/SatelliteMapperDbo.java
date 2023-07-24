package com.quasar.orduz.adapters.springdata.mapper;

import com.quasar.orduz.adapters.springdata.entity.SatelliteEntity;
import com.quasar.orduz.domain.model.Satellite;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SatelliteMapperDbo {

    @Mappings({@Mapping(source = "name", target = "name"),
                @Mapping(source = "distance", target = "distance"),
                @Mapping(target = "message", expression = "java(listToString(satellite.getMessage()))")
    })

    SatelliteEntity toDbo(Satellite satellite);

    @InheritInverseConfiguration
    @Mapping(target = "message", expression = "java(stringToList(satelliteEntity.getMessage()))")
    Satellite toDomain(SatelliteEntity satelliteEntity);

    List<Satellite> toDomains(List<SatelliteEntity> satelliteEntities);

    default String listToString(List<String> message) {
        return String.join(",", message);
    }

    default List<String> stringToList(String message) {
        return Arrays.stream(message.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
