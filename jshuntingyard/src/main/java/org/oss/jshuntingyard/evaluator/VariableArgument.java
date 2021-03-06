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
package org.oss.jshuntingyard.evaluator;


/**
 * Argument that is temporary used as placeholder for bound variables<br>
 * till evaluation. Then the actual type is known and the variable is replaced<br>
 * by one of the built in types (double, integer, boolean, ...)
 *
 * @author magnus
 */
@SuppressWarnings("rawtypes")
public class VariableArgument implements FunctionElementArgument {

	private final String variableName;

	VariableArgument(String variableName) {
		super();
		this.variableName = variableName;
	}

	@Override
	public String getString() {
		return variableName;
	}

	@Override
	public ArgumentType getType() {
		throw new UnsupportedOperationException("The methode getType() is handled by the FunctionArgumentFactory and at this place not implemented.");
	}

	@Override
	public Object getValue() {
		throw new UnsupportedOperationException("The methode getValue() is handled by the FunctionArgumentFactory and at this place not implemented.");
	}

	public String getName() {
		return variableName;
	}

}
