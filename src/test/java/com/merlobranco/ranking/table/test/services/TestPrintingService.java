package com.merlobranco.ranking.table.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.merlobranco.ranking.table.services.PrintingService;
import com.merlobranco.ranking.table.services.PrintingServiceImpl;

public class TestPrintingService {
	
	private static PrintingService printingService;
	
	@BeforeAll
	public static void setUp() {
		printingService = new PrintingServiceImpl();
	}
	
	
	@Test
    public void testPrintRanking_whenNoData() {
		printingService.printRanking(null);
        assertEquals(0, printingService.getPosition());
    }
	
	@Test
    public void testPrintRanking_whenData() {
		int pos = printingService.getPosition();
		Map<String, Integer> map = new HashMap<>();
		map.put("Team1", 3);
		map.put("Team2", 7);
		printingService.printRanking(map);
        assertTrue(printingService.getPosition() > pos);
    }
	
	@Test void testprintRanking_whenTie() {
		int pos = printingService.getPosition();
		printingService.printRanking("Team1", 3);
		printingService.printRanking("Team2", 3);
		assertEquals(pos + 1, printingService.getPosition());
	} 
	
	@Test void testprintRanking_whenNoTie() {
		int pos = printingService.getPosition();
		printingService.printRanking("Team1", 3);
		printingService.printRanking("Team2", 5);
		assertEquals(pos + 2, printingService.getPosition());
	} 
}
