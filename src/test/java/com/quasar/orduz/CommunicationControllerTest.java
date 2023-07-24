package com.quasar.orduz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasar.orduz.adapters.rest.controller.CommunicationController;
import com.quasar.orduz.adapters.rest.dto.SatelliteDto;
import com.quasar.orduz.adapters.rest.dto.TopSecretRequest;
import com.quasar.orduz.adapters.rest.dto.TopSecretResponse;
import com.quasar.orduz.domain.model.Point;
import com.quasar.orduz.domain.model.Satellite;
import com.quasar.orduz.ports.service.CommunicationManager;
import com.quasar.orduz.ports.service.SatelliteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CommunicationController.class)
public class CommunicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunicationManager communicationManager;

    @MockBean
    private SatelliteManager satelliteManager;

    private ObjectMapper objectMapper;

    private TopSecretRequest topSecretRequest;

    private TopSecretResponse topSecretResponse;

    private Point point;

    private SatelliteDto satelliteDto;

    private Satellite satellite;

    List<Satellite> satellites;

    @BeforeEach
    public void setup() {

        objectMapper = new ObjectMapper();

        topSecretRequest = new TopSecretRequest();
        topSecretResponse = new TopSecretResponse();
        satelliteDto = new SatelliteDto();
        satellite = new Satellite();
        point = new Point();

        satellites = new ArrayList<>();
        satellites.add(new Satellite("kenobi",100.0, Arrays.asList("este", "", "", "mensaje", "")));
        satellites.add(new Satellite("skywalker",115.5, Arrays.asList("", "es", "", "", "secreto")));
        satellites.add(new Satellite("sato",142.7, Arrays.asList("este", "", "un", "", "")));
        topSecretRequest.setSatellites(satellites);

        point.setX(-58.315252587138595);
        point.setY(-69.55141837312165);
        topSecretResponse.setPosition(point);
        topSecretResponse.setMessage("este es un mensaje secreto");

        satelliteDto.setDistance(100.0);
        satelliteDto.setMessage(Arrays.asList("este", "", "", "mensaje", ""));

        satellite = new Satellite("kenobi",100.0, Arrays.asList("este", "", "", "mensaje", ""));

    }

    @Test
    public void testGetTopSecretData() throws Exception {

        when(communicationManager.getLocationAndMessage(topSecretRequest)).thenReturn(topSecretResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/topsecret")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(topSecretRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testTopSecretSplit() throws Exception {

        String satelliteName = "kenobi";
        when(satelliteManager.updateDistanceAndMessageByName(satelliteDto.getDistance(),
                satelliteDto.getMessage(), satelliteName))
                .thenReturn(satellite);

        mockMvc.perform(MockMvcRequestBuilders.post("/topsecret_split/" + satelliteName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(satelliteDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testTopSecretSplitByName() throws Exception {

        String satelliteName = "kenobi";
        when(satelliteManager.findByName(any(String.class))).thenReturn(satellite);

        mockMvc.perform(MockMvcRequestBuilders.get("/topsecret_split_by_name/" + satelliteName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllTopSecret() throws Exception {
        // Mock
        when(satelliteManager.getAllSatellites()).thenReturn(satellites);

        mockMvc.perform(MockMvcRequestBuilders.get("/get_all_topsecret/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
