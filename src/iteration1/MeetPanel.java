package iteration1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MeetPanel extends JPanel{
    private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    private Dimension pnlSize = new Dimension(117,1000);
    private Font font1 = new Font("Arial", Font.BOLD, 14);
    private Font font2 = new Font("Arial", Font.PLAIN, 14);
    
    private MapPanel map;
    private Match[] meetsArr;
    private JButton allMeetsBtn;
    private ArrayList<String> hostArr;
    
    // TODO Ryan
    // Constructor
    public MeetPanel(String meetCategory, Match[] listOfMeets, MapPanel map) {
        // formating
        setLayout(layout);
        setPreferredSize(pnlSize);
        
        // prepare map for drawing
        this.map = map;
        
        // get meets
        meetsArr = listOfMeets;        
        
        // construct and add title button
        allMeetsBtn = new JButton(meetCategory);
        allMeetsBtn.addActionListener(new AllMeetsBtnListener());
        allMeetsBtn.setFont(font1);
        add(allMeetsBtn);
        
        // construct meet buttons, catalog name in hostArr, and add to frame
        String hostName;
        JButton meetBtn;
        hostArr = new ArrayList<>();
        for (int i = 0; i < meetsArr.length; i++) {
            hostName = meetsArr[i].host.toString();
            meetBtn = new JButton(hostName);
            meetBtn.addActionListener(new MeetBtnListener());
            meetBtn.setFont(font2);
            
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
    
    // Send selected Match for mapPanel display
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
