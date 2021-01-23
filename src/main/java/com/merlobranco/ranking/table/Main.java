package com.merlobranco.ranking.table;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.merlobranco.ranking.table.services.FileService;
import com.merlobranco.ranking.table.services.FileServiceImpl;
import com.merlobranco.ranking.table.services.PrintingService;
import com.merlobranco.ranking.table.services.PrintingServiceImpl;
import com.merlobranco.ranking.table.services.ResultsService;
import com.merlobranco.ranking.table.services.ResultsServiceImpl;


/**
 * Application that provides the final ranking table of a teams list
 * from a collection of results stored in several folders or files
 * @author brais
 *
 */
public class Main {
	
	private static final String PRINT = "print";
	private static final String EXIT = "exit";
	private static final String FILE = "file";
	private static final String FOLDER = "folder";
	
	private static ResultsService resultsService = new  ResultsServiceImpl();
	private static PrintingService printingService = new PrintingServiceImpl();
	private static FileService fileService = new FileServiceImpl();
	
	private static String line = "";
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		while (!line.equals(EXIT)) {
			System.out.println("Welcome to Ranking Table");
			System.out.println("Avaliable entries:");
			System.out.println("	file <<file absolute path>>");
			System.out.println("	folder <<folder absolute path>>");
			System.out.println("	print");
			System.out.println("	exit");
			System.out.println("What do you want to do");
			line = in.nextLine();
			process(line);
			System.out.println();
		}
		
		in.close();
		System.out.println("Goodbye!");
	}
	
	/**
	 * Processing the provided commands
	 * @param commands
	 */
	private static void process(String commands) {
		if (commands == null || commands.equals(EXIT))
			return;
		
		if (commands.equals(PRINT)) {
			Map<String, Integer> resultsMap = resultsService.getResults();
			printingService.printRanking(resultsMap);
			return;
		}
		
		if (commands.startsWith(FILE)) {
			String path = commands.replace(FILE + " ", "");
			List<String> results = fileService.getContentFile(path);
			resultsService.addResults(results);
			return;
		}
		
		if (commands.startsWith(FOLDER)) {
			String path = commands.replace(FOLDER + " ", "");
			List<String> results = fileService.getContentFolder(path);
			resultsService.addResults(results);
		}
	}

}
