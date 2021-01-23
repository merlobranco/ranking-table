package com.merlobranco.ranking.table.services;

import java.nio.file.Path;
import java.util.List;

public interface FileService {
	
	public List<String> getContentFile(String absolutePath);
	
	public List<String> getContent(Path path);
	
	public List<String> getContentFolder(String absolutePath);

}
