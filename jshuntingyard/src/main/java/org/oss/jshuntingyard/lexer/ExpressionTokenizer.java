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
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ExpressionTokenizer{

	private ExpressionTokenizer(){
		// nothing
	}


	public static List<ExpressionToken> tokenize(String source){
		boolean isLastTokenOperator = false;
		boolean isFirstToken = true;
		source = stripNonQuotedWhiteSpaces(source);
		List<ExpressionToken> tokens = new ArrayList<ExpressionToken>();
		int pos = 0;
		final int end = source.length();
		Matcher m = Pattern.compile("dummy").matcher(source);
		m.useTransparentBounds(true).useAnchoringBounds(false);
		while (pos < end){
			m.region(pos, end);
			for (TokenType tokenType : TokenType.values()){
				if (m.usePattern(tokenType.pattern()).lookingAt()){
					if (hasNumberOperatorAmbiguity(tokenType,m) && !isFirstToken && !isLastTokenOperator) {
						tokenType = TokenType.OPERATOR; 
					} else {
						m.usePattern(tokenType.pattern()).lookingAt(); // restore matcher state
					}
					tokens.add(new ExpressionToken(tokenType, m.start(), m.end(), source));
					pos = m.end()-1;
					isFirstToken = (tokenType == TokenType.OPENBRACE || tokenType == TokenType.COMMA);
					isLastTokenOperator = (tokenType == TokenType.OPERATOR);
					break;
				}
			}
			pos++;
		}
		return tokens;
	}
	
	/**
	 * Checks if the last match was one of number literals and if an operator would match too.<br>
	 * The function is used to determine whether + and - should be interpreted as sign or<br>
	 * the operators add and subtract. A sign must be interpreted as operator if the previous token<br>
	 * is a number. 
	 * @param matchingType
	 * @param matcher
	 * @return
	 */
	private static boolean hasNumberOperatorAmbiguity(TokenType matchingType, Matcher matcher) {
		if (matchingType==TokenType.DOUBLENUMBER || matchingType==TokenType.FLOATNUMBER ||
				matchingType==TokenType.INTEGERNUMBER || matchingType==TokenType.INTEGERNUMBER) {
			return matcher.usePattern(TokenType.OPERATOR.pattern()).lookingAt();
		}
		return false;
	}
	
	private static String stripNonQuotedWhiteSpaces(String text) {
		StringBuffer out = new StringBuffer();
		boolean insideQuoetes = false;
		char quoteChar = '"';
		for (int i=0;i<text.length();i++) {
			char ch = text.charAt(i);
			if (ch==' ' && !insideQuoetes) {
				continue;
			}
			if (insideQuoetes) {
				if (ch==quoteChar) {
					insideQuoetes = false;
				}
			} else if (ch=='\'' || ch=='"' ){
				insideQuoetes = true;
				quoteChar = ch;
			}
			out.append(ch);
		}
		return out.toString();
	}

}
