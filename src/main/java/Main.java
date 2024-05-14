public class Main {

    public static void main(String[] args) {

        FileUtil.writeToFile("firstGeneration.sql", FolderDataGenerator.generateFirstGeneration(1, 100, 10));
        FileUtil.writeToFile("secondGeneration.sql", FolderDataGenerator.generateGeneration(1106, 1205, 10, 10, 100));

        StringBuilder allGenerations = new StringBuilder();

        int lastIndexOfSecondGeneration = 5206;

        for (int i = 1; i <= 10; i++) {
            int fromIndex = lastIndexOfSecondGeneration * i + 1;
            int firstParentIndex = (i-1) * 100 + lastIndexOfSecondGeneration;
            int lastParentIndex = firstParentIndex + 100;
            allGenerations.append(FolderDataGenerator.generateGeneration(firstParentIndex, lastParentIndex, 10, 0, fromIndex));
            allGenerations.append("\n"); // Add a newline character after each generation
        }

        FileUtil.writeToFile("thirdGeneration.sql", allGenerations.toString());
    }
}
