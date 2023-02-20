package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Star {
    private boolean hab; // 1 high prob. 0 low prob.
    private String displayName;
    private float distance;
    private float Xg;
    private float Yg;
    private float Zg;
    private float absMag;

    public Star(boolean hab, String displayName, float distance, float Xg, float Yg, float Zg, float absMag) {
        this.hab = hab;
        this.displayName = displayName;
        this.distance = distance;
        this.Xg = Xg;
        this.Yg = Yg;
        this.Zg = Zg;
        this.absMag = absMag;
    }

    // Constructor for CSV
    public Star(TableRow row) {
        // Constructor chaining
        this(
            row.getInt("Hab?") == 1, // Is this star habitable?
            row.getString("Display Name"), // The name of the star
            row.getFloat("Distance"), // Distance from Sol
            row.getFloat("Xg"), // X coordinate from Sol
            row.getFloat("Yg"), // Y coordinate from Sol
            row.getFloat("Zg"), // Z coordinate from Sol
            row.getFloat("AbsMag") // Absolute magnitude
        );
    }

    // Getters & Setters
    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the distance from Sol
     */
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getXg() {
        return Xg;
    }

    public void setXg(float Xg) {
        this.Xg = Xg;
    }

    public float getYg() {
        return Yg;
    }

    public void setYg(float Yg) {
        this.Yg = Yg;
    }

    public float getZg() {
        return Zg;
    }

    public void setZg(float Zg) {
        this.Zg = Zg;
    }

    public float getAbsMag() {
        return absMag;
    }

    public void setAbsMag(float absMag) {
        this.absMag = absMag;
    }

    @Override
    public String toString() {
        return "Star [hab=" + hab + ", displayName=" + displayName + ", distance=" + distance + ", Xg=" + Xg + ", Yg="
                + Yg + ", Zg=" + Zg + ", absMag=" + absMag + "]";
    }

    /**
     * Calculate the distance to another star
     * @param other The other star to calculate the distance to
     * @return The distance to the other star
     */
    public float distanceTo(Star other) {
        return PApplet.dist(Xg, Yg, Zg, other.Xg, other.Yg, other.Zg);
    }


    public void render(PApplet p)
    {
        float border = p.width * 0.1f;
        // A static method is called using the class name, not an instance
        float x = PApplet.map(Xg, -5, 5, border, p.width - border);
        float y = PApplet.map(Yg, -5, 5, border, p.height - border);

        p.stroke(255,255,0);
        p.line(x, y-5, x, y+5);
        p.line(x-5, y, x+5, y);

        p.stroke(255,0,0);
        p.circle(x, y, absMag);
        p.fill(255);

        p.textAlign(PApplet.LEFT, PApplet.CENTER);
        p.text(displayName, x + 10, y);

        p.noFill();
    }
}
