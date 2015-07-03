package org.oss.performance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sourceforge.jeval.EvaluationException;

import org.junit.Test;
import org.oss.jshuntingyard.evaluator.DoubleArgument;
import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.jshuntingyard.evaluator.interpreter.Evaluator;
import org.oss.jshuntingyard.evaluator.interpreter.Expression;
import org.oss.jshuntingyard.evaluator.parser.ExtendedSHuntingYardParser;

public class PerformanceTest {

	@Test
	public void evaluatingSpeedJEval() throws EvaluationException {
		net.sourceforge.jeval.Evaluator evaluator = new net.sourceforge.jeval.Evaluator();
		evaluator.parse("( 1 + 2 ) * pow(( 3 / 4 ) , ( 5 + 6 ))");
		long t = System.currentTimeMillis();
		for (int i=0;i<10000;i++){
			assertEquals("0.12670540809631348", evaluator.evaluate());
		}
		System.out.println("JEval with parsing and evaluation together, time elapsed within " + (System.currentTimeMillis() -t) + " ms");
	}
	@Test
	public void evaluatingWithParsingSpeedJEval() throws EvaluationException {
		net.sourceforge.jeval.Evaluator evaluator = new net.sourceforge.jeval.Evaluator();
		long t = System.currentTimeMillis();
		for (int i=0;i<10000;i++){
			assertEquals("0.12670540809631348", evaluator.evaluate("( 1 + 2 ) * pow(( 3 / 4 ) , ( 5 + 6 ))"));
		}
		System.out.println("JEval with parsing separated from evaluation, time elapsed within " + (System.currentTimeMillis() -t) + " ms");
	}

	@Test
	public void evaluatingSpeed() {
		String[] input = "( 1 + 2 ) * ( 3 / 4 ) ^ ( 5 + 6 )".split(" ");
		Expression expression = new ExtendedSHuntingYardParser().infixToRPN(input);
		long t = System.currentTimeMillis();
		for (int i=0;i<10000;i++){
			FunctionElementArgument<?> result = new Evaluator().evaluate(expression);
			assertDoubleResult(result,0.12670540809631348);
		}
		System.out.println("JSHuntingYard with parsing separated from evaluation, time elapsed within "+ (System.currentTimeMillis() -t) + " ms");
	}
	@Test
	public void evaluatingWithParsingSpeed() {
		String[] input = "( 1 + 2 ) * ( 3 / 4 ) ^ ( 5 + 6 )".split(" ");
		long t = System.currentTimeMillis();
		for (int i=0;i<10000;i++){
			Expression expression = new ExtendedSHuntingYardParser().infixToRPN(input);
			FunctionElementArgument<?> result = new Evaluator().evaluate(expression);
			assertDoubleResult(result,0.12670540809631348);
		}
		System.out.println("JSHuntingYard with parsing and evaluation together, time elapsed within " + (System.currentTimeMillis() -t) + " ms");
	}
	@Test
	public void nativSpeed() {
		long t = System.currentTimeMillis();
		for (int i=0;i<10000;i++){
			FunctionElementArgument<?> result = FunctionArgumentFactory.createObject((double)(( 1 + 2 ) * Math.pow(( 3.0 / 4 ) , ( 5 + 6 ))));
			assertDoubleResult(result,0.12670540809631348);
		}
		System.out.println("Compiled JSHuntingYard Expression, time elapsed within " + (System.currentTimeMillis() -t) + " ms");
	}

	private void assertDoubleResult(FunctionElementArgument<?> result, Double expected) {
		assertTrue("DoubleArgument expected",result instanceof DoubleArgument);
		DoubleArgument doubleResult = (DoubleArgument) result;
		assertTrue("Value of " + expected + " expected and not " + doubleResult ,doubleResult.getValue().equals(expected));
	}

}
