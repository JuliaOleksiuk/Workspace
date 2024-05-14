package models;

import utils.IDUtils;

public class TestCase {

    private String id;
    private String name;
    private String className;
    private String time;
    private String status;
    private TestCycle testCycle;
    private String jiraIssueKey;
    public TestCase() {
        // needed for hibernate
    }

    public TestCase(String id, String name, String className, String time, String status, TestCycle testCycle) {
        this.id = IDUtils.generateId(name, className);
        this.name = name;
        this.className = className;
        this.time = time;
        this.status = status;
        this.testCycle = testCycle;
        this.jiraIssueKey = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TestCycle getTestCycle() {
        return testCycle;
    }

    public void setTestCycle(TestCycle testCycle) {
        this.testCycle = testCycle;
    }

    public String getJiraIssueKey() {
        return jiraIssueKey;
    }

    public void setJiraIssueKey(String jiraIssueKey) {
        this.jiraIssueKey = jiraIssueKey;
    }

    @Override
    public String toString() {
        return "   {\n" +
                "    \t\"id\": \"" + id + "\",\n" +
                "    \t\"name\": \"" + name + "\",\n" +
                "    \t\"className\": \"" + className + "\",\n" +
                "    \t\"time\": \"" + time + "\",\n" +
                "    \t\"status\": \"" + status + "\",\n" +
                "    \t\"testCycle\": \"" + testCycle.getName() + "\"\n" +
                "    \t\"JIRA issue key\": \"" + jiraIssueKey + "\"\n" +
                "    }";
    }
}
