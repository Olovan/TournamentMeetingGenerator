package iteration1;

import java.util.ArrayList;
import java.io.Serializable;

//This class represents a single tournament match
public class Match implements Serializable{
	public School host;
	public ArrayList<School> schools = new ArrayList<>(); //Schools competing in the match
	
	public void addSchool(School school) {
		schools.add(school);
	}
	
	public void removeSchool(School school) {
		schools.remove(school);
	}
	
	public void swapSchools(Match otherMatch, School mySchool, School theirSchool) {
		this.addSchool(theirSchool);
		this.removeSchool(mySchool);
		otherMatch.addSchool(mySchool);
		otherMatch.removeSchool(theirSchool);

	}
        
        // Create Match listing
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            // header
            sb.append("Hosted by ").append(host).append("\r\n");
            sb.append("=========================================================\r\n");
            
            // list competing schools
            for (School sc : schools) {
                sb.append(sc + "\r\n");
            }
            
            // footer
            sb.append("=========================================================\r\n");
            return sb.toString();
        }
}
