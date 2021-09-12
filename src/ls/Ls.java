package ls;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Ls {

    private final boolean longFlag;
    private final boolean humanReadable;
    private final boolean reverseFlag;
    private final String output;

    public Ls(boolean longFlag, boolean humanReadable, boolean reverseFlag, String output) {
        this.longFlag = longFlag;
        this.humanReadable = humanReadable;
        this.reverseFlag = reverseFlag;
        this.output = output;
    }

    public void start(String path) throws IOException {
        List<String> answer = new ArrayList<>();
        File object = new File(path);

        if (object.isDirectory()) {
            File[] objectsList = object.listFiles();
            if (Objects.requireNonNull(objectsList).length == 0) {
                return;
            }
            Arrays.sort(objectsList);

            for (File file : objectsList) {
                fileFeature(answer, file);
            }
        } else {
            fileFeature(answer, object);
        }

        if (reverseFlag) {
            Collections.reverse(answer);
        }
        if (output != null) {
            output(answer);
        } else {
            answer.forEach(System.out::println);
        }
    }

    private String objectSize(BasicFileAttributes attr) {
        String size = String.valueOf(attr.size());
        if (humanReadable) {
            size = FileUtils.byteCountToDisplaySize(attr.size());
        }
        return size;
    }

    private void output(List<String> list) throws IOException {
        try (FileWriter writer = new FileWriter(output)) {
            list.forEach(element -> {
                try {
                    writer.write(element + " \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void fileFeature(List<String> result, File file) throws IOException {
        if (longFlag) {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            String canRead;
            String canWrite;
            String canExecute;

            if (humanReadable) {
                if (file.canRead()) {
                    canRead = "r";
                } else {
                    canRead = "-";
                }
                if (file.canWrite()) {
                    canWrite = "w";
                } else {
                    canWrite = "-";
                }
                if (file.canExecute()) {
                    canExecute = "x";
                } else {
                    canExecute = "-";
                }
            } else {
                if (file.canRead()) {
                    canRead = "1";
                } else {
                    canRead = "0";
                }
                if (file.canWrite()) {
                    canWrite = "1";
                } else {
                    canWrite = "0";
                }
                if (file.canExecute()) {
                    canExecute = "1";
                } else {
                    canExecute = "0";
                }
            }
            result.add(canRead + canWrite + canExecute + "  " + attr.lastModifiedTime() + "  " + objectSize(attr) + "  " + file.getName());
        } else {
            result.add(file.getName());
        }
    }

}