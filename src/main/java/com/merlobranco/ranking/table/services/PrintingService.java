package com.merlobranco.ranking.table.services;

import java.util.Map;

public interface PrintingService {
	
	public void printRanking(Map<String, Integer> resultsMap); 
	
	public void printRanking(String team, Integer points);
	
	public int getPosition();

}
