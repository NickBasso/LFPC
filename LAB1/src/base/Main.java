package base;

import RegularLanguage.Automata.FiniteStateAutomaton;
import RegularLanguage.Grammar.RegularGrammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        // An object to read & extract grammar given an input file
        RegularGrammar gr = new RegularGrammar();
        // pr - production rules
        HashMap<Character, ArrayList<String>> pr = new HashMap<>(gr.getProductionRules());
        // vt - set of terminal symbols, vn - set of non-terminal symbols
        HashSet<Character> vt = new HashSet<>(gr.getVt()), vn = new HashSet<>(gr.getVn());
        // print the non-terminals, terminals and production rules
        System.out.println(vn + "\n" + vt + "\n" + pr);

        FiniteStateAutomaton fsa = new FiniteStateAutomaton(gr);
        System.out.println(fsa.isValidWord("abbcbbcb"));
    }
}
