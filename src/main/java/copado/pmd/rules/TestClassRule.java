package com.accenture.pmd.lang.apex.rule.description;


import net.sourceforge.pmd.lang.apex.ast.ASTUserClass;
import net.sourceforge.pmd.lang.apex.ast.ApexNode;
import net.sourceforge.pmd.lang.apex.rule.AbstractApexRule;

import java.util.Set;

public class TestClassRule extends AbstractApexRule {
    private static final String VIOLATION_TEST_CLASSNAME_MSG = "Test class must be named as 'Classname that we test + Test";


    @Override
    public Object visit(ASTUserClass node, Object data) {
        if (node.getModifiers().isTest()) {
            checkTestClassFilenames(node.getImage(), node, data);
        }
        return data;
    }

    private void checkTestClassFilenames(String filename, ApexNode node, Object data) {
        final Set<String> APEX_FILENAMES_CLS = PropertyUtils.getClassFilenames();
        final String className = filename.substring(0, filename.length() - "Test".length()) + ".cls";
        if (APEX_FILENAMES_CLS != null && !APEX_FILENAMES_CLS.contains(className)) {
            this.addViolationWithMessage(data, node, VIOLATION_TEST_CLASSNAME_MSG);
        }
    }
}
