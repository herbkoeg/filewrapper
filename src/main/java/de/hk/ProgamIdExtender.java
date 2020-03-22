package de.hk;

public class ProgamIdExtender {



    public String extendProgramId(String line) {
        if(line.contains("PROGRAM-ID")) {
            String[] words = line.split(" ");
            return "PROGRAM-ID\t" + "jawoi." + words[words.length-1];
           // return words[1];
        } else {
            return line;
        }
    }

}
