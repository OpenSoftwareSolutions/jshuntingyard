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

public class ConverterTest {

	@Test
	public void booleanStringConverterTrue() {
		FunctionElementArgument<?> result = new Evaluator("booleanString ( true ) ").evaluate();
		AssertUtil.assertStringResult(result, "TRUE");
	}


	@Test
	public void booleanStringConverterFalse() {
		FunctionElementArgument<?> result = new Evaluator("booleanString ( false ) ").evaluate();
		AssertUtil.assertStringResult(result, "FALSE");
	}

	@Test(expected = ClassCastException.class)
	public void booleanStringConverterException() {
		new Evaluator("booleanString ( 0 ) ").evaluate();
	}


	@Test
	public void dateStringConverter() {
		FunctionElementArgument<?> result = new Evaluator("dateString ( 09.1974 ) ").evaluate();
		AssertUtil.assertStringResult(result, "(date)9.1974");
	}

	@Test(expected = ClassCastException.class)
	public void dateStringConverterException() {
		new Evaluator("dateString ( 1974 ) ").evaluate();
	}

	@Test
	public void doubleStringConverter() {
		FunctionElementArgument<?> result = new Evaluator("doubleString ( 56.00 ) ").evaluate();
		AssertUtil.assertStringResult(result, "56.0");
	}

	@Test(expected = ClassCastException.class)
	public void doubleStringExceptionConverter() {
		FunctionElementArgument<?> result = new Evaluator("doubleString (1234) ").evaluate();
		AssertUtil.assertStringResult(result, "56.0");
	}

	@Test
	public void integerStringConverter() {
		FunctionElementArgument<?> result = new Evaluator("intString ( 1234 ) ").evaluate();
		AssertUtil.assertStringResult(result, "1234");
	}

	@Test(expected = ClassCastException.class)
	public void integerStringExceptionConverter() {
		new Evaluator("intString ( 56.00 ) ").evaluate();
	}

}
