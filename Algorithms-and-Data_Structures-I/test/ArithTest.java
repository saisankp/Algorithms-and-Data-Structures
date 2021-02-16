// -------------------------------------------------------------------------
/**
 *  JUnit testing for Arith.java which is a utility class containing validation/evaluation/conversion operations
 *  for infix arithmetic expressions.
 *  @author  Prathamesh Sai
 *  @version 7/12/20 5:40:00
 */

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArithTest {

	@Test
	public void testvalidateInfixOrder() {
	  new Arith(); //to get coverage for the class declaration.
		//valid inputs
	   // (5-2+4*(8-(5+1))+9 is in valid infix order
	  assertTrue("Validating infix order with valid input", Arith.validateInfixOrder(new String[] {"5", "-", "2", "+" ,"4", "*", "(",  "8", "-", "(", "5", "+", "1", ")", ")", "+", "9"}));
	  
	   // (( 1 - 2 ) * 3) + (10 - (3 + (6 / 3))) is in valid infix order
	  assertTrue("Validating infix order with valid input", Arith.validateInfixOrder(new String[] {"(", "(", "1", "-", "2", ")", "*", "3", ")", ")", ")"}));
	  
	   // (( 1 -) 2 ) * 3) + (10 - (3 + (6 / 3))) is not in valid infix order
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"(", "(", "1", "-", ")", "2", ")", "*", "3", ")", ")", ")"}));
	 
	  // +-/1+2+4+9+ is not in valid infix order
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"+", "-", "/", "1", "+", "2", "+", "4", "+", "9", "+"}));
	
	  // just an operator
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"+"}));
	  
	  //number before a left bracket breaks infix i.e.  1 + 2 + 5 (5 + 5) is not valid infix.
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"1", "+", "2", "+", "5", "(", "5", "+", "5", ")" }));
	
	  //operator after a bracket breaks infix i.e. (+(+-9)) is not valid infix.
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"(", "+", "(", "-", "9", ")", ")"}));
	
	  //two numbers one after another breaks infix i.e. ( 5 5 + 5) is not valid infix
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"(", "5", "5", "+", "5", ")"}));
	
	  //two operators one after another breaks infix i.e. ( 5 + + 5) is not valid infix.
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"(", "5", "+", "+", "5", ")"}));
	
	  //an operator before a right bracket breaks infix i.e. ( 5 + 5 +)
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {")"}));
	  
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"("}));
	
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"1", "+"}));
	  
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"1", "+", "2", "(",}));
	  
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"1", "+"}));
	  
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"1", "+", "1", ")"}));
	  
	  assertTrue("Validating infix order with valid input", Arith.validateInfixOrder(new String[] {"(", "5", "-", "5", ")"}));
	
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"(", "1", "+" ,")"}));
	  
	  assertFalse("Validating infix order with invalid input", Arith.validateInfixOrder(new String[] {"(", "1", "+", ")"}));
	  
	}
	
	
	@Test
	  public void testValidatePostfixOrder() {
	    assertTrue("Validating postfix order with valid input", Arith.validatePostfixOrder(new String[] {"5", "4", "+"}));
	    assertFalse( "Validating postfix order with only a operator", Arith.validatePostfixOrder(new String[] {"/"}));
	    assertFalse("Validating postfix order with invalid input", Arith.validatePostfixOrder(new String[] {"1", "-", "2", "3", "+"}));
	    assertTrue("Validating postfix order with valid input", Arith.validatePostfixOrder(new String[] {"3", "1", "+", "4", "+", "11", "4", "1", "4", "+", "-", "+", "-"}));
	    assertFalse("Validating postfix order with only a operator", Arith.validatePostfixOrder(new String[] {"-"}));
	    assertTrue("Validating postfix order with valid input", Arith.validatePostfixOrder(new String[] {"1", "2", "3", "+", "-", "6", "6", "/", "2", "-", "12", "+", "-"}));
	  }
	
	
	@Test
	  public void testEvaluatePostfixOrder() {
	    assertEquals("Evaluating postfix order", 3, Arith.evaluatePostfixOrder(new String[] {"1", "2", "+"}));
	    assertEquals("Evaluating postfix order", -3, Arith.evaluatePostfixOrder(new String[] {"-1", "-2", "+"}));
	    assertEquals("Evaluating postfix order",0, Arith.evaluatePostfixOrder(new String[] {"1", "2", "+", "3", "-"}));
	    assertEquals("Evaluating postfix order",0, Arith.evaluatePostfixOrder(new String[] {"-1", "-2", "+", "-3", "-"}));
	    assertEquals("Evaluating postfix order", 3, Arith.evaluatePostfixOrder(new String[] {"-3", "1", "2", "-", "*"}));
	    assertEquals("Evaluating postfix order", -3, Arith.evaluatePostfixOrder(new String[] {"3", "1", "2", "-", "*"}));
	    assertEquals("Evaluating postfix order", 2, Arith.evaluatePostfixOrder(new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"}));
	}

	
	//test all the exceptions 
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionInEvaluatePostfixOrder() {
	    Arith.evaluatePostfixOrder(new String[] {"-"});
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionInConvertInfixToPostfix() {
        Arith.convertInfixToPostfix(new String[] {")"});
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionInConvertPostfixToInfix() {
        Arith.convertPostfixToInfix(new String[] {"-"});
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentExceptionInApplyOperator() {
        Arith.applyOperator(1, 2, "hello");
	} 
	
	  @Test
	  public void testEvaluateInfixOrder() {
		assertEquals("Evaluating infix order", 2, Arith.evaluateInfixOrder(new String[] {"(", "1", "+", "1", ")"}));
		assertEquals("Evaluating infix order", 2, Arith.evaluateInfixOrder(new String[] {"(", "(", "1", "+", "1", ")", ")"}));
		assertEquals("Evaluating infix order", 20, Arith.evaluateInfixOrder(new String[] {"(", "(", "1", "+", "1", ")",  "*", "(", "1", "*", "10", ")", ")"}));
	}
	  
//assertArrayEquals("Converting infix to postfix", new String[] {}, Arith.convertInfixToPostfix(new String[] {}));
      @Test
	  public void testConvertInfixToPostfix() {
       assertArrayEquals("Converting infix to postfix", new String[] {"1", "2", "+"}, Arith.convertInfixToPostfix(new String[] {"(", "1", "+", "2", ")"}));
       assertArrayEquals("Converting infix to postfix", new String[] {"-1", "-2", "+"}, Arith.convertInfixToPostfix(new String[] {"(", "-1", "+", "-2", ")"}));
       assertArrayEquals("Converting infix to postfix", new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"}, Arith.convertInfixToPostfix(new String[] {"(", "(", "(", "1", "-", "2", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "/", "3", ")", ")", ")", ")"}));
       assertArrayEquals("Converting infix to postfix", new String[] {"5", "5", "5",  "/", "*",  "5", "2", "+", "*"}, Arith.convertInfixToPostfix(new String[] {"(", "5", "*",  "(" , "5", "/", "5", ")", "*", "(", "5", "+", "2", ")", ")"}));	   
       assertArrayEquals("Converting infix to postfix", new String[] {"1", "1", "6", "5", "-", "/", "+"}, Arith.convertInfixToPostfix(new String[] {"1", "+", "1", "/", "(", "6", "-", "5", ")"}));
       assertArrayEquals("Converting infix to postfix", new String[] {"1", "1", "6", "5", "-", "/", "1", "*", "+", "1", "6", "5", "-", "/", "10", "*", "+"}, Arith.convertInfixToPostfix(new String[] {"1", "+", "1", "/", "(", "6", "-", "5", ")",  "*", "1", "+", "1", "/", "(", "6", "-", "5", ")", "*", "10"}));
       assertArrayEquals("Converting infix to postfix", new String[] {"2", "2", "+", "1", "+"}, Arith.convertInfixToPostfix(new String[] {"(", "2", "+", "2", ")", "+", "1"}));
       assertArrayEquals("Converting infix to postfix", new String[] {"5", "5", "5",  "/", "*",  "5", "2", "+", "*", "5", "/"}, Arith.convertInfixToPostfix(new String[] {"(", "5", "*",  "(" , "5", "/", "5", ")", "*", "(", "5", "+", "2", ")", "/", "5", ")"}));	
       assertArrayEquals("Converting infix to postfix", new String[] {"1", "1", "+"}, Arith.convertInfixToPostfix(new String[] {"1", "+", "1"}));
      }
	  
      
      @Test
      public void testConvertPostfixToInfix() {
        assertArrayEquals("Converting postfix to infix", new String[] {"(", "1", "+", "2", ")"}, Arith.convertPostfixToInfix(new String[] {"1", "2", "+"}));
        assertArrayEquals("Converting postfix to infix", new String[] {"(", "-1", "+", "-2", ")"}, Arith.convertPostfixToInfix(new String[] {"-1", "-2", "+"}));
        assertArrayEquals("Converting postfix to infix", new String[] {"(", "(", "1", "+", "2", ")", "-", "3", ")"}, Arith.convertPostfixToInfix(new String[] {"1", "2", "+", "3", "-"}));
        assertArrayEquals("Converting postfix to infix", new String[] {"(", "(", "-1", "+", "-2", ")", "-", "-3", ")"}, Arith.convertPostfixToInfix(new String[] {"-1", "-2", "+", "-3", "-"}));
        assertArrayEquals("Converting postfix to infix", new String[] {"(", "-3", "*", "(", "1", "-", "2", ")", ")"}, Arith.convertPostfixToInfix(new String[] {"-3", "1", "2", "-", "*"}));
        assertArrayEquals("Converting postfix to infix", new String[] {"(", "3", "*", "(", "1", "-", "2", ")", ")"}, Arith.convertPostfixToInfix(new String[] {"3", "1", "2", "-", "*"}));
        assertArrayEquals("Converting postfix to infix", new String[] { "(", "(", "(", "1", "-", "2", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(","6", "/", "3", ")", ")", ")", ")"}, Arith.convertPostfixToInfix(new String[] {"1", "2", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"}));
      }
      
      @Test
      public void testOperatorOfGreaterThanOrEqualPrecedence() {
    	  Stack<Integer> stack = new Stack<Integer>();
    	  stack.push(0);
    	  String[] array = {"-"};
    	  assertTrue("testing the precedence method", Arith.operatorOfGreaterThanOrEqualPrecedence(stack, "-", array));
    	  String[] array3 = {"/"};
    	  assertTrue("testing the precedence method", Arith.operatorOfGreaterThanOrEqualPrecedence(stack, "+", array3));
      }
      
      @Test
      public void testIsOperator() {
    	  assertFalse(Arith.isOperator(null));
      }   
}
