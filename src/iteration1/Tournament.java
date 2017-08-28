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
            tournamentName = name;
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
            StringBuilder sb = new StringBuilder();
            sb.append("\t\t\t").append(tournamentName);
            
            // list sectionals
            sb.append("\n\t\t\tSECTIONALS");
            for(int i = 0; i < sectionals.length; i++) {
                // header
                sb.append("\nSectional #").append(i + 1).append("\n");
                
                // match listing
                sb.append(sectionals[i].toString());
            }
            
            // list regionals
            sb.append("\n\t\t\tREGIONALS");
            for(int i = 0; i < regionals.length; i++) {
                // header
                sb.append("\nRegional #").append(i + 1).append("\n");
                
                // match listing
                sb.append(regionals[i].toString());
            }
            
            // list semi-state
            sb.append("\n\t\t\tSEMISTATE");
            for(int i = 0; i < semiState.length; i++) {
                // header
                sb.append("\nSemi-State #").append(i + 1).append("\n");
                
                // match listing
                sb.append(semiState[i].toString());
            }
            
            // list state
            sb.append("\n\t\t\tSTATE\n");
            sb.append(finals.toString());
            
            return sb.toString();
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
