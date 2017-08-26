package iteration1;

public class School {
	
	private String schoolName;
	private int enrollment;
	private int participation;
	private int hostSectionals;
	private int hostRegionals;
	private int hostSemiState;
	
	public School (String name, int total, int par, int sec, int reg, int sem) {
		this.schoolName = name;
		this.enrollment = total;
		this.participation = par;
		this.hostSectionals = sec;
		this.hostRegionals = reg;
		this.hostSemiState = sem;
	}
	
	public String PrintSchool () {
		return "\nSchool name: " + schoolName + 
				"\nTotal Enrollment: " + enrollment + 
				"\nParticipating: " + participation +
				"\nWould host Sectionals: " + hostSectionals + 
				"\nWould host Regionals: " + hostRegionals + 
				"\nWould host Semi-State: " + hostSemiState + "\n";
	}
	
	// TODO:MICAH
	public void DistanceToSchool (String school1, String school2) {
		
	}
        
        public String toString() {
            return schoolName;
        }	
}

