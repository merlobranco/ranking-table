package com.merlobranco.ranking.table.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service that permits calculating and storing in memory a collection of match results
 * @author brais
 *
 */
public class ResultsServiceImpl implements ResultsService {
	
	private Map<String, Integer> resultsMap;

	public ResultsServiceImpl() {
		resultsMap = new HashMap<>();
	}
	
	/**
	 * Adding a collections of match results
	 * @param results
	 */
	@Override
	public void addResults(List<String> results) {
		for (String result: results)
			addResult(result);
	}

	/**
	 * Adding a match result:
	 * @param result: "team1 score1, team2 score2" 
	 * 
	 */
	@Override
	public void addResult(String result) {
		String[] teams = result.split(",");
		addResult(teams[0].trim(), teams[1].trim());
		
	}
	
	/**
	 * Adding 2 separated scores
	 * @param team1: "team1 score1" 
	 * @param team2: "team2 score2" 
	 */
	@Override
	public void addResult(String team1, String team2) {
		String[] data1 = getTeamData(team1);
		String[] data2 = getTeamData(team2);
		addResult(data1, data2);
	}
	
	/**
	 * Adding 2 separated scores
	 * @param data1: {"<<team1>>", "<<score1>>"} 
	 * @param data2: {"<<team2>>", "<<score2>>"}
	 */
	@Override
	public void addResult(String[] data1, String[] data2) {
		int score1 = getScore(data1);
		 int score2 = getScore(data2);
		 
		 if (score1 == score2) {
			 updatePts(data1[0], 1);
			 updatePts(data2[0], 1);
			 return;
		 }
		 updatePts(score1 > score2 ? data1[0] : data2[0], 3);
		 updatePts(score1 > score2 ? data2[0] : data1[0], 0);
	}

	/**
	 * Adding the provided points to the selected team
	 * @param team
	 * @param pts
	 */
	@Override
	public void updatePts(String team, int pts) {
		int currentPts = 0;
		if (resultsMap.containsKey(team)) {
			 currentPts = resultsMap.get(team);
		}
		currentPts += pts;
		resultsMap.put(team,  currentPts);
	}

	/**
	 * Extracting the pair <<team name, score>> from the the provide team result
	 * @param team: team1 score1
	 * @return {"<<team1>>", "<<score1>>"} 
	 */
	@Override
	public String[] getTeamData(String team) {
		int pos = team.lastIndexOf(" ");
		return new String[] { team.substring(0, pos),  team.substring(pos + 1, team.length())};	
	}

	/**
	 * Getting the score from the formated team data
	 *  @param data: {"<<team1>>", "<<score1>>"}
	 *  @return score1
	 */
	@Override
	public int getScore(String[] data) {
		try {
			return Integer.parseInt(data[1]); 
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}

	@Override
	public Map<String, Integer> getResults() {
		return resultsMap;
	}
}
