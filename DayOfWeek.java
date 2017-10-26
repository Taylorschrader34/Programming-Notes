import java.util.HashMap;
import java.util.Map;

public enum DayOfWeek {

	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

	private static final Map<DayOfWeek, String> map = new HashMap<DayOfWeek, String>();

	static {
		map.put(SUNDAY, "Sunday");
		map.put(MONDAY, "Monday");
		map.put(TUESDAY, "Tuesday");
		map.put(WEDNESDAY, "Wednesday");
		map.put(THURSDAY, "Thursday");
		map.put(FRIDAY, "Friday");
		map.put(SATURDAY, "Saturday");
	}

	@Override
	public String toString() {
		return map.get(this);
	}

}
