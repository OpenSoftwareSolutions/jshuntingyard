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

package org.oss.jshuntingyard.evaluator.userfunction.math;

import org.oss.jshuntingyard.evaluator.AbstractOneArgFunctionElement;
import org.oss.jshuntingyard.evaluator.DoubleArgument;
import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;


/**
 * The java.lang.Math.toDegrees(double angrad) converts an angle measured in radians to an approximately equivalent angle measured in degrees.
 * The conversion from radians to degrees is generally inexact; users should not expect cos(toRadians(90.0)) to exactly equal 0.0.
 */
public class ToDegrees extends AbstractOneArgFunctionElement<Double,Double> {

	public ToDegrees() {
		super("toDegrees", Precedence.USERFUNCTION);
	}



	/* This method returns the measurement of the angle angrad in degrees.
	 * @see org.oss.evaluator.operator.AbstractNumericOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	public FunctionElementArgument<Double> execute(FunctionElementArgument<Double> a) throws IllegalArgumentException {

		if (a instanceof DoubleArgument) {
			return FunctionArgumentFactory.createObject(Math.toDegrees(a.getValue()));
		}
		throw new IllegalArgumentException(String.format("only double operator supported and not ", a.getType()));
	}

	@Override
	public boolean isUserFunction() {
		return false;
	}
}