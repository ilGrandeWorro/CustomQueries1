package com.example.demo.services;

import com.example.demo.models.Flight;
import com.example.demo.models.Status;
import com.example.demo.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public String generateString(int length){
        Random random = new Random();
        return  random.ints(97, 122)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public List<Flight> provision50Flights(){
        List<Flight> flights = IntStream.range(1,51)
                .mapToObj(flight-> new Flight(
                        generateString(10),
                        generateString(10),
                        generateString(10)
                ))
                .toList();

        return flightRepository.saveAll(flights);
    }

    public List<Flight> getAll(){
        return flightRepository.findAll();
    }

}
