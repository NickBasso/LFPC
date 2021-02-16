package RegularLanguage.Automata;

import RegularLanguage.Grammar.RegularGrammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FiniteStateAutomaton {
    private RegularGrammar grammar = null;

    public FiniteStateAutomaton(RegularGrammar grammar) {
        this.grammar = grammar;
    }

    public boolean isValidWord(String word) {
        HashSet<Character> Vn = grammar.getVn();
        HashSet<Character> Vt = grammar.getVt();
        HashMap<Character, ArrayList<String>> pr = grammar.getProductionRules();

        // Proceed to checking for erroneous transitions
        // for each character one by one

        // not possible to reach first character from starting symbol?
        ArrayList<String> startingTransitions = pr.get('S');

        /* declare j outside the loop, because
               its value is going to be needed after the loop had executed
               to check if all transitions traversed, but no solution found */
        int j = 0;
        char previous = 'S';
        for(; j < startingTransitions.size(); j++) {
            String production = startingTransitions.get(j);

            if(production.charAt(0) == word.charAt(0)) {
                if(production.length() > 1 && word.length() > 1)
                    previous = production.charAt(1);
                else if(production.length() == 1 && word.length() > 1)
                    return false;

                break;
            }
        }
        if(j == startingTransitions.size())
            return false;

        // check rest of the characters after index 0
        for(int i = 1; i < word.length(); i++) {
            ArrayList<String> transitions = pr.get(previous);
            char current = word.charAt(i);

            j = 0;
            for(; j < transitions.size(); j++) {
                String production = transitions.get(j);

                if(production.charAt(0) == current) {
                    if(production.length() > 1 && i < word.length() - 1)
                        previous = production.charAt(1);
                    else if(production.length() == 1 && i < word.length() - 1)
                        return false;
                    else if(production.length() > 1 && i == word.length() - 1)
                        return false;

                    break;
                }
            }
            if(j == transitions.size())
                return false;
        }

        return true;
    }
}
