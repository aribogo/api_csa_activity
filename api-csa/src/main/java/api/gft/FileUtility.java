package api.gft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtility {
	
	@Value("${file.root.path}")
	private String root;
	
	@Value("${file.csa.folder}")
	private String imageDirectory;

	
	public void saveFile( MultipartFile file, String fileParticipant) {
		Path directoryPath = Paths.get(this.root, imageDirectory + fileParticipant);
		Path filePath = directoryPath.resolve(file.getOriginalFilename());
		
		try {
			Files.createDirectories(directoryPath);
			file.transferTo(filePath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problems trying to save the file", e);
		}		
	}

	public void saveFile(byte[] file, String fileParticipant, String fileName) {
		Path directoryPath = Paths.get(this.root, imageDirectory + fileParticipant);
		
		try {
			Files.createDirectories(directoryPath);
			new FileOutputStream(new File(directoryPath.toString()+"/"+fileName)).write(file);			
		} catch (IOException e) {
			throw new RuntimeException("Problems trying to save the file", e);
		}
		
	}
	
	public byte[] readFile(String fileParticipant, String fileName) {
		Path directoryPath = Paths.get(this.root, imageDirectory + fileParticipant+"/"+fileName);
		
		try {
			File file = directoryPath.toFile();
			
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			throw new RuntimeException("Problems trying to save the file", e);
		}
		
	}
}
