package base;

import Exceptions.WrongProductionException;
import Grammar.GrammarReader;

import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        GrammarReader gr = new GrammarReader();

        // pr - production rules
        HashMap<String, String> pr = new HashMap<>();
        // vt - set of terminal symbols, vn - set of non-terminal symbols
        HashSet<String> vt = new HashSet<>(), vn = new HashSet<>();

        // PR.putIfAbsent();

    }
}
