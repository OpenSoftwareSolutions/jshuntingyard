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
package org.oss.evaluator.parser;

import java.util.List;

import org.junit.Test;
import org.oss.evaluator.function.ExpressionElement;
import org.oss.evaluator.parser.ExtendedSHuntingYardParser;
import org.oss.evaluator.parser.ParserUtil;

public class PaserUtilTest {

	@Test
	public void parserTwoStringsAsArgument() {
		String expression = "concat ( 'Tutorial' , 'spoint' )";
		String tokenBuffer = ParserUtil.modifyExpression(expression);
		String[] result = tokenBuffer.split(ParserUtil.DELIMITER);
		List<ExpressionElement> elements = new ExtendedSHuntingYardParser().infixToRPN(result);
		System.out.println("PRINTING LIST OF EXPRESSIONS" + " parserTwoStringsAsArgument");
		for(ExpressionElement element : elements){
			System.out.println(element.getString());
		}
		System.out.println("PRINTING ARRAY OF ELEMENT_EXPRESSIONS"+ " parserTwoStringsAsArgument");
		for(int i = 0; result.length > i; i++){
			System.out.println(result[i]);
		}
		System.out.println("\n");
	}

	@Test
	public void parserMath() {
		String expression = " 2 + 3 == 5  && true";
		String tokenBuffer = ParserUtil.modifyExpression(expression);
		String[] result = tokenBuffer.split(ParserUtil.DELIMITER);
		List<ExpressionElement> elements = new ExtendedSHuntingYardParser().infixToRPN(result);
		System.out.println("PRINTING LIST OF EXPRESSIONS"  + " parserMath");
		for(ExpressionElement element : elements){
			System.out.println(element.getString());
		}
		System.out.println("PRINTING ARRAY OF ELEMENT_EXPRESSIONS"  + " parserMath");
		for(int i = 0; result.length > i; i++){
			System.out.println(result[i]);
		}
		System.out.println("\n");
	}

	@Test
	public void parserLogic() {
		String expression = " 2 < 4  &&  3 > 4 ";
		String tokenBuffer = ParserUtil.modifyExpression(expression);
		String[] result = tokenBuffer.split(ParserUtil.DELIMITER);
		List<ExpressionElement> elements = new ExtendedSHuntingYardParser().infixToRPN(result);
		System.out.println("PRINTING LIST OF EXPRESSIONS"  + " parserLogic");
		for(ExpressionElement element : elements){
			System.out.println(element.getString());
		}
		System.out.println("PRINTING ARRAY OF ELEMENT_EXPRESSIONS"  + " parserLogic");
		for(int i = 0; result.length > i; i++){
			System.out.println(result[i]);
		}
		System.out.println("\n");
	}


	@Test
	public void parserSingleStrings() {
		String expression = "'Tutorial'";
		String tokenBuffer = ParserUtil.modifyExpression(expression);
		String[] result = tokenBuffer.split(ParserUtil.DELIMITER);
		List<ExpressionElement> elements = new ExtendedSHuntingYardParser().infixToRPN(result);
		System.out.println("PRINTING LIST OF EXPRESSIONS"  + " parserSingleStrings");
		for(ExpressionElement element : elements){
			System.out.println(element.getString());
		}
		System.out.println("PRINTING ARRAY OF ELEMENT_EXPRESSIONS"  + " parserSingleStrings");
		for(int i = 0; result.length > i; i++){
			System.out.println(result[i]);
		}
		System.out.println("\n");
	}

	@Test
	public void parserTwoStringsWithWhitespaceAsArgument() {
		String expression = "concat ( ' Tutorial' , ' spoint' )";
		String tokenBuffer = ParserUtil.modifyExpression(expression);
		String[] result = tokenBuffer.split(ParserUtil.DELIMITER);
		List<ExpressionElement> elements = new ExtendedSHuntingYardParser().infixToRPN(result);
		System.out.println("PRINTING LIST OF EXPRESSIONS" + " parserTwoStringsWithWhitespaceAsArgument");
		for(ExpressionElement element : elements){
			System.out.println(element.getString());
		}
		System.out.println("PRINTING ARRAY OF ELEMENT_EXPRESSIONS" + " parserTwoStringsWithWhitespaceAsArgument");
		for(int i = 0; result.length > i; i++){
			System.out.println(result[i]);
		}
		System.out.println("\n");
	}

	@Test
	public void parserOneStringsWithWhitespaceAsArgument() {
		String expression = "concat ( ' Tutorial spoint' )";
		String tokenBuffer = ParserUtil.modifyExpression(expression);
		String[] result = tokenBuffer.split(ParserUtil.DELIMITER);
		List<ExpressionElement> elements = new ExtendedSHuntingYardParser().infixToRPN(result);
		System.out.println("PRINTING LIST OF EXPRESSIONS" + " parserOneStringsWithWhitespaceAsArgument");
		for(ExpressionElement element : elements){
			System.out.println(element.getString());
		}
		System.out.println("PRINTING ARRAY OF ELEMENT_EXPRESSIONS" + " parserOneStringsWithWhitespaceAsArgument");
		for(int i = 0; result.length > i; i++){
			System.out.println(result[i]);
		}
		System.out.println("\n");
	}

	@Test
	public void split() {
		String expression = "concat ( ' Tutorial spoint' )";
		String tokenBuffer = ParserUtil.modifyExpression(expression);
		String[] result = tokenBuffer.split(ParserUtil.DELIMITER);
		List<ExpressionElement> elements = new ExtendedSHuntingYardParser().infixToRPN(result);
		System.out.println("PRINTING LIST OF EXPRESSIONS" + " split");
		for(ExpressionElement element : elements){
			System.out.println(element.getString());
		}
		System.out.println("PRINTING ARRAY OF ELEMENT_EXPRESSIONS" + " split");
		for(int i = 0; result.length > i; i++){
			System.out.println(result[i]);
		}
		System.out.println("\n");
	}

}
