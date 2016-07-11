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
package org.oss.jshuntingyard.evaluator.operator.cast;

import java.math.BigDecimal;

import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public class BigDecimalCast extends AbstractOneArgNumericFunctionElement<BigDecimal> {

	public BigDecimalCast(boolean doResultCast) {
		super(getName(doResultCast,"(bigdecimal)"), getPrecedence(doResultCast));
	}

	@Override
	protected FunctionElementArgument<BigDecimal> execute(
			FunctionElementArgument<?> a) throws IllegalArgumentException {
		if (a.getValue() instanceof BigDecimal) {
			return FunctionArgumentFactory.createObject((BigDecimal)a.getValue());
		} else {
			return FunctionArgumentFactory.createObject(new BigDecimal(((Number)a.getValue()).toString()));			
		}
	}
}
