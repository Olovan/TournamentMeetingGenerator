package iteration1;

import java.awt.*;
import javax.swing.*;

public class ListPanel extends JPanel{
    private FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
    
    private JPanel sectionalsPanel;
    private JPanel regionalsPanel;
    private JPanel semiStatePanel;
    private JPanel finalsPanel;

    // Constructor
    public ListPanel(Tournament currentTournament, MapPanel map) {
        // formating
        setLayout(layout);
        
        // Create panels containing meet schedules
        sectionalsPanel = new MeetPanel("Sectionals", currentTournament.sectionals, map);
        regionalsPanel = new MeetPanel("Regionals", currentTournament.regionals, map);
        semiStatePanel = new MeetPanel("Semi-State", currentTournament.semiState, map);
        finalsPanel = new MeetPanel("State", currentTournament.finals, map);
        
        // add meet panels to frame
        add(sectionalsPanel);
        add(regionalsPanel);
        add(semiStatePanel);
        add(finalsPanel);
    }
}
