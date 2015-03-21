package org.oss.evaluator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.oss.evaluator.function.FunctionArgument;
import org.oss.evaluator.function.impl.BooleanArgument;

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
public class BooleanTests {

	@Test
	public void booleanEvalTrue() {
		FunctionArgument<?> result = new Evaluator("true == true").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalFalse() {
		FunctionArgument<?> result = new Evaluator("False == true").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerTrue() {
		FunctionArgument<?> result = new Evaluator("7 == 7").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerFalse() {
		FunctionArgument<?> result = new Evaluator("3 == 7").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerLesserTrue() {
		FunctionArgument<?> result = new Evaluator("3 < 7").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}
	@Test
	public void booleanEvalIntegerLesserOrEqualToTrue() {
		FunctionArgument<?> result = new Evaluator("3 <= 7").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}
	@Test
	public void booleanEvalIntegerLesserOrEqualToBoundsTrue() {
		FunctionArgument<?> result = new Evaluator("3 <= 3").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerLesserFalse() {
		FunctionArgument<?> result = new Evaluator("10 < 7").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerLesserOrEqualToFalse() {
		FunctionArgument<?> result = new Evaluator("10 <= 7").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerGreaterTrue() {
		FunctionArgument<?> result = new Evaluator("10 > 7").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerGreaterFalse() {
		FunctionArgument<?> result = new Evaluator("10 > 10").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerGreaterOrEqualToTrue() {
		FunctionArgument<?> result = new Evaluator("10 >= 7").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerGreaterOrEqualToBoundsTrue() {
		FunctionArgument<?> result = new Evaluator("10 >= 10").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringEqualTrue() {
		FunctionArgument<?> result = new Evaluator("'foo' == 'foo'").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringEqualFalse() {
		FunctionArgument<?> result = new Evaluator("'foo' == 'bar'").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringNotEqualTrue() {
		FunctionArgument<?> result = new Evaluator("'foo' != 'bar'").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringEqualToFalse() {
		FunctionArgument<?> result = new Evaluator("'AB' == 'AA'").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringGreaterFalse() {
		FunctionArgument<?> result = new Evaluator("'AB' == 'A'").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}


	@Test
	public void booleanEvalLogicalAndTrue1() {
		FunctionArgument<?> result = new Evaluator("true && true").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalLogicalNot() {
		FunctionArgument<?> result = new Evaluator("! true").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalLogicalAndTrue() {
		FunctionArgument<?> result = new Evaluator(" 2 + 3 == 5  && true").evaluate();
		assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalLogicalAndFalse() {
		FunctionArgument<?> result = new Evaluator(" 2 < 4  &&  3 > 4 ").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}



	@Test
	public void booleanEvalIntegerCalc1() {
		FunctionArgument<?> result = new Evaluator("6 == ( 3 + 2 )").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerCalc2() {
		FunctionArgument<?> result = new Evaluator("6 == 3 + 2 ").evaluate();
		assertBooleanResult(result, Boolean.FALSE);
	}

	private void assertBooleanResult(FunctionArgument<?> result, Boolean expected) {
		assertTrue("BooleanArgument expected",result instanceof BooleanArgument);
		BooleanArgument boolResult = (BooleanArgument) result;
		assertTrue("Value of " + expected + " expected." ,boolResult.getValue() == expected);
	}
}
