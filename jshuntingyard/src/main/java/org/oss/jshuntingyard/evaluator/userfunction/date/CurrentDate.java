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
package org.oss.jshuntingyard.evaluator.userfunction.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.oss.jshuntingyard.evaluator.AbstractOneArgFunctionElement;
import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;

/**
 *returns a formatted "today".
 */
public class CurrentDate extends AbstractOneArgFunctionElement<String,String> {

	public CurrentDate() {
		super("currentDate", Precedence.USERFUNCTION);
	}


	@Override
	public boolean isUserFunction() {
		return false;
	}


	/*
	 * @see org.oss.evaluator.function.string.AbstractStringOperatorAssociativityLeftOneArg#execute(org.oss.evaluator.function.FunctionArgument)
	 */
	@Override
	protected FunctionElementArgument<String> execute(FunctionElementArgument<String> a) throws IllegalArgumentException {
		SimpleDateFormat sdf = new SimpleDateFormat(a.getValue());
		return FunctionArgumentFactory.createString(sdf.format(new Date()));
	}

}
