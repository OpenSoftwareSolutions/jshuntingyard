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
import java.util.List;
import java.util.regex.Pattern;

public class ExpressionRule {

	public enum TokenType {
		FUNCTIONNAME,
		DIGIT,
		SINGLEQUOTED,
		COMMA,
		NAN,
		PARENTHESE,
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
		rules.add(new ExpressionRule(TokenType.PARENTHESE, "\\(|\\)"));
		rules.add(new ExpressionRule(TokenType.OPERATOR, "\\+|-|\\*|/|==|!=|\\^|\\%|\\|\\||<=|>=|<|>|&&|!"));
		rules.add(new ExpressionRule(TokenType.VARIABLE, "\\$([a-zA-Z0-9])+([_])?([a-zA-Z0-9])*"));
		return rules;
	}
}
