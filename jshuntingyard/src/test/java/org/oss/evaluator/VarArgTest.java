package org.oss.evaluator;

import org.junit.Test;
import org.oss.jshuntingyard.evaluator.AbstractFunctionElement;
import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;
import org.oss.jshuntingyard.evaluator.interpreter.Evaluator;

public class VarArgTest {

	@Test
	public void varagrgs() {
		Evaluator eval = new Evaluator();
		eval.addFunction(new VarArgFunction());
		eval.parse("foo('1','2','3','4','5')");
		@SuppressWarnings("unchecked")
		FunctionElementArgument<String> result = (FunctionElementArgument<String>) eval.evaluate();
		AssertUtil.assertStringResult(result, "12345");
	}
	
	@Test
	public void varagrgsWithOtherFn() {
		Evaluator eval = new Evaluator();
		eval.addFunction(new VarArgFunction());
		eval.parse("'AAA' + toUpperCase('xxx') + foo('1','2','3','4','5')");
		@SuppressWarnings("unchecked")
		FunctionElementArgument<String> result = (FunctionElementArgument<String>) eval.evaluate();
		AssertUtil.assertStringResult(result, "AAAXXX12345");
	}
	
	
	private static class VarArgFunction extends AbstractFunctionElement {

		public VarArgFunction() {
			super("foo", -1, Precedence.USERFUNCTION);
		}

		@Override
		public boolean isUserFunction() {
			return true;
		}

		@Override
		public FunctionElementArgument<?> execute(
				FunctionElementArgument<?>... args)
				throws IllegalArgumentException {
			String text = "";
			for (FunctionElementArgument<?> arg : args) {
				text += arg.getValue();
			}
			return FunctionArgumentFactory.createString(text);
		}
		
	}
}
