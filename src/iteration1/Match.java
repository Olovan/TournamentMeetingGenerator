package iteration1;

import java.util.ArrayList;

//This class represents a single tournament match
public class Match {
	public School host;
	public ArrayList<School> schools; //Schools competing in the match
	
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
}
