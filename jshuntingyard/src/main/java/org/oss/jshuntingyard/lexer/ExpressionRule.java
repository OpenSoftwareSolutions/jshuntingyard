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
package org.oss.jshuntingyard.lexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.oss.jshuntingyard.evaluator.FunctionElement;
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

public class ExpressionRule {

	public enum TokenType {
		FUNCTIONNAME,
		DIGIT,
		SINGLEQUOTED,
		COMMA,
		NAN,
		OPENBRACE,
		CLOSEBRACE,
		OPERATOR,
		BOOLEANVALUE,
		NULL,
		VARIABLE
	}
	final TokenType type;
	final Pattern pattern;
	

	public ExpressionRule(TokenType type, String regex){
		this.type = type;
		pattern = Pattern.compile(regex);
	}

	public static List<ExpressionRule> getJSHuntingYardRules(){
		List<ExpressionRule> rules = new ArrayList<ExpressionRule>();
		rules.add(new ExpressionRule(TokenType.BOOLEANVALUE, "false|true|False|True|FALSE|TRUE"));
		rules.add(new ExpressionRule(TokenType.NULL, "null|Null|NULL"));
		rules.add(new ExpressionRule(TokenType.NAN, "NaN"));
		rules.add(new ExpressionRule(TokenType.FUNCTIONNAME, "[A-Za-z]+([0-9])?"));
		rules.add(new ExpressionRule(TokenType.DIGIT, "([-])?([0-9]+(\\.\\d+)?)"));
		rules.add(new ExpressionRule(TokenType.SINGLEQUOTED, "\'[^\']*+\'"));
		rules.add(new ExpressionRule(TokenType.COMMA, ","));
		rules.add(new ExpressionRule(TokenType.OPENBRACE, "\\("));
		rules.add(new ExpressionRule(TokenType.CLOSEBRACE, "\\)"));
		rules.add(new ExpressionRule(TokenType.OPERATOR, "\\+|-|\\*|/|==|!=|\\^|\\%|\\|\\||<=|>=|<|>|&&|!"));
		rules.add(new ExpressionRule(TokenType.VARIABLE, "\\$([a-zA-Z0-9])+([_])?([a-zA-Z0-9])*"));
		return rules;
	}
	
	public static Collection<FunctionElement> getExpressionFunctions() {
		Collection<FunctionElement>  functions = new ArrayList<FunctionElement>();
		// Primitiv
		functions.add(new Add());
		functions.add(new Subtract());
		functions.add(new Multiply());
		functions.add(new Divide());
		functions.add(new Power());
		functions.add(new Modulo());
		// Math
		functions.add(new Round());
		functions.add(new Max());
		functions.add(new Min());
		functions.add(new Abs());
		functions.add(new Acos());
		functions.add(new Cos());
		functions.add(new Asin());
		functions.add(new Atan());
		functions.add(new Atan2());
		functions.add(new Ceil());
		functions.add(new Exp());
		functions.add(new Floor());
		functions.add(new IEEEremainder());
		functions.add(new Log());
		functions.add(new Random());
		functions.add(new Rint());
		functions.add(new Sin());
		functions.add(new Sqrt());
		functions.add(new Tan());
		functions.add(new ToDegrees());
		functions.add(new ToRadians());

		// Relational
		functions.add(new EqualTo());
		functions.add(new NotEqualTo());
		functions.add(new LessThan());
		functions.add(new GreaterThan());
		functions.add(new LessThanOrEqualTo());
		functions.add(new GreaterThanOrEqualTo());
		// Logic
		functions.add(new AndOperator());
		functions.add(new OrOperator());
		functions.add(new NotOperator());
		// String
		functions.add(new Length());
		functions.add(new Substring());
		functions.add(new CharAt());
		functions.add(new CompareTo());
		functions.add(new CompareToIgnoreCase());
		functions.add(new Equals());
		functions.add(new EqualsIgnoreCase());
		functions.add(new Concat());
		functions.add(new Contains());
		functions.add(new EndsWith());
		functions.add(new StartsWith());
		functions.add(new Replace());
		functions.add(new ToUpperCase());
		functions.add(new ToLowerCase());
		functions.add(new IndexOf());
		functions.add(new LastIndexOf());
		functions.add(new Like());
		functions.add(new Matches());
		functions.add(new NumberFormat());
		return functions;
	}

}
