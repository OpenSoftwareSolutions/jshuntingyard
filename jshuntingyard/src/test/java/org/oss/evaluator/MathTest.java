package org.oss.evaluator;

import org.junit.Test;
import org.oss.evaluator.function.FunctionArgument;

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
public class MathTest {


	@Test
	public void mathOperatorsMaxInt() {
		FunctionArgument<?> result = new Evaluator("max ( 2 , 3 ) + 2").evaluate();
		AssertUtil.assertIntegerResult(result,5);
	}

	@Test
	public void mathOperatorsMaxDouble() {
		FunctionArgument<?> result = new Evaluator("max ( 2.2929 , 3.49494 )").evaluate();
		AssertUtil.assertDoubleResult(result,3.49494);
	}

	@Test
	public void mathOperatorsMinInt() {
		FunctionArgument<?> result = new Evaluator("min ( 2 , 3 )").evaluate();
		AssertUtil.assertIntegerResult(result,2);
	}

	@Test
	public void mathOperatorsMinDouble() {
		FunctionArgument<?> result = new Evaluator("min ( 2.2929 , 3.49494 )").evaluate();
		AssertUtil.assertDoubleResult(result, 2.2929);
	}

	@Test
	public void mathOperatorsRound() {
		FunctionArgument<?> result = new Evaluator("round ( 3.49494 )").evaluate();
		AssertUtil.assertIntegerResult(result,3);
	}

	@Test
	public void mathOperatorsAbsPositiv() {
		FunctionArgument<?> result = new Evaluator("abs ( 175.00 )").evaluate();
		AssertUtil.assertDoubleResult(result,175.00);
	}

	@Test
	public void mathOperatorsAbsNegativ() {
		FunctionArgument<?> result = new Evaluator("abs ( -175.00 )").evaluate();
		AssertUtil.assertDoubleResult(result,175.00);
	}

	@Test
	public void mathOperatorsAcos() {
		// get a variable x which is equal to PI/2
	      double x = Math.PI / 2;

	      // convert x to radians
	      x = Math.toRadians(x);
		FunctionArgument<?> result = new Evaluator("acos ( 0.027415567780803774 )").evaluate();
		AssertUtil.assertDoubleResult(result, 1.5433773235341761);
	}

	@Test
	public void mathOperatorsCosPositiv() {
		// get double numbers
	      double x = 45.0;
	      // convert to radians
	      x = Math.toRadians(x);
		FunctionArgument<?> result = new Evaluator("cos ( 0.7853981633974483 )").evaluate();
		AssertUtil.assertDoubleResult(result, 0.7071067811865476);
	}

	@Test
	public void mathOperatorsCosNegativ() {
		// get double numbers
	      double y = 180.0;
	      // convert to radians
	      y = Math.toRadians(y);
		FunctionArgument<?> result = new Evaluator("cos ( 3.141592653589793 )").evaluate();
		AssertUtil.assertDoubleResult(result, -1.0);
	}

	@Test
	public void mathOperatorsAsin() {
		// get a variable x which is equal to PI/2
	      double x = Math.PI / 2;
	      // convert x to radians
	      x = Math.toRadians(x);
	      // 0.027415567780803774

		FunctionArgument<?> result = new Evaluator("asin ( 0.027415567780803774 )").evaluate();
		AssertUtil.assertDoubleResult(result, 0.02741900326072046);
	}

	@Test
	public void mathOperatorsAtan() {
		// get a variable x which is equal to PI/2
	      double x = Math.PI / 2;

	      // convert x to radians
	      x = Math.toRadians(x);
		FunctionArgument<?> result = new Evaluator("atan ( 0.027415567780803774 )").evaluate();
		AssertUtil.assertDoubleResult(result, 0.0274087022410345);
	}

	@Test
	public void mathOperatorsAtan2() {
		// get a variable x which is equal to PI/2
	      double x = Math.PI / 2;

	      // get a variable y which is equal to PI/3
	      double y = Math.PI / 3;

	      // convert x  and y to degrees
	      x = Math.toDegrees(x);
	      //90.0
	      y = Math.toDegrees(y);
	      //59.99999999999999
		FunctionArgument<?> result = new Evaluator("atan2 ( 90.0 , 59.99999999999999 )").evaluate();
		AssertUtil.assertDoubleResult(result, 0.9827937232473292);
	}

	@Test
	public void mathOperatorsCeilPositiv1() {
		FunctionArgument<?> result = new Evaluator("ceil ( 125.9 )").evaluate();
		AssertUtil.assertDoubleResult(result,126.0);
	}

	@Test
	public void mathOperatorsCeilPositiv2() {
		FunctionArgument<?> result = new Evaluator("ceil ( 0.4873 )").evaluate();
		AssertUtil.assertDoubleResult(result,1.0);
	}

