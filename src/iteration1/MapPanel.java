package iteration1;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class MapPanel extends JPanel {
	String path = "http://maps.googleapis.com/maps/api/staticmap?center=39.65331,-86.34526&size=640x640&zoom=7";
	String key = "AIzaSyD_DOA88RdINC5ZiEqTB3Ov6R03LVPRKcU";
	URL url;
	BufferedImage img;
	JLabel imageLabel;
	// Constructor
	public MapPanel() {

		setLayout(new BorderLayout()); 
		imageLabel = new JLabel();
		add(imageLabel, BorderLayout.CENTER);

		loadImageUrl(path + "&key=" + key);
		setPreferredSize(new Dimension(640, 640));
	}

	public void displayMatch(Match match) {
		StringBuilder builder = new StringBuilder();

		builder.append("&markers=color:blue%7Clabel:H%7C" + match.host.latitude + "," + match.host.longitude);
		builder.append("&markers=color:red%7Csize:small");
		for(School school : match.schools) {
			builder.append("%7C" + school.latitude + "," + school.longitude);
		}

		loadImageUrl(path + builder + "&key=" + key);
	}

	private void loadImageUrl(String path) {
		try {
			url = new URL(path);
			img = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Unable to load image from Google Maps");
		}

		imageLabel.setIcon(new ImageIcon(img));
	}
}
