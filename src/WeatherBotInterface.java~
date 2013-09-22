import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherBotInterface extends JFrame {

	private JLabel data;
	private Weather[] weather;
	private CurrentConditions cc;

	public WeatherBotInterface(String location, final Weather[] weather, final CurrentConditions cc) {
		super("WeatherBot - " + location);
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVariables(weather, cc);

		JPanel buttonPanel = new JPanel();
		JPanel dataPanel = new JPanel();
		data = new JLabel(displayWeather(weather, 1));
		dataPanel.add(data);

		this.add(dataPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);

		addWeatherButton(buttonPanel, "Current Conditions", 0, false);
		addWeatherButton(buttonPanel, "Today's Forecast", 1, true);
		addWeatherButton(buttonPanel, "Tomorrow's Forecast", 2, true);
		addWeatherButton(buttonPanel, "2-Day Forecast", 3, true);

		this.setVisible(true);
	}

	private JPanel addWeatherButton(JPanel panel, String text, final int day, final boolean forecast) {
		JButton button = new JButton(text);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				data.setVisible(false);
				if (forecast) data.setText(displayWeather(weather, day));
				else data.setText(displayCurrentConditions(cc));
				data.setVisible(true);
			}
		});
		panel.add(button);
		return panel;
	}

	private String displayCurrentConditions(CurrentConditions cc) {
		return "<html>" +
				"<center><img src='" + cc.getImageURL() + "' /></center><br />" +
				"Current Temperature: " + cc.getCurrentTemp() + "*F<br />" +
				"Current Relative Humidity: " + cc.getHumidity() + "%<br />" +
				"Current Windspeed: " + cc.getWindSpeed() + "mph from the " + cc.getDirection() + "<br />" +
				"Current Cloud Cover: " + cc.getCloudCover() + "<br />" + 
				"Description: " + cc.getDescription() + "<br /></html>";
	}

	private String displayWeather(Weather[] weather, int day) {
		return "<html>" + 
				"<center><img src='" + weather[day-1].getImageURL() + "' /></center><br />" +  
				"High Temperature: " + weather[day-1].getHighTemp() + "*F<br />" + 
				"Low Temperature: " + weather[day-1].getLowTemp() + "*F<br />"+
				"Windspeed: " + weather[day-1].getWindSpeed() + "mph from the " + weather[day-1].getDirection() + "<br />" + 
				"Description: " + weather[day-1].getDescription() + "<br />" + 
				"</html>";
	}

	private void setVariables(Weather[] weather, CurrentConditions cc) {
		this.weather = weather;
		this.cc = cc;
	}
}
