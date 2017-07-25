package mmcalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Myanmar Months List for Specific Myanmar Year
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0.2 
 * @since 1.0.1
 *
 */
public class MyanmarMonths {

	private List<Integer> index = new ArrayList<Integer>();
	private List<String> monthNameList = new ArrayList<String>();
	private int currentIndex; //Calculation Month

	protected MyanmarMonths(List<Integer> index, List<String> monthNameList, int currentIndex) {
		this.index = new ArrayList<Integer>(index);
		this.monthNameList = new ArrayList<String>(monthNameList);
		this.currentIndex = currentIndex;
	}

	public List<Integer> getIndex() {
		return index;
	}

	public List<String> getMonthNameList() {
		return monthNameList;
	}

	public List<String> getMonthNameList(LanguageCatalog languageCatalog) {
		if (languageCatalog.getLanguage() == Language.ENGLISH) {
			return monthNameList;
		}
		List<String> temp = new ArrayList<String>();
		for (String string : monthNameList) {
			temp.add(languageCatalog.translate(string));
		}
		return temp;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

}
