package iteration1;

import java.util.ArrayList;

public class Tournament {
	String         tournamentName;
	Configuration  config;
	School[]       participants;
	Match[]        sectionals;
	Match[]        regionals;
	Match[]        semiState;
	Match          finals;

	//TODO: Micah
	public Tournament(String name, School[] participants, School[] hosts, Configuration config) {
	}
	
	
	//Gets all the hosts willing to host at a specific level
	private School[] getHostsForMeet(int meetLevel, School[] hosts) {
		ArrayList<School> listHosts = new ArrayList<School>();
		switch(meetLevel) {
		//Sectionals
		case 1:
			for(School host : hosts)
				if(host.hostSectionals == 1)
					listHosts.add(host);
			break;
		//Regionals
		case 2:
			for(School host : hosts)
				if(host.hostRegionals == 1)
					listHosts.add(host);
			break;
		//Semi-State
		case 3:
			for(School host : hosts)
				if(host.hostSemiState == 1)
					listHosts.add(host);
			break;
		//Finals
		case 4:
			break;
		}

		//TODO: Micah - Restrict number of hosts to maximum in config settings
		return (School[]) listHosts.toArray();
	}
	
	
	// TODO: Micah
	// Teams assign themselves to closest host
	private Match[] assignTeamsToHosts(School[] hosts, School[] participants) {
		return null;
	}
	
	// TODO: Micah
	// Makes sure each team has minimum number of hosts
	private Match[] balanceHosts(Match[] matches) {
		return null;
	}
	
}
