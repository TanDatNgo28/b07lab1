import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String[]args) throws IOException {
		// add
		double[] c1 = new double[] {2, 3};
		int[] e1 = new int[] {0, 1};
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = new double[] {4, 5};
		int[] e2 = new int[] {1, 2};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial p3 = p1.add(p2);
		System.out.println("This is polynomial p3 evaluated at x = 2");
		System.out.println(p3.evaluate(2)); // expect 36
		
		double[] ca = new double[] {2, 3};
		int[] ea = new int[] {1, 2};
		double[] cb = new double[] {-2, 5};
		int[] eb = new int[] {1, 3};
 		Polynomial a = new Polynomial(ca, ea);
		Polynomial b = new Polynomial(cb, eb);
		Polynomial c = a.add(b);
		System.out.println("This is polynomial c evaluated at x = 3");
		System.out.println(c.evaluate(3)); // expect 162
		
		// multiply
		double[] c4 = new double[] {2, 3};
		int[] e4 = new int[] {0, 1};
		Polynomial p4 = new Polynomial(c4, e4);
		double[] c5 = new double[] {4, 5};
		int[] e5 = new int[] {1, 2};
		Polynomial p5 = new Polynomial(c5, e5);
		Polynomial p6 = p4.multiply(p5);
		System.out.println("This is polynomial p6 evaluated at x = 2");
		System.out.println(p6.evaluate(2)); //expect 224
		
		// Polynomial takes file
		File f = new File("/Users/ngotandat/test_poly/poly_take.txt");
		Polynomial p7 = new Polynomial(f);
		System.out.println("This is polynomial p7 evaluated at x = 2");
		System.out.println(p7.evaluate(2)); //expected 1785
		
		// saveToFile
		double[] c8 = new double[] {3, -2, 5, -1};
		int[] e8 = new int[] {0, 1, 2, 3};
		Polynomial p8 = new Polynomial(c8, e8);
		p8.saveToFile("/Users/ngotandat/test_poly/poly_receive.txt"); // expect "3-2x1+5x2-1x3" poly_receive
	}
}