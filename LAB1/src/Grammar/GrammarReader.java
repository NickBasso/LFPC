package Grammar;

import Exceptions.WrongProductionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class GrammarReader {
    // ------------------------ Fields ------------------------
    private String inputPath = "src/Grammar/GrammarInput.txt";
    private String input = "";
    // pr - production rules
    private HashMap<Character, String> pr = new HashMap<>();
    // vt - set of terminal symbols, vn - set of non-terminal symbols
    private HashSet<Character> vt = new HashSet<>(), vn = new HashSet<>();

    // ------------------------ Constructors ------------------------

    public GrammarReader() throws WrongProductionException {
        readGrammar();
    }

    public GrammarReader(String inputPath) throws WrongProductionException {
        this.inputPath = inputPath;
        readGrammar();
    }

    // ------------------------ Read & Compute ------------------------

    private void readGrammar() throws WrongProductionException{
        readInput();
        extractVn();
        extractVt();
        extractPR();

        int i = 0;

        // read && save non-terminal symbols
        if(input.charAt(i) == 'V' && input.charAt(i + 1) == 'N' && input.charAt(i + 2) == '=' && input.charAt(i + 3) == '{') {
            i += 4;

            while(input.charAt(i) != ',') {
                vn.add(input.charAt(i));
                i+=2;
            }

            i += 2;
        }

        // read && save terminal symbols
        if(input.charAt(i) == 'V' && input.charAt(i + 1) == 'T' && input.charAt(i + 2) == '=' && input.charAt(i + 3) == '{') {
            i += 4;

            while(input.charAt(i) != ',') {
                vt.add(input.charAt(i));
                i += 2;
            }

            i += 2;
        }

        // read && save production rules
        if(input.charAt(i) == 'P' && input.charAt(i + 1) == '=' && input.charAt(i + 2) == '{') {

            while(Character.isDigit(input.charAt(i)) && input.charAt(i + 1) == '.') {
                Character from;
                String to;

                i += 2;

                if(vn.contains(input.charAt(i)) && !vt.contains(input.charAt(i)) && input.charAt(i + 1) == '-'){
                    from = input.charAt(i);
                    i += 3;
                }
                else
                    throw new WrongProductionException();

                StringBuilder sb = new StringBuilder("");

                if(!vn.contains(input.charAt(i)) && vt.contains(input.charAt(i))) {
                    sb.append(input.charAt(i));
                    i++;

                    if(input.charAt(i) != '\n' && vn.contains(input.charAt(i)) && !vt.contains(input.charAt(i))){
                        sb.append(input.charAt(i + 1));
                        i += 2;
                    }
                    else
                        throw new WrongProductionException();

                    to = sb.toString();

                    pr.putIfAbsent(from, to);
                }
                else
                    throw new WrongProductionException();

            }
        } else
            throw new WrongProductionException("Start of Production rules' section not found,\nshould be \"P={\n1. ...\n}");


        System.out.println(vn);
        System.out.println(vt);
        System.out.println(pr);

    }

    private void readInput() {
        try {

            File file = new File(inputPath);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine())
                input += myReader.nextLine() + "\n";

            StringBuilder shrinkedInput = new StringBuilder("");

            for(int i = 0; i < input.length(); i++)
                if(input.charAt(i) != ' ')
                    shrinkedInput.append(input.charAt(i));

            input = shrinkedInput.toString();

            myReader.close();
            System.out.println(input);

        } catch (FileNotFoundException e) {
            System.err.println("An error occurred when trying to access file at specified direction.");
            e.printStackTrace();
        }
    }

    private void extractVn() {

    }

    private void extractVt() {

    }

    private void extractPR() {

    }

    // ------------------------ Getters ------------------------

    public HashMap<Character, String> getProductionRules() {


        return pr;
    }

    public HashSet<Character> getVt() {
        return vt;
    }

    public HashSet<Character> getVn() {
        return vn;
    }
}
