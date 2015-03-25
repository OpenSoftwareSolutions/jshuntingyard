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

public class ConverterTest {

	@Test
	public void booleanStringConverterTrue() {
		FunctionArgument<?> result = new Evaluator("booleanString ( '1.0' ) ").evaluate();
		AssertUtil.assertStringResult(result, "TRUE");
	}


	@Test
	public void booleanStringConverterFalse() {
		FunctionArgument<?> result = new Evaluator("booleanString ( '0.0' ) ").evaluate();
		AssertUtil.assertStringResult(result, "FALSE");
	}


	@Test
	public void dateStringConverter() {
		FunctionArgument<?> result = new Evaluator("dateString ( 'MM/dd/yyyy' ) ").evaluate();
		System.out.println(result);
	}

	@Test
	public void doubleStringConverter() {
		FunctionArgument<?> result = new Evaluator("doubleString ( '56.00' ) ").evaluate();
		AssertUtil.assertStringResult(result, "56.0");
	}

	@Test
	public void integerStringConverter() {
		FunctionArgument<?> result = new Evaluator("intString ( '1234' ) ").evaluate();
		AssertUtil.assertStringResult(result, "1234");
	}

}
