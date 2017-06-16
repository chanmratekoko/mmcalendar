package mmcalendar;

import org.junit.Assert;
import org.junit.Test;

import mmcalendar.LanguageCatalog;
import mmcalendar.NumberToStringUtil;

public class NumberToStringUtilTest {

	@Test
	public void convert(){
		String string = NumberToStringUtil.convert(999, LanguageCatalog.getInstance());
		Assert.assertEquals("\u1049\u1049\u1049", string);
	}
}
