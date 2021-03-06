package org.oss.evaluator.tokenizer;
import java.util.ArrayList;
import java.util.List;

import org.oss.jshuntingyard.lexer.ExpressionToken;
import org.oss.jshuntingyard.lexer.ExpressionTokenizer;



public class TestExpressionTokenizer{

  public static void main(String[] args) throws Exception{

    // TEST INPUT
    List<String> expressions = new ArrayList<String>();
    expressions.add("ifelse ( '1.0' , 'YES' , 'NO' ) ");
    expressions.add("ieeeremainder ( -497.99 , 60984.1 )");
    expressions.add("ifelse('1.0','YES','NO')");
    expressions.add("ifelse('1.0',' YES','NO ')");
    expressions.add("booleanString ( '1.0' ) ");
    expressions.add("booleanString( '1.0')");
    expressions.add("dateString('MM/dd/yyyy')");
    expressions.add("true && true");
    expressions.add("! true");
    expressions.add(" 2 + 3 == 5  && true");
    expressions.add("2+ 3==5  &&true");
    expressions.add(" 2 + 3 == 5 || true");
    expressions.add(" 2 < 4  &&  3 > 4 ");
    expressions.add(" 2 <= 4  || 3 >= 4 ");
    expressions.add("min (2.2929,3.49494)");
    expressions.add("( 1 + 2 ) * ( 3 / 4 ) ^ ( 5 + 6 )");
    expressions.add("( 11 + 22 ) * ( 33 / 44 ) ^ ( 55 + 66 )");
    expressions.add("(1 + 2) * ( 3/4 )^( 5+6 )");
    expressions.add("1 + 2 * 3");
    expressions.add("7 == 7");
    expressions.add("'foo' == 'foo'");
    expressions.add("true == true");
    expressions.add("min ( 2.2929 , 3.49494 )");
    expressions.add("2.2929 - 3.49494");
    expressions.add("$22 - $3A");
    expressions.add("'22' != '3A'");
    expressions.add("2.0d");
    expressions.add("2");
    expressions.add("2L");
    expressions.add("max(10 / 2 * 3, 12)");
    expressions.add("max((float)10 / 2 * (int)3.0, 12)");
    expressions.add("max((float)10 / 2 * (int)3.0, 12)");
    expressions.add("(int)3.2f");
    expressions.add("0 - +4");
    expressions.add("- 4");
    expressions.add("-4");
    expressions.add("0-+4");
    
    


    for(String expression : expressions){
    	List<ExpressionToken> result = ExpressionTokenizer.tokenize(expression);
        for (ExpressionToken t : result){
        	System.out.println(t);
        }
    }
  }
}
