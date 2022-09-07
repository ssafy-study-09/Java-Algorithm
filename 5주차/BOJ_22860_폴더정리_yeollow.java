import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int Q;
    static Tree main;
    static List<String> fileList;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            for (int i = 0; i < Q; i++) {
                String directory = bufferedReader.readLine();

                fileList = new LinkedList<>();
                main.print(main, directory);
                System.out.println(fileList.stream().distinct().count() + " " + fileList.size());
            }
        }
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(inputLine.nextToken());
        M = Integer.parseInt(inputLine.nextToken());

        inputLine = new StringTokenizer(bufferedReader.readLine());
        String parent = inputLine.nextToken();
        String child = inputLine.nextToken();
        int isDirectory = Integer.parseInt(inputLine.nextToken());
        main = new Tree(parent);
        main.insert(main, parent, child, isDirectory == 1 ? true : false);
        for (int i = 0; i < N + M - 1; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());
            parent = inputLine.nextToken();
            child = inputLine.nextToken();
            isDirectory = Integer.parseInt(inputLine.nextToken());

            main.insert(main, parent, child, isDirectory == 1 ? true : false);
        }
        Q = Integer.parseInt(bufferedReader.readLine());
    }

    static class Tree {

        private String name;
        private Set<String> files = new HashSet<>();
        private Map<String, Tree> directory = new HashMap<>();


        public Tree(String name) {
            this.name = name;
        }

        public void print(Tree node, String directory) {
            String[] pwd = directory.split("/");
            for (int i = 1; i < pwd.length; i++) {
                node = node.directory.get(pwd[i]);
            }

            doQuery(node);
        }

        private void doQuery(Tree node) {
            fileList.addAll(node.files);
            if (node.directory.size() == 0) {
                return;
            }

            for (Map.Entry<String, Tree> entry : node.directory.entrySet()) {
                doQuery(entry.getValue());
            }
        }

        public void insert(Tree node, String parent, String child, boolean isDirectory) {
            if (node.name.equals(parent)) {
                if (isDirectory) {
                    node.directory.putIfAbsent(child, new Tree(child));

                    return;
                }
                node.files.add(child);
                return;
            }

//           parent 위치를 찾는 것이 목표. -> 찾고 나서 이후 계속 찾아본다..
            for (Map.Entry<String, Tree> entry : node.directory.entrySet()) {
                insert(entry.getValue(), parent, child, isDirectory);
            }
        }
    }

}