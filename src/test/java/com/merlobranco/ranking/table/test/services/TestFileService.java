package com.merlobranco.ranking.table.test.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.merlobranco.ranking.table.services.FileService;
import com.merlobranco.ranking.table.services.FileServiceImpl;

public class TestFileService {
	
	private static FileService fileService;
	
	@BeforeAll
	public static void setUp() {
		fileService = new FileServiceImpl();
	}
	
	@Test
	public void getContentFile_whenNoAbsolutePath() {
		 assertTrue(fileService.getContentFile(null).isEmpty());
	}
	
	@Test
	public void getContentFile_whenNoPath() {
		assertTrue(fileService.getContent(null).isEmpty());
	}
	
	@Test
	public void getContentFolder_whenNoAbsolutePath() {
		assertTrue(fileService.getContentFolder(null).isEmpty());
	}

}
