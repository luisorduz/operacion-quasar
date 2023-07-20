package com.quasar.orduz.adapters.rest.dto;

import com.quasar.orduz.domain.model.Satellite;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class TopSecretRequest {

    @Valid
    @NotEmpty(message = "La lista de satélites no puede estar vacía")
    @Size(min = 3, max = 3, message = "Debe proporcionar exactamente tres satélites")
    private List<Satellite> satellites;

}
