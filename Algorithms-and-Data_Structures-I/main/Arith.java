// -------------------------------------------------------------------------
/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for infix arithmetic expressions.
 *
 *  @author  Prathamesh Sai
 *  @version 7/12/20 5:40:00
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.io.*; 
import java.util.*;

import static org.junit.Assert.assertTrue;

import java.lang.Object;
public class Arith 
{
	
  //~ Validation methods ..........................................................


  /**
   * Validation method for infix notation.
   *
   *<p> Asymptotic worst case running time: O(N)
   *
   *<p> Justification: <br>
   * 1. First I have a if-statement, checking if the first or last element in the infixLiterals[] array is an operator. 
   *   - The simple if-statement runs at constant time.
   *   - The method isOperator() has a worst case running time complexity of O(1). 
   * 2. Then, I have another if-statement which makes sure that the length of the string array is not 1. This has a worst case running time complexity of O(1).
   * 3. Next, I loop through each element of the string array infixLiterals[] with a for-loop and do certain checks for each string.
   *   - We have an if/else-if /else-if/else statement inside of the for-loop. This has a worst case running time complexity of O(1) since all the operations inside operate at running time (see here: http://pages.cs.wisc.edu/~vernon/cs367/notes/3.COMPLEXITY.html#:~:text=For%20example%2C%20if%20sequence%201,would%20be%20O(N).&text=Every%20time%20the%20outer%20loop,O(N%20*%20M).) 
   *   - Inside the for-loop we have many simple if-statements too, which have a worst case running time complexity of O(1).
   *   - We use the method IsInteger() which has a worst case running time complexity of O(1).
   *   - We push and pop elements onto/off the stack, which both have a worst case running time complexity of O(1) (see here: https://www.bigocheatsheet.com/)
   *   - We also check if the stack is empty using isEmpty(), which has a worst case running time complexity of O(1).
   * 4. Finally I have an if-statement which checks if the number of brackets in the string is correct for infix notation.
   *   - The simple if-statement runs at constant time i.e. O(1)
   *  
   *  Also, during this method we also access elements at certain indexes in the string array infixLiterals, which has a worst case running time complexity of O(1). <br><br>
   *  
   *  After doing an asymptotic worst case analysis using asymptotic notation (i.e. addition: keep only higher order terms, multiplication: multiply inner functions etc [more at https://www.scss.tcd.ie/Vasileios.Koutavas/teaching/csu22011/content/data/uploads/lecture4.pdf])
   *  We find out that this method has an asymptotic worst case running time of O(N), where N = length of the array infixLiterals[]
   *  This is mainly caused by the for-loop inside the method which has a worst case running time of O(N).
   *  The smaller order terms with O(1) were discarded according to the asymptotic notation.
   *
   * <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case, which I thought was important to know before passing in an array containing string literals<br>
   * 
   * <p> Why I think it's optimal:
   * - The running time is optimal because we have to iterate through the string literals (runs N times) to validate infix notation anyways.
   *   Also, the majority of the function operates at running time, which is good.
   * - I think that the space complexity (the amount of memory space required) for this method is optimal in my opinion because it has a constant space complexity.
   * 
   * 
   * @param infixLiterals : an array containing the string literals hopefully in infix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer "0", "1" , "2", ..., "-1", "-2", ...
   *
   * @return true if the parameter is indeed in infix notation, and false otherwise.
   **/
  public static boolean validateInfixOrder(String infixLiterals[])
  {
	 
	//if the first literal is an operator, then it isn't in infix notation! (can't have "+ 1 + 2" for example)
    //if the last literal is an operator, then it isn't in infix notation! (can't have "1 + 2 +" for example)
    if(isOperator(infixLiterals[0]) || isOperator(infixLiterals[infixLiterals.length-1]) ) {
	   return false;
    }
    
    boolean operatorNext = false;
    boolean numberNext = false;
    int numberOfBrackets = 0;
    int numberOfOperators = 0;
    boolean endingBracketNext = false;
    
    if(infixLiterals.length ==1) { //or two or three
    	return false;
    }
    
    for(int i = 0; i < infixLiterals.length; i++) {  
    	
    	if(infixLiterals[i] == "(") { //if it's a left bracket 
    		if(i != 0) {
    			if(isInteger(infixLiterals[i-1])) { //can't have a number before a starting bracket
    				return false;
    			}
    		}
    		
    		if(isOperator(infixLiterals[i+1])) { //can't have an operator after a left bracket 
        	   return false;
    		}
    		
    		//modify booleans
    		numberOfBrackets++;
    		operatorNext = false;
    		numberNext = true;
    		endingBracketNext = true;
    	}
    	
    	else if(infixLiterals[i] == ")") { //if it's a right bracket
    		if(isOperator(infixLiterals[i-1])) { //if there's an operator before a closing bracket i.e. (5 + 5+), it isn't infix!
        		return false;
        	}
    		else {
    		 //modify booleans
    		 numberOfBrackets++;
             operatorNext = true;
        	 numberNext = false;
    		}	
    	  
    	}
    	
    	else if(isOperator(infixLiterals[i])) { //if it's an operator.
    		//can't have two operators after each other!
    		if(operatorNext == false) {
    			return false;
    		}
    		
    		else {
    		  //modify booleans
    		  numberOfOperators++;
    		  operatorNext = false;
    		  numberNext = true;
    		  endingBracketNext = false;
    		}
    		
    	}
    	
    	else  { //if the program reached this far, it has to be an operator!
    		  		
    		if (i != infixLiterals.length -1) { //if we aren't on the last element in infixLiterals[]
      		  if(isInteger(infixLiterals[i+1])) { //can't have two numbers one after another, i.e. ( 1 + 1 1 + 1 ), it should be ( 1 + 11 + 1 )
        			return false;
        		}
      	    }
      	      //modify booleans
      		  numberNext = false;
      		  operatorNext = true;
      		  endingBracketNext = true;
    	}
    }
	
    
    //we need an even number of total brackets for infix notation
    if((numberOfBrackets % 2 != 0)){ 
    	return false; 
    }
    
    //if we haven't found anything wrong with the array of string literals, then it's in infix notation!
	 return true;
  }

  
  
