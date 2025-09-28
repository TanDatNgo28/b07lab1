import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Polynomial {
	// field of non-zero coefficients
	double[]coeff;
	// field of exponents
	int[]exp;
	// constructor takes no argument
	public Polynomial() {
		coeff = new double[0];
		exp = new int[0];
	}
	// constructor takes an array of coefficients and an array of exponents
	public Polynomial(double[]c, int[]e) {
		this.coeff = c;
		this.exp = e;
	}
	// constructor takes a file
	public Polynomial(File f) throws IOException {
		// parseInt, parseDouble, split
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String p = reader.readLine();
		String regex = "(?=[+-])"; //split just before +/-, ?= positve lookahead
		String[] strArray = p.split(regex);
		// For testing purpose
		/*for (String s:strArray) {
			System.out.println(s);
		}*/
		// parseDouble for coeff., parseInt for exp.
		this.coeff = new double[strArray.length];
		this.exp = new int[strArray.length];
		for (int i=0; i<strArray.length; i++) {
			// For constant
			if (strArray[i].length() == 1) {
				this.coeff[i] = Double.parseDouble(strArray[i]);
			}
			// For coefficients and exponents
			else {
				String[] both = strArray[i].split("[x]"); //split the coefficent and the exponent
				this.coeff[i] = Double.parseDouble(both[0]); // 1st element is coefficient
				this.exp[i] = Integer.parseInt(both[1]); // 2nd element is exponent
			}
		}
	}
	// add method
	Polynomial add(Polynomial p) {
		// Hashmap of (exp,coeff)
		HashMap<Integer, Double> res = new HashMap<>();
		// insert all coeff and exp of the first polynomial
		for (int i =0; i<this.coeff.length; i++) {
			res.put(this.exp[i], this.coeff[i]);
		}
		// insert all coeff and exp of the second polynomial
		// if exp exists, add the new and old coeff instead
		for (int j=0; j<p.coeff.length; j++) {
			if (res.containsKey(p.exp[j])) {
				// if the sum of coeffs is non-zero, add
				if (p.coeff[j] + res.get(p.exp[j]) != 0.0) {
					res.replace(p.exp[j], res.get(p.exp[j]) + p.coeff[j]);
				}
				// if zero, remove
				else res.remove(p.exp[j]);
			}
			else res.put(p.exp[j], p.coeff[j]);
		}
		// put back to array
		double[] coeff = new double[res.size()];
		int[] exp = new int[res.size()];
		int k = 0;
		for (Integer e:res.keySet()) {
			coeff[k] = res.get(e);
			exp[k] = e;
			k++;
		}
		return new Polynomial(coeff, exp);
	}
	// multiply method
	Polynomial multiply(Polynomial p) {
		// Hasmap of (exp,coeff)
		HashMap <Integer, Double> res = new HashMap<>();
		// for every coeff of this, multiply it with each coeff of p
		// for every exp of this, add it with each exp of p
		for (int i=0; i<p.coeff.length; i++) {
			for (int j=0; j<this.coeff.length; j++) {
				Integer exp = this.exp[j] + p.exp[i];
				if (res.containsKey(exp)) {
					res.replace(exp, res.get(exp) + (this.coeff[j] * p.coeff[i]));
				}
				else res.put(exp, this.coeff[j] * p.coeff[i]);
			}
		}
		// put back to array
		double[] coeff = new double[res.size()];
		int[] exp = new int[res.size()];
		int k = 0;
		for (Integer e:res.keySet()) {
			coeff[k] = res.get(e);
			exp[k] = e;
			k++;
		}
		return new Polynomial(coeff, exp);
	}
	// evaluate method
	double evaluate(double x) {
		double result = 0.0;
		for (int i=0; i < this.coeff.length; i++) {
			result += (this.coeff[i] * Math.pow(x, this.exp[i]));
		}
		return result;
	}
	// hasRoot method
	boolean hasRoot(double r) {
		return this.evaluate(r)==0.0;
	}
	// saveToFile method
	void saveToFile(String fname) throws IOException {
		// Convert the polynomial in the file to a string text like "5-3x2+7x8"
		String poly = "";
		// add the constant of the polynomial first (if have)
		if (this.exp[0]==0) {
			poly = poly.concat(String.valueOf((int)this.coeff[0]));
		}
		// format: "coeff" + "x" + "exp"
		for (int i=1; i<this.coeff.length; i++) {
			String coeff = String.valueOf((int)this.coeff[i]);
			String exp = String.valueOf(this.exp[i]);
			if (this.coeff[i]>0) {
				String term = "+" + coeff + "x" + exp;
				poly = poly.concat(term);
			}
			else {
				String term = coeff + "x" + exp;
				poly = poly.concat(term);
			}
		}
		BufferedWriter text = new BufferedWriter(new FileWriter(fname));
		text.write(poly);
		text.close();
	}
}