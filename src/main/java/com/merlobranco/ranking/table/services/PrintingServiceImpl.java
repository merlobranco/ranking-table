package com.merlobranco.ranking.table.services;

import java.util.Map;

/**
 * Service that manages the printing of a collection of match results
 * @author brais
 *
 */
public class PrintingServiceImpl implements PrintingService {
	
	private int pos; 
	private int realPos;
	private int pts = Integer.MAX_VALUE;

	/**
	 * Prints the provided collections of results in the right order
	 * @param resultsMap
	 */
	@Override
	public void printRanking(Map<String, Integer> resultsMap) {
		pos = 0;
		realPos = 0;
		
		if (resultsMap == null || resultsMap.isEmpty()) {
			System.out.println("NO results available");
			return;
		}
		
		System.out.println("Ranking Table");
		System.out.println();
		resultsMap.entrySet()
		.stream()
		.sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()) == 0 ? e1.getKey().compareTo(e2.getKey()) : e1.getValue().compareTo(e2.getValue()) * -1)
		.forEach(e -> printRanking(e.getKey(), e.getValue()));
	}

	/**
	 * Prints the provided result
	 * @param team
	 * @param points
	 */
	@Override
	public void printRanking(String team, Integer points) {
		++realPos;
		if (pts != points) {
			pos = realPos;
		}
		pts = points;
		StringBuilder sb = new StringBuilder();
		sb.append(pos).append(". ").append(team).append(", ").append(points).append(" pt");
		if (points != 1)
			sb.append("s");
		
		System.out.println(sb.toString());
	}

	@Override
	public int getPosition() {
		return pos;
	}
}