  /**
   * Validation method for post-fix notation.
   *
   * <p>Asymptotic worst-case running time : O(N)
   * 
   * <p>Justification: <br>
   * 
   * In this method we use a simpler approach than for validating infix order which is arguably more hard to implement.
   * We will use this method inside of evaluatePostfixOrder(), which will eventually be used in evaluateInfixOrder().
   * We simply have a counter which increases if we come across a string literal that is an operator and decrease the counter otherwise.
   * If our counter is greater than or equal to 0, then postfixLiterals[] is not in valid post-fix notation!
   * 
   * 1. We have a for-loop which iterates through the postfixLiterals array that is passed into the function.
   * - The operations inside the for-loop run at constant time (if/else and if-statements) and have a worst case running time complexity of O(1).
   * - The function call for isOperator() has a worst case running time complexity of O(1). <br>
   * 
   * <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case <br>
   * 
   * <p> Why I think it's optimal: <br>
   * 
   * - The running time is optimal because there is no other way to validate the array of string literals without iterating through each element.
   * - The space complexity (the amount of memory space required) for this method is optimal in my opinion because it has a constant space complexity.
   *
   * @param postfixLiterals : an array containing the string literals hopefully in post-fix order.
   *     The method assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or
   *     a valid string representation of an integer.
   * @return true if the parameter is indeed in postfix notation, and false otherwise.
   */
  public static boolean validatePostfixOrder(String postfixLiterals[]) {
    int notOperator = 0;
    for (String literal : postfixLiterals) {
      if (isOperator(literal)) {
    	  notOperator--;
      }
      else {
    	  notOperator++;
      }
      
      if (notOperator <= 0) {
    	  return false;
      }
    }
    return true;
  }
  

