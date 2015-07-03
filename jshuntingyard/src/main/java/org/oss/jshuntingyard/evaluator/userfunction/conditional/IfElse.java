/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.jshuntingyard.evaluator.userfunction.conditional;

import org.oss.jshuntingyard.evaluator.AbstractFunctionElement;
import org.oss.jshuntingyard.evaluator.FunctionArgumentFactory;
import org.oss.jshuntingyard.evaluator.FunctionElementArgument;



public class IfElse extends AbstractFunctionElement {

	public IfElse() {
		super("ifelse", 3, Precedence.USERFUNCTION);
	}


	@Override
	public boolean isUserFunction() {
		return true;
	}


	protected FunctionElementArgument<?> execute(FunctionElementArgument<Boolean> a, FunctionElementArgument<?> b, FunctionElementArgument<?> c) throws IllegalArgumentException {
		return FunctionArgumentFactory.createObject(a.getValue() ? b : c);
	}


	@SuppressWarnings("unchecked")
	@Override
	public FunctionElementArgument<?> execute(
			FunctionElementArgument<?>... args) throws IllegalArgumentException {
		assertNumArgs(args);
		return execute((FunctionElementArgument<Boolean>)args[0],args[1],args[2]);
	}
}