	@Test
	public void mathOperatorsCeilNegativ() {
		FunctionArgument<?> result = new Evaluator("ceil ( -0.65 )").evaluate();
		AssertUtil.assertDoubleResult(result,-0.0);
	}

	@Test
	public void mathOperatorsExp() {
		FunctionArgument<?> result = new Evaluator("exp ( 5.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,148.4131591025766);
	}

	@Test
	public void mathOperatorsFloorPositiv() {
		FunctionArgument<?> result = new Evaluator("floor ( 60984.1 )").evaluate();
		AssertUtil.assertDoubleResult(result,60984.0);
	}

	@Test
	public void mathOperatorsFloorNegativ() {
		FunctionArgument<?> result = new Evaluator("floor ( -497.99 )").evaluate();
		AssertUtil.assertDoubleResult(result,-498.0);
	}

	@Test
	public void mathOperatorsIEEEremainderPositiv() {
		FunctionArgument<?> result = new Evaluator("ieeeremainder ( 60984.1 , -497.99 )").evaluate();
		AssertUtil.assertDoubleResult(result,229.31999999999744);
	}

	@Test
	public void mathOperatorsIEEEremainderNegativ() {
		FunctionArgument<?> result = new Evaluator("ieeeremainder ( -497.99 , 60984.1 )").evaluate();
		AssertUtil.assertDoubleResult(result,-497.99);
	}

	@Test
	public void mathOperatorsLog() {
		FunctionArgument<?> result = new Evaluator("log ( 60984.1 )").evaluate();
		AssertUtil.assertDoubleResult(result,11.018368453441132);
	}

	@Test
	public void mathOperatorsLogNaN() {
		FunctionArgument<?> result = new Evaluator("log ( -497.99 )").evaluate();
		AssertUtil.assertDoubleResult(result,Double.NaN);
	}

	@Test
	public void mathOperatorsRandom() {
		FunctionArgument<?> result1 = new Evaluator("random ( )").evaluate();
		FunctionArgument<?> result2 = new Evaluator("random ( )").evaluate();
		//System.out.println("random result1 = " + result1 + "random result2 = " + result2);
	}

	@Test
	public void mathOperatorsRintPositiv() {
		FunctionArgument<?> result = new Evaluator("rint ( 1654.9874 )").evaluate();
		AssertUtil.assertDoubleResult(result,1655.0);
	}

	@Test
	public void mathOperatorsRintNegativ() {
		FunctionArgument<?> result = new Evaluator("rint ( -9765.134 )").evaluate();
		AssertUtil.assertDoubleResult(result,-9765.0);
	}

	@Test
	public void mathOperatorsSinPositiv() {
		FunctionArgument<?> result = new Evaluator("sin ( 0.7853981633974483 )").evaluate();
		AssertUtil.assertDoubleResult(result,0.7071067811865475);
	}

	@Test
	public void mathOperatorsSinNegativ() {
		FunctionArgument<?> result = new Evaluator("sin ( -3.141592653589793 )").evaluate();
		AssertUtil.assertDoubleResult(result,-1.2246467991473532E-16);
	}

	@Test
	public void mathOperatorsSqrtPositiv() {
		FunctionArgument<?> result = new Evaluator("sqrt ( 9.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,3.0);
	}

	@Test
	public void mathOperatorsSqrtNegativ() {
		FunctionArgument<?> result = new Evaluator("sqrt ( 25.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,5.0);
	}

	@Test
	public void mathOperatorsSqrtNaN() {
		FunctionArgument<?> result = new Evaluator("sqrt ( -25.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,Double.NaN);
	}

	@Test
	public void mathOperatorsTanPositiv() {
		FunctionArgument<?> result = new Evaluator("tan ( 0.7853981633974483 )").evaluate();
		AssertUtil.assertDoubleResult(result,0.9999999999999999);
	}

	@Test
	public void mathOperatorsTanNaN() {
		FunctionArgument<?> result = new Evaluator("tan ( "+Double.NaN+" )").evaluate();
		AssertUtil.assertDoubleResult(result,Double.NaN);
	}

	@Test
	public void mathOperatorsTanZero() {
		FunctionArgument<?> result = new Evaluator("tan ( 0.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,0.0);
	}

	@Test
	public void mathOperatorsToDegreesPositiv() {
		FunctionArgument<?> result = new Evaluator("toDegrees ( 45.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,2578.3100780887044);
	}

	@Test
	public void mathOperatorsToDegreesNegativ() {
		FunctionArgument<?> result = new Evaluator("toDegrees ( -180.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,-10313.240312354817);
	}

	@Test
	public void mathOperatorsToRadiansPositiv() {
		FunctionArgument<?> result = new Evaluator("toRadians ( 45.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,0.7853981633974483);
	}

	@Test
	public void mathOperatorsToRadiansNegativ() {
		FunctionArgument<?> result = new Evaluator("toRadians ( -180.0 )").evaluate();
		AssertUtil.assertDoubleResult(result,-3.141592653589793);
	}
}