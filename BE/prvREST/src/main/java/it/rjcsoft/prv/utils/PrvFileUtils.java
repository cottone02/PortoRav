package it.rjcsoft.prv.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class PrvFileUtils {

	protected static final Logger log = LoggerFactory.getLogger(PrvFileUtils.class);

	private PrvFileUtils() {
	}

	public static File zipFile(String basePath, String pathStr, List<String> filePaths) {
		File zipFile = null;
		try {
			ZipFile zip = new ZipFile(pathStr);
			for (String filePath : filePaths) {
				File file = new File(basePath, filePath);
				log.trace("To add (exists={}) {}", file.exists(), file.getAbsolutePath());
				if (file.exists()) {
					zip.addFile(file);
				}
			}
			zipFile = zip.getFile();
		} catch (ZipException e) {
			log.warn("ZIP NOT CREATED, e={}", e.getMessage());
			zipFile = null;
		}
		return zipFile;
	}

	public static void deleteFile(File target) {
		log.debug("START - delete target={}", target);
		String filePath = null;
		try {
			if (!target.exists()) {
				return;
			}
			filePath = target.getAbsolutePath();
			Path path = target.toPath();
			Files.delete(path);
		} catch (IOException e) {
			log.error("Error during file deleting. File={}, {}", filePath, e.getMessage(), e);
		}
	}

	public static File saveFile(MultipartFile file, String directoryPath) throws IOException {
		
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			Files.createDirectories(directory.toPath());
		}
		String fileName = file.getOriginalFilename();
		File myFile = new File(directory, fileName);
		int i = 1;
		while (myFile.exists()) {
			String newFileName = fileName;
			newFileName = i + "_" + fileName;
			i++;
			myFile = new File(directory, newFileName);
		}

		try (FileOutputStream fos = new FileOutputStream(myFile)) {
			fos.write(file.getBytes());
		}
		
		return myFile;
	}
	
	public static File sostituisciFile(MultipartFile file, String oldFileName, String directoryPath) throws IOException {
		
		File oldFile = new File(directoryPath, oldFileName);
		if(oldFile.exists()) 
			deleteFile(oldFile);
			
		return saveFile(file, directoryPath);
		
	}
	
}
