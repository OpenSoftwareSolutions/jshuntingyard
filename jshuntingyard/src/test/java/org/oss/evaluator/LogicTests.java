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

public class LogicTests {

	@Test
	public void booleanEvalLogicalTrueTrue() {
		FunctionElementArgument<?> result = new Evaluator("true && true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalLogicalNotTrueFalse() {
		FunctionElementArgument<?> result = new Evaluator("! true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalLogicalNotTrueTrue() {
		FunctionElementArgument<?> result = new Evaluator("! false").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalLogicalAndTrue() {
		FunctionElementArgument<?> result = new Evaluator(" 2 + 3 == 5  && true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalLogicalOrTrue() {
		FunctionElementArgument<?> result = new Evaluator(" 2 + 3 == 5 || true").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}


	@Test
	public void booleanEvalLogicalAndTrueFalse() {
		FunctionElementArgument<?> result = new Evaluator(" 2 < 4  &&  3 > 4 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalLogicalAndTrueTrue() {
		FunctionElementArgument<?> result = new Evaluator(" 3 < 4  &&  5 > 4 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalLogicalAndFalseTrue() {
		FunctionElementArgument<?> result = new Evaluator(" 5 < 4  &&  6 > 4 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalLogicalAndFalseFalse() {
		FunctionElementArgument<?> result = new Evaluator(" 6 < 4  &&  3 > 4 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalLogicalOrFalseTrue() {
		FunctionElementArgument<?> result = new Evaluator(" 2 < 4  || 3 > 4 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void booleanEvalLogicalOrFalse() {
		FunctionElementArgument<?> result = new Evaluator(" 5 < 4  || 3 > 4 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void booleanEvalLogicalOrTrueTrue() {
		FunctionElementArgument<?> result = new Evaluator(" 5 < 4  || 5 > 4 ").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test(expected = ClassCastException.class)
	public void booleanEvalLogicalOrStringNumber() {
		new Evaluator(" '2'  || 5 > 4 ").evaluate();
	}
}
