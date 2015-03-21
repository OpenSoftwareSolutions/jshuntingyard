package org.oss.evaluator.function.impl;

import org.oss.evaluator.function.FunctionArgument;

@SuppressWarnings("rawtypes")
public class VariableArgument implements FunctionArgument {

	private final String variableName;
	private Object value;

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
		if (value instanceof Integer) {
			return ArgumentType.INTEGER;
		}
		if (value instanceof Double) {
			return ArgumentType.DOUBLE;
		}
		if (value instanceof String) {
			return ArgumentType.STRING;
		}
		if (value instanceof Object) {
			return ArgumentType.OBJECT;
		}
		if (value instanceof Boolean) {
			return ArgumentType.BOOLEAN;
		}
		return ArgumentType.NULL;
	}

	@Override
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return variableName;
	}

}
