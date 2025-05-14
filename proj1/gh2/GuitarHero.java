package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import gh2.GuitarString;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString[] guitarStrings = new GuitarString[37];
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for(int i = 0; i < guitarStrings.length; i++){
            guitarStrings[i] = new GuitarString(440 * Math.pow(2, (double) (i - 24) /12));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keyboard.contains(String.valueOf(key))) {
                    guitarStrings[keyboard.indexOf(key)].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for(int i = 0; i < guitarStrings.length; i++){
                sample += guitarStrings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(int i = 0; i < guitarStrings.length; i++){
                guitarStrings[i].tic();
            }
        }
    }
}


