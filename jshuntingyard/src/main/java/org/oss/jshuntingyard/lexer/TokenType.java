package org.oss.jshuntingyard.lexer;

import java.util.regex.Pattern;

public enum TokenType {
	BOOLEANVALUE("false|true|False|True|FALSE|TRUE"),
	NULL("null|Null|NULL"),
	NAN("NaN"),
	FUNCTIONNAME("[A-Za-z]+([0-9])?"),
	DIGIT("([-])?([0-9]+(\\.\\d+)?)"),
	SINGLEQUOTED("\'[^\']*+\'"),
	COMMA(","),
	OPENBRACE("\\("),
	CLOSEBRACE("\\)"),
	OPERATOR("\\+|-|\\*|/|==|!=|\\^|\\%|\\|\\||<=|>=|<|>|&&|!"),
	VARIABLE("\\$([a-zA-Z0-9])+([_])?([a-zA-Z0-9])*");
	
	private final Pattern pattern;
	
	TokenType(String regularExpression) {
		this.pattern = Pattern.compile(regularExpression);
	}
	
	public Pattern pattern() {
		return pattern;
	}
}