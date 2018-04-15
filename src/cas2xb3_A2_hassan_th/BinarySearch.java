package cas2xb3_A2_hassan_th;
//Reference to page 9 of Algorithms textbook
import java.util.ArrayList;

public class BinarySearch{

	public static int rank(String key, ArrayList<USCitiesADT> a) { 
		
		int lo = 0;
		int hi = a.size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key.compareToIgnoreCase(a.get(mid).getName()) < 0)
				hi = mid - 1;
			else if (a.get(mid).getName().compareToIgnoreCase(key) < 0)
				lo = mid + 1;
			else
				return mid;
		}
		return 0;
	}

}

