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
package org.oss.jshuntingyard.evaluator.operator.primitive;
import java.math.BigInteger;

import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument.ArgumentType;

public class Power extends AbstractTwoArgNumericFunctionElement {

	public Power() {
		super("^", Precedence.POWER);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected FunctionElementArgument<?> execute(FunctionElementArgument<?> a,
			FunctionElementArgument<?> b, ArgumentType evaluatesTo)
					throws IllegalArgumentException {
		switch (evaluatesTo) {
		case INTEGER:
			BigInteger bigA = new BigInteger(a.getString());
			return FunctionArgumentFactory.createObject(bigA.pow(((FunctionElementArgument<Integer>)b).getValue().intValue()).intValue());
		case DOUBLE:
			return FunctionArgumentFactory.createObject(Math.pow(getDouble(a), getDouble(b)));
		case BIG_DECIMAL:
			return FunctionArgumentFactory.createObject(getBigDecimal(a).pow(getDouble(b).intValue()));
		default:
			throw new IllegalArgumentException("Unsupported power operation for the types " + a.getType() + " and " + b.getType() + " for expected evaluation to " + evaluatesTo);
		}
	}


	@Override
	public boolean isUserFunction() {
		return false;
	}
}
