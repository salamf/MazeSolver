public class A4Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;
	
	public static void main(String[] args) {
		testStackOperations();
		testStackIsGeneric();
		testStackExceptions();
		
		System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
	}
	
	public static void testStackOperations() {
		System.out.println("Stack Operations Tests:");
		A4Stack<Integer> defaultSizeStack = new A4Stack<Integer>();
		A4Stack<Integer> smallStack = new A4Stack<Integer>(2);
		int result = 0;
		
		displayResults(smallStack.isEmpty(), "stack initially empty");
		displayResults(!smallStack.isFull(), "empty stack is not full");


		smallStack.push(2);
		result = smallStack.top();
		displayResults(!smallStack.isEmpty(), "stack no longer empty");
		displayResults(!smallStack.isFull(), "stack still not full");
		displayResults(result == 2, "top works after initial push");

		
		smallStack.push(5);
		result = smallStack.top();
		displayResults(!smallStack.isEmpty(), "stack not empty");
		displayResults(smallStack.isFull(), "stack now full");
		displayResults(result==5, "top works after second push");
		
		result = smallStack.pop();
		displayResults(!smallStack.isEmpty(), "stack not empty");
		displayResults(!smallStack.isFull(), "stack no longer full");
		displayResults(result==5, "correct value returned from pop");	
		result = smallStack.top();
		displayResults(result==2, "top works after pop");
		
		result = smallStack.pop();
		displayResults(smallStack.isEmpty(), "stack is empty again");
		displayResults(!smallStack.isFull(), "stack is not full");
		displayResults(result==2, "correct value returned from pop");

		smallStack.push(9);
		result = smallStack.top();
		displayResults(!smallStack.isEmpty(), "stack is no longer empty");
		displayResults(!smallStack.isFull(), "stack is not full");
		displayResults(result==9, "correct value returned from top");		
		
		// Add tests for defaultSizeStack, when should it be full?

		Stack<Integer> test = new A4Stack<Integer>();

		test.push(3);
		test.push(4);
		test.push(6);
		test.push(1);
		test.push(8);
		//test.push(10);

		test.popAll();

		//System.out.println(test.top());


		System.out.println();
	}
	
	public static void testStackIsGeneric() {
		System.out.println("Stack Generics Tests:");
		A4Stack<Integer> s1 = new A4Stack<Integer>();
		A4Stack<String> s2 = new A4Stack<String>();
		A4Stack<Double> s3 = new A4Stack<Double>();
		
		int result1;
		String result2;
		double result3;
		
		s1.push(3);
		s1.push(8);
		s2.push("CSC");
		s2.push("ENGR");
		s3.push(5.5);
		s3.push(9.1);
		
		result1 = s1.pop();
		result2 = s2.pop();
		result3 = s3.pop();
		
		displayResults(result1==8, "Integer Stack");
		displayResults(result2.equals("ENGR"), "String Stack");
		displayResults(result3==9.1, "Double Stack");		
		
		result1 = s1.top();
		result2 = s2.top();
		result3 = s3.top();
		
		displayResults(result1==3, "Integer Stack");
		displayResults(result2.equals("CSC"), "String Stack");
		displayResults(result3==5.5, "Double Stack");
		
		System.out.println();
	}
	
	public static void testStackExceptions() {
		System.out.println("Stack Exceptions Tests:");
		A4Stack<Integer> s = new A4Stack<Integer>(2);
		int result = 0;

		try {
			result = s.top();
			displayResults(false, "top of empty stack");
		} catch (EmptyStackException e) {
			displayResults(true, "top of empty stack");
		}
		
		try {
			result = s.pop();
			displayResults(false, "pop from empty stack");
		} catch (EmptyStackException e) {
			displayResults(true, "pop from empty stack");
		}
		
		s.push(5);
		s.push(2);
		displayResults(s.isFull(), "filled stack");
		try {
			s.push(3);
			displayResults(false, "push to full stack");
		} catch (FullStackException e) {
			displayResults(true, "push to full stack");
		}
		
		s.pop();
		s.push(8); // should work, as pop made room
		try {
			s.push(1);
			displayResults(false, "push to full stack");
		} catch (FullStackException e) {
			displayResults(true, "push to full stack");
		}	

		s.pop();
		s.pop();
		displayResults(s.isEmpty(), "emptied stack");
		try {
			result = s.pop();
			displayResults(false, "pop from empty stack");
		} catch (EmptyStackException e) {
			displayResults(true, "pop from empty stack");
		}

		s.push(5);
		result = s.top();
		displayResults(result==5, "pushed to empty stack, top is correct");
		
		System.out.println();
	}

	public static void displayResults (boolean passed, String testName) {
       /* There is some magic going on here getting the line number
        * Borrowed from:
        * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
        */
        
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
	
}