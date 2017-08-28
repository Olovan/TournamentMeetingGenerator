package iteration1;

import java.lang.Math;

public class School {

    private String schoolName;
    public int enrollment;
    public int participation;
    public int hostSectionals;
    public int hostRegionals;
    public int hostSemiState;
    private double longitude;
    private double latitude;

    public School (String name, int total, int par, int sec, int reg, int sem, double lon, double lat) {
        this.schoolName = name;
        this.enrollment = total;
        this.participation = par;
        this.hostSectionals = sec;
        this.hostRegionals = reg;
        this.hostSemiState = sem;
        this.longitude = lon;
        this.latitude = lat;
    }

    public String PrintSchool () {
        return "\nSchool name: " + schoolName + 
            "\nTotal Enrollment: " + enrollment + 
            "\nParticipating: " + participation +
            "\nWould host Sectionals: " + hostSectionals + 
            "\nWould host Regionals: " + hostRegionals + 
            "\nWould host Semi-State: " + hostSemiState + 
            "\nLongitude: " + longitude +
            "\nLatitude: " + latitude + "\n";
    }

    //Returns distance between 2 schools in miles
    public static double DistanceBetweenSchools (School school1, School school2) {
        double DEG_TO_RAD = 3.14159/180;
        double EARTH_RADIUS = 3959; //Earth radius in miles

        double rad_lat1 = school1.latitude * DEG_TO_RAD;
        double rad_lng1 = school1.longitude * DEG_TO_RAD;
        double rad_lat2 = school2.latitude * DEG_TO_RAD;
        double rad_lng2 = school2.longitude * DEG_TO_RAD;
        
        double a = Math.pow(Math.sin((rad_lat1 - rad_lat2)/2),2) + Math.cos(rad_lat1) * Math.cos(rad_lat2) * Math.pow(Math.sin((rad_lng1 - rad_lng2) / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return c * EARTH_RADIUS;
    }

    public String toString() {
        return schoolName;
    }	
}