  //~ Evaluation  methods ..........................................................
  
  
  /**
   * Evaluation method for postfix notation.
   *
   * <p>Asymptotic worst-case running time : O(N)
   * 
   * <p>Justification: <br>
   * 
   * 1. There is one for-loop which iterates through the array of string literals.
   *  - The operations inside the for-loop run are constant time operations.
   *  - The function isOperator has a worst case running time complexity of O(1).
   *  - The function applyOperator has a worst case running time complexity of O(1).
   *  - The functions push() and pop() for the stack also have a worst case running time of O(1).
   * 
   *  <p> I got the worst case running time so that we get a  rough estimate of how the program will run at the worst case. <br>
   *    
   * @param postfixLiterals : an array containing the string literals in postfix order. The method
   *     assumes that each of these literals can be one of: - "+", "-", "*", or "/" - or a valid
   *     string representation of an integer.
   * @return the integer result of evaluating the expression
   */
  public static int evaluatePostfixOrder(String postfixLiterals[]) {
    if (!validatePostfixOrder(postfixLiterals)) {
    	throw new IllegalArgumentException();
    }
    Stack<Integer> stack = new Stack<>();
    for (String token : postfixLiterals) {
      if (isOperator(token)) { //for operators, we pop off the last two elements on the stack and do operations on them.
        int secondOperand = stack.pop();
        int firstOperand = stack.pop();
        int result = applyOperator(firstOperand, secondOperand, token);
        stack.push(result); //push back the result onto the stack
      } 
      else {
    	  stack.push(Integer.parseInt(token)); //just push the integer version of the string
      }
    }
    return stack.pop(); //pop off the final result
  }
  
  
  
  
  
  /**
   * Evaluation method for infix notation.
   *
   * <p> Asymptotic worst case running time: O(N * M) where N = length of infixLiterals[], M = number of operators in infixLiterals[]
   * 
   * <p> Justification: <br>
   * 
   * - In this method, we simply convert the array of string literals into post-fix.
   * - Then, we evaluate post-fix order and calculate the result as an integer.
   * 
   * Why do we do this?:
   * 
   * - It's often seen that evaluating post-fix order is more simple to implement and hence will probably have a better worst-case run time.
   * - Hence, I made the decision to simply convert the infix to post-fix, and then evaluate post-fix.
   * - This turned out to be more easier than evaluating infix by itself.
   * - Also, during the module CSU22014 SYSTEMS PROGRAMMING, We learned how to convert infix to post-fix, so it was easier to implement this from the C version we made.
   * 
   * 
   * - The method convertInfixToPostfix() has a worst case running time complexity of  O(N * M) where N = length of infixLiterals[], M = number of operators in infixLiterals[]
   * - The method evaluatePostfixOrder() has a worst case running time complexity of O(N)
   * 
   * 
   * <p> Why I think it's optimal:
   *  - I think that the run time complexity of this method is optimal, since the only major expense comes from convertInfixToPostfix().
   *    The other run-time expense from evaluate evaluatePostfixOrder was acceptable, because it gets eliminated using asymptotic notation, and also doing better than O(N) is impossible since we need to go through every string literal.
   *  - I think that the space complexity of this method is also optimal, as it uses O(N) space. This comes from the method evaluatePostfixOrder()
   *    and was unavoidable because we need a stack to evaluate the order of post-fix notation.
   * 
   *  <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case. <br>
   *  
   * Therefore, using asymptotic notation, the worst case running time complexity is = O(N * M) + O(N) = O(N * M) where N = length of infixLiterals[], M = number of operators in infixLiterals[]
   * @param infixLiterals : an array containing the string literals in infix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the integer result of evaluating the expression
   **/
  public static int evaluateInfixOrder(String infixLiterals[])
  {
    //step 1: convert from infix to post-fix
	  String [] postfixLiterals = convertInfixToPostfix(infixLiterals);
	  
	//Step 3: calculate the post-fix expression with the the method for evaluating post-fix expressions.
	  int answer = evaluatePostfixOrder(postfixLiterals);
	  	  
	  return answer;
  }
  
