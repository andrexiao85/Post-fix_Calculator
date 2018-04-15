// Postfix Calculator Applet
//
// CS 201 HW 8  -  Skeleton

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; // for Stack

public class Calc extends Applet implements ActionListener {

    private static final long serialVersionUID = 1L; // to avoid Eclipse warning
    
    // instance variables
    
    protected Label result;         // label used to show result
    protected Stack<Integer> stack; // stack used for calculations
    protected int current;          // current number being entered
    protected boolean entered;      // whether current number has been entered
                                    // if so, show number in red

    // local color constants
    static final Color black = Color.black;
    static final Color white = Color.white;
    static final Color red = Color.red;
    static final Color blue = Color.blue;
    static final Color yellow = Color.yellow;
    static final Color orange = Color.orange;
    static final Color lblue = new Color(200, 220, 255);
    static final Color dred = new Color(160, 0, 100);
    static final Color dgreen = new Color(0, 120, 90);
    static final Color dblue = Color.blue.darker();

    public void init() 
    /**
     * Initializes the instance variables and the layout of the
     * applet.
     */
    {
        setFont(new Font("TimesRoman", Font.BOLD, 56));

        // add your code here to set up the applet.
	
        // hint: to get the blue border around the result label,
        // I used a BorderLayout and added empty labels to the borders

        // make good use of helper methods to keep your code readable -
        // a rule of thumb is: if it doesn't fit on the screen, the
        // method is too long (and you might loose points :( )


        // just a placeholder to get it to compile and to demonstrate
        // the event handling:
        stack = new Stack<Integer>();
        entered = false;
        current = 0;
        setLayout(new BorderLayout());
        add("North", resultPanel());
        add("Center", numberOps());
        add("South", clearEnter());
        //result = new Label("result");
        //add(result);
        //add(CButton("+", dblue, orange));
        //add(CButton("3", dgreen, yellow));
        //add(CButton("9", dgreen, yellow));
        
    }
    
    protected Panel resultPanel() 
    /**
     * Creates the label that holds the inputted number
     * and results (the "screen" of the calculator).
     */
    {
    	Panel resultP = new Panel();
    	Label north = new Label();
    	Label south = new Label();
    	Label east = new Label();
    	Label west = new Label();
    	north.setBackground(blue);
    	south.setBackground(blue);
    	east.setBackground(blue);
    	west.setBackground(blue);
    	resultP.setLayout(new BorderLayout());
    	resultP.add("North", north);
    	resultP.add("South", south);
    	resultP.add("East", east);
    	resultP.add("West", west);
    	result = new Label("0");
    	result.setForeground(dgreen);//edit still
    	result.setAlignment(Label.RIGHT);
    	resultP.add("Center", this.result);
    	return resultP;
    }

    protected Panel numberOps()
    /**
     * Creates a 4x4 grid Panel that contains the numbers
     * and operators.
     */
    {
    	Panel numOps = new Panel();
    	numOps.setLayout(new GridLayout(4, 4));
    	numOps.add(CButton("7", dgreen, yellow));
    	numOps.add(CButton("8", dgreen, yellow));
    	numOps.add(CButton("9", dgreen, yellow));
    	numOps.add(CButton("+", dblue, orange));
    	numOps.add(CButton("4", dgreen, yellow));
    	numOps.add(CButton("5", dgreen, yellow));
    	numOps.add(CButton("6", dgreen, yellow));
    	numOps.add(CButton("-", dblue, orange));
    	numOps.add(CButton("1", dgreen, yellow));
    	numOps.add(CButton("2", dgreen, yellow));
    	numOps.add(CButton("3", dgreen, yellow));
    	numOps.add(CButton("*", dblue, orange));
    	numOps.add(CButton("0", dgreen, yellow));
    	numOps.add(CButton("(-)", dred, yellow));
    	numOps.add(CButton("Pop", dred, yellow));
    	numOps.add(CButton("/", dblue, orange));
    	return numOps;
    }
    
    protected Panel clearEnter() 
    /**
     * Creates a 1 x 2 grid for the clear and enter
     * buttons.
     */
    {
    	Panel cE = new Panel();
    	cE.setLayout(new GridLayout(1, 2));
    	cE.add(CButton("Enter", dblue, lblue));
    	cE.add(CButton("Clear", dblue, lblue));
    	return cE;
    }
    // a useful helper methods, given to you for free!

    // create a colored button
    protected Button CButton(String s, Color fg, Color bg) {
        Button b = new Button(s);
        b.setBackground(bg);
        b.setForeground(fg);
        b.addActionListener(this);
        return b;
    }

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            String label = ((Button)e.getSource()).getLabel();
            if (label.equals("+"))
                add();
            else if (label.equals("-"))
                sub();
            else if (label.equals("*"))
                mult();
            else if (label.equals("/"))
            	div();
            else if (label.equals("Enter"))
            	enter();
            else if (label.equals("Pop"))
            	pop();
            else if (label.equals("(-)"))
            	neg();
            else if (label.equals("Clear"))
            	clear();


