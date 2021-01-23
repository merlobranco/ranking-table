package com.merlobranco.ranking.table.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.merlobranco.ranking.table.services.ResultsService;
import com.merlobranco.ranking.table.services.ResultsServiceImpl;

public class TestResultsService {
	
	private static ResultsService resultsService;
	private String team = "Lions";
	private String team2 = "Snakes";
	private int result;
	private String[] results = {
			"Lions 3, Snakes 3",
			"Tarantulas 1, FC Awesome 0",
			"Lions 1, FC Awesome 1",
			"Tarantulas 3, Snakes 1",
			"Lions 4, Grouches 0" };
	private String[] t = {"Lions", "3"};
	private String[] t2 = {"Snakes", "3"};
	
	@BeforeAll
	public static void setUp() {
		resultsService = new ResultsServiceImpl();
	}
	

	@Test
	public void addResultsColection() {
		result = getResult(team);
		resultsService.addResults(Arrays.asList(results));
		assertTrue(result < getResult(team));
	}

	@Test
	public void addResultSingle() {
		result = getResult(team);
		resultsService.addResult(results[0]);
		assertTrue(result < getResult(team));
	}
	
	@Test
	public void addResultTeams() {
		result = getResult(team);
		resultsService.addResult(team + " " + 3, team2 + " " + 3);
		assertTrue(result < getResult(team));
	}
	
	@Test
	public void addResultTeamsFormatted() {
		result = getResult(t[0]);
		resultsService.addResult(t, t2);
		assertTrue(result < getResult(t[0]));
	}
	
	@Test
	public void updatePts() {
		int pts = 3;
		result = getResult(team);
		resultsService.updatePts(team, pts);
		assertEquals(result + pts, getResult(team));
	}
	
	@Test
	public void getTeamData() {
		assertTrue(resultsService.getTeamData(team + " " + 3).length == 2);
	}
	
	@Test
	public void getScore() {
		assertTrue(resultsService.getScore(t) > -1);
	}
	
	private int getResult(String team) {
		if (resultsService.getResults().containsKey(team))
			return resultsService.getResults().get(team);
		return -1;
	}
}
