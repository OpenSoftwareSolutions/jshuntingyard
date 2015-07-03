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

public class StringTest {
	@Test
	public void strlengthCompare() {
		FunctionElementArgument<?> result = new Evaluator("len ( 'foo' ) < 4").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}
	@Test
	public void strlength() {
		FunctionElementArgument<?> result = new Evaluator("len ( 'foo' )").evaluate();
		AssertUtil.assertIntegerResult(result, 3);
	}

	@Test
	public void booleanEvalLogicalAndTrue() {
		FunctionElementArgument<?> result = new Evaluator("'foo' + '4' + 'bar'").evaluate();
		AssertUtil.assertStringResult(result, "foo4bar");
	}

	@Test
	public void substring() {
		FunctionElementArgument<?> result = new Evaluator("substr ( 'foo5bar' , 3 , 7 )").evaluate();
		AssertUtil.assertStringResult(result, "5bar");
	}

	@Test
	public void charAt1() {
		FunctionElementArgument<?> result = new Evaluator("charAt ( 'Tutorialspoint' , 0 )").evaluate();
		AssertUtil.assertStringResult(result, "T");
	}

	@Test
	public void charAt2() {
		FunctionElementArgument<?> result = new Evaluator("charAt ( 'Tutorialspoint' , 4 )").evaluate();
		AssertUtil.assertStringResult(result, "r");
	}

	@Test
	public void compareToPositiv() {
		FunctionElementArgument<?> result = new Evaluator("compareTo ( 'Tutorialspoint' , 'Tutorialspoint' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void compareToNegativ() {
		FunctionElementArgument<?> result = new Evaluator("compareTo ( 'Tutorialspoint' , 'Tutorialspoin' )").evaluate();
		AssertUtil.assertBooleanResult(result, false);
	}

	@Test
	public void compareToIgnoreCasePositiv() {
		FunctionElementArgument<?> result = new Evaluator("compareToIgnoreCase ( 'Tutorialspoint' , 'tutorialSpoint' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void compareToIgnoreCaseNegativ() {
		FunctionElementArgument<?> result = new Evaluator("compareToIgnoreCase ( 'Tutorialspoint' , 'tutorialSpoin' )").evaluate();
		AssertUtil.assertBooleanResult(result, false);
	}

	@Test
	public void equalsIgnoreCasePositiv() {
		FunctionElementArgument<?> result = new Evaluator("equalsIgnoreCase ( 'Tutorialspoint' , 'tutorialSpoint' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void equalsIgnoreCaseNegativ() {
		FunctionElementArgument<?> result = new Evaluator("equalsIgnoreCase ( 'Tutorialspoint' , 'tutorialSpoin' )").evaluate();
		AssertUtil.assertBooleanResult(result, false);
	}

	@Test
	public void equalsPositiv() {
		FunctionElementArgument<?> result = new Evaluator("equals ( 'Tutorialspoint' , 'Tutorialspoint' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void equalsNegativ() {
		FunctionElementArgument<?> result = new Evaluator("equals ( 'Tutorialspoint' , 'Tutorialspoin' )").evaluate();
		AssertUtil.assertBooleanResult(result, false);
	}

	@Test
	public void concat() {
		FunctionElementArgument<?> result = new Evaluator("concat ( 'Tutorial' , 'spoint' )").evaluate();
		AssertUtil.assertStringResult(result, "Tutorialspoint");
	}

	@Test
	public void containsTrue1() {
		FunctionElementArgument<?> result = new Evaluator("contains ( 'Tutorial' , 'Tuto' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void containsTrue2() {
		FunctionElementArgument<?> result = new Evaluator("contains ( 'Tutorial' , 'T' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void containsFalse() {
		FunctionElementArgument<?> result = new Evaluator("contains ( 'Tutorial' , 'B' )").evaluate();
		AssertUtil.assertBooleanResult(result, false);
	}

	@Test
	public void endsWithTrue() {
		FunctionElementArgument<?> result = new Evaluator("endsWith ( 'Tutorial' , 'l' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void endsWithFalse() {
		FunctionElementArgument<?> result = new Evaluator("endsWith ( 'Tutorial' , 'B' )").evaluate();
		AssertUtil.assertBooleanResult(result, false);
	}

	@Test
	public void replace() {
		FunctionElementArgument<?> result = new Evaluator("replace ( 'Tutorial' , 'or' , 'tt' )").evaluate();
		AssertUtil.assertStringResult(result, "Tutttial");
	}

	@Test
	public void startsWithTrue() {
		FunctionElementArgument<?> result = new Evaluator("startsWith ( 'Tutorial' , 'Tut' )").evaluate();
		AssertUtil.assertBooleanResult(result, true);
	}

	@Test
	public void startsWithFalse() {
		FunctionElementArgument<?> result = new Evaluator("startsWith ( 'Tutorial' , 'But' )").evaluate();
		AssertUtil.assertBooleanResult(result, false);
	}

	@Test
	public void toUpperCase() {
		FunctionElementArgument<?> result = new Evaluator("toUpperCase ( 'Tutorial' )").evaluate();
		AssertUtil.assertStringResult(result, "TUTORIAL");
	}

	@Test
	public void toLowerCase() {
		FunctionElementArgument<?> result = new Evaluator("toLowerCase ( 'Tutorial' )").evaluate();
		AssertUtil.assertStringResult(result, "tutorial");
	}

	@Test
	public void indexOf() {
		FunctionElementArgument<?> result = new Evaluator("indexOf ( 'Tutorialo' , 'o' )").evaluate();
		AssertUtil.assertIntegerResult(result, 3);
	}

	@Test
	public void lastIndexOf() {
		FunctionElementArgument<?> result = new Evaluator("lastIndexOf ( 'tutorialo' , 'o' )").evaluate();
		AssertUtil.assertIntegerResult(result, 8);
	}

	@Test
	public void likeTrue1() {
		FunctionElementArgument<?> result = new Evaluator("like ( 'Hello' , 'he%' )").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void likeTrue2() {
		FunctionElementArgument<?> result = new Evaluator("like ( 'Hello' , '%Hel%' )").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void likeFalse() {
		FunctionElementArgument<?> result = new Evaluator("like ( 'Hello' , '%bye%' )").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void matchesTrue() {
		FunctionElementArgument<?> result = new Evaluator("matches ( '7:05' , '.*?\\d+?:\\d+?' )").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.TRUE);
	}

	@Test
	public void matchesFalse() {
		FunctionElementArgument<?> result = new Evaluator("matches ( '7.05' , '.*?\\d+?:\\d+?' )").evaluate();
		AssertUtil.assertBooleanResult(result, Boolean.FALSE);
	}

	@Test
	public void numberFormat() {
		FunctionElementArgument<?> result = new Evaluator("numberFormat ( '%f' , 3.141592653589793 )").evaluate();
		AssertUtil.assertStringResult(result, "3.141593");
	}

}
