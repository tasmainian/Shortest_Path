package cas2xb3_A2_hassan_th;

//Abstract Data Type for the locations of the restaurants
public class LongLatADT {
	private double longitude;
	private double latitude;
	private String res;
	private String address;

	//Constructors
	public LongLatADT(double longitude, double latitude, String res, String address){
		this.longitude = longitude;
		this.latitude = latitude;
		this.res = res;
		this.address = address;
	}
	
	//Getter methods
	public double getLong(){
		return this.longitude;
	}
	

	public double getLat(){
		return this.latitude;
	}
	
	public String getRes(){
		return this.res;
	}
	
	public String getAddress(){
		return this.address;
	}
}
