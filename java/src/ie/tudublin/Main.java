package ie.tudublin;

public class Main
{


	public static void osci() {
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new Oscilloscope());
	}

	public static void main(String[] args)
	{
		System.out.println("Program Begin");
		osci();
		System.out.println("Program End");
	}
}