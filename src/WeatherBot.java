import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WeatherBot {

	public static void main(String[] args) throws Exception {
		getZipCode();
	}

	public static Weather[] getWeather(int zip) {
		return getWeather(String.valueOf(zip));
	}

	public static Weather[] getWeather(String loc) {
		String weatherxml = "";
		try {
			URL url = new URL("http://api.worldweatheronline.com/free/v1/weather.ashx?key=p6fzvmxkkscd4kjh8am3bgdf&num_of_days=5&q=" + loc.replaceAll(" ", "+"));
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				weatherxml = line;
				if (weatherxml.contains("Unable")) return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XMLParser rp = new XMLParser();
		return rp.getWeatherFromXML(weatherxml.replaceAll("><", ">\n<"));
	}

	public static CurrentConditions getCurrentConditions(String loc) {
		String weatherxml = "";
		try {
			URL url = new URL("http://api.worldweatheronline.com/free/v1/weather.ashx?key=p6fzvmxkkscd4kjh8am3bgdf&q=" + loc.replaceAll(" ", "+"));
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				weatherxml = line;
				if (weatherxml.contains("Unable")) return null;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XMLParser rp = new XMLParser();
		return rp.getCurrentConditionsFromXML(weatherxml.replaceAll("><", ">\n<"));
	}

	public static void getZipCode() {
		final JFrame zaWindow = new JFrame("Enter your zipcode");
		zaWindow.setSize(350, 150);
		JPanel jp = new JPanel();
		final JTextField tf = new JTextField("Enter Zip Here");
		JLabel label = new JLabel();
		JButton button = new JButton("Get Weather");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sZip = tf.getText();
				Weather[] weather = null;
				weather = getWeather(sZip);
				if (weather == null) {
					JOptionPane.showMessageDialog(null, "Invalid zipcode!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					zaWindow.setVisible(false);
					WeatherBotInterface wbInterface = new WeatherBotInterface(sZip, getWeather(sZip), getCurrentConditions(sZip));
					wbInterface.setVisible(true);
				}
			}
		});
		label.setText("Enter your city or zipcode:");
		jp.add(label);
		jp.add(tf);
		jp.add(button);
		zaWindow.add(jp);
		zaWindow.setVisible(true);
	}
}