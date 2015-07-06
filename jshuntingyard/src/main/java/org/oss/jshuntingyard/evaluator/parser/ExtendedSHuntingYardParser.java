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
package org.oss.jshuntingyard.evaluator.parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElement;
import org.oss.jshuntingyard.evaluator.FunctionElement.Associativity;
import org.oss.jshuntingyard.evaluator.interpreter.Expression;
import org.oss.jshuntingyard.evaluator.interpreter.ExpressionElement;
import org.oss.jshuntingyard.evaluator.operator.logic.AndOperator;
import org.oss.jshuntingyard.evaluator.operator.logic.NotOperator;
import org.oss.jshuntingyard.evaluator.operator.logic.OrOperator;
import org.oss.jshuntingyard.evaluator.operator.primitive.Add;
import org.oss.jshuntingyard.evaluator.operator.primitive.Divide;
import org.oss.jshuntingyard.evaluator.operator.primitive.Modulo;
import org.oss.jshuntingyard.evaluator.operator.primitive.Multiply;
import org.oss.jshuntingyard.evaluator.operator.primitive.Power;
import org.oss.jshuntingyard.evaluator.operator.primitive.Subtract;
import org.oss.jshuntingyard.evaluator.operator.relational.EqualTo;
import org.oss.jshuntingyard.evaluator.operator.relational.GreaterThan;
import org.oss.jshuntingyard.evaluator.operator.relational.GreaterThanOrEqualTo;
import org.oss.jshuntingyard.evaluator.operator.relational.LessThan;
import org.oss.jshuntingyard.evaluator.operator.relational.LessThanOrEqualTo;
import org.oss.jshuntingyard.evaluator.operator.relational.NotEqualTo;
import org.oss.jshuntingyard.evaluator.userfunction.math.Abs;
import org.oss.jshuntingyard.evaluator.userfunction.math.Acos;
import org.oss.jshuntingyard.evaluator.userfunction.math.Asin;
import org.oss.jshuntingyard.evaluator.userfunction.math.Atan;
import org.oss.jshuntingyard.evaluator.userfunction.math.Atan2;
import org.oss.jshuntingyard.evaluator.userfunction.math.Ceil;
import org.oss.jshuntingyard.evaluator.userfunction.math.Cos;
import org.oss.jshuntingyard.evaluator.userfunction.math.Exp;
import org.oss.jshuntingyard.evaluator.userfunction.math.Floor;
import org.oss.jshuntingyard.evaluator.userfunction.math.IEEEremainder;
import org.oss.jshuntingyard.evaluator.userfunction.math.Log;
import org.oss.jshuntingyard.evaluator.userfunction.math.Max;
import org.oss.jshuntingyard.evaluator.userfunction.math.Min;
import org.oss.jshuntingyard.evaluator.userfunction.math.Random;
import org.oss.jshuntingyard.evaluator.userfunction.math.Rint;
import org.oss.jshuntingyard.evaluator.userfunction.math.Round;
import org.oss.jshuntingyard.evaluator.userfunction.math.Sin;
import org.oss.jshuntingyard.evaluator.userfunction.math.Sqrt;
import org.oss.jshuntingyard.evaluator.userfunction.math.Tan;
import org.oss.jshuntingyard.evaluator.userfunction.math.ToDegrees;
import org.oss.jshuntingyard.evaluator.userfunction.math.ToRadians;
import org.oss.jshuntingyard.evaluator.userfunction.string.CharAt;
import org.oss.jshuntingyard.evaluator.userfunction.string.CompareTo;
import org.oss.jshuntingyard.evaluator.userfunction.string.CompareToIgnoreCase;
import org.oss.jshuntingyard.evaluator.userfunction.string.Concat;
import org.oss.jshuntingyard.evaluator.userfunction.string.Contains;
import org.oss.jshuntingyard.evaluator.userfunction.string.EndsWith;
import org.oss.jshuntingyard.evaluator.userfunction.string.Equals;
import org.oss.jshuntingyard.evaluator.userfunction.string.EqualsIgnoreCase;
import org.oss.jshuntingyard.evaluator.userfunction.string.IndexOf;
import org.oss.jshuntingyard.evaluator.userfunction.string.LastIndexOf;
import org.oss.jshuntingyard.evaluator.userfunction.string.Length;
import org.oss.jshuntingyard.evaluator.userfunction.string.Like;
import org.oss.jshuntingyard.evaluator.userfunction.string.Matches;
import org.oss.jshuntingyard.evaluator.userfunction.string.NumberFormat;
import org.oss.jshuntingyard.evaluator.userfunction.string.Replace;
import org.oss.jshuntingyard.evaluator.userfunction.string.StartsWith;
import org.oss.jshuntingyard.evaluator.userfunction.string.Substring;
import org.oss.jshuntingyard.evaluator.userfunction.string.ToLowerCase;
import org.oss.jshuntingyard.evaluator.userfunction.string.ToUpperCase;
import org.oss.jshuntingyard.lexer.ExpressionRule;
import org.oss.jshuntingyard.lexer.ExpressionRule.TokenType;
import org.oss.jshuntingyard.lexer.ExpressionToken;
import org.oss.jshuntingyard.lexer.ExpressionTokenizer;

