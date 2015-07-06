package org.oss.evaluator;

import org.junit.Test;
import org.oss.jshuntingyard.evaluator.AbstractTwoArgFunctionElement;
import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.jshuntingyard.evaluator.interpreter.Evaluator;

public class NestedUserfunctionTests {

	@Test
	public void singleNested() {
		Evaluator eval = new Evaluator();
		eval.addFunction(new UserFunctionA());
		eval.addFunction(new UserFunctionB());
		eval.parse("funcA('AA',funcB(': 1','2'))");
		FunctionElementArgument<?> result = eval.evaluate();
		AssertUtil.assertStringResult(result, "AA: 1-2");
		
	}
	private static class UserFunctionA extends AbstractTwoArgFunctionElement<String, String, String> {
		public UserFunctionA() {
			super("funcA", Precedence.USERFUNCTION);
		}

		@Override
		public boolean isUserFunction() {
			return true;
		}

		@Override
		protected FunctionElementArgument<String> execute(
				FunctionElementArgument<String> a,
				FunctionElementArgument<String> b)
				throws IllegalArgumentException {
			return FunctionArgumentFactory.createString(a.getValue() + b.getValue());
		}
	}
	
	private static class UserFunctionB extends AbstractTwoArgFunctionElement<String, String, String> {
		public UserFunctionB() {
			super("funcB", Precedence.USERFUNCTION);
		}

		@Override
		public boolean isUserFunction() {
			return true;
		}

		@Override
		protected FunctionElementArgument<String> execute(
				FunctionElementArgument<String> a,
				FunctionElementArgument<String> b)
				throws IllegalArgumentException {
			return FunctionArgumentFactory.createString(a.getValue() + "-" + b.getValue());
		}
		
	}
	
}
