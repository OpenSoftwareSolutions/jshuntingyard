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
package org.oss.evaluator.tokenizer;

import java.util.Enumeration;

/**
 * This class allow for tokenizer methods to be called on a String of arguments.
 */
public class ExpressionTokenizer implements Enumeration {

	/**
	 * The comma character.
	 */
	static final char COMMA = ',';

	/**
	 * The single quote character.
	 */
	static final char SINGLE_QUOTE = '\'';

	/**
	 * The double quote character.
	 */
	static final char DOUBLE_QUOTE = '"';

	/**
	 * The whitespace character.
	 */
	static final char WHITESPACE = ' ';

	/**
	 * The default delimitor.
	 */
	public final char defaultDelimiter = COMMA;

	// The arguments to be tokenized. This is updated every time the nextToken
	// method is called.
	private String expression = null;

	// The separator between the arguments.
	private char delimiter = defaultDelimiter;

	/**
	 * Constructor that takes a String of arguments and a delimitoer.
	 *
	 * @param arguments
	 *            The String of srguments to be tokenized.
	 * @param delimiter
	 *            The argument tokenizer.
	 */
	public ExpressionTokenizer(final String arguments, final char delimiter) {
		this.expression = arguments;
		this.delimiter = delimiter;
	}

	/**
	 * Constructor that takes a String of arguments.
	 * defaultDelimiter = COMMA
	 *
	 * @param arguments
	 *            The String of srguments to be tokenized.
	 */
	public ExpressionTokenizer(final String arguments) {
		this.expression = arguments;
	}

	/**
	 * Indicates if there are more elements.
	 *
	 * @return True if there are more elements and false if not.
	 */
	public boolean hasMoreElements() {
		return hasMoreTokens();
	}

	/**
	 * Indicates if there are more tokens.
	 *
	 * @return True if there are more tokens and false if not.
	 */
	public boolean hasMoreTokens() {

		if (expression.length() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * Returns the next element.
	 *
	 * @return The next element.
	 */
	public Object nextElement() {
		return nextToken();
	}

	/**
	 * Returns the next token.
	 *
	 * @return The next element.
	 */
	public String nextToken() {
		expression.trim();
		int charCtr = 0;
		int letterCtr = 0;
		int letterBegin = 0;
		int size = expression.length();
		int singleQuote = 0;
		String returnArgument = null;

		while (charCtr < size) {
			if (expression.charAt(charCtr) == '(') {
				returnArgument = "(,";
				expression = expression.substring(charCtr + 1);
				break;
			} else if (expression.charAt(charCtr) == ')') {
				returnArgument = "),";
				expression = expression.substring(charCtr + 1);
				break;
			} else if (expression.charAt(charCtr) == delimiter) {
				returnArgument = ",";
				expression = expression.substring(charCtr + 1);
				break;
			} else if (expression.charAt(charCtr) == SINGLE_QUOTE && singleQuote == 0) {
				singleQuote++;
				letterBegin = charCtr;
				letterCtr++;
			} else if (expression.charAt(charCtr) == (Character.LOWERCASE_LETTER | Character.UPPERCASE_LETTER) && singleQuote == 1) {
				letterCtr++;
			} else if (expression.charAt(charCtr) == SINGLE_QUOTE && singleQuote == 1) {
				singleQuote++;
				returnArgument = expression.substring(letterBegin, charCtr);
				expression = expression.substring(charCtr + 1);
				break;
			}

			charCtr++;
		}

		if (returnArgument == null) {
			returnArgument = expression;
			expression = "";
		}

		return returnArgument;
	}
}
