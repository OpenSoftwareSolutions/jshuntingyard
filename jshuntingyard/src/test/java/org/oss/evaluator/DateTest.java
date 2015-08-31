package org.oss.evaluator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.jshuntingyard.evaluator.interpreter.Evaluator;

public class DateTest {
	
	@Test
	public void parseSwissDate() throws ParseException {
		Locale l = Locale.getDefault(Locale.Category.FORMAT);
		if (l.toString().equals("de_CH")) {
			FunctionElementArgument<?> result = new Evaluator("toLocalDate('23.05.1969')").evaluate();
			Date expected = SimpleDateFormat.getDateInstance().parse("23.05.1969");
			AssertUtil.assertDateResult(result,expected);			
		}
	}
	
	@Test
	public void parseDate() throws ParseException {
		FunctionElementArgument<?> result = new Evaluator("toDate('dd/MM/yy','23/05/69')").evaluate();
		Date expected = new SimpleDateFormat("dd/MM/yy").parse("23/05/69");
		AssertUtil.assertDateResult(result,expected);			
	}

}
