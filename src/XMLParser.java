import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {

	public Weather[] getWeatherFromXML(String xml) {
		int htemp = 0;
		int ltemp = 0;
		int wspeed = 0;
		List<String> imgurl = new ArrayList<String>();
		List<String> desc = new ArrayList<String>();
		String dir = "";
		try {
			for (String s : xml.replaceAll("><", ">\n<").split("\n")) {
				if (s.contains("CDATA")) {
					if (s.contains("http")) {
						imgurl.add(s.substring(9, s.length()-3));
					} else {
						desc.add(s.substring(9, s.length()-3));
					}
				}
			}
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			
			List<Weather> weathers = new ArrayList<Weather>();
			NodeList nodes = doc.getElementsByTagName("weather");
			for (int i=0; i<nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					htemp = Integer.parseInt(getValue("tempMaxF", element));
					ltemp = Integer.parseInt(getValue("tempMinF", element));
					wspeed = Integer.parseInt(getValue("windspeedMiles", element));
					String d = getValue("winddirection", element);
					if (d.equals("N")) dir = "North";
					else if (d.equals("E")) dir = "East";
					else if (d.equals("S")) dir = "South";
					else if (d.equals("W")) dir = "West";
					weathers.add(new Weather(htemp, ltemp, wspeed, imgurl.get(i+1), desc.get(i+1), dir));
				}
			}

			Weather[] weatherarray = new Weather[weathers.size()];
			return weathers.toArray(weatherarray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CurrentConditions getCurrentConditionsFromXML(String xml) {
		int ctemp = 0;
		int humid = 0;
		int wspeed = 0;
		int pressure = 0;
		int ccover = 0;
		List<String> imgurl = new ArrayList<String>();
		String dir = "";
		List<String> desc = new ArrayList<String>();
		try {
			for (String s : xml.replaceAll("><", ">\n<").split("\n")) {
				if (s.contains("CDATA")) {
					if (s.contains("http")) {
						imgurl.add(s.substring(9, s.length()-3));
					} else {
						desc.add(s.substring(9, s.length()-3));
					}
				}
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("current_condition");
			for (int i=0; i<nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					ctemp = Integer.parseInt(getValue("temp_F", element));
					humid = Integer.parseInt(getValue("humidity", element));
					wspeed = Integer.parseInt(getValue("windspeedMiles", element));
					pressure = Integer.parseInt(getValue("pressure", element));
					ccover = Integer.parseInt(getValue("cloudcover", element));
					String d = getValue("winddir16Point", element);
					if (d.equals("N")) dir = "North";
					else if (d.equals("E")) dir = "East";
					else if (d.equals("S")) dir = "South";
					else if (d.equals("W")) dir = "West";
					return new CurrentConditions(ctemp, humid, wspeed, pressure, ccover, imgurl.get(0), dir, desc.get(0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}