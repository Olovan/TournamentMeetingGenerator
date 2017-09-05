package iteration1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;

//Contains options for easy passing as a function argument
//Initialized with Config.txt file
public class Configuration implements Serializable{
	public int numberOfSectionalHosts = 32;
	public int numberOfRegionalHosts = 16;
	public int numberOfSemiStateHosts = 4;
	public int[] minTeamsPerHost = { 8, 2, 4 };

	public boolean useBreakpoints = false;
	public int numberOfClasses = 4;
	public int[] classBreakpoints = {200, 400, 800};
	public int selectedBreakpoint = 0;

	public Configuration() {
		
	}
	
	public Configuration(String filename) {
		loadFromFile(filename);
	}


	public void loadFromFile(String filename) {
		try {
			BufferedReader reader = new BufferedReader( new FileReader(filename) );

			String line;
			while((line = reader.readLine()) != null)
			{
				parseString(line);
			}

			reader.close();
		} catch(Exception e) {
			System.out.println("Unable to load configuration file. Exception has been thrown");
		}
	}
	
	public void parseString(String input) {
		String[] tokens = input.split(" ");

		switch(tokens[0].toUpperCase())
		{
			case "NUMBER_OF_SECTIONAL_HOSTS":
				numberOfSectionalHosts = Integer.parseInt(tokens[1]);
				break;
			case "NUMBER_OF_REGIONAL_HOSTS":
				numberOfRegionalHosts = Integer.parseInt(tokens[1]);
				break;
			case "NUMBER_OF_SEMI-STATE_HOSTS":
				numberOfSemiStateHosts = Integer.parseInt(tokens[1]);
				break;
			case "USE_BREAKPOINTS":
				useBreakpoints = Boolean.parseBoolean(tokens[1]);
				break;
			case "NUMBER_OF_CLASSES":
				numberOfClasses = Integer.parseInt(tokens[1]);
				break;
			case "CLASS_BREAKPOINTS":
				numberOfClasses = tokens.length;
				classBreakpoints = new int[numberOfClasses - 1];
				for(int i = 1; i < tokens.length; i++) {
					classBreakpoints[i - 1] = Integer.parseInt(tokens[i]);
				}
				break;
			case "MINIMUM_TEAMS_PER_HOST":
				for(int i = 0; i < 3; i++) {
					minTeamsPerHost[i] = Integer.parseInt(tokens[i + 1]);
				}
				break;
			case "SELECTED_BREAKPOINT":
				selectedBreakpoint = Integer.parseInt(tokens[1]);
				break;
			default:
				break;
		}
	}
}
