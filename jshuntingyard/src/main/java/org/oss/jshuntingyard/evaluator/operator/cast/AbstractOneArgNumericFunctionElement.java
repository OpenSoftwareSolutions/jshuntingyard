package org.oss.jshuntingyard.evaluator.operator.cast;

import org.oss.jshuntingyard.evaluator.AbstractFunctionElement;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;

public abstract class AbstractOneArgNumericFunctionElement<R> extends AbstractFunctionElement implements ResultCastCapable {

	public AbstractOneArgNumericFunctionElement(String name, Precedence precendence) {
		super(name, 1, precendence);
	}

	@Override
	public FunctionElementArgument<R> execute(FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		if (isNumeric(args)) {
			return execute(args[0]);
		}
		throw new IllegalArgumentException("Single numeric operand are expected but not " + args[0].getType());
	}

	

	@Override
	public boolean isUserFunction() {
		return false;
	}

	@Override
	public String getNamePrefix() {
		return "R";
	}

	abstract protected FunctionElementArgument<R> execute(FunctionElementArgument<?> a) throws IllegalArgumentException;
	
	protected static String getName(boolean doResultCast, String name) {
		return doResultCast ? "R" + name : name;
	}
	
	protected static Precedence getPrecedence(boolean doResultCast) {
		return doResultCast ? Precedence.RESULTCAST : Precedence.UNARY;
	}
}
