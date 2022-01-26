package com.accenture.pmd.lang.apex.rule.description;

import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.lang.apex.ApexHandler;
import net.sourceforge.pmd.lang.apex.ApexLanguageModule;
import net.sourceforge.pmd.lang.xml.ast.XmlNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NameConventionsRuleTest {
    private NameConventionsRule nameConvRule;
    private List<RuleViolation> receivedViolations;
    private RuleContext ruleContextMock;
    private final static String INCORRECT_FILENAME = "incorrect_filename_for_test.xml";
    private final static String CORRECT_FILENAME = "AP_TestClass.xml";
    private XmlNode nodeMock;

    @Before
    public void setUp() {
        nameConvRule = new NameConventionsRule();
        receivedViolations = new LinkedList<>();
        nodeMock = Mockito.mock(XmlNode.class);
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
        ruleContextMock.setSourceCodeFile(new File(INCORRECT_FILENAME));
        nameConvRule.visit(nodeMock,ruleContextMock);
        assertEquals(1,receivedViolations.size());
    }

    @Test
    public void testClassRuleTestCorrect() {
        ruleContextMock.setSourceCodeFile(new File(CORRECT_FILENAME));
        nameConvRule.visit(nodeMock,ruleContextMock);
        assertEquals(0,receivedViolations.size());
    }
}
