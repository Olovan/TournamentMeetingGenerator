package iteration1;

import java.awt.*;
import javax.swing.*;

public class ListPanel extends JPanel{
    private FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
    
    private JPanel sectionalsPanel;
    private JPanel regionalsPanel;
    private JPanel semiStatePanel;
    private JPanel finalsPanel;

    // TODO Ryan
    // Constructor
    public ListPanel() {
        // formating
        setLayout(layout);
        
        // Create panels containing meet schedules
        sectionalsPanel = new MeetPanel("Sectionals");
        regionalsPanel = new MeetPanel("Regionals");
        semiStatePanel = new MeetPanel("Semi-State");
        finalsPanel = new MeetPanel("State");
        
        // add meet panels to frame
        add(sectionalsPanel);
        add(regionalsPanel);
        add(semiStatePanel);
        add(finalsPanel);
    }
}
