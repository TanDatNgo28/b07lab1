class Polynomial {
	double[]coeff;
	// constructor of no argument
	public Polynomial () {
		coeff = new double[] {0};
	}
	// constructor takes an array of double
	public Polynomial (double[]array) {
		coeff = new double[array.length];
		for (int i=0; i< array.length; i++) {
			coeff[i]=array[i];
		}
	}
	// add method
	Polynomial add (Polynomial p) {
		Polynomial res = new Polynomial();
		if (this.coeff.length > p.coeff.length){
			res.coeff = new double[this.coeff.length];
			for (int k=0; k < this.coeff.length; k++) {
				res.coeff[k]= this.coeff[k];
			}
			for (int i=0; i < p.coeff.length; i++) {
				res.coeff[i]+= p.coeff[i];
			}
		}
		else {
			res.coeff = new double[p.coeff.length];
			for (int k=0; k < p.coeff.length; k++) {
				res.coeff[k]= p.coeff[k];
			}
			for (int i=0; i < this.coeff.length; i++) {
				res.coeff[i]+= this.coeff[i];
			}
		}
		return res;
	}
	// evaluate method
	double evaluate (double x) {
		double result=0.0;
		for (int i=0; i < (this.coeff).length; i++) {
			if (i==0) {
				result += this.coeff[i];
			}
					
			else {
				result += this.coeff[i] * Math.pow(x, i);
			}
		}
		return result;
	}
	// hasRoot method
	boolean hasRoot (double r) {
		return this.evaluate(r)==0;
	}
}