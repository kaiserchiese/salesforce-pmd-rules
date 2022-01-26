package com.accenture.pmd.lang.apex.rule.description;


import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.lang.apex.ApexHandler;
import net.sourceforge.pmd.lang.apex.ApexLanguageModule;
import net.sourceforge.pmd.lang.apex.ast.ASTModifierNode;
import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestClassRuleTest  {
    private TestClassRule testRule;
    private List<RuleViolation> receivedViolations;
    private RuleContext ruleContextMock;

    @Before
    public void setUp() {
        testRule = new TestClassRule();
        receivedViolations = new LinkedList<>();
        ruleContextMock = new RuleContext() {
            @Override
            public Report getReport() {
                return new Report() {
                    @Override
                    public void addRuleViolation(RuleViolation violation) {
                        receivedViolations.add(violation);
                    }
                };
            }
        };
        ruleContextMock.setLanguageVersion(new LanguageVersion(new ApexLanguageModule(),"",new ApexHandler()));
    }

    @Test
    public void testClassRuleTestWithViolation() {
        ASTModifierNode astModifierNode = Mockito.mock(ASTModifierNode.class);
        when(astModifierNode.isTest()).thenReturn(true);

        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        when(astUserClassMock.getImage()).thenReturn("NotRealClass");
        when(astUserClassMock.getModifiers()).thenReturn(astModifierNode);
        when(astUserClassMock.getModifiers().isTest()).thenReturn(true);

        testRule.visit(astUserClassMock,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }
}
