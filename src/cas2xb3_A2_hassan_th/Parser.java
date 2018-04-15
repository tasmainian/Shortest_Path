package cas2xb3_A2_hassan_th;
/**
 * @author Tasmiha Hassan, hassat3, 400083305
 */
import java.math.*; 
import java.io.*;
import java.util.*;
public class Parser {

	//Reads through the USCities file and loads the values onto the USCitiesADT, returns a list of type USCitiesADT
	public static ArrayList<USCitiesADT> parseCity(String file) throws IOException{
		Scanner input = new Scanner(new File(file));
		ArrayList<USCitiesADT> cities = new ArrayList<USCitiesADT>();
		input.nextLine();
		String line;
		while(input.hasNext()){
			line = input.nextLine();
			String[] cols = line.split(",");
			USCitiesADT city = new USCitiesADT(cols[3], Double.parseDouble(cols[4]), Double.parseDouble(cols[5]));
			cities.add(city);
		}
		return cities;
	}
	
	//Reads through the menu file and loads the values onto the MenuADT, returns a list of type MenuADT
	public static ArrayList<MenuADT> parseMenu(String file) throws IOException{
		Scanner input = new Scanner(new File(file));
		ArrayList<MenuADT> menus = new ArrayList<MenuADT>();
		input.nextLine();
		String line;
		while(input.hasNext()){
			line = input.nextLine();
			String[] cols = line.split(",");
			MenuADT item = new MenuADT(cols[0], cols[1], cols[2], cols[3]);
			menus.add(item);
		}
		return menus;
	}
	
	//Reads through the restaurant location file and loads the values onto the LongLatADT, returns a list of type LongLatADT
	public static ArrayList<LongLatADT> parseLongLat(String file) throws IOException{
		Scanner input = new Scanner(new File(file));
		ArrayList<LongLatADT> location = new ArrayList<LongLatADT>();
		input.nextLine();
		String line;
		while(input.hasNext()){
			line = input.nextLine();
			String[] cols = line.split(",");
			cols[2].replace("\"", "");
			LongLatADT coord = new LongLatADT(Double.parseDouble(cols[0]),Double.parseDouble(cols[1]), cols[2], cols[3]);
			location.add(coord);
		}
		return location;
	}
}
