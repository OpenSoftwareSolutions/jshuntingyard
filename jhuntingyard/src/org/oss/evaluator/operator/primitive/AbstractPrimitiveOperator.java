package org.oss.evaluator.operator.primitive;
import org.oss.evaluator.funtion.FunctionArgument;
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
public abstract class AbstractPrimitiveOperator extends AbstractOperator {


	public AbstractPrimitiveOperator(String name, int numberOfParameters,
			Associativity associativity, Precedence precendence) {
		super(name, numberOfParameters, associativity, precendence);
	}


	protected void assertNumeric(FunctionArgument<?>... args) throws IllegalArgumentException {
		for (FunctionArgument<?> arg : args) {
			if (!(arg.getType()==FunctionArgument.ArgumentType.INTEGER || arg.getType()==FunctionArgument.ArgumentType.DOUBLE)) {
				throw new IllegalArgumentException(String.format("operator supports only numeric arguments and not %s", arg.getType()));
			}
		}
	}

	protected boolean isNumeric(FunctionArgument<?>... args) throws IllegalArgumentException {
		for (FunctionArgument<?> arg : args) {
			if (!(arg.getType()==FunctionArgument.ArgumentType.INTEGER || arg.getType()==FunctionArgument.ArgumentType.DOUBLE)) {
				return false;
			}
		}
		return true;
	}


	protected void assertInteger(FunctionArgument<?>... args) throws IllegalArgumentException {
		for (FunctionArgument<?> arg : args) {
			if (!(arg.getType()==FunctionArgument.ArgumentType.INTEGER) ) {
				throw new IllegalArgumentException(String.format("operator supports only integer arguments and not %s", arg.getType()));
			}
		}
	}


	@Override
	public FunctionArgument<?> execute(FunctionArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		assertNumeric(args);
		return execute(args[0],args[1]);
	}

	abstract protected FunctionArgument<?> execute(FunctionArgument<?> a, FunctionArgument<?> b) throws IllegalArgumentException;

}
