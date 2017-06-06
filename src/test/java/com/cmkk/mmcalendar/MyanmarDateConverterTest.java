package com.cmkk.mmcalendar;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.*;

public class MyanmarDateConverterTest {
	
	@Test
	public void calculateWithAstro(){
		MyanmarDate myanmarDate = MyanmarDateConverter.convert(1983, 12, 31);
		Astro astro = AstroConverter.mmDate2Astro(myanmarDate);
		System.out.println(myanmarDate.toString());
		System.out.println(astro.toString());
	}

	@Test
	public void convert() {

	}
	
	@Test
	public void j2m(){
		
	}
	
	@Test
	public void chk_my(){
		
	}
	
	@Test
	public void chk_watat(){
		
	}
	
	@Test
	public void m2j(){
		
	}
	
	@Test
	public void t2d(){
		
	}
	
	@Test
	public void getMyanmarMonth(){
		MyanmarMonth myanmarMonth = MyanmarDateConverter.getMyanmarMonth(1380, 3);
		
		List<Integer> index = new ArrayList<Integer>();
		List<String> list = new ArrayList<String>();
		index.add(2);
		list.add("Kason");
		index.add(3);
		list.add("Nayon");
		index.add(0);
		list.add("1st Waso");
		index.add(4);
		list.add("2nd Waso");
		index.add(5);
		list.add("Wagaung");
		index.add(6);
		list.add("Tawthalin");
		index.add(7);
		list.add("Thadingyut");
		index.add(8);
		list.add("Tazaungmon");
		index.add(9);
		list.add("Nadaw");
		index.add(10);
		list.add("Pyatho");
		index.add(11);
		list.add("Tabodwe");
		index.add(12);
		list.add("Tabaung");		
		index.add(13);
		list.add("Late Tagu");
		
		assertThat(index, hasItem(13));		
		Assert.assertEquals(list, myanmarMonth.getList());
		Assert.assertEquals(index, myanmarMonth.getIndex());
		Assert.assertEquals(3, myanmarMonth.getNowIndex());

	}
	
}
