package com.quasar.orduz.adapters.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SatelliteDto {
    @NotNull(message = "El campo 'distance' no puede ser nulo")
    private double distance;

    @NotEmpty(message = "El campo 'message' no puede estar vacío")
    @Size(min = 1, message = "El campo 'message' debe contener al menos un mensaje")
    private List<String> message;

    public SatelliteDto(double distance, List<String> message) {
        this.distance = distance;
        this.message = message;
    }
}
