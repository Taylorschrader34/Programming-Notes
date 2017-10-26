import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Demo {

	public static void main(String args[]) {

		DayOfWeek today = DayOfWeek.SATURDAY;
		//DayOfWeek tomorrow = today + 1;
		if(today == DayOfWeek.WEDNESDAY) {
			System.out.println("STart your lab!");
		}
		
		int a = today.compareTo(DayOfWeek.MONDAY);
		System.out.println(a);
		
		System.out.println(today.name());
		System.out.println(today.toString());
		System.out.println(today);
		
		
		//1 => "one", 2 => "two", etc.
		Map<Integer, String> myMap = new HashMap<Integer, String>();
		//put stuff into your map:
		myMap.put(1, "one");
		myMap.put(2, "two");
		myMap.put(3, "three");
		
		int key = 2;
		String s = myMap.get(key);
		System.out.println(key + " maps to " + s);
		
	}
}
