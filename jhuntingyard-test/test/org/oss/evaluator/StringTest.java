package org.oss.evaluator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.oss.evaluator.function.FunctionArgument;
import org.oss.evaluator.function.impl.BooleanArgument;
import org.oss.evaluator.function.impl.IntegerArgument;
import org.oss.evaluator.function.impl.StringArgument;

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
public class StringTest {
	@Test
	public void strlengthCompare() {
		FunctionArgument<?> result = new Evaluator("len ( 'foo' ) < 4").evaluate();
		assertBooleanResult(result, true);
	}
	@Test
	public void strlength() {
		FunctionArgument<?> result = new Evaluator("len ( 'foo' )").evaluate();
		assertIntegerResult(result, 3);
	}

	@Test
	public void booleanEvalLogicalAndTrue() {
		FunctionArgument<?> result = new Evaluator("'foo' + '4' + 'bar'").evaluate();
		assertStringResult(result, "foo4bar");
	}

	@Test
	public void substring() {
		FunctionArgument<?> result = new Evaluator("substr ( 'foo5bar' , 3 , 7 )").evaluate();
		assertStringResult(result, "5bar");
	}


	private void assertStringResult(FunctionArgument<?> result, String expected) {
		assertTrue("StringArgument expected",result instanceof StringArgument);
		StringArgument stringResult = (StringArgument) result;
		assertTrue("Value of " + expected + " expected and not " + stringResult.getValue()  ,stringResult.getValue().equals(expected));
	}

	private void assertIntegerResult(FunctionArgument<?> result, Integer expected) {
		assertTrue("DoubleArgument expected",result instanceof IntegerArgument);
		IntegerArgument doubleResult = (IntegerArgument) result;
		assertTrue("Value of " + expected + " expected and not " + doubleResult ,doubleResult.getValue().equals(expected));
	}

	private void assertBooleanResult(FunctionArgument<?> result, Boolean expected) {
		assertTrue("BooleanArgument expected",result instanceof BooleanArgument);
		BooleanArgument boolResult = (BooleanArgument) result;
		assertTrue("Value of " + expected + " expected." ,boolResult.getValue() == expected);
	}

}
