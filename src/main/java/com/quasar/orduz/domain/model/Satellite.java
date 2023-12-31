package com.quasar.orduz.domain.model;

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
public class Satellite {

    @NotEmpty(message = "El campo 'name' no puede estar vacío")
    private String name;

    @NotNull(message = "El campo 'distance' no puede ser nulo")
    private double distance;

    @NotEmpty(message = "El campo 'message' no puede estar vacío")
    @Size(min = 1, message = "El campo 'message' debe contener al menos un mensaje")
    private List<String> message;

    public Satellite(String name, double distance, List<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

}
