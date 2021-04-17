package com.gridnine.testing.builders;

import static com.gridnine.testing.conditions.Operators.EQ;
import static com.gridnine.testing.conditions.Operators.GT;
import static com.gridnine.testing.conditions.Operators.GTE;
import static com.gridnine.testing.conditions.Operators.LT;
import static com.gridnine.testing.conditions.Operators.LTE;

import java.util.EnumMap;
import java.util.Map;

import com.gridnine.testing.conditions.Operators;
import com.gridnine.testing.filter.FlightFilter;

public class FlightFilterBuilder {
	private final Map<Operators, Long> arrivalStatementsMap = new EnumMap<>(Operators.class);
	private final Map<Operators, Long> departureStatementsMap = new EnumMap<>(Operators.class); 
	private final Map<Operators, Long> fieldTimeStatementsMap = new EnumMap<>(Operators.class);
	private Map<Operators, Long> targetStatementsMap;
	private boolean invalidFlightFilter = false;
	
	private void checkTargetCriteria() {
		if (targetStatementsMap == null)
			throw new IllegalStateException("Use one of the following operators: arrival(), departure(), fieldTime()",
					new NullPointerException("targetStatementsMap = null"));
	}
	
	public FlightFilterBuilder arrival() {
		targetStatementsMap = arrivalStatementsMap;
		return this;
	}
	
	public FlightFilterBuilder departure() {
		targetStatementsMap = departureStatementsMap;
		return this;
	}
	
	public FlightFilterBuilder fieldTime() {
		targetStatementsMap = fieldTimeStatementsMap;
		return this;
	}
	
	public FlightFilterBuilder gt(long epochTime) {
		checkTargetCriteria();
		targetStatementsMap.put(GT, epochTime);
		return this;
	}
	
	public FlightFilterBuilder gte(long epochTime) {
		checkTargetCriteria();
		targetStatementsMap.put(GTE, epochTime);
		return this;
	}
	
	public FlightFilterBuilder lt(long epochTime) {
		checkTargetCriteria();
		targetStatementsMap.put(LT, epochTime);
		return this;
	}
	
	public FlightFilterBuilder lte(long epochTime) {
		checkTargetCriteria();
		targetStatementsMap.put(LTE, epochTime);
		return this;
	}
	
	public FlightFilterBuilder eq(long epochTime) {
		checkTargetCriteria();
		targetStatementsMap.put(EQ, epochTime);
		return this;
	}
	
	public FlightFilterBuilder invalidFlightFilterOn() {
		invalidFlightFilter = true;
		return this;
	}
	
	public FlightFilterBuilder invalidFlightFilterOff() {
		invalidFlightFilter = false;
		return this;
	}
	
	
	public FlightFilter build() {
		return new FlightFilter(arrivalStatementsMap, departureStatementsMap, fieldTimeStatementsMap, invalidFlightFilter);
	}
}
