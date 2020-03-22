package de.hk;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class MyUtil {

    public static String readAndExtendProgramId() {

        System.err.println("path: " +
                Paths.get(".").toAbsolutePath().normalize().toString());

        String fileName = "src/main/resources/somefile.txt";

        StringBuilder sb = new StringBuilder();
        ProgamIdExtender pe = new ProgamIdExtender();

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream
                    .map(pe::extendProgramId)
                    .forEach(sb::append);


            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }




        return "";
    }


    static String extendProgramId(String line) {
        if(line.contains("PROGRAM-ID")) {
            String[] words = line.split(" ");
            return words[1];
        } else {
            return line;
        }
    }

}
