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
package org.oss.jshuntingyard.evaluator.userfunction.string;

import org.oss.jshuntingyard.evaluator.AbstractTwoArgFunctionElement;
import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;

/**
 * The java.lang.String.equalsIgnoreCase() method compares this string to the specified object.
 * The result is true if and only if the argument is not null and is a String object that represents the same sequence of characters as this object.
 */
public class EqualsIgnoreCase extends AbstractTwoArgFunctionElement<Boolean,String,String> {

	public EqualsIgnoreCase() {
		super("equalsIgnoreCase", Precedence.USERFUNCTION);

	}

	@Override
	public boolean isUserFunction() {
		return true;
	}


	/* This method returns true if the given object represents a String equivalent to this string, else false.
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftTwoArg#execute(org.oss.evaluator.function.FunctionArgument, org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionElementArgument<Boolean> execute(FunctionElementArgument<String> a,FunctionElementArgument<String> b) throws IllegalArgumentException {
		return FunctionArgumentFactory.createObject(a.getValue().equalsIgnoreCase(b.getValue()));
	}
}