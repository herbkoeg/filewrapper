package de.hk;

import java.io.*;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class MyUtil {


    public static void correctDirectory()  {
//        System.err.println("path: " +
//                Paths.get(".").toAbsolutePath().normalize().toString());

        try (Stream<Path> walk = Files.walk(Paths.get("/Users/berti/IdeaProjects/filewrapper/src/main/resources"))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .filter(file->file.toString().endsWith(".txt"))
                    .map(x -> x.toString()).collect(Collectors.toList());

            for (String filename:result) {
                generateCorrectedFile(filename);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateCorrectedFile(String filename) throws IOException {
        String correctedContent = getCorrectedContent(filename,"de.hk");

        saveFile(correctedContent,filename+".corrected");
    }

    static String getCorrectedContent(String filename, String packagename) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (String line : Files.lines(Paths.get(filename)).collect(Collectors.toList())) {
                sb.append(extendProgramId(line,packagename)).append("\n");
        }

        return sb.toString();
    }

    static String extendProgramId(String line, String packageName) {
        if(line.contains("PROGRAM-ID")) {
            String[] words = line.split(" ");
            return "      PROGRAM-ID\t"+packageName+"."+words[words.length-1];
        } else {
            return line;
        }
    }

    static void saveFile(String content, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
        outStream.writeUTF(content);
        outStream.close();
    }

}