/**
 * Shunting-yard algorithm
 * In computer science, the shunting-yard algorithm is a method for parsing mathematical expressions specified in infix notation.
 * It can be used to produce output in Reverse Polish notation (RPN) or as an abstract syntax tree (AST).
 * The algorithm was invented by Edsger Dijkstra and named the "shunting yard" algorithm because its operation resembles that of a railroad shunting yard.
 * Dijkstra first described the Shunting Yard Algorithm in the Mathematisch Centrum report MR 34/61.
 * Like the evaluation of RPN, the shunting yard algorithm is stack-based. Infix expressions are the form of mathematical notation most people are used to,
 * for instance 3+4 or 3+4*(2−1). For the conversion there are two text variables (strings), the input and the output.
 * There is also a stack that holds operators not yet added to the output queue.
 * To convert, the program reads each symbol in order and does something based on that symbol.
 * The shunting-yard algorithm has been later generalized into operator-precedence parsing.
 *
 *
 */
public class ExtendedSHuntingYardParser {

	private final Map<String,FunctionElement> functionElements;
	public ExtendedSHuntingYardParser() {
		functionElements = new HashMap<String, FunctionElement>();
		// Primitiv
		addFunction(new Add());
		addFunction(new Subtract());
		addFunction(new Multiply());
		addFunction(new Divide());
		addFunction(new Power());
		addFunction(new Modulo());
		// Math
		addFunction(new Round());
		addFunction(new Max());
		addFunction(new Min());
		addFunction(new Abs());
		addFunction(new Acos());
		addFunction(new Cos());
		addFunction(new Asin());
		addFunction(new Atan());
		addFunction(new Atan2());
		addFunction(new Ceil());
		addFunction(new Exp());
		addFunction(new Floor());
		addFunction(new IEEEremainder());
		addFunction(new Log());
		addFunction(new Random());
		addFunction(new Rint());
		addFunction(new Sin());
		addFunction(new Sqrt());
		addFunction(new Tan());
		addFunction(new ToDegrees());
		addFunction(new ToRadians());

		// Relational
		addFunction(new EqualTo());
		addFunction(new NotEqualTo());
		addFunction(new LessThan());
		addFunction(new GreaterThan());
		addFunction(new LessThanOrEqualTo());
		addFunction(new GreaterThanOrEqualTo());
		// Logic
		addFunction(new AndOperator());
		addFunction(new OrOperator());
		addFunction(new NotOperator());
		// String
		addFunction(new Length());
		addFunction(new Substring());
		addFunction(new CharAt());
		addFunction(new CompareTo());
		addFunction(new CompareToIgnoreCase());
		addFunction(new Equals());
		addFunction(new EqualsIgnoreCase());
		addFunction(new Concat());
		addFunction(new Contains());
		addFunction(new EndsWith());
		addFunction(new StartsWith());
		addFunction(new Replace());
		addFunction(new ToUpperCase());
		addFunction(new ToLowerCase());
		addFunction(new IndexOf());
		addFunction(new LastIndexOf());
		addFunction(new Like());
		addFunction(new Matches());
		addFunction(new NumberFormat());
	}

	/**
	 * add user defined function.
	 * @param function
	 */
	public void addFunction(FunctionElement function) {
		functionElements.put(function.getName(), function);
	}
	
	/**
	 * add user defined functions
	 * @param functions
	 */
	public void addFunctions(Collection<FunctionElement> functions) {
		for (FunctionElement function : functions) {
			addFunction(function);
		}
	}

