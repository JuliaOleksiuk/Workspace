package utils;

import models.TestCase;
import models.TestCycle;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JUnitXMLReader {

    public Map<String, TestCycle> readJUnitXMLFile(String filePath) {
        Map<String, TestCycle> testCycles = new HashMap<>();
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            File inputFile = new File(filePath);
            Document document = saxBuilder.build(inputFile);
            Element rootElement = document.getRootElement();
            List<Element> testSuites = rootElement.getChildren("testsuite");

            for (Element testSuite : testSuites) {
                String hostname = testSuite.getAttributeValue("hostname");
                TestCycle testCycle = testCycles.computeIfAbsent(hostname, k -> new TestCycle(IDUtils.generateId(), hostname));

                List<Element> testCases = testSuite.getChildren("testcase");
                for (Element testCase : testCases) {
                    String testName = testCase.getAttributeValue("name");
                    String className = testCase.getAttributeValue("classname");
                    String time = testCase.getAttributeValue("time");
                    String status = determineStatus(testCase);

                    TestCase testCaseObj = new TestCase(IDUtils.generateId(testName, className), testName, className, time, status, testCycle);
                    String jiraIssueKey = extractJiraKey(testCase);
                    if (jiraIssueKey != null) {
                        testCaseObj.setJiraIssueKey(jiraIssueKey);
                    }
                    testCycle.getTestCases().add(testCaseObj);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return testCycles;
    }

    private String determineStatus(Element testCase) {
        boolean hasFailure = testCase.getChild("failure") != null;
        boolean hasError = testCase.getChild("error") != null;
        boolean isSkipped = testCase.getChild("skipped") != null;

        if (hasFailure) return "FAILED";
        if (hasError) return "ERROR";
        if (isSkipped) return "SKIPPED";
        return "PASSED";
    }

    private List<Element> extractProperties(Element testCase) {
        Element propertiesElement = testCase.getChild("properties");
        if (propertiesElement != null) {
            return propertiesElement.getChildren("property");
        }
        return new java.util.ArrayList<>();
    }

    private String extractJiraKey(Element testCase) {
        Element propertiesElement = testCase.getChild("properties");
        if (propertiesElement != null) {
            List<Element> properties = propertiesElement.getChildren("property");
            for (Element property : properties) {
                if (property.getAttributeValue("name").equals("Jira")) {
                    return property.getAttributeValue("value");
                }
            }
        }
        return null;
    }
}
