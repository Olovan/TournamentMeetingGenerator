package iteration1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MeetPanel extends JPanel{
    private GridLayout layout = new GridLayout(0,1);
    private Dimension size = new Dimension(115,115);
    
    private MapPanel map;
    private Match[] meetsArr;
    private JButton allMeetsBtn;
    private ArrayList<String> hostArr;
    
    // TODO Ryan
    // Constructor
    public MeetPanel(String meetCategory, Match[] listOfMeets, MapPanel map) {
        // formating
        setLayout(layout);
        setPreferredSize(size);
        
        // prepare map for drawing
        this.map = map;
        
        // get meets
        meetsArr = listOfMeets;        
        
        // construct and add title button
        allMeetsBtn = new JButton(meetCategory);
        allMeetsBtn.addActionListener(new AllMeetsBtnListener());
        add(allMeetsBtn);
        
        // construct meet buttons, catalog name in hostArr, and add to frame
        String hostName;
        JButton meetBtn;
        hostArr = new ArrayList<>();
        for (int i = 0; i < meetsArr.length; i++) {
            hostName = meetsArr[i].host.toString();
            meetBtn = new JButton(hostName);
            meetBtn.addActionListener(new MeetBtnListener());
            
            hostArr.add(hostName);
            add(meetBtn);
        }
    }
    
    // TODO Ryan
    public class AllMeetsBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // display all meets at once
        }
    }
    
    // TODO Ryan
    // [TEST]
    public class MeetBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            int index = hostArr.indexOf(command);
            if (index != -1) {
                map.displayMatch(meetsArr[index]);
            }            
        }
    }
}
