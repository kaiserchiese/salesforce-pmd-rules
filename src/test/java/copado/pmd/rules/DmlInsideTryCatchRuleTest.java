package com.accenture.pmd.lang.apex.rule.description;

import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.lang.apex.ApexHandler;
import net.sourceforge.pmd.lang.apex.ApexLanguageModule;
import net.sourceforge.pmd.lang.apex.ast.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DmlInsideTryCatchRuleTest {
    private List<RuleViolation> receivedViolations;
    private RuleContext ruleContextMock;

    @Before
    public void setUp() {

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
    public void DmlInsideTryCatchRuleTestInsert() {
        DmlInsideTryCatchRule dmlRule = new DmlInsideTryCatchRule();
        ASTDmlInsertStatement astDmlInsertStatement = Mockito.mock(ASTDmlInsertStatement.class);
        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        ASTModifierNode modifierNode = Mockito.mock(ASTModifierNode.class);

        when(astUserClassMock.getFirstChildOfType(ASTModifierNode.class)).thenReturn(modifierNode);
        when(modifierNode.isTest()).thenReturn(false);

        when(astDmlInsertStatement.getFirstParentOfType(ASTUserClass.class)).thenReturn(astUserClassMock);
        when(astDmlInsertStatement.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class)).thenReturn(null);

        dmlRule.visit(astDmlInsertStatement,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }

    @Test
    public void DmlInsideTryCatchRuleTestUpdate() {
        DmlInsideTryCatchRule dmlRule = new DmlInsideTryCatchRule();
        ASTDmlUpdateStatement astDmlUpdateStatement = Mockito.mock(ASTDmlUpdateStatement.class);
        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        ASTModifierNode modifierNode = Mockito.mock(ASTModifierNode.class);

        when(astUserClassMock.getFirstChildOfType(ASTModifierNode.class)).thenReturn(modifierNode);
        when(modifierNode.isTest()).thenReturn(false);

        when(astDmlUpdateStatement.getFirstParentOfType(ASTUserClass.class)).thenReturn(astUserClassMock);
        when(astDmlUpdateStatement.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class)).thenReturn(null);

        dmlRule.visit(astDmlUpdateStatement,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }

    @Test
    public void DmlInsideTryCatchRuleTestUpsert() {
        DmlInsideTryCatchRule dmlRule = new DmlInsideTryCatchRule();
        ASTDmlUpsertStatement astDmlUpsertStatement = Mockito.mock(ASTDmlUpsertStatement.class);
        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        ASTModifierNode modifierNode = Mockito.mock(ASTModifierNode.class);

        when(astUserClassMock.getFirstChildOfType(ASTModifierNode.class)).thenReturn(modifierNode);
        when(modifierNode.isTest()).thenReturn(false);

        when(astDmlUpsertStatement.getFirstParentOfType(ASTUserClass.class)).thenReturn(astUserClassMock);
        when(astDmlUpsertStatement.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class)).thenReturn(null);

        dmlRule.visit(astDmlUpsertStatement,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }

    @Test
    public void DmlInsideTryCatchRuleTestUndelete() {
        DmlInsideTryCatchRule dmlRule = new DmlInsideTryCatchRule();
        ASTDmlUndeleteStatement astDmlUndeleteStatement = Mockito.mock(ASTDmlUndeleteStatement.class);
        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        ASTModifierNode modifierNode = Mockito.mock(ASTModifierNode.class);

        when(astUserClassMock.getFirstChildOfType(ASTModifierNode.class)).thenReturn(modifierNode);
        when(modifierNode.isTest()).thenReturn(false);

        when(astDmlUndeleteStatement.getFirstParentOfType(ASTUserClass.class)).thenReturn(astUserClassMock);
        when(astDmlUndeleteStatement.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class)).thenReturn(null);

        dmlRule.visit(astDmlUndeleteStatement,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }

    @Test
    public void DmlInsideTryCatchRuleTestDelete() {
        DmlInsideTryCatchRule dmlRule = new DmlInsideTryCatchRule();
        ASTDmlDeleteStatement astDmlDeleteStatement = Mockito.mock(ASTDmlDeleteStatement.class);
        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        ASTModifierNode modifierNode = Mockito.mock(ASTModifierNode.class);

        when(astUserClassMock.getFirstChildOfType(ASTModifierNode.class)).thenReturn(modifierNode);
        when(modifierNode.isTest()).thenReturn(false);

        when(astDmlDeleteStatement.getFirstParentOfType(ASTUserClass.class)).thenReturn(astUserClassMock);
        when(astDmlDeleteStatement.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class)).thenReturn(null);

        dmlRule.visit(astDmlDeleteStatement,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }

    @Test
    public void DmlInsideTryCatchRuleTestMerge() {
        DmlInsideTryCatchRule dmlRule = new DmlInsideTryCatchRule();
        ASTDmlMergeStatement astDmlMergeStatement = Mockito.mock(ASTDmlMergeStatement.class);
        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        ASTModifierNode modifierNode = Mockito.mock(ASTModifierNode.class);

        when(astUserClassMock.getFirstChildOfType(ASTModifierNode.class)).thenReturn(modifierNode);
        when(modifierNode.isTest()).thenReturn(false);

        when(astDmlMergeStatement.getFirstParentOfType(ASTUserClass.class)).thenReturn(astUserClassMock);
        when(astDmlMergeStatement.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class)).thenReturn(null);

        dmlRule.visit(astDmlMergeStatement,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }

    @Test
    public void DmlInsideTryCatchRuleTestDatabase() {
        DmlInsideTryCatchRule dmlRule = new DmlInsideTryCatchRule();
        ASTMethodCallExpression methodCallExpression = Mockito.mock(ASTMethodCallExpression.class);
        ASTUserClass astUserClassMock = Mockito.mock(ASTUserClass.class);
        ASTModifierNode modifierNode = Mockito.mock(ASTModifierNode.class);
        ASTReferenceExpression astReferenceExpression = Mockito.mock(ASTReferenceExpression.class);

        when(methodCallExpression.getFirstChildOfType(ASTReferenceExpression.class)).thenReturn(astReferenceExpression);
        when(astReferenceExpression.getImage()).thenReturn("Database");

        when(astUserClassMock.getFirstChildOfType(ASTModifierNode.class)).thenReturn(modifierNode);
        when(modifierNode.isTest()).thenReturn(false);

        when(methodCallExpression.getFirstParentOfType(ASTUserClass.class)).thenReturn(astUserClassMock);
        when(methodCallExpression.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class)).thenReturn(null);

        dmlRule.visit(methodCallExpression,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }
}
