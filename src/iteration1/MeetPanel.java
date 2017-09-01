package iteration1;

import java.awt.*;
import javax.swing.*;

public class MeetPanel extends JPanel{
    private GridLayout layout = new GridLayout(0,1);
    private Dimension size = new Dimension(110,110);
    
    private JLabel meetType;
    
    // TODO Ryan
    // Constructor
    public MeetPanel(String meetCategory) {
        // construct label
        meetType = new JLabel(meetCategory);

        // formating
        setLayout(layout);
        setPreferredSize(size);
        meetType.setHorizontalAlignment(JLabel.CENTER);
        
        // add label
        add(meetType);
    }
}
