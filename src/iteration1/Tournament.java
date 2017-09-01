package iteration1;

import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Tournament implements Serializable {
	String         tournamentName;
	Configuration  config;
	School[]       participants;
	Match[]        sectionals;
	Match[]        regionals;
	Match[]        semiState;
	Match[]          finals; //Only contains 1 match but is an array type for consistency

	//TODO: Micah
	public Tournament(String name, School[] participants, School[] hosts, Configuration config) {
		tournamentName = name;
		this.config = config;

		School[] sectionalHosts = getHostsForMeet(1, hosts);
		School[] regionalHosts = getHostsForMeet(2, hosts);
		School[] semiStateHosts = getHostsForMeet(3, hosts);
		School[] stateHost = getHostsForMeet(4, hosts);

		sectionals = assignTeamsToHosts(sectionalHosts, participants, config.numberOfSectionalHosts);
		regionals = assignTeamsToHosts(regionalHosts, sectionalHosts, config.numberOfRegionalHosts);
		semiState = assignTeamsToHosts(semiStateHosts, regionalHosts, config.numberOfSemiStateHosts);
		finals = assignTeamsToHosts(stateHost, semiStateHosts, 1);

		balanceHosts(sectionals, config.minTeamsPerHost[0]);
		balanceHosts(regionals, config.minTeamsPerHost[1]);
		balanceHosts(semiState, config.minTeamsPerHost[2]);
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
		// number of hosts should be dealt with before creating the tournament
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

	// Makes sure each team has minimum number of hosts
	private void balanceHosts(Match[] matches, int minTeamsPerHost) {
		for(Match underfilledMatch : matches)
		{
			while(underfilledMatch.schools.size() < minTeamsPerHost) 
			{
				Match targetMatch = null; //The match you will steal a school from
				School targetSchool = null; //The School you will steal

				for(Match overfilledMatch : matches) 
				{
					if(overfilledMatch.schools.size() <= minTeamsPerHost) // Don't steal from Schools at or below the minimum
						continue;
					for(School school : overfilledMatch.schools) 
					{
						if(targetSchool == null || School.DistanceBetweenSchools(underfilledMatch.host, school) < School.DistanceBetweenSchools(underfilledMatch.host, targetSchool))
						{
							targetSchool = school;
							targetMatch = overfilledMatch;
						}
					}
				}
				//Now that we've found the closest school from an overfilled Match we will steal it for the underfilledMatch
				underfilledMatch.addSchool(targetSchool);
				targetMatch.removeSchool(targetSchool);
			}
		}
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

        // Format tournament schedule into tabular form.
	private String formatString() {
		String meetClosed = "\r\nNOT HELD THIS YEAR\r\n";
                StringBuilder sb = new StringBuilder();
		sb.append(tournamentName);

		// list sectionals if held
		sb.append("\r\n\r\n\t\t\tSECTIONALS");
                if (sectionals == null || sectionals[0] == null) {
                    sb.append(meetClosed);
                }
                else {
                    for(int i = 0; i < sectionals.length; i++) {
			// header
			sb.append("\r\nSectional #").append(i + 1).append("\r\n");

			// match listing
			sb.append(sectionals[i].toString());
                    }
                }

		// list regionals if held
		sb.append("\r\n\t\t\tREGIONALS");
                if (regionals == null || regionals[0] == null) {
                    sb.append(meetClosed);
                }
                else {
                    for(int i = 0; i < regionals.length; i++) {
                            // header
                            sb.append("\r\nRegional #").append(i + 1).append("\r\n");

                            // match listing
                            sb.append(regionals[i].toString());
                    }
                }

		// list semi-state if held
		sb.append("\r\n\t\t\tSEMISTATE");
                if (semiState == null || semiState[0] == null) {
                    sb.append(meetClosed);
                }
                else {
                    for(int i = 0; i < semiState.length; i++) {
                            // header
                            sb.append("\r\nSemi-State #").append(i + 1).append("\r\n");

                            // match listing
                            sb.append(semiState[i].toString());
                    }
                }

		// list state if held
		sb.append("\r\n\t\t\t  STATE\r\n");
                if (finals == null) {
                    sb.append(meetClosed);
                }
                else {    
                    sb.append(finals.toString());
                }
		
                return sb.toString();
	}

        // [TODO: Ryan Arnold]
        // [NEEDS TESTING]
        // Save tournament schedule, in tabular form, to a text file.
        // @return: True if text file was successfully created and written to,
        //          False otherwise.
	public boolean outputToFile(File path) {
		String filename = tournamentName + ".txt";
                
                // check parameter
                if (path == null){
                    return false;
                }
                
                // prepare for writing
		String output;
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			// create file and write tournament schedule
                        output = formatString();
			fw = new FileWriter(new File(path, filename));
			bw = new BufferedWriter(fw);
			bw.write(output);
		}
		catch (IOException e) {
			return false;
		}
		finally {
			// close file
                        try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			}
			catch (IOException e) {
				return false;
			}
		}
                
                return true;
	}
        
        // [TODO: Ryan Arnold]
        // [NEEDS TESTING]
        // Save current Tournament data to a ser file.
        // @return: True if Tournament was written serialized to a file,
        // False otherwise.
        public boolean save(File path) {
            String filename = tournamentName + ".ser";

            // check parameter
            if (path == null){
                return false;
            }
                        
            // prepare for writing
            FileOutputStream fOut;
            ObjectOutputStream oOut;
            
            try {
                // write Tournamnet to file
                fOut = new FileOutputStream(new File(path, filename));
                oOut = new ObjectOutputStream(fOut);
                oOut.writeObject(this);
                
                // close stream
                fOut.close();
                oOut.close();
            }
            catch (IOException e) {
                return false;
            }
            
            return true;
        }
        
        // Get tournament schedule in tabular form.
	public String toString() {
		return formatString();
	}
}
