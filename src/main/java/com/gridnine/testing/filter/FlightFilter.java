package com.gridnine.testing.filter;


import static com.gridnine.testing.conditions.SegmentType.ARRIVAL;
import static com.gridnine.testing.conditions.SegmentType.DEPARTURE;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gridnine.testing.conditions.Operators;
import com.gridnine.testing.conditions.SegmentType;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

public class FlightFilter {
	private Map<Operators, Long> arrivalStatementsMap;
	private Map<Operators, Long> departureStatementsMap;
	private Map<Operators, Long> fieldTimeStatementsMap;
	private boolean invalidFlightFilter = false;
	private boolean useParallelStream = false;
	
	public FlightFilter(Map<Operators, Long> arrivalStatementsMap, Map<Operators, Long> departureStatementsMap,
			Map<Operators, Long> fieldTimeStatementsMap, boolean invalidFlightFilter) {
		
		this.arrivalStatementsMap = arrivalStatementsMap;
		this.departureStatementsMap = departureStatementsMap;
		this.fieldTimeStatementsMap = fieldTimeStatementsMap;
		this.invalidFlightFilter = invalidFlightFilter;
	}
	
	private Stream<Flight> getFlightStream(List<Flight> flightList) {
		if (useParallelStream == false) {
			return flightList.stream();
		}
		return flightList.parallelStream();
	}
	
	private List<Flight> conditionFilter(List<Flight> flightList, SegmentType segmentType, Map<Operators, Long> statementsMap) {
		List<Flight> filteredList = new ArrayList<>(flightList);
		for (Map.Entry<Operators, Long> condition : statementsMap.entrySet()) {
			filteredList = getFlightStream(filteredList)
					.filter(flight -> flight.getSegments().stream()
							.anyMatch(segment -> statementFilter(segment, segmentType, condition.getKey(), condition.getValue())))
					.collect(Collectors.toList());
		}
		return filteredList;
	}
	
	private long getSegmentEpochByType(Segment segment, SegmentType segmentType) {
		switch (segmentType) {
			case ARRIVAL:
				return segment.getArrivalDate().toEpochSecond(ZoneOffset.UTC);
			default:
			case DEPARTURE:
				return segment.getDepartureDate().toEpochSecond(ZoneOffset.UTC);
		}
	}
	
	private List<Flight> removeInvalidFlights(List<Flight> flightlist) {
		List<Flight> validFligthsList = new ArrayList<Flight>(flightlist);
		return validFligthsList.stream().filter(flight -> checkFlight(flight))
								  .collect(Collectors.toList());
	}
	
	private boolean checkFlight(Flight flight) {
		List<Segment> segmentsList = flight.getSegments();
		for (Segment segment : segmentsList) {
			long departureEpochTime = segment.getDepartureDate().toEpochSecond(ZoneOffset.UTC);
			long arrivalEpochTime = segment.getArrivalDate().toEpochSecond(ZoneOffset.UTC);
			if (departureEpochTime >= arrivalEpochTime) {
				return false;
			} 
		}
		return true;
	}
	private boolean statementFilter(Segment segment, SegmentType segmentType, Operators condition, long compareEpochTime) {
		switch (condition) {
			case GT: 
				return getSegmentEpochByType(segment, segmentType) > compareEpochTime;
			case GTE:
				return getSegmentEpochByType(segment, segmentType) >= compareEpochTime;
			case LT:
				return getSegmentEpochByType(segment, segmentType) < compareEpochTime;
			case LTE:
				return getSegmentEpochByType(segment, segmentType) <= compareEpochTime;
			case EQ:
				return getSegmentEpochByType(segment, segmentType) == compareEpochTime;
			default:
				return false;
		}
	}
	
	public List<Long> findFieldTime(Flight flight) {
		List<Segment> segments = flight.getSegments();
		List<Long> fieldTimeList = new ArrayList<>();
		long fieldEpochTime = 0;
		if (segments.size() < 2) {
			fieldTimeList.add(fieldEpochTime);
		} else {
			for (int i = 0; i < segments.size() - 1; i++) {
				Segment currSegment = segments.get(i);
				Segment nextSegment = segments.get(i+1);
				long currEpochArrivalTime = currSegment.getArrivalDate().toEpochSecond(ZoneOffset.UTC);
				long nextEpochDepartureTime = nextSegment.getDepartureDate().toEpochSecond(ZoneOffset.UTC);
				fieldEpochTime = nextEpochDepartureTime - currEpochArrivalTime;
				fieldTimeList.add(fieldEpochTime);
			}
		}
		return fieldTimeList;
	}
	
	private List<Flight> fieldTimeFilter(List<Flight> flightList) {
		List<Flight> filteredList = new ArrayList<>(flightList);
		for (Map.Entry<Operators, Long> condition : fieldTimeStatementsMap.entrySet()) {
			filteredList = getFlightStream(filteredList)
					.filter(flight -> findFieldTime(flight).stream()
							.anyMatch(fieldTime -> statementFilter(fieldTime, condition.getKey(), condition.getValue())))
					.collect(Collectors.toList());
		}
		return filteredList;
	}
	
	private boolean statementFilter(long fieldEpochTime, Operators condition, long compareEpochTime) {
		switch (condition) {
			case GT: 
				return fieldEpochTime > compareEpochTime;
			case GTE:
				return fieldEpochTime >= compareEpochTime;
			case LT:
				return fieldEpochTime < compareEpochTime;
			case LTE:
				return fieldEpochTime <= compareEpochTime;
			case EQ:
				return fieldEpochTime == compareEpochTime;
			default:
				return false;
		}
	} 
	
	public List<Flight> useFilter (List<Flight> flightList) {
		List<Flight> filteredList = new ArrayList<Flight>(flightList);
		
		if (!arrivalStatementsMap.isEmpty())
			filteredList = conditionFilter(filteredList, ARRIVAL, arrivalStatementsMap);
		if (!departureStatementsMap.isEmpty())
			filteredList = conditionFilter(filteredList, DEPARTURE, departureStatementsMap);
		if (!fieldTimeStatementsMap.isEmpty());
			filteredList = fieldTimeFilter(filteredList);
		if (invalidFlightFilter == true)
			filteredList = removeInvalidFlights(filteredList);
			
		return filteredList;
	}
	
	public FlightFilter parallelFilter() {
		useParallelStream = true;
		return this;
	}
	
	public FlightFilter sequentialFilter() {
		useParallelStream = false;
		return this;
	}
	
	
}
