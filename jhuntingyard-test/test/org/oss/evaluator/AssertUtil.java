package org.oss.evaluator;

import static org.junit.Assert.assertTrue;

import org.oss.evaluator.function.FunctionArgument;
import org.oss.evaluator.function.impl.BooleanArgument;
import org.oss.evaluator.function.impl.DoubleArgument;
import org.oss.evaluator.function.impl.IntegerArgument;
import org.oss.evaluator.function.impl.StringArgument;

public class AssertUtil {

	AssertUtil(){
		// empty constructor
	}


	static void assertBooleanResult(FunctionArgument<?> result, Boolean expected) {
		assertTrue("BooleanArgument expected",result instanceof BooleanArgument);
		BooleanArgument boolResult = (BooleanArgument) result;
		assertTrue("Value of " + expected + " expected." ,boolResult.getValue() == expected);
	}

	static void assertDoubleResult(FunctionArgument<?> result, Double expected) {
		assertTrue("DoubleArgument expected",result instanceof DoubleArgument);
		DoubleArgument doubleResult = (DoubleArgument) result;
		assertTrue("Value of " + expected + " expected and not " + doubleResult ,doubleResult.getValue().equals(expected));
	}

	static void assertIntegerResult(FunctionArgument<?> result, Integer expected) {
		assertTrue("IntegerArgument expected",result instanceof IntegerArgument);
		IntegerArgument integerResult = (IntegerArgument) result;
		assertTrue("Value of " + expected + " expected and not " + integerResult ,integerResult.getValue().equals(expected));
	}

	static void assertStringResult(FunctionArgument<?> result, String expected) {
		assertTrue("StringArgument expected",result instanceof StringArgument);
		StringArgument stringResult = (StringArgument) result;
		assertTrue("Value of " + expected + " expected and not " + stringResult.getValue()  ,stringResult.getValue().equals(expected));
	}

}
