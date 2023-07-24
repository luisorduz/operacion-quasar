package com.quasar.orduz.ports.service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.quasar.orduz.adapters.rest.dto.TopSecretRequest;
import com.quasar.orduz.adapters.rest.dto.TopSecretResponse;
import com.quasar.orduz.domain.model.Point;
import com.quasar.orduz.domain.model.Satellite;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.quasar.orduz.adapters.config.constans.SatelliteConstants.SATELLITE_POSITIONS;

@Service
public class CommunicationManagerImpl implements CommunicationManager {

    @Override
    public TopSecretResponse getLocationAndMessage(TopSecretRequest topSecretRequest) throws Exception {
        List<Satellite> satellites = topSecretRequest.getSatellites();
        Point location = getLocation(satellites);
        String message = getMessage(satellites);

        if (location == null) {
            throw new Exception("No se pudo determinar la ubicación.");
        }

        if (message == null) {
            throw new Exception("No se pudo determinar el mensaje.");
        }

        return new TopSecretResponse(location, message);
    }

    // Método para obtener la ubicación del emisor del mensaje
    @Override
    public Point getLocation(List<Satellite> satellites) throws Exception {
        if (satellites == null || satellites.size() != 3) {
            throw new Exception("Debe proporcionar exactamente tres satélites.");
        }

        double[] distances = satellites.stream()
                .mapToDouble(Satellite::getDistance)
                .toArray();

        double[] location = trilateration(SATELLITE_POSITIONS, distances);

        return new Point(location[0], location[1]);
    }

    // Método para obtener el mensaje original que emite el emisor
    @Override
    public String getMessage(List<Satellite> satellites) throws Exception {
        if (satellites == null || satellites.size() != 3) {
            throw new Exception("Debe proporcionar exactamente tres satélites.");
        }

        int maxMessageLength = satellites.stream()
                .mapToInt(satellite -> satellite.getMessage().size())
                .max()
                .orElse(0);

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < maxMessageLength; i++) {
            int finalI = i;
            List<String> words = satellites.stream()
                    .map(satellite -> getWordAtIndex(satellite.getMessage(), finalI))
                    .collect(Collectors.toList());

            // Reemplazar palabras desconocidas por un string en blanco
            List<String> validWords = words.stream()
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.toList());

            String commonWord = getCommonWord(validWords);
            message.append(commonWord).append(" ");
        }

        return message.toString().trim();
    }

    // Método para obtener la palabra en la posición dada
    private String getWordAtIndex(List<String> message, int index) {
        if (index >= 0 && index < message.size()) {
            return message.get(index);
        } else {
            return "";
        }
    }


    // Método para obtener la palabra común en los mensajes
    private String getCommonWord(List<String> words) {
        if (words.isEmpty()) {
            return "";
        }

        String firstWord = words.get(0);
        boolean allWordsEqual = words.stream().allMatch(word -> word.equals(firstWord));

        if (allWordsEqual) {
            return firstWord;
        } else {
            return "";
        }
    }

    // Método para el cálculo de trilateración utilizando la librería com.lemmingapex.trilateration
    public double[] trilateration(double[][] positions, double[] distances) {

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return nSolver.solve().getPoint().toArray();
    }
}
