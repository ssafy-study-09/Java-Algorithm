import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int U;
    static int F;
    static int Q;

    static Map<String, Set<String>> groups;
    static Map<String, File> files;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            for (int i = 0; i < Q; i++) {
                StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
                String userName = inputLine.nextToken();
                String fileName = inputLine.nextToken();
                String operation = inputLine.nextToken();

                System.out.println(isUserCanExecuteFile(userName, fileName, operation) ? 1 : 0);
            }
        }
    }

    private static boolean isUserCanExecuteFile(String userName, String fileName, String operation) {
        File file = files.get(fileName);
        String[] permissions = file.getPermission().split("");
        String permission = "";
        if (file.getOwner().equals(userName)) {
            permission = getFilePermission(Integer.parseInt(permissions[0]));

        } else if (groups.get(userName).contains(file.getGroup())) {
            permission = getFilePermission(Integer.parseInt(permissions[1]));
        } else {
            permission = getFilePermission(Integer.parseInt(permissions[2]));
        }

        return permission.contains(operation);
    }

    private static String getFilePermission(int permission) {
        String answer = "";
        switch (permission) {
            case 0:
                answer = "NONE";
                break;

            case 1:
                answer = "X";
                break;

            case 2:
                answer = "W";
                break;

            case 3:
                answer = "WX";
                break;
            case 4:
                answer = "R";
                break;

            case 5:
                answer = "RX";
                break;

            case 6:
                answer = "RW";
                break;
            case 7:
                answer = "RWX";
                break;
        }

        return answer;
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        U = Integer.parseInt(inputLine.nextToken());
        F = Integer.parseInt(inputLine.nextToken());

        groups = new HashMap<>();
        for (int i = 0; i < U; i++) {
            String input = bufferedReader.readLine();
            String[] split = input.split(" ");
            String user = split[0];
            groups.putIfAbsent(user, new HashSet<>());
            groups.get(user).add(user);
            if (split.length > 1) {
                StringTokenizer tokenizer = new StringTokenizer(split[1], ",");
                while (tokenizer.hasMoreTokens()) {
                    groups.get(user).add(tokenizer.nextToken());
                }
            }
        }

        files = new HashMap<>();
        for (int i = 0; i < F; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());
            String fileName = inputLine.nextToken();
            String permission = inputLine.nextToken();
            String owner = inputLine.nextToken();
            String group = inputLine.nextToken();

            files.put(fileName, new File(permission, owner, group));
        }

        Q = Integer.parseInt(bufferedReader.readLine());
    }

    static class File {
        private final String permission;
        private final String owner;
        private final String group;

        public File(String permission, String owner, String group) {
            this.permission = permission;
            this.owner = owner;
            this.group = group;
        }

        public String getPermission() {
            return permission;
        }

        public String getOwner() {
            return owner;
        }

        public String getGroup() {
            return group;
        }
    }

}