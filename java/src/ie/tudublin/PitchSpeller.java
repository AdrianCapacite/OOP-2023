package ie.tudublin;

public class PitchSpeller {
    // Variables
    // Lowest note: C0 16.35Hz
    float C0 = 16.35f;
    float coeff = 1.0594630943592953f;
    String[] noteNames = {"C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"};

    /**
     * Get position of note in musical frequency range
     * noteIndex = log2(freq / C0) / log2(coeff)
     * @param freq
     * @return
     */
    public int getNote(float freq) {
        return (int) Math.round((Math.log(freq / C0) / Math.log(coeff)));
    }

    public String spell(float freq) {
        int note = getNote(freq);

        if (note < 0) {
            return "Out of range";
        }
        return noteNames[note % 12];
    }
}
