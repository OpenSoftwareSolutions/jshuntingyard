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

public class ConditionalTest {

	@Test
	public void conditionalYes() {
		FunctionElementArgument<?> result = new Evaluator("ifelse ( true , 'YES' , 'NO' ) ").evaluate();
		AssertUtil.assertStringResult(result, "YES");
	}

	@Test
	public void conditionalNo() {
		FunctionElementArgument<?> result = new Evaluator("ifelse ( false , 'YES' , 'NO' ) ").evaluate();
		AssertUtil.assertStringResult(result, "NO");
	}

}
