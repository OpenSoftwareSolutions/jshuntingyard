package org.oss.evaluator.function.string;

import org.oss.evaluator.funtion.FunctionArgument;
import org.oss.evaluator.funtion.impl.FunctionArgumentFactory;
import org.oss.evaluator.funtion.impl.StringArgument;
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
public class StrLength extends AbstractOperator {

	public StrLength() {
		super("len", 1, Associativity.LEFT, Precedence.USERFUNCTION);
	}


	@Override
	public FunctionArgument<?> execute(FunctionArgument<?>... args)
			throws IllegalArgumentException {
		assertNumArgs(args);
		if (isString(args)) {
			StringArgument a = (StringArgument) args[0];
			return FunctionArgumentFactory.createObject(a.getValue().length());
		}
		throw new IllegalArgumentException(String.format("only string operator supported and not ", args[0].getType()));
	}


	@Override
	public boolean isUserFunction() {
		return true;
	}

}
