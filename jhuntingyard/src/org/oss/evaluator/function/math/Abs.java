/*
 * Copyright 2002-2007 Robert Breidecker.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.oss.evaluator.function.math;

import org.oss.evaluator.function.FunctionArgument;
import org.oss.evaluator.function.impl.FunctionArgumentFactory;
import org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg;


/**
 * This class is a function that executes within Evaluator. The function returns
 * the absolute value of a double value. See the Math.abs(double) method in the
 * JDK for a complete description of how this function works.
 */
public class Abs extends AbstractNumericOperatorAssociativityLeftOneArg {

	public Abs(String name, Precedence precendence) {
		super("abs", Precedence.USERFUNCTION);
	}



	@Override
	public FunctionArgument<?> execute(FunctionArgument<?> a) throws IllegalArgumentException {

		if (a.getType()==FunctionArgument.ArgumentType.DOUBLE) {
			return FunctionArgumentFactory.createObject(Math.abs(getDouble(a)));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return true;
	}
}