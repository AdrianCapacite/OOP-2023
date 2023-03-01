package ie.tudublin;

public class Main
{
	static void perlinLines() {
		String a = "MAIN";
		processing.core.PApplet.runSketch(new String[] {a}, new PerlinLines());
	}
	static void base(){
		String a = "MAIN";
		processing.core.PApplet.runSketch(new String[] {a}, new Base());
	}
	public static void main(String[] args)
	{
		System.out.println("Program Started");
		// base();
		perlinLines();
		System.out.println("Program Ended");
	}
}