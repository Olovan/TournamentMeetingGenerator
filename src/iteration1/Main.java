package iteration1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Main {
    static final String CONFIG_FILE = "etc/config.txt";

   public static void main(String[] args) throws Exception {
	   MapAndListGUI gui = new MapAndListGUI(new File("etc/Boys_Enrollment.csv"));

	   /*
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

	   MapPanel map = new MapPanel();
	   JFrame testFrame = new JFrame();
	   testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   testFrame.setContentPane(new JPanel(new BorderLayout()));
	   testFrame.getContentPane().add(map, BorderLayout.CENTER);
	   testFrame.pack();
	   testFrame.setVisible(true);

	   for(int i = 0; i < config.numberOfSectionalHosts; i++) {
		   map.displayMatch(t.sectionals[i]);
		   Thread.sleep(2000);
	   }
	   for(int i = 0; i < config.numberOfRegionalHosts; i++) {
		   map.displayMatch(t.regionals[i]);
		   Thread.sleep(2000);
	   }
	   for(int i = 0; i < config.numberOfSemiStateHosts; i++) {
		   map.displayMatch(t.semiState[i]);
		   Thread.sleep(2000);
	   }
	   map.displayMatch(t.finals[0]);
	   */
   } 
}