  //~ Conversion  methods ..........................................................

  /**
   * Converts infix to post-fix.
   *
   * <p> Asymptotic worst case running time: O(N * M) where N = length of infixLiterals[], M = number of operators in infixLiterals[]
   * 
   * <p> Justification: <br>
   * 
   * 1. At the start, we validate that infixLiterals[] is in valid infix order before converting it.
   *  - The function call to validateInfixOrder() has a worst case running time complexity of O(N)
   * 2. Then, we iterate through the array of string literals to convert infix to post-fix.
   *  - The function calls inside of the for-loop are constant time operations e.g.
   *    - isInteger() has a a worst case running time complexity of O(1)
   *    - isOperator() has a worst case running time complexity of O(1)
   *    - operatorOfGreaterThanOrEqualPrecedence() has a worst case running time complexity of O(1)
   *  - We use the stack to convert infix to post-fix. All the stack operations also are constant time operations e.g
   *    - push()
   *    - pop()
   *    - isEmpty()
   * {source: http://pages.cs.wisc.edu/~siff/CS367/Notes/stacks.html#:~:text=For%20all%20the%20standard%20stack,can%20be%20O(1).&text=It's%20obvious%20that%20size%20and,the%20top%20of%20the%20stack.}
   *
   *  - We add elements to an array-list, which has a worst case run time complexity of O(1). { see here: https://www.baeldung.com/java-collections-complexity }
   *  - To initialize the array-list it is O(1)
   *  
   *  
   *  - The worst case running time complexity of the if/else-if/else-if/else statement inside the for-loop is O(M) where M = the number of operators to pop off the stack.
   *    This is because the worst case time is max(time(execute first if-statement), time(execute 1st else-if statement), time(execute 2nd else-if statement), time(execute else statement))
   *    The "if/else-if/else-if" parts have a worst case run time complexity of O(1) but the "else" part has a worst case run time complexity of O(M) as mentioned above.
   *  
   *  - At the end, we convert the array-list to an array using toArray() which has a worst case run time complexity of O(N).
   *  
   *  Therefore, using asymptotic notation, the worst case running time is O(N) [from validateInfixOrder()] + O(1) [create arrayList] +  O(N) [from for-loop] X O(M) [from else statement] + O(M) [from popping any remaining operators off stack] +  O(N) [from converting array-list to array] = O(N) + O(1) + O(N * M) + O(N + M) =  O(N * M) {since we keep only highest order terms}
   *  The asymptotic worst case running time is O(N*M) where N = length of the array of string literals and M = number of operators in the array.
   *
   *  <p> Why I think it's optimal:
   *  - I think that the run time complexity of this method is optimal because the major expense only comes from looping through each element in infixLiterals[] and the operators we put on the stack.
   *  - I think that the space complexity of this method is O(N) because we use the stack, however we had no choice but to use the stack here.
   *
   * <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case. <br>
   * 
   * @param infixLiterals : an array containing the string literals in infix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in post-fix order.
   **/
  public static String[] convertInfixToPostfix(String infixLiterals[])
  {
	  if (!validateInfixOrder(infixLiterals)) throw new IllegalArgumentException();
	 Stack<Integer> stack = new Stack<Integer>();
     int numberOfBrackets = 0;
     
     List<String> postfix = new ArrayList<String>();
     
     
	 for(int i = 0; i < infixLiterals.length; i++) {
		 if(isInteger(infixLiterals[i])) { //if it's an operand
			 postfix.add(infixLiterals[i]);
		 }
		 
		 else if(infixLiterals[i] == "(") { //if it's a left bracket
			 //push the index of the bracket onto the stack
			 numberOfBrackets++;
			 stack.push(i);
		 }
		 
		 else if(isOperator(infixLiterals[i])) {
			 if(stack.isEmpty() == false) {
				 while(operatorOfGreaterThanOrEqualPrecedence(stack, infixLiterals[i], infixLiterals)) {
					 int op = stack.pop();
					 postfix.add(infixLiterals[op]);
				 }
			 }
			 stack.push(i);	 
		 }
		 
		 else { // if the program reached this far, it's a right bracket !
			 numberOfBrackets++;
			 int op;
			 while(infixLiterals[stack.peek()] != "(") { 
				 op = stack.pop();
				 postfix.add(infixLiterals[op]);
			 }
			 op = stack.pop();
		 }
		 
		 
 	 }
	 
	 //pop off any remaining operators on the stack
	 if(stack.isEmpty() == false) {
		 int top = stack.peek();
			int op2;
			while(!stack.isEmpty()) { 
			
					top = stack.peek();
					op2 = stack.pop();
					postfix.add(infixLiterals[op2]);
			}
	 }
	    
	  String[] result = postfix.toArray(new String[0]); //change array-list to array
	  return result;
  }
 
