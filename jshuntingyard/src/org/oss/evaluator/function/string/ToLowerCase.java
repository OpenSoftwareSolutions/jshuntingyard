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
package org.oss.evaluator.function.string;

import org.oss.evaluator.function.FunctionArgument;
import org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.evaluator.function.impl.StringArgument;

/**
 * The java.lang.String.toLowerCase() method converts all of the characters in this String to upper case using the rules of the default locale.
 *
 */
public class ToLowerCase extends AbstractStringOperatorAssociativityLeftOneArg {

	public ToLowerCase() {
		super("toLowerCase", Precedence.USERFUNCTION);
	}


	@Override
	public boolean isUserFunction() {
		return true;
	}


	/* This method returns the String, converted to lower case.
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionArgument<?> execute(FunctionArgument<?> a)
			throws IllegalArgumentException {

		if (a.getType()==FunctionArgument.ArgumentType.STRING) {
			String value = ((StringArgument)a).getValue();
			return FunctionArgumentFactory.createString(value.toLowerCase());
		}

		throw new IllegalArgumentException(String.format("only string as type is supported and not ", a.getType()));
	}

}