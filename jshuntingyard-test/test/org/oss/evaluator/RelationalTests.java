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

import org.junit.Test;
import org.oss.evaluator.function.FunctionArgument;

public class RelationalTests {

	@Test
	public void booleanEvalTrue() {
		FunctionArgument<?> result = new Evaluator("true == true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalFalse() {
		FunctionArgument<?> result = new Evaluator("False == true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerTrue() {
		FunctionArgument<?> result = new Evaluator("7 == 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerFalse() {
		FunctionArgument<?> result = new Evaluator("3 == 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerLesserTrue() {
		FunctionArgument<?> result = new Evaluator("3 < 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}
	@Test
	public void booleanEvalIntegerLesserOrEqualToTrue() {
		FunctionArgument<?> result = new Evaluator("3 <= 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}
	@Test
	public void booleanEvalIntegerLesserOrEqualToBoundsTrue() {
		FunctionArgument<?> result = new Evaluator("3 <= 3").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerLesserFalse() {
		FunctionArgument<?> result = new Evaluator("10 < 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerLesserOrEqualToFalse() {
		FunctionArgument<?> result = new Evaluator("10 <= 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerGreaterTrue() {
		FunctionArgument<?> result = new Evaluator("10 > 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerGreaterFalse() {
		FunctionArgument<?> result = new Evaluator("10 > 10").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerGreaterOrEqualToTrue() {
		FunctionArgument<?> result = new Evaluator("10 >= 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerGreaterOrEqualToBoundsTrue() {
		FunctionArgument<?> result = new Evaluator("10 >= 10").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringEqualTrue() {
		FunctionArgument<?> result = new Evaluator("'foo' == 'foo'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringEqualFalse() {
		FunctionArgument<?> result = new Evaluator("'foo' == 'bar'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringNotEqualTrue() {
		FunctionArgument<?> result = new Evaluator("'foo' != 'bar'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringEqualToFalse() {
		FunctionArgument<?> result = new Evaluator("'AB' == 'AA'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringGreaterFalse() {
		FunctionArgument<?> result = new Evaluator("'AB' == 'A'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerCalc1() {
		FunctionArgument<?> result = new Evaluator("6 == ( 3 + 2 )").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerCalc2() {
		FunctionArgument<?> result = new Evaluator("6 == 3 + 2 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}
}
