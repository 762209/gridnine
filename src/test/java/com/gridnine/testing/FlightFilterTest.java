package com.gridnine.testing;

import static org.junit.Assert.assertArrayEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gridnine.testing.builders.FlightBuilder;
import com.gridnine.testing.builders.FlightFilterBuilder;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;

public class FlightFilterTest {
	
    @Test
    public void departureGtTest() {
    	LocalDateTime date = LocalDateTime.now().plusDays(3).plusHours(5);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.departure()
    												.gt(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(5));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void departureGteTest() {
    	LocalDateTime date = LocalDateTime.now().plusDays(3).plusHours(6);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.departure()
    												.gte(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(5));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void departureLtTest() {
    	LocalDateTime date = LocalDateTime.now().minusDays(3).plusHours(1);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.departure()
    												.lt(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(2));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void departureLteTest() {
    	LocalDateTime date = LocalDateTime.now().minusDays(3);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.departure()
    												.lte(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(2));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void departureEqTest() {
    	LocalDateTime date = LocalDateTime.now().minusDays(3);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.departure()
    												.eq(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(2));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void arrivalGtTest() {
    	LocalDateTime date = LocalDateTime.now().plusDays(3).plusHours(6);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.arrival()
    												.gt(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(5));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void arrivalGteTest() {
    	LocalDateTime date = LocalDateTime.now().plusDays(3).plusHours(7);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.arrival()
    												.gte(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(5));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void arrivalLtTest() {
    	LocalDateTime date = LocalDateTime.now().plusDays(3).minusHours(5);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.arrival()
    												.lt(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(3));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void arrivalLteTest() {
    	LocalDateTime date = LocalDateTime.now().plusDays(3).minusHours(6);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.arrival()
    												.lte(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(3));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void arrivalEqTest() {
    	LocalDateTime date = LocalDateTime.now().plusDays(3).minusHours(6);
    	
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.arrival()
    												.eq(date.toEpochSecond(ZoneOffset.UTC))
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(3));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void fieldTimeGtTest() {
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.fieldTime()
    												.gt(2*60*60)
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(4));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void fieldTimeGteTest() {
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.fieldTime()
    												.gte(3*60*60)
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(4));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void fieldTimeLtTest() {
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.fieldTime()
    												.lt(1*60*60)
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(0), flights.get(2), flights.get(3));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void fieldTimeLteTest() {
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.fieldTime()
    												.lte(0*60*60)
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(0), flights.get(2), flights.get(3));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void fieldTimeEqTest() {
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.fieldTime()
    												.lte(0*60*60)
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights = List.of(flights.get(0), flights.get(2), flights.get(3));
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
    
    @Test
    public void invalidFlightFilterTest() {
    	FlightFilterBuilder filterBuilder = new FlightFilterBuilder();
    	FlightFilter flightFilter = filterBuilder.fieldTime()
    												.invalidFlightFilterOn()
    											 .build();
    	
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        List<Flight> filtredFlights = flightFilter.useFilter(flights);
        flights.remove(3);
        
        assertArrayEquals(flights.toArray(), filtredFlights.toArray());
    }
}
