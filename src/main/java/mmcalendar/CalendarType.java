package mmcalendar;

/**
 * Support Calendar Type
 * 
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0
 *
 */
public enum CalendarType {
	
	ENGLISH(0, "English"), GREGORIAN(1, "Gregorian"), JULIAN(2, "Julian");

	private String label;

	private int number;

	private CalendarType(int number, String label) {
		this.label = label;
		this.number = number;
	}

	public String getLabel() {
		return label;
	}

	public int getNumber() {
		return number;
	}
}
