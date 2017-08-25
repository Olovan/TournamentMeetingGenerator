package iteration1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadSchoolFile {
	
	// TODO:QUENTIN
	public static List<School> GetSchoolsFromFile (String fileName) {
		List<School> schools = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try (BufferedReader fileBR = Files.newBufferedReader(pathToFile,
				StandardCharsets.US_ASCII)) {
		
			// Read the first line from csv file
			String line = fileBR.readLine();
			
			// Reads a single line until there are none left to read
			while (line != null) {
				// A token to take 6 columns separated by commas
				String[] schoolInfo  = line.split(",");
					
				School school = SetSchoolInfo(schoolInfo);
				
				// Adds to ArrayList
				schools.add(school);
				
				// Read next line
				// If EOF, line is null
				line = fileBR.readLine();	
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return schools;
	}
	
	private static School SetSchoolInfo (String[] schoolInfo) {
		String name = schoolInfo[0];
		int total = Integer.parseInt(schoolInfo[1]);
		int part = Integer.parseInt(schoolInfo[2]);
		int hostSectionals = Integer.parseInt(schoolInfo[3]);
		int hostRegionals = Integer.parseInt(schoolInfo[4]);
		int hostSemiState = Integer.parseInt(schoolInfo[5]);
		
		return new School(name, total, part, hostSectionals, hostRegionals, hostSemiState);
	}
}