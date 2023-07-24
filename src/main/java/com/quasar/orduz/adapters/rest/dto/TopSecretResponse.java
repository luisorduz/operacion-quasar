package com.quasar.orduz.adapters.rest.dto;

import com.quasar.orduz.domain.model.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopSecretResponse {
    private Point position;
    private String message;

    public TopSecretResponse(Point position, String message) {
        this.position = position;
        this.message = message;
    }
}
