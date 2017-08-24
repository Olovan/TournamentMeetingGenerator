package iteration1;

public class School {
	
	private String schoolName;
	private int enrollment;
	
	public School (String name, int total) {
		this.schoolName = name;
		this.enrollment = total;
	}
	
	public String printSchool () {
		return "\nSchool name: " + schoolName + "\nTotal Enrollment: " + enrollment + "\n";
	}

}

