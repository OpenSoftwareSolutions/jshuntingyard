package org.oss.evaluator.function.string;

import org.oss.evaluator.function.FunctionArgument;
import org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.evaluator.operator.AbstractOperator;

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
public class Substring extends AbstractOperator {

	public Substring() {
		super("substr", 3, Associativity.LEFT, Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}

	@Override
	public FunctionArgument<?> execute(FunctionArgument<?>... args)
			throws IllegalArgumentException {
		assertNumArgs(args);
		String string = (String) args[0].getValue();
		Integer begin = (Integer) args[1].getValue();
		Integer end = (Integer) args[2].getValue();

		return FunctionArgumentFactory.createString(string.substring(begin, end));
	}

}