  /**
   * Converts post-fix to infix.
   *
   * <p> Asymptotic amortized running time: O(N * M) where M <= N
   * 
   * <p> Justification: 
   * 
   * 1. There is an outer for-loop which iterates through the array of string literals.
   *  - We used toArray() at the end which has a worst-case time complexity of O(N)
   *  - All the operations inside of the for-loop have a worst case/amortized running time complexity of O(1) 
   *    except for addAll(Collection<? extends E> c) and new LinkedList<>(Collection<? extends E> c) on a linked list  {more at: http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/java/util/LinkedList.java}
   *    
   *    These have a worst case running time complexity of O(length of (c)). 
   *    Therefore the size of c = M where M <= N, henceforth the amortized running time complexity is O(M).
   *    
   *    Overall: O(N * M ) + O(N) = O(N * M) using asymptotic notation
   *    
   * 2. I used the amortized running time because I got the average running time per operation over a worst-case sequence of operations and this will give us a rough estimate of how the program will run at the worst case. <br>
   *
   *  <p>Why I think it's optimal: <br>
   * - The running time is optimal we have to iterate through all the elements of the array of string literals and concatenating the strings 
   * - The space complexity (the amount of memory space required) for this method is optimal in my opinion because it uses O(N) space, and it was unavoidable.
   *   It was unavoidable because we needed a stack when converting the post-fix to infix.
   *  
   *  <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case. <br>
   *  
   * @param postfixLiterals : an array containing the string literals in post-fix order.
   * The method assumes that each of these literals can be one of:
   * - "+", "-", "*", or "/"
   * - or a valid string representation of an integer.
   *
   * @return the expression in infix order.
   **/
  public static String[] convertPostfixToInfix(String postfixLiterals[])
  {
	  if (!validatePostfixOrder(postfixLiterals)) { //make sure it isn't invalid!
		  throw new IllegalArgumentException();
	  }
	  
	  Stack<List<String>> stack = new Stack<>();
	    for (String token : postfixLiterals) {
	      if (isOperator(token)) { //if it's an operator, pop off the top two elements on the stack
	        List<String> expression2 = stack.pop();
	        List<String> expression1 = stack.pop();
	        List<String> list = new LinkedList<>(); 
	        
	        //parenthesize with "(" and ")" and add the expressions and tokens appropriately.
	        list.add("(");
	        list.addAll(expression1);
	        list.add(token);
	        list.addAll(expression2);
	        list.add(")");
	        stack.push(list);
	      } 
	      
	      else { //it it's not an operand
	        List<String> operand = new LinkedList<>();
	        operand.add(token);
	        stack.push(operand);
	      }
	    }
	    //get the final result by popping off the list and converting it to an array.
	    String[] finalAnswer = stack.pop().toArray(new String[0]);
	    return finalAnswer;
	  }
	  
  //~ Helper Methods ..........................................................
  
