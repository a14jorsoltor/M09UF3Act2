import java.util.concurrent.Callable;

public class KKThread extends Thread implements Callable<String> {

    private final String theInput;

    public KKThread(String theInput) {
        this.theInput = theInput;
    }

    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
    private static final int NUMJOKES = 5;
    private int state = WAITING;
    private int currentJoke = 0;
    private String[] frase1 = {"Qui és?", "¿Quien es?", "Who is?"};
    private String[] frase2 = {"Advocat", "Thomas", "Atch"};
    private String[] frase3 = {"Quin advocat?", "¿Qué Thomas?", "Atch who?"};
    private String[] frase4 = {"El que tinc aquí penjat!", "Yo un cubata, ¿y tú?", "Bless you!"};

    @Override
    public String call() throws Exception {


        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
                        "Try again. Knock! Knock!";
            }

        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + clues[currentJoke] +
                        " who?\"" + "! Try again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }

}





