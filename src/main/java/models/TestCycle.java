package models;

import utils.IDUtils;

import java.util.ArrayList;

public class TestCycle {

    private String id;
    private String name;
    private ArrayList<TestCase> testCases = new ArrayList<>();

    public TestCycle() {
        // needed for hibernate
    }

    public TestCycle(String id, String name) {
        this.id = IDUtils.generateId();
        this.name = name;
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

    public ArrayList<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(ArrayList<TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": \"" + id + "\",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"testCases\": " + testCases.toString() + "\n" +
                "}";
    }
}
