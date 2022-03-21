import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KKThread extends Thread implements Runnable {

    public KKThread(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;


        private String idioma;
        private static final int WAITING = 0;
        private static final int SENTKNOCKKNOCK = 1;
        private static final int SENTCLUE = 2;
        private static final int ANOTHER = 3;

        private static final int NUMJOKES = 5;

        private int state = WAITING;
        private int currentJoke = 0;

        private String[] clues = {"Atch", "Thomas", "Advocat"};
        private String[] answers = {"Bless you!",
                "Yo un cubata, ¿y tú?",
                "El que tinc aquí penjat!"};

        public String processInput (String theInput){
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                idioma = "ENG";
                state = SENTCLUE;
            } else if(theInput.equalsIgnoreCase("Qui es? ")){
                theOutput = clues[currentJoke];
                idioma = "CAT";
                state = SENTCLUE;
            }else if (theInput.equalsIgnoreCase("Quien es? ")){
                theOutput = clues[currentJoke];
                idioma = "ESP";
                state = SENTCLUE;
            }else {
                state = SENTKNOCKKNOCK;
                if(idioma.equals("ENG"))
                theOutput = "You're supposed to say \"Who's there?\"! " +
                        "Try again. Knock! Knock!";
                if(idioma.equals("ESP"))
                    theOutput = "Capullo tienes que decir \"Quien es?\"! " +
                            "Pruebe de nuevo, Knock! Knock!";
                if(idioma.equals("ENG"))
                    theOutput = "Estupid ha de dir \"Qui es? \"! " +
                            "Torni a probar. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[1])&& idioma.equals("ENG")) {
                theOutput = answers[1] + " Want another? (y/n)";
                state = ANOTHER;
            } else if (theInput.equalsIgnoreCase(clues[2])&& idioma.equals("ESP")) {
                theOutput = answers[2] + " Quiere otro? (y/n)";
                state = ANOTHER;


            }else if (theInput.equalsIgnoreCase(clues[3])&& idioma.equals("CAT")) {
                theOutput = answers[3] + " Vols un altre? (y/n)";
                state = ANOTHER;
            }else {
                theOutput = "You're supposed to say \"" +
                        clues[currentJoke] +
                        " who?\"" +
                        "! Try again. Knock! Knock!";
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



    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        ) {
            String ouputLine = "";
            ouputLine = processInput(in.readLine());
            out.println(ouputLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