            // add similar calls for all other "non-number" buttons


            else {     // number button
                int n = Integer.parseInt(label);
                number(n);
                entered = false;
                //System.out.println(current);
            }
        }
    }

    // display number n in result label
    protected void show(int n) {
    	result.setForeground(dgreen);
    	if (result.equals("0") || entered) {
    		result.setText(Integer.toString(n));
    		current = Integer.parseInt(result.getText());
    	}else {
    		result.setText(Integer.toString(Integer.parseInt(result.getText())*10 + n));
    		current = Integer.parseInt(result.getText());
    	}
    }


    // just placeholders to get it to compile

    // handle add button
    protected void add() 
    /**
     * Handles the add (+) button. If number was not
     * entered previously, enters number then adds.
     */
    {
        //System.out.println("add was pressed");
        if(!entered) {
    		entered = true;
        	stack.push(current);
    	}
        if(stack.size() > 1)
        	stack.push(stack.pop() + stack.pop());
        result.setForeground(red);
        result.setText(Integer.toString(stack.peek()));
        current = 0;
    }
    protected void sub() 
    /**
     * Handles the subtract (-) button. If number was not
     * entered previously, enters number then subtracts.
     */
    {
    	//System.out.println("sub was pressed");
    	if(!entered) {
    		entered = true;
        	stack.push(current);
    	}
    	if(stack.size() > 1) {
    		//num 1 and num2 are used to ensure the correct order of subtraction.
    		
    		int num2 = stack.pop();
    		int num1 = stack.pop();
    		stack.push(num1 - num2);
    	}
    	else {
    		stack.push(stack.pop() * -1);
    	}
    	result.setForeground(red);
    	result.setText(Integer.toString(stack.peek()));
    }
    protected void mult() 
    /**
     * Handles the multiply (*) button. If number was not
     * entered previously, enters number then multiplies.
     */
    {
    	//System.out.println("mult was pressed");
    	if(!entered) {
    		entered = true;
        	stack.push(current);
    	}
    	if(stack.size() > 1) {
    		stack.push(stack.pop() * stack.pop());
    	}
    	else {
    		stack.push(stack.pop() * 0);
    	}
    	result.setForeground(red);
    	result.setText(Integer.toString(stack.peek()));
    }
    protected void div() 
    /**
     * Handles the divide (/) button. If number was not
     * entered previously, enters number then divides.
     */
    {
    	//System.out.println("div was pressed");
    	if(!entered) {
    		entered = true;
        	stack.push(current);
    	}
    	if(stack.size() > 1) {
    		//denom and numer are used to ensure the correct order of division.
    		
    		int denom = stack.pop();
    		int numer = stack.pop();
    		stack.push(numer / denom);
    	} else {
    		stack.push(0 / stack.pop());
    	}
    	result.setForeground(red);
    	result.setText(Integer.toString(stack.peek()));
    }
    protected void enter() 
    /**
     * Handles the enter button. 
     * Pushes the current number into stack.
     * Highlights the number red and resets current to 0.
     */
    {
    	//System.out.println("enter was pressed");
    	stack.push(current);
    	entered = true;
    	result.setForeground(red);
    	current = 0;
    }
    protected void pop()
    /**
     * Handles the pop button.
     * Pops the top most integer out of stack.
     * Displays the top most integer after popping, if
     * 	stack is empty after popping, displays 0.
     */
    {
    	//System.out.println("pop was pressed");
    	result.setForeground(red);
    	if (current != 0)
    		stack.push(current);
    	if(!stack.isEmpty())
    		stack.pop();
    	if (!stack.isEmpty())
    		result.setText(Integer.toString(stack.peek()));
    	else
    		result.setText("0");
    	current = 0;
    }
    protected void neg()
    /**
     * Handles the negative ((-)) button.
     * Turns current and the number in the display
     * 	negative.
     */
    {
    	//System.out.println("neg was pressed");
    	current = current * -1;
    	result.setText(Integer.toString(current));
    }
    protected void clear() 
    /**
     * Handles the clear button. 
     * Clears resets calculator.
     * 	-Stack cleared
     * 	-current = 0
     * 	-display reset to 0
     * 
     */
    {
    	//System.out.println("clear was pressed");
    	stack.clear();
    	result.setText("0");
    	result.setForeground(dgreen);
    }

    // handle number buttons
    protected void number(int n) {
        show(n);
    }
}
