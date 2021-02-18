package RegularLanguage.Automata;

import Exceptions.EmptyWordInputException;
import RegularLanguage.Grammar.RegularGrammar;

import java.util.ArrayList;
import java.util.HashMap;

public class FiniteStateAutomaton {
    private RegularGrammar grammar = null;
    private HashMap<Character, ArrayList<String>> pr = null;
    private boolean isValidWord = false;

    public FiniteStateAutomaton(RegularGrammar grammar) {
        this.grammar = grammar;
        this.pr = grammar.getProductionRules();
    }

    public boolean isValidWord(String word) throws Exception {
        // check if it's possible to reach first character from starting symbol 'S'
        try {

            ArrayList<String> startingTransitions = pr.get('S');

            // declare j outside the loop, because
            // its value is going to be needed after the loop had executed
            // to check if all transitions traversed, but no solution found
            int j = 0;
            char previous = 'S';
            for (; j < startingTransitions.size(); j++) {
                String production = startingTransitions.get(j);

                if (production.charAt(0) == word.charAt(0) && production.length() > 1 && word.length() > 1) {
                    StringBuilder productionTrace = new StringBuilder("S -> ");
                    productionTrace.append(production + " -> ");
                    checkProduction(word, 1, production.charAt(1), productionTrace);
                } else if(production.charAt(0) == word.charAt(0) && production.length() == 1 && word.length() == 1) {
                    isValidWord = true;
                    System.out.println("S -> " + production.charAt(0));
                }

            }

        } catch (Exception exception) {

            System.err.println(exception);

            if(word.length() == 0)
                System.err.println(new EmptyWordInputException("Empty word input provided, which is illegal!"));

        } finally {
            if(isValidWord == false)
                return false;
            else
                return true;
        }
    }

    private void checkProduction(String word, int index, char from, StringBuilder productionTrace) {
        ArrayList<String> transitions = pr.get(from);
        char current = word.charAt(index);

        int j = 0;
        for(; j < transitions.size(); j++) {
            String production = transitions.get(j);

            if(production.charAt(0) == current) {
                if(production.length() > 1 && index < word.length() - 1) {
                    StringBuilder uniqueProductionTrace = new StringBuilder(productionTrace);
                    uniqueProductionTrace.append(production + " -> ");
                    checkProduction(word, index + 1, production.charAt(1), uniqueProductionTrace);
                }
                else if(production.length() == 1 && index < word.length() - 1)
                    return;
                else if(production.length() > 1 && index == word.length() - 1)
                    return;
                else if(production.length() == 1 && index == word.length() - 1) {
                    setValidWord(true);

                    productionTrace.append(production);
                    System.out.println(productionTrace);

                    return;
                }
            }
        }
    }

    private void setValidWord(boolean state) {
        isValidWord = state;
    }
}
