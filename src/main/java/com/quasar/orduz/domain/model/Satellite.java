package com.quasar.orduz.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class Satellite {

    @NotEmpty(message = "El campo 'name' no puede estar vacío")
    private String name;

    @NotNull(message = "El campo 'distance' no puede ser nulo")
    private float distance;

    @NotEmpty(message = "El campo 'message' no puede estar vacío")
    @Size(min = 1, message = "El campo 'message' debe contener al menos un mensaje")
    private List<String> message;

    public Satellite(String name, float distance, List<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

}
