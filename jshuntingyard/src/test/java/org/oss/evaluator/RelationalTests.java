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
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.jshuntingyard.evaluator.interpreter.Evaluator;

public class RelationalTests {

	@Test
	public void booleanEvalNullFalse() {
		FunctionElementArgument<?> result = new Evaluator("null == true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalNullTrue() {
		FunctionElementArgument<?> result = new Evaluator("null == null").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalTrue() {
		FunctionElementArgument<?> result = new Evaluator("true == true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalFalse() {
		FunctionElementArgument<?> result = new Evaluator("False == true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerTrue() {
		FunctionElementArgument<?> result = new Evaluator("7 == 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerFalse() {
		FunctionElementArgument<?> result = new Evaluator("3 == 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerLesserTrue() {
		FunctionElementArgument<?> result = new Evaluator("3 < 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}
	@Test
	public void booleanEvalIntegerLesserOrEqualToTrue() {
		FunctionElementArgument<?> result = new Evaluator("3 <= 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}
	@Test
	public void booleanEvalIntegerLesserOrEqualToBoundsTrue() {
		FunctionElementArgument<?> result = new Evaluator("3 <= 3").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerLesserFalse() {
		FunctionElementArgument<?> result = new Evaluator("10 < 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerLesserOrEqualToFalse() {
		FunctionElementArgument<?> result = new Evaluator("10 <= 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerGreaterTrue() {
		FunctionElementArgument<?> result = new Evaluator("10 > 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerGreaterFalse() {
		FunctionElementArgument<?> result = new Evaluator("10 > 10").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerGreaterOrEqualToTrue() {
		FunctionElementArgument<?> result = new Evaluator("10 >= 7").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerGreaterOrEqualToBoundsTrue() {
		FunctionElementArgument<?> result = new Evaluator("10 >= 10").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalIntegerGreaterOrEqualToFalse() {
		FunctionElementArgument<?> result = new Evaluator("9 >= 10").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringEqualTrue() {
		FunctionElementArgument<?> result = new Evaluator("'foo' == 'foo'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringEqualFalse() {
		FunctionElementArgument<?> result = new Evaluator("'foo' == 'bar'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringNotEqualTrue() {
		FunctionElementArgument<?> result = new Evaluator("'foo' != 'bar'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalStringNotEqualFalse() {
		FunctionElementArgument<?> result = new Evaluator("'foo' != 'foo'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringEqualToFalse() {
		FunctionElementArgument<?> result = new Evaluator("'AB' == 'AA'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalStringGreaterFalse() {
		FunctionElementArgument<?> result = new Evaluator("'AB' == 'A'").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerCalc1() {
		FunctionElementArgument<?> result = new Evaluator("6 == ( 3 + 2 )").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalIntegerCalc2() {
		FunctionElementArgument<?> result = new Evaluator("6 == 3 + 2 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}
}
