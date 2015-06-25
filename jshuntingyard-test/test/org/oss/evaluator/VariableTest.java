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
import org.oss.evaluator.function.impl.StringArgument;

public class VariableTest {

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
		AssertUtil.assertDoubleResult(result,22.89);
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
		AssertUtil.assertDoubleResult(result,22.89);
	}

	@Test
	public void variablesWithUnderscore() {
		Evaluator evaluator = new Evaluator("max ( $WITH_ , $2 )");
		MyVar var1 = new MyVar();
		var1.name = "WITH_";
		var1.value = 2.3456;
		MyVar var2 = new MyVar();
		var2.name = "2";
		var2.value=22.89;
		evaluator.bindVariable(var2);
		evaluator.bindVariable(var1);
		FunctionArgument<?> result = evaluator.evaluate();
		AssertUtil.assertDoubleResult(result,22.89);
	}

	@Test
	public void variablesWithUnderscoreAndLetters() {
		Evaluator evaluator = new Evaluator("max ( $WITH_ME , $2 )");
		MyVar var1 = new MyVar();
		var1.name = "WITH_ME";
		var1.value = 2.3456;
		MyVar var2 = new MyVar();
		var2.name = "2";
		var2.value=22.89;
		evaluator.bindVariable(var2);
		evaluator.bindVariable(var1);
		FunctionArgument<?> result = evaluator.evaluate();
		AssertUtil.assertDoubleResult(result,22.89);
	}
	@Test
	public void variablesConcatenating() {
		Evaluator evaluator = new Evaluator("'hallo ' + $1");
		MyVar var1 = new MyVar();
		var1.name = "1";
		var1.value = "welt";
		evaluator.bindVariable(var1);
		FunctionArgument<?> result = evaluator.evaluate();
		AssertUtil.assertStringResult(result,"hallo welt");
	}
	@Test
	public void variablesAdd() {
		Evaluator evaluator = new Evaluator("2 + $1");
		MyVar var1 = new MyVar();
		var1.name = "1";
		var1.value = 1;
		evaluator.bindVariable(var1);
		FunctionArgument<?> result = evaluator.evaluate();
		AssertUtil.assertIntegerResult(result,3);
	}

	private static class MyVar implements Variable {

		String name;
		Object value;
		@Override
		public String getName() {
			return name;
		}

		@Override
		public Object getValue() {
			return value;
		}

	}

}
