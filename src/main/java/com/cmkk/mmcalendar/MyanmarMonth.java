package com.cmkk.mmcalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class MyanmarMonth {

	List<Integer> index = new ArrayList<Integer>();
	List<String> list = new ArrayList<String>();
	int nowIndex;

	protected MyanmarMonth() {
		super();
	}

	public List<Integer> getIndex() {
		return index;
	}

	public List<String> getList() {
		return list;
	}

	public int getNowIndex() {
		return nowIndex;
	}
}
