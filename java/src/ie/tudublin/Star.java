package ie.tudublin;

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
            row.getInt("Hab?") == 1,
            row.getString("Display Name"),
            row.getFloat("Distance"),
            row.getFloat("Xg"),
            row.getFloat("Yg"),
            row.getFloat("Zg"),
            row.getFloat("AbsMag")
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
        return ("Star [absMag=" + absMag + ", displayName=" + displayName + ", distance=" + distance + ", hab="
                + hab + ", Xg=" + Xg + ", Yg=" + Yg + ", Zg=" + Zg + "]");
    }


}
