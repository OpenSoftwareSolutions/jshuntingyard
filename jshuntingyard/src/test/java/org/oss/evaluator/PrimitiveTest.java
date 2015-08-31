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

public class PrimitiveTest {


	@Test
	public void basicOperatorsCalc() {
		FunctionElementArgument<?> result = new Evaluator("( 1 + 2 ) * ( 3 / 4.0 ) ^ ( 5 + 6 )").evaluate();
		double expected = ( 1 + 2 ) * Math.pow( ( 3 / 4.0 ) , ( 5 + 6 ));
		AssertUtil.assertDoubleResult(result,expected);
	}

	@Test
	public void basicOperatorsPrecedenceComplex() {
		FunctionElementArgument<?> result = new Evaluator("(7 + 3 * 2 ^ 2 + 3) / 4.0").evaluate();
		double expected = (7 + 3 * Math.pow(2 , 2) + 3) / 4.0;
		AssertUtil.assertDoubleResult(result,expected);
	}

	@Test
	public void basicOperatorsPrecedence() {
		FunctionElementArgument<?> result = new Evaluator("1 + 2 * 3").evaluate();
		AssertUtil.assertIntegerResult(result,7);
	}

	@Test
	public void basicOperatorsDivide() {
		FunctionElementArgument<?> result = new Evaluator("10 / 2").evaluate();
		AssertUtil.assertIntegerResult(result,5);
	}
	
	@Test
	public void integerDivision() {
		FunctionElementArgument<?> result = new Evaluator("5 / 2").evaluate();
		int expected = 5 / 2;
		AssertUtil.assertIntegerResult(result,expected);
	}

	@Test
	public void longDivision() {
		FunctionElementArgument<?> result = new Evaluator("5 / 2l").evaluate();
		long expected = 5 / 2l;
		AssertUtil.assertLongResult(result,expected);
	}

	@Test
	public void doubleDivision() {
		FunctionElementArgument<?> result = new Evaluator("5 / 2.0").evaluate();
		double expected = 5 / 2.0;
		AssertUtil.assertDoubleResult(result,expected);
	}

	@Test
	public void floatDivision() {
		FunctionElementArgument<?> result = new Evaluator("5 / 2.0f").evaluate();
		float expected = 5 / 2.0f;
		AssertUtil.assertFloatResult(result,expected);
	}

	@Test
	public void basicOperatorsModulo() {
		FunctionElementArgument<?> result = new Evaluator("10 % 3").evaluate();
		AssertUtil.assertIntegerResult(result,1);
	}

	@Test
	public void basicAdd() {
		FunctionElementArgument<?> result = new Evaluator("10 + 3").evaluate();
		AssertUtil.assertIntegerResult(result,13);
	}

	@Test
	public void basicIntegerSubstraction() {
		FunctionElementArgument<?> result = new Evaluator("10 - 3").evaluate();
		AssertUtil.assertIntegerResult(result,7);
	}

	@Test
	public void basicDoubleSubstraction() {
		FunctionElementArgument<?> result = new Evaluator("10.0 - 3.0").evaluate();
		AssertUtil.assertDoubleResult(result,7.0);
	}

	@Test
	public void basicIntegerPower() {
		FunctionElementArgument<?> result = new Evaluator("8 ^ 2").evaluate();
		AssertUtil.assertIntegerResult(result,64);
	}

	@Test
	public void basicDoublePower() {
		FunctionElementArgument<?> result = new Evaluator("8.0 ^ 2.0").evaluate();
		AssertUtil.assertDoubleResult(result,64.0);
	}

	@Test
	public void basicDoubleIntegerPower() {
		FunctionElementArgument<?> result = new Evaluator("8.0 ^ 2").evaluate();
		AssertUtil.assertDoubleResult(result,64.0);
	}

	@Test
	public void basicAddString() {
		FunctionElementArgument<?> result = new Evaluator("'8' + '2'").evaluate();
		AssertUtil.assertStringResult(result,"82");
	}

	@Test
	public void basicDivideIntDouble() {
		FunctionElementArgument<?> result = new Evaluator("10 / 2.5").evaluate();
		AssertUtil.assertDoubleResult(result,4.0);
	}

	@Test
	public void basicMultiplyIntDouble() {
		FunctionElementArgument<?> result = new Evaluator("10 * 2.5").evaluate();
		AssertUtil.assertDoubleResult(result,25.0);
	}

	@Test
	public void basicMultiplyDoubleInt() {
		FunctionElementArgument<?> result = new Evaluator("4.3 * 2").evaluate();
		AssertUtil.assertDoubleResult(result,8.6);
	}


	@Test
	public void basicDivideDoubleInt() {
		FunctionElementArgument<?> result = new Evaluator("7.5 / 3").evaluate();
		AssertUtil.assertDoubleResult(result,2.5);
	}

	@Test
	public void basicAddDouble() {
		FunctionElementArgument<?> result = new Evaluator("8.0 + 2.3").evaluate();
		AssertUtil.assertDoubleResult(result,10.3);
	}

	@Test
	public void basicAddDoubleString() {
		FunctionElementArgument<?> result =  new Evaluator("8.0 + '2.0'").evaluate();
		AssertUtil.assertStringResult(result,"8.02.0");
	}

	@Test
	public void basicAddIntString() {
		FunctionElementArgument<?> result =  new Evaluator("'8.0' + 2").evaluate();
		AssertUtil.assertStringResult(result,"8.02");
	}

}
