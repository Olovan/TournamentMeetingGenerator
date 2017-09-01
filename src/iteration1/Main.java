package iteration1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final String CONFIG_FILE = "etc/config.txt";

   public static void main(String[] args) {
       if(args.length < 1) {
           System.out.println("Must give filename to load Schools from");
           return;
       }


       List<School> allSchools = ReadSchoolFile.GetSchoolsFromFile(args[0]);
       Configuration config = new Configuration(CONFIG_FILE);

       List<School> hosts = new ArrayList<School>();
       List<School> participants = new ArrayList<School>();
       for(School school : allSchools)
       {
           if(school.participation != 0)
               participants.add(school);

           if(school.hostSectionals != 0 || school.hostRegionals != 0 || school.hostSemiState != 0)
               hosts.add(school);

           if(school.schoolName.contentEquals("Terre Haute LaVern Gibson"))
               hosts.add(school);
       }

       School[] hostsArray = new School[hosts.size()];
       School[] participantsArray = new School[participants.size()];
       hosts.toArray(hostsArray);
       participants.toArray(participantsArray);

       Tournament t = new Tournament("Boys Test Tournament", participantsArray, hostsArray, config);

       System.out.println(t);
   } 
}
