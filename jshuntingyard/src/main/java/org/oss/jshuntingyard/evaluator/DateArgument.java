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
package org.oss.jshuntingyard.evaluator;

import java.util.Date;


public class DateArgument extends AbstractFunctionElementArgument<Date> implements Comparable<DateArgument>{

	private final Date dateValue;

	DateArgument(Date dateValue) {
		this.dateValue = dateValue;
	}

	@Override
	public FunctionElementArgument.ArgumentType getType() {
		return FunctionElementArgument.ArgumentType.DATE;
	}

	@Override
	public Date getValue() {
		return dateValue;
	}

	@Override
	public int compareTo(DateArgument o) {
		return dateValue.compareTo(o.getValue());
	}
}
