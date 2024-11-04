package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestExpressionParsing {

    private ExpressionParsing parser;

    @BeforeEach
    void setUp() {
        parser = new ExpressionParsing("3 + 5 * (2 - 8)");
    }

    @Test
    void testGetExpression() {
        assertEquals("3 + 5 * (2 - 8)", parser.getExpression());
    }

    @Test
    void testBracketBalanceCorrect() {
        assertTrue(ExpressionParsing.checkBracketsBalance());
    }

    @Test
    void testBracketBalanceIncorrect() {
        ExpressionParsing unbalancedParser = new ExpressionParsing("3 + (5 * 2 - 8");
        assertFalse(unbalancedParser.checkBracketsBalance());
    }

    @Test
    void testParseExpression() {
        List<String> expectedTokens = Arrays.asList("3", "+", "5", "*", "(", "2", "-", "8", ")");
        assertEquals(expectedTokens, ExpressionParsing.parseExpression(parser.getExpression()));
    }


    @Test
    void testIsOperator() {
        assertTrue(ExpressionParsing.isOperator('+'));
        assertFalse(ExpressionParsing.isOperator('('));
    }

    @Test
    void testIsParenthesis() {
        assertTrue(ExpressionParsing.isParenthesis('('));
        assertFalse(ExpressionParsing.isParenthesis('+'));
    }
}
