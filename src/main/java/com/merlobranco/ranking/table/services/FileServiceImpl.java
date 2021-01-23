package com.merlobranco.ranking.table.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reading matches results from the provided folders or files
 * @author brais
 *
 */
public class FileServiceImpl implements FileService {
	
	/**
	 * Getting a list of matches results from the provided file
	 * @param absolutePath: of the file
	 * @return list of read matches results
	 */
	public List<String> getContentFile(String absolutePath) {
		if (absolutePath == null || absolutePath.isEmpty())
			return new ArrayList<>();
		
		Path path = Paths.get(absolutePath);
		return getContent(path);
	}
	
	/**
	 * Getting a list of matches results from the provided file
	 * @param path: of the file
	 * @return list of read matches results
	 */
	public List<String> getContent(Path path) {
		if (path == null)
			return new ArrayList<>();
		
		try {
			return Files.lines(path).collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/**
	 * Getting a list of matches results from the provided folder
	 * @param absolutePath: of the folder
	 * @return list of read matches results
	 */
	public List<String> getContentFolder(String absolutePath) {
		if (absolutePath == null || absolutePath.isEmpty())
			return new ArrayList<>();
		
		List<String> totalResults = new ArrayList<>();
		try {
			((Stream<Path>)Files.walk(Paths.get(absolutePath)))
					.filter(Files::isRegularFile)
			        .forEach(p -> {
			        	List<String> results = getContent(p);
			        	totalResults.addAll(results);
			        });
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return totalResults;
	}

}