	/**
	 * Test if a certain is an operator .
	 * @param token The token to be tested .
	 * @return True if token is an operator . Otherwise False .
	 */
	private boolean isOperator(String token) {
		return functionElements.containsKey(token);
	}
	/**
	 * Test if a certain is an function element .
	 * @param token The token to be tested .
	 * @return True if token is an operator . Otherwise False .
	 */
	private boolean isFunctionElement(ExpressionElement stackItem) {
		if (stackItem instanceof FunctionElement) {
			FunctionElement operator = (FunctionElement) stackItem;
			return functionElements.containsKey(operator.getName());
		}
		return false;
	}


	/**
	 * Test the associativity of a certain operator token .
	 * @param token The token to be tested (needs to operator).
	 * @param type LEFT_ASSOC or RIGHT_ASSOC
	 * @return True if the tokenType equals the input parameter type .
	 */
	private boolean isAssociative(FunctionElement operation, Associativity associativity) {
		return functionElements.get(operation.getName()).getAssociativity() == associativity;
	}

	/**
	 * Compare precendece of two operators.
	 * @param token1 The first operator .
	 * @param token2 The second operator .
	 * @return A negative number if token1 has a smaller precedence than token2,
	 * 0 if the precendences of the two tokens are equal, a positive number
	 * otherwise.
	 */
	private final int cmpPrecedence(ExpressionElement token1, ExpressionElement token2) {
		if (!isFunctionElement(token1) || !isFunctionElement(token2)) {
			throw new IllegalArgumentException("Invalied tokens: " + token1
					+ " " + token2);
		}
		FunctionElement operator1 = (FunctionElement) token1;
		FunctionElement operator2 = (FunctionElement) token2;
		return operator1.getPrecendence().ordinal() - operator2.getPrecendence().ordinal();
	}


	/**
	 * @param expression
	 * @return List<ExpressionElement>
	 */
	public Expression infixToRPN(String expression) {

		//String[] tokens = TokenizerUtil.modifyExpression(expression).split(TokenizerUtil.DELIMITER);
		List<ExpressionToken> tokens = ExpressionTokenizer.tokenize(expression);
		return infixToRPN(tokens.iterator(),false);
	}

	public Expression infixToRPN(Iterator<ExpressionToken> tokenIterator, boolean stopAtNextCloseBrace) {
		Expression out = new Expression();
		Stack<ExpressionElement> stack = new Stack<ExpressionElement>();
			// For all the input tokens [S1] read the next token [S2]
			while (tokenIterator.hasNext()) {
				ExpressionToken token = tokenIterator.next();
				if (token.getType()==TokenType.COMMA) {
					continue;
				} else if (token.getType()==TokenType.FUNCTIONNAME) {
					FunctionElement function = functionElements.get(token.getToken());
					if (function== null) {
						System.out.println("foo");
					}
					int paramsStart = out.size();
					out.addAll(infixToRPN(tokenIterator,true));
					if (function.getNumberOfParameters()==-1) {
						function = new VarArgFunctionElementWrapper(function, out.size() - paramsStart);
					}
					out.add(function);
					continue;
				} else if (token.getType()==TokenType.OPERATOR) {
					FunctionElement operator = functionElements.get(token.getToken());
					// If token is an operator (x) [S3]
					while (!stack.empty() && isFunctionElement(stack.peek())) {
						// [S4]
						if ((isAssociative(operator, Associativity.LEFT) && cmpPrecedence(
								operator, stack.peek()) <= 0)
								|| (isAssociative(operator, Associativity.RIGHT) && cmpPrecedence(
										operator, stack.peek()) < 0)) {
							out.add(stack.pop()); 	// [S5] [S6]
							continue;
						}
						break;
					}
					// Push the new operator on the stack [S7]
					stack.push(operator);
				} else if (token.getToken().equals("(")) {
					stack.push(new LeftParenthese()); 	// [S8]
				} else if (token.getToken().equals(")")) {
					if (stopAtNextCloseBrace) {
						return out;
					}
					// [S9]
					while (!stack.empty() && !(stack.peek() instanceof LeftParenthese)) {
						out.add(stack.pop()); // [S10]
					}
					stack.pop(); // [S11]
				} else if (!token.getToken().isEmpty()){
					out.add(FunctionArgumentFactory.createObject(token.getToken())); // [S12]
				}
			}
			while (!stack.empty()) {
				out.add(stack.pop()); // [S13]
			}
			return out;
	}

	private static class LeftParenthese implements ExpressionElement{
		@Override
		public String getString() {
			return "(";
		}
	}

}
