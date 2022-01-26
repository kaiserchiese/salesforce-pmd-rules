package com.accenture.pmd.lang.apex.rule.description;

import net.sourceforge.pmd.lang.apex.ast.*;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

public class DmlInsideTryCatchRule extends AbstractApexRule {

    private final static String VIOLATION_MSG = "DML operations should only be used in combination with try/catch blocks.\n";

    private Boolean isParentTryCatchBlockExist(final ApexNode<?> node) {
        ASTTryCatchFinallyBlockStatement tryClause = node.getFirstParentOfType(ASTTryCatchFinallyBlockStatement.class);
        return !(tryClause == null);
    }

    private Boolean isTestMethodOrClass(final ApexNode<?> node) {
        final ASTModifierNode modifierNode = node.getFirstChildOfType(ASTModifierNode.class);
        return modifierNode != null && modifierNode.isTest();
    }

    private Boolean isViolatedNode(ApexNode<?> node) {
        boolean answer = true;
        if (isTestMethodOrClass(node.getFirstParentOfType(ASTUserClass.class))) {
            answer = false;
        }

        if (isParentTryCatchBlockExist(node)) {
            answer = false;
        }
        return answer;
    }

   @Override
   public Object visit(ASTDmlInsertStatement node, Object data) {
       if (isViolatedNode(node)) {
           this.addViolationWithMessage(data, node, VIOLATION_MSG);
       }
       return data;
   }

    @Override
    public Object visit(ASTDmlUpdateStatement node, Object data) {
        if (isViolatedNode(node)) {
            this.addViolationWithMessage(data, node, VIOLATION_MSG);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlUpsertStatement node, Object data) {
        if (isViolatedNode(node)) {
            this.addViolationWithMessage(data, node, VIOLATION_MSG);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlDeleteStatement node, Object data) {
        if (isViolatedNode(node)) {
            this.addViolationWithMessage(data, node, VIOLATION_MSG);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlUndeleteStatement node, Object data) {
        if (isViolatedNode(node)) {
            this.addViolationWithMessage(data, node, VIOLATION_MSG);
        }
        return data;
    }

    @Override
    public Object visit(ASTDmlMergeStatement node, Object data) {
        if (isViolatedNode(node)) {
            this.addViolationWithMessage(data, node, VIOLATION_MSG);
        }
        return data;
    }

    @Override
    public Object visit(ASTMethodCallExpression node, Object data) {
        ASTReferenceExpression ref = node.getFirstChildOfType(ASTReferenceExpression.class);
        if (ref != null && ref.getImage() == "Database") {
            if (isViolatedNode(node)) {
                this.addViolationWithMessage(data, node, VIOLATION_MSG);
            }
        }
        return data;
    }
}
