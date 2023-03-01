package ie.tudublin;

public class PitchSpeller {
    // Variables
    // Lowest note: C0 16.35Hz
    // Multiplier: 1.0594630943592953
    public char spell(float freq) {
        float indexF = (float) (Math.log(freq / 16.35) / Math.log(1.0594630943592953));
        int index = (int) Math.round(indexF) % 12;
        return (char) (index + 65);
    }
}
