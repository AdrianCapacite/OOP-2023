package ie.tudublin;

public class Main
{
	public static void osci() {
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new Oscilloscope());
	}

	static void base(){
		String a = "MAIN";
		processing.core.PApplet.runSketch(new String[] {a}, new Base());
	}

	public static void main(String[] args)
	{
		System.out.println("Program Started");
		base();
		System.out.println("Program Ended");
	}
}