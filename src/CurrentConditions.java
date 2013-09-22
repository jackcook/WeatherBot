public class CurrentConditions {
	
	public CurrentConditions(int ctemp, int humid, int wspeed, int pressure, int ccover, String imgurl, String dir, String desc) {
		this.ctemp = ctemp;
		this.humidity = humid;
		this.windspeed = wspeed;
		this.pressure = pressure;
		this.cloudcover = ccover;
		this.imgurl = imgurl;
		this.direction = dir;
		this.description = desc;
	}
	
	private int ctemp = 0;
	private int humidity = 0;
	private int windspeed = 0;
	private int pressure = 0;
	private int cloudcover = 0;
	private String imgurl = "";
	private String direction = "";
	private String description = "";
	
	public int getCurrentTemp() {
		return this.ctemp;
	}
	
	public int getHumidity() {
		return this.humidity;
	}
	
	public int getWindSpeed() {
		return this.windspeed;
	}
	
	public int getPressure() {
		return this.pressure;
	}
	
	public int getCloudCover() {
		return this.cloudcover;
	}
	
	public String getImageURL() {
		return this.imgurl;
	}
	
	public String getDirection() {
		return this.direction;
	}

	public String getDescription() {
		return this.description;
	}
}
