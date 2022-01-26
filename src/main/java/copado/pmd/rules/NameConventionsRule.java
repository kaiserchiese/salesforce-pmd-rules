package com.accenture.pmd.lang.apex.rule.description;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.xml.ast.XmlNode;
import net.sourceforge.pmd.lang.xml.rule.AbstractXmlRule;

import java.io.IOException;
import java.util.Set;

public class NameConventionsRule extends AbstractXmlRule {
    
    private static final String VIOLATION_UNDERSCORES_MSG = "Underscores are only allowed to seperate package prefixes and class names";

    @Override
    protected void visit(XmlNode node, RuleContext ctx) {
        checkUnderscoresAndPrefixes(ctx.getSourceCodeFilename(), node, ctx);
    }

    private void checkUnderscoresAndPrefixes(String filename, XmlNode node, RuleContext ctx) {
        if (filename.contains("__")) {
            filename = filename.split("__")[0];
        }
        String[] splitResult = filename.split("_");
        if (splitResult.length > 2) {
            this.addViolationWithMessage(ctx, node, VIOLATION_UNDERSCORES_MSG);
        } else if (splitResult.length == 2) {
            if (!PropertyUtils.getPrefixes().contains(splitResult[0])) {
                this.addViolationWithMessage(ctx, node, VIOLATION_UNDERSCORES_MSG);
            }
        }
    }
}