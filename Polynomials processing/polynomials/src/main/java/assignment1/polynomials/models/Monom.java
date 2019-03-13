package assignment1.polynomials.models;

public class Monom implements Comparable<Monom> {
	private int coeff;
	private int exp;

	public Monom(int coeff, int exp) {
		this.coeff = coeff;
		this.exp = exp;
	}

	public int getCoeff() {
		return this.coeff;
	}

	public int getExponent() {
		return this.exp;
	}

	public void setCoeff(int newCoeff) {
		this.coeff = newCoeff;
	}

	public int compareTo(Monom otherMonom) {
		return otherMonom.getExponent() - this.getExponent();
	}

}