  /**
   * Test and see if the string passed in is an operator
   *
   * <p>Asymptotic worst-case running time : O(1)
   *
   * <p>Justification:<br>
   * 1. We check if the string that is passed into the function is one of the operators (using "=="), and we divide them using OR symbols.
   * These comparisons are constant time operations, each comparison has a worst case run time complexity of O(1). <br>
   *  
   * 2. The if-statement which contains these comparisons is also a constant time operation, hence it also has a worst run time complexity of O(1). <br>
   * 
   * <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case. <br>
   *
   * @param String (to see if it's an operator)
   * @return Boolean {TRUE (if the string is an operator) or FALSE (if not an operator)}
   */
  public static boolean isOperator(String potentialOperator) {
     boolean operator = false;
	  if(potentialOperator == "-" || potentialOperator == "+" || potentialOperator == "*" || potentialOperator == "/")   {
    	 operator = true;
     }
	  return operator;
  }
 
  
  /**
   * See if there is an operator of greater than or equal precedence on the stack
   *
   * <p>Asymptotic worst-case running time : O(1)
   *
   * <p>Justification:<br>
   * 1. The function calls inside of this method runs at constant time e.g.
   *   - isEmpty for the stack has a worst case running time complexity of O(1)
   *   - peek() for the stack has a worst case running time complexity of O(1) { source: https://medium.com/@kaichimomose/stack-implementation-and-complexity-c176924e6a6b } <br>
   * 2. The if/else-if statements also run at constant time, and have a worst case running time complexity of O(1) <br>
   * 
   * <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case. <br>
   *
   * @param String (to see if it's an operator)
   * @return Boolean {TRUE (if the string is an operator) or FALSE (if not an operator)}
   */
  public static boolean operatorOfGreaterThanOrEqualPrecedence (Stack<Integer> stack, String valueBeingChecked, String infixLiterals[]) {
	  boolean operatorOfGEPrecedence = false;
	  int precedenceOfTop = 0;
	  int precedenceOfValueBeingChecked = 0;
	  
	
	  if(stack.isEmpty()) {
		  return false;
	  }
	  int top = stack.peek();
	 
	  if(infixLiterals[top] == "(") {  //make sure that if a left bracket is at the top of the stack, just ignore it and return false.
		  return false;
	  }
	  
	  if(infixLiterals[top] == "*") {
		  precedenceOfTop = 2;
	  }
	  else if(infixLiterals[top] == "/") {
		  precedenceOfTop = 2;
	  }
	  else if(infixLiterals[top] == "+") {
		  precedenceOfTop = 1;
	  }
	  else  { //if(infixLiterals[top] == "-")
		  precedenceOfTop = 1;
	  }
	 
	  
	  if(valueBeingChecked == "*") {
		  precedenceOfValueBeingChecked = 2;
	  }
	  else if(valueBeingChecked == "/") {
		  precedenceOfValueBeingChecked = 2;
	  }
	  else if(valueBeingChecked == "+") {
		  precedenceOfValueBeingChecked= 1;
	  }
	  else  { //if(valueBeingChecked == "-")
		  precedenceOfValueBeingChecked = 1;
	  }
	 
	
	  
	  if(precedenceOfTop >= precedenceOfValueBeingChecked) {
	        operatorOfGEPrecedence = true;
	    }
	  
	  
	  return operatorOfGEPrecedence;
  }
  
  
  /**
   * Applies the operator on num1 and num2
   *
   * <p>Asymptotic worst-case running time : O(1).
   * 
   * <p> Justification: <br>
   * 
   * - We have a bunch of if-statements which compare two strings and do basic binary operations depending on the string.
   * - These if-statements have a worst case asymptotic running time of O(1).
   * - They are constant time operations. <br>
   * 
   * <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case. <br>
   *
   * @param firstNumber (first operand)
   * @param secondNumber (second operand)
   * @param operator (to apply on the first two parameters)
   * @return integer result of the operator being applied to the operands
   */
  public static int applyOperator(int firstNumber, int secondNumber, String operator) {
    
	  if(operator == "+") {
		  return firstNumber + secondNumber;
	  }
	  
	  if(operator == "-") {
		  return firstNumber - secondNumber;
	  }
	  
	  if(operator == "/") {
		  return firstNumber / secondNumber;
	  }
	  
	  if(operator == "*") {
		  return firstNumber * secondNumber;
	  }

    throw new IllegalArgumentException();
  }
  
