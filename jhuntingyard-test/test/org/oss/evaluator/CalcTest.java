package org.oss.evaluator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.oss.evaluator.function.FunctionArgument;
import org.oss.evaluator.function.impl.DoubleArgument;
import org.oss.evaluator.function.impl.IntegerArgument;

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
public class CalcTest {


	@Test
	public void basicOperatorsCalc() {
		FunctionArgument<?> result = new Evaluator("( 1 + 2 ) * ( 3 / 4 ) ^ ( 5 + 6 )").evaluate();
		assertDoubleResult(result,0.12670540809631348);
	}

	@Test
	public void basicOperatorsPrecedence() {
		FunctionArgument<?> result = new Evaluator("1 + 2 * 3").evaluate();
		assertIntegerResult(result,7);
	}

	@Test
	public void basicOperatorsMaxInt() {
		FunctionArgument<?> result = new Evaluator("max ( 2 , 3 ) + 2").evaluate();
		assertIntegerResult(result,5);
	}

	@Test
	public void basicOperatorsMaxDouble() {
		FunctionArgument<?> result = new Evaluator("max ( 2.2929 , 3.49494 )").evaluate();
		assertDoubleResult(result,3.49494);
	}

	@Test
	public void basicOperatorsMinInt() {
		FunctionArgument<?> result = new Evaluator("min ( 2 , 3 )").evaluate();
		assertIntegerResult(result,2);
	}

	@Test
	public void basicOperatorsMinDouble() {
		FunctionArgument<?> result = new Evaluator("min ( 2.2929 , 3.49494 )").evaluate();
		assertDoubleResult(result, 2.2929);
	}

	@Test
	public void basicOperatorsRound() {
		FunctionArgument<?> result = new Evaluator("round ( 3.49494 )").evaluate();
		assertIntegerResult(result,3);
	}


	@Test
	public void basicOperatorsModulo() {
		FunctionArgument<?> result = new Evaluator("10 % 3").evaluate();
		assertIntegerResult(result,1);
	}

	@Test
	public void variables() {
		Evaluator evaluator = new Evaluator("max ( $1 , $2 )");
		MyVar var1 = new MyVar();
		var1.name = "1";
		var1.value = 2.3456;
		MyVar var2 = new MyVar();
		var2.name = "2";
		var2.value=22.89;
		evaluator.bindVariable(var2);
		evaluator.bindVariable(var1);
		FunctionArgument<?> result = evaluator.evaluate();
		assertDoubleResult(result,22.89);
	}

	@Test
	public void variables2() {
		Evaluator evaluator = new Evaluator("max ( $1 , $2 )");
		MyVar var1 = new MyVar();
		var1.name = "1";
		var1.value = 2.3456;
		MyVar var2 = new MyVar();
		var2.name = "2";
		var2.value=22.89;
		evaluator.bindVariable(var2);
		evaluator.bindVariable(var1);
		FunctionArgument<?> result = evaluator.evaluate();
		assertDoubleResult(result,22.89);
	}

	private static class MyVar implements Variable {

		String name;
		Double value;
		@Override
		public String getName() {
			return name;
		}

		@Override
		public Object getValue() {
			return value;
		}

	}

	private void assertDoubleResult(FunctionArgument<?> result, Double expected) {
		assertTrue("DoubleArgument expected",result instanceof DoubleArgument);
		DoubleArgument doubleResult = (DoubleArgument) result;
		assertTrue("Value of " + expected + " expected and not " + doubleResult ,doubleResult.getValue().equals(expected));
	}

	private void assertIntegerResult(FunctionArgument<?> result, Integer expected) {
		assertTrue("IntegerArgument expected",result instanceof IntegerArgument);
		IntegerArgument integerResult = (IntegerArgument) result;
		assertTrue("Value of " + expected + " expected and not " + integerResult ,integerResult.getValue().equals(expected));
	}

}
