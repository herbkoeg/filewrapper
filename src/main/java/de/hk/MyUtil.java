package de.hk;

import de.hk.my_test.SomeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class MyUtil {

    static SomeClass sc = new SomeClass();

    public static String readAndExtendProgramId() throws IOException{
        System.err.println("path: " +
                Paths.get(".").toAbsolutePath().normalize().toString());

        String filename = "src/main/resources/somefile.txt";

        return correctFile(filename,"de.hk");

    }

    static String[] getFiles(String path) {
        return null;
    }

    static void saveFile(String content, String filename) {

    }

    static String correctFile(String filename, String packagename) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (String line : Files.lines(Paths.get(filename)).collect(Collectors.toList())) {
                sb.append(extendProgramId(line,packagename)).append("\n");
        }

        return sb.toString();

    }

    static String extendProgramId(String line, String packageName) {
        if(line.contains("PROGRAM-ID")) {
            String[] words = line.split(" ");
            return "PROGRAM-ID\t"+packageName+words[words.length-1];
        } else {
            return line;
        }
    }

}
