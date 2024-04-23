//package CalculatorController;//package Controller;
//
//import java.util.Stack;
//
//public class ArthmeticEvaluator {
//
//    public static double calculate(String expression) {
//        // Stack to store operands
//        Stack<Double> operands = new Stack<>();
//        // Stack to store operators
//        Stack<Character> operators = new Stack<>();
//
//        // Iterating through each character of the expression
//        for (int i = 0; i < expression.length(); i++) {
//            char ch = expression.charAt(i);
////            System.out.println(ch);
//
//            if (ch == ' ') {
//                continue; // Skip whitespace
//            }
//            if (Character.isDigit(ch) || ch == '.') {
//                // If character is a digit or a decimal point, parse the number
//                StringBuilder num = new StringBuilder();
//                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
//                    num.append(expression.charAt(i));
//                    i++;
//                }
//
//                i--; // Move the index back by 1 to account for the last character of the number
//                operands.push(Double.parseDouble(num.toString()));
//            } else if (ch == '+' || ch == '-' || ch == 'x' || ch == '/') {
//                // If character is an operator
//                if (ch == '-' && (i == 0 || expression.charAt(i - 1) == '(')) {
//                    // If '-' is at the beginning of expression or after '(' it is unary negation
//                    operators.push('~');
//                } else {
//                    while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
//                        applyOperator(operators.pop(), operands);
//                    }
//                    operators.push(ch);
////                    System.out.println(" 2nd else if");
//
//                }
//            }
//
//        }
//
//        // Apply remaining operators
//        while (!operators.isEmpty()) {
//            applyOperator(operators.pop(), operands);
//        }
//
//        // The result will be the only element left in the operands stack
//        return operands.pop();
//    }
//
//    // Function to apply the operator to the top two operands on the operands stack
//    private static void applyOperator(char operator, Stack<Double> operands) {
//        double operand2 = operands.pop();
//        double operand1 = operands.isEmpty() ? 0 : operands.pop(); // Handle unary negation
//        double result = 0;
//        switch (operator) {
//            case '+':
//                result = operand1 + operand2;
//                break;
//            case '-':
//                result = operand1 - operand2;
//                break;
//            case 'x':
//                result = operand1 * operand2;
//                break;
//            case '/':
//                result = operand1 / operand2;
//                break;
//            case '~':
//                result = -operand2; // Unary negation
//                break;
//        }
//        operands.push(result);
//    }
//
//    // Function to check operator precedence
//    public static boolean hasPrecedence(char op1, char op2) {
//        if (op2 == '(' || op2 == ')') return false;
//        if ((op1 == 'x' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
//        return true;
//    }
//
//    public static Boolean isInRange(int lowerBound, int upperBound, String numberString) {
//
//        try {
////        int number = Integer.parseInt(numberString);
//            double number = Double.parseDouble(numberString);
//            return number >= lowerBound && number <= upperBound;
//        } catch (NumberFormatException e) {
//            // If parsing fails, the string is not a valid integer
//            return false;
//        }
//    }
//
//
//    public static void main(String[] args) {
//        // Test the calculator
//        String expression = "-2-2";
//        double result = calculate(expression);
//        System.out.println("Result: " + result);
//    }
//}

package CalculatorController;//package Controller;


public class ArthmeticEvaluator {
 static double  initialValue=0.0;

    public static double calculate(Button[] buttons) {
        if (buttons[0].type.equals("NUMBER")) {
                    initialValue = Double.parseDouble(buttons[0].value);
                }

                String currentOperationString = "";
                for (int i = 0; i < buttons.length; i++) {

                    if (i == 0) {
                        continue;
                    }

                    String type = buttons[i].type;
                    String stringValue = buttons[i].value;
                    double value = 0.0;
                    if (buttons[i].type.equals("NUMBER")) {
                        value = Double.parseDouble(stringValue);
                        initialValue= performOperation(initialValue, value, currentOperationString);
                        currentOperationString = "";
                    }
                    if (buttons[i].type.equals("OPERATOR")) {

                        if (buttons[i - 1].value.equals("SUBTRACTION") && buttons[i].value.equals("ADDITION")) {
                            currentOperationString = "SUBTRACTION";
                        } else if (buttons[i - 1].value.equals("SUBTRACTION") && buttons[i].value.equals("SUBTRACTION")) {
                            currentOperationString = "ADDITION";
                        } else {
                            currentOperationString = stringValue;
                        }
                    }
                }
        System.out.println("for loop completed");
        System.out.println("final ouput"+initialValue);
                return initialValue;
    }

    public static double performOperation(double operand1, double operand2, String operation) {
        switch (operation) {
            case "ADDITION":
                return operand1 + operand2;
            case "SUBTRACTION":
                return operand1 - operand2;
            case "MULTIPLY":
                return operand1 * operand2;
            case "DIVISION":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return Double.POSITIVE_INFINITY;
//                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }
    public static void main(String[] args) {
        // Test the calculator
        String expression = "-2-2";
//        double result = calculate(expression);
//        System.out.println("Result: " + result);
    }

}

