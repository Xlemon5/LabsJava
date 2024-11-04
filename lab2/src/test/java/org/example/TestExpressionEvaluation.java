package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestExpressionEvaluation {

    private final ExpressionEvaluation evaluator = new ExpressionEvaluation();

    @Test
    void testSimpleAddition() {
        List<String> expression = Arrays.asList("3", "+", "5");
        assertEquals(8.0, evaluator.evaluateExpression(expression));
    }

    @Test
    void testSimpleSubtraction() {
        List<String> expression = Arrays.asList("10", "-", "4");
        assertEquals(6.0, evaluator.evaluateExpression(expression));
    }

    @Test
    void testSimpleMultiplication() {
        List<String> expression = Arrays.asList("2", "*", "3");
        assertEquals(6.0, evaluator.evaluateExpression(expression));
    }

    @Test
    void testSimpleDivision() {
        List<String> expression = Arrays.asList("10", "/", "2");
        assertEquals(5.0, evaluator.evaluateExpression(expression));
    }

    @Test
    void testComplexExpression() {
        List<String> expression = Arrays.asList("3", "+", "5", "*", "2");
        assertEquals(13.0, evaluator.evaluateExpression(expression)); // 5*2 + 3 = 13
    }

    @Test
    void testParentheses() {
        List<String> expression = Arrays.asList("(", "3", "+", "5", ")", "*", "2");
        assertEquals(16.0, evaluator.evaluateExpression(expression)); // (3 + 5) * 2 = 16
    }

    @Test
    void testOperatorPrecedence() {
        List<String> expression = Arrays.asList("1", "+", "2", "*", "3");
        assertEquals(7.0, evaluator.evaluateExpression(expression)); // 2*3 + 1 = 7
    }
}
