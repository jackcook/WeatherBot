public class Weather {

	public Weather(int htemp, int ltemp, int wspeed, String url, String desc, String dir) {
		this.hightemperature = htemp;
		this.lowtemperature = ltemp;
		this.windspeed = wspeed;
		this.imgurl = url;
		this.description = desc;
		this.direction = dir;
	}

	private int hightemperature = 0;
	private int lowtemperature = 0;
	private int windspeed = 0;
	private String imgurl = "";
	private String description = "";
	private String direction = "";

	public int getHighTemp() {
		return this.hightemperature;
	}

	public int getLowTemp() {
		return this.lowtemperature;
	}

	public int getWindSpeed() {
		return this.windspeed;
	}

	public String getImageURL() {
		return this.imgurl;
	}

	public String getDescription() {
		return this.description;
	}

	public String getDirection() {
		return this.direction;
	}
}