package iteration1;

//Contains options for easy passing as a function argument
//Initialized with Config.txt file
public class Configuration {
	public int numberOfSectionalHosts = 32;
	public int numberOfRegionalHosts = 16;
	public int numberOfSemiStateHosts = 4;
	public int[] minTeamsPerHost { 8, 2, 4 };
	
	public boolean useBreakpoints = false;
	public int[] classBreakpoints = {200, 400, 800};

	
	//TODO:MICAH
	public void loadFromFile(String filename) {
	}
}
