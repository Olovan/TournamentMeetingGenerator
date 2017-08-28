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
	private Match[] assignTeamsToHosts(School[] hosts, School[] participants, int numHosts) {
        //TODO error checking to make sure there are enough hosts
        Match[] matches = new Match[numHosts];

        for(int i = 0; i < numHosts; i++) 
        {
            matches[i].host = hosts[i];
        }

        for(School school : participants)
        {
            sortMatchesByProximity(school, matches)[0].schools.add(school);
        }
        
        
        return matches;
	}
	
	// TODO: Micah
	// Makes sure each team has minimum number of hosts
	private Match[] balanceHosts(Match[] matches) {
		return null;
	}
	
	//Returns a sorted list of the Matches ordered from closest to furthest away
	private Match[] sortMatchesByProximity(School school, Match[] matches) {
		Match[] retMatches = new Match[matches.length];
		for(int i = 0; i < retMatches.length; i++)
			retMatches[i] = matches[i];
		
		for(int i = 0; i < retMatches.length; i++) {
			Match closest = retMatches[i];
			double closestDistance = School.DistanceBetweenSchools(school, closest.host);
			int closestIndex = i;
			for(int j = i; j < retMatches.length; j++) {
				if(School.DistanceBetweenSchools(school, retMatches[j].host) < closestDistance) {
					closest = retMatches[j];
					closestDistance = School.DistanceBetweenSchools(school, closest.host);
					closestIndex = j;
				}
			}
			
			//Swap the closest remaining index with the first unsorted index
			Match temp = retMatches[i];
			retMatches[i] = closest;
			retMatches[closestIndex] = temp;
		}
		
		return retMatches;
	}
	
        // TODO: Ryan
        private String formatString() {
            String tournySchedule = "temp";

            // loop sectionals
            
            // loop regionals
            
            // loop semiState
            
            // add state
            
            return tournySchedule;
        }
        
        // TODO: Ryan
        public void outputToFile() {
            // write formated String to text file
        }
        
        // TODO: Ryan
        public String toString() {
            return formatString();
        }
}
