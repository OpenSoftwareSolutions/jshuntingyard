package org.oss.evaluator;

import org.junit.Test;
import org.oss.evaluator.function.FunctionArgument;

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
public class MathTest {


	@Test
	public void basicOperatorsMaxInt() {
		FunctionArgument<?> result = new Evaluator("max ( 2 , 3 ) + 2").evaluate();
		AssertUtil.assertIntegerResult(result,5);
	}

	@Test
	public void basicOperatorsMaxDouble() {
		FunctionArgument<?> result = new Evaluator("max ( 2.2929 , 3.49494 )").evaluate();
		AssertUtil.assertDoubleResult(result,3.49494);
	}

	@Test
	public void basicOperatorsMinInt() {
		FunctionArgument<?> result = new Evaluator("min ( 2 , 3 )").evaluate();
		AssertUtil.assertIntegerResult(result,2);
	}

	@Test
	public void basicOperatorsMinDouble() {
		FunctionArgument<?> result = new Evaluator("min ( 2.2929 , 3.49494 )").evaluate();
		AssertUtil.assertDoubleResult(result, 2.2929);
	}

	@Test
	public void basicOperatorsRound() {
		FunctionArgument<?> result = new Evaluator("round ( 3.49494 )").evaluate();
		AssertUtil.assertIntegerResult(result,3);
	}

}
