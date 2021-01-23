package com.merlobranco.ranking.table.services;

import java.util.List;
import java.util.Map;

public interface ResultsService {
	
	public void addResults(List<String> results);
	
	public void addResult(String result);
	
	public void addResult(String team1, String team2);
	
	public void addResult(String[] data1, String[] data2);
	
	public void updatePts(String team, int pts);
	
	public String[] getTeamData(String team);
	
	public int getScore(String[] data);
	
	public Map<String, Integer> getResults();
}