  /**
   * Checks if a string is an integer
   *
   * <p>Asymptotic worst-case running time : O(1)
   *
   * <p> Justification: <br>
   * 1. This method mainly uses if-statements, which have a worst case run time complexity of O(1)
   * 2. We use the charAt(), which is a constant time operation and has a worst case run time complexity of O(1)
   * 3. We check if the string is an operator, the function call for isOperator() runs at constant time 
   *    since the worst case running time complexity of isOperator() is O(1) <br>
   *    
   * Therefore the worst case running time complexity of the method is O(1)
   *    
   * <p>I chose the worst-case running time to use as it gives a rough estimate of how the program will run in the worst case. <br>
   *
   * @param String (possible integer in string form)
   * @return Boolean (TRUE (if the string is an integer) or FALSE (if the string isn't an integer))
   */
  public static boolean isInteger(String str) {

	    if((str.charAt(0) == '(') || str.charAt(0) == ')') {
	    	return false;
	    }
	    
	    if(isOperator(str)) {
	    	return false;
	    }
	    
	    return true;
	}

  
	//~ Research ...................................................................
	
   /*
    * The data structures I used:
    * 
    * 1. LinkedLists
    * 2. Stacks
    * 3. Array-Lists
    * 
    * LinkedLists:
    *   SOURCE: http://developer.classpath.org/doc/java/util/LinkedList-source.html
	*    The list of methods my code calls:
	*      * Constructing an empty linked list 
	*          -> public LinkedList()
	*          -> worst-case running time complexity: O(1) 
	*      
	*      * Construct a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
	*          -> public LinkedList(Collection<? extends E> c)
	*          -> worst-case running time complexity: - O(M) where M = length of c
	*      
	*      * Insert the specified element at the specified position in this list.
    *          -> public void add(int index, E element) 
    *          -> worst-case running time complexity: - O(1)
    *          
	*      * Insert all of the elements in the specified collection into another list
	*          ->public boolean addAll(Collection<? extends E> c)
	*          -> worst-case running time complexity: - O(M) where M = length of c
	*          

	*      
	*      
	* Stacks:
	*  SOURCE: http://developer.classpath.org/doc/java/util/Stack-source.html
	*   The list of methods my code calls:
	*       
	*      * Create a stack
	*          -> public Stack() 
	*          -> worst-case running time complexity: - O(1)
	*      
	*      * Removes the object at the top of this stack and returns that object as the value of this function.
	*          -> public synchronized E pop() 
	*          -> worst-case running time complexity: - O(1)
	*      
	*      * Push an item onto the top of this stack
	*          -> public E push(E item) 
	*          -> worst-case running time complexity: - O(1)
	*
	*
	* Array-list:
	*   SOURCE: http://developer.classpath.org/doc/java/util/ArrayList-source.html
	*   The list of methods my code calls:
	*   
	*     * Create an Array-List 
	*       -> public ArrayList()
	*       -> worst-case running time complexity: - O(1)
	*       
	*     * Add to the end of an Array-List
	*       -> public add(E e)
	*       -> worst-case running time complexity: - O(1)
	*       
	*     * Convert Array-list to Array
	*       -> public toArray()
	*       -> worst-case running time complexity: - O(N)
	*  
	*  I chose to talk about the worst-case running time, because I believed that it would be a good indicator of how the program will perform in the worst case.
	*  It will not illustrate an exact performance of the program in the worst case, but it will provide a good indicator of how the program copes.
	*  I also liked to use asymptotic notation because I felt that it was the simplest but most effective way to capture the worst-case run-time complexity.
	*/

}