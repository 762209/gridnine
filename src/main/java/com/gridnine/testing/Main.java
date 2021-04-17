package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import com.gridnine.testing.builders.FlightBuilder;
import com.gridnine.testing.builders.FlightFilterBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

public class Main {
	public static void main(String[] args) {
		FlightFilterBuilder flightFilterBuilder = new FlightFilterBuilder();
		LocalDateTime currentTime = LocalDateTime.now();
		
		List<Flight> flightList = FlightBuilder.createFlights();
		
		FlightFilter filter = flightFilterBuilder.departure()
													.gt(currentTime.toEpochSecond(ZoneOffset.UTC))
												 .invalidFlightFilterOn()
												 .fieldTime().gt(2*60*60)
												 .build();
		List<Flight> filtredFlightList = filter.parallelFilter().useFilter(flightList);
		filtredFlightList = filter.sequentialFilter().useFilter(flightList);
		
		filtredFlightList.stream().forEach(System.out::println);
	}
}
