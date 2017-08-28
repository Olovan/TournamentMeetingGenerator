package iteration1;

import java.util.ArrayList;

//This class represents a single tournament match
public class Match {
	public School host;
	public ArrayList<School> schools = new ArrayList<>(); //Schools competing in the match
	
	void addSchool(School school) {
		schools.add(school);
	}
	
	void removeSchool(School school) {
		schools.remove(school);
	}
	
	void swapSchools(Match otherMatch, School mySchool, School theirSchool) {
		this.addSchool(theirSchool);
		this.removeSchool(mySchool);
		otherMatch.addSchool(mySchool);
		otherMatch.removeSchool(theirSchool);

	}
        
        // Create Match listing
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            // header
            sb.append("Hosted by ").append(host).append("\n");
            sb.append("=========================================================\n");
            
            // list competing schools
            for (School sc : schools) {
                sb.append(sc + "\n");
            }
            
            // footer
            sb.append("=========================================================\n");
            return sb.toString();
        }
}
