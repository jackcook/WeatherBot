
public class GeoLoc {
	private int zip;
	private String placeName;
	private float ip = 0;
	public GeoLoc(int zipcode) {
		
		
		
	}
	
	public GeoLoc(String cityname){
		
		
		
	}
	
	public GeoLoc(float ipAdress){
		
	}
	
	public int getZip(){
		return zip;
	}
	
	public String getPlaceName(){
		return placeName;
	}
	
	public float getIP(){
		 //if no ip then returns 0
			return ip;
	}
}
