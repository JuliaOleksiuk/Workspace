import java.util.Random;

public class FolderDataGenerator {

    private static final String PROJECT_ID = "10003";
    private static final String CLIENT_KEY = "a4e72bcd-698a-3fd9-ac21-11690b352fdd";

    public static String generateInsertArgument(int id, int parentId, int totalSubfolders) {
        String name = generateRandomName();
        String parent = "NULL";
        if(parentId != -1){
            parent = String.valueOf(parentId);
        }
        return String.format("('%s', '%s', '%s', %s, %d, 0)", CLIENT_KEY, name, PROJECT_ID, parent, totalSubfolders);
    }

    public static String generateFirstGeneration( int fromIndex, int toIndex, int totalSubfolders) {

        StringBuilder sb = new StringBuilder();
        sb.append(generateInsertBeggining());
        for (int i = fromIndex; i <= toIndex; i++) {
            sb.append(generateInsertArgument(i, -1, totalSubfolders));
            if (i != toIndex) {
                sb.append(", \n ");
            }
        }
        sb.append(";");
        return sb.toString();
    }

    public static String generateGeneration(int firstParentIndex, int lastParentIndex,
                                                   int numberOfChildren,int totalSubfoldersOfChildren, int fromIndex) {
        int currentIndex = fromIndex + 1;
        StringBuilder sb = new StringBuilder();
        sb.append(generateInsertBeggining());

        for (int i = firstParentIndex; i <= lastParentIndex; i++) {
            for(int j = 1; j <= numberOfChildren; j++) {
                sb.append(generateInsertArgument(currentIndex, i, totalSubfoldersOfChildren));
                if (i != lastParentIndex || j != numberOfChildren) {
                    sb.append(", \n ");
                }
                currentIndex ++;
            }
        }

        sb.append(";");
        return sb.toString();
    }

    public static String generateInsertBeggining() {
        return "INSERT INTO public.folder " +
                "(client_key, \"name\", project_id, parent_id, total_subfolders, total_test_cases) VALUES \n";
    }

    public static String generateRandomName() {
        Random random = new Random();
        int length = random.nextInt(91) + 10; // Random length between 10 and 100 characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a'); // Random lowercase letter
            sb.append(c);
        }
        return sb.toString();
    }
}
