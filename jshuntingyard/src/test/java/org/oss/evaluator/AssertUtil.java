/**
 * Copyright [2015] [Open Software Solutions GmbH]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.oss.evaluator;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.oss.jshuntingyard.evaluator.BigDecimalArgument;
import org.oss.jshuntingyard.evaluator.BooleanArgument;
import org.oss.jshuntingyard.evaluator.DateArgument;
import org.oss.jshuntingyard.evaluator.DoubleArgument;
import org.oss.jshuntingyard.evaluator.FloatArgument;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.jshuntingyard.evaluator.IntegerArgument;
import org.oss.jshuntingyard.evaluator.LongArgument;
import org.oss.jshuntingyard.evaluator.StringArgument;

public class AssertUtil {

	AssertUtil(){
		// empty constructor
	}


	static void assertBooleanResult(FunctionElementArgument<?> result, Boolean expected) {
		assertTrue("BooleanArgument expected and not " + result.getClass().getSimpleName(),result instanceof BooleanArgument);
		BooleanArgument boolResult = (BooleanArgument) result;
		assertTrue("Value of " + expected + " expected." ,boolResult.getValue() == expected);
	}

	static void assertDoubleResult(FunctionElementArgument<?> result, Double expected) {
		assertTrue("DoubleArgument expected and not " + result.getClass().getSimpleName(),result instanceof DoubleArgument);
		DoubleArgument doubleResult = (DoubleArgument) result;
		assertTrue("Value of " + expected + " expected and not " + doubleResult ,doubleResult.getValue().equals(expected));
	}

	static void assertBigDecimalResult(FunctionElementArgument<?> result, BigDecimal expected) {
		assertTrue("BigDecimalArgument expected and not " + result.getClass().getSimpleName(),result instanceof BigDecimalArgument);
		BigDecimalArgument bigDecimalResult = (BigDecimalArgument) result;
		assertTrue("Value of " + expected + " expected and not " + bigDecimalResult ,bigDecimalResult.getValue().equals(expected));
	}

	static void assertFloatResult(FunctionElementArgument<?> result, Float expected) {
		assertTrue("FloatArgument expected and not " + result.getClass().getSimpleName(),result instanceof FloatArgument);
		FloatArgument floatResult = (FloatArgument) result;
		assertTrue("Value of " + expected + " expected and not " + floatResult ,floatResult.getValue().equals(expected));
	}

	static void assertIntegerResult(FunctionElementArgument<?> result, Integer expected) {
		assertTrue("IntegerArgument expected and not " + result.getClass().getSimpleName(),result instanceof IntegerArgument);
		IntegerArgument integerResult = (IntegerArgument) result;
		assertTrue("Value of " + expected + " expected and not " + integerResult ,integerResult.getValue().equals(expected));
	}

	static void assertLongResult(FunctionElementArgument<?> result, Long expected) {
		assertTrue("LongArgument expected and not " + result.getClass().getSimpleName(),result instanceof LongArgument);
		LongArgument longResult = (LongArgument) result;
		assertTrue("Value of " + expected + " expected and not " + longResult ,longResult.getValue().equals(expected));
	}

	static void assertDateResult(FunctionElementArgument<?> result, Date expected) {
		assertTrue("DateArgument expected and not " + result.getClass().getSimpleName(),result instanceof DateArgument);
		DateArgument dateResult = (DateArgument) result;
		assertTrue("Value of " + expected + " expected and not " + dateResult ,dateResult.getValue().equals(expected));
	}

	static void assertStringResult(FunctionElementArgument<?> result, String expected) {
		assertTrue("StringArgument expected and not " + result.getClass().getSimpleName(),result instanceof StringArgument);
		StringArgument stringResult = (StringArgument) result;
		assertTrue("Value of " + expected + " expected and not " + stringResult.getValue()  ,stringResult.getValue().equals(expected));
	}

}
