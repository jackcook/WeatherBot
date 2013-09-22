import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherBotInterface extends JFrame {

	public WeatherBotInterface(String location, final Weather[] weather, final CurrentConditions cc) {
		super("WeatherBot - " + location);
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel dataPanel = new JPanel();
		final JLabel data = new JLabel(displayWeather(weather, 1));
		dataPanel.add(data);
		JPanel ccPanel = new JPanel();
		JPanel buttons = new JPanel();
		this.add(dataPanel, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.SOUTH);

		JButton currentConditions = new JButton("Current Conditions");
		ccPanel.add(currentConditions);
		this.add(ccPanel, BorderLayout.CENTER);
		JButton todayButton = new JButton("Today's Forecast");
		buttons.add(todayButton);
		JButton tommorowButton = new JButton("Tomorrow's Forecast");
		buttons.add(tommorowButton);
		JButton day2Button = new JButton("2 Day Forecast");
		buttons.add(day2Button);
		JButton day3Button = new JButton("3 Day Forecast");
		day3Button.setVisible(true);
		currentConditions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				data.setVisible(false);
				data.setText(displayCurrentConditions(cc));
				data.setVisible(true);
			}
		});
		todayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				data.setVisible(false);
				data.setText(displayWeather(weather, 1));
				data.setVisible(true);
			}
		});
		tommorowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				data.setVisible(false);
				data.setText(displayWeather(weather, 2));
				data.setVisible(true);
				
			}
		});
		day2Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				data.setVisible(false);
				data.setText(displayWeather(weather, 3));
				data.setVisible(true);
			}
		});
		
		
		buttons.setLocation(400, 50);

		this.setVisible(true);
		
		/*try {
			BufferedImage myImg = ImageIO.read(new URL("http://maps.googleapis.com/maps/api/staticmap?zoom=13&size=600x400&maptype=hybrid&sensor=false&center=" + zipcode));
			this.setContentPane(new ImagePanel(myImg));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	private String displayCurrentConditions(CurrentConditions cc) {
		return "<html>" +
				"As of " + cc.timeLastChecked() + "<br /><br />" +
				"<img src='" + cc.getImageURL() + "' /><br />" +
				"Current Temperature: " + cc.getCurrentTemp() + "*F<br />" +
				"Current Relative Humidity: " + cc.getHumidity() + "%<br />" +
				"Current Windspeed: " + cc.getWindSpeed() + "mph from the " + cc.getDirection() + "<br />" +
				"Current Cloud Cover: " + cc.getCloudCover() + "<br /></html>";
	}

	private String displayWeather(Weather[] weather, int day){
		
		return "<html>" + 
				"<img src='" + weather[day-1].getImageURL() + "' /><br />" +  
				"High Temperature: " + weather[day-1].getHighTemp() + "*F<br />" + 
				"Low Temperature: " + weather[day-1].getLowTemp() + "*F<br />"+
				"Windspeed: " + weather[day-1].getWindSpeed() + "mph from the " + weather[day-1].getDirection() + "<br />" + 
				"Description: " + weather[day-1].getDescription() + "<br />" + 
				"</html>";
	}
}
