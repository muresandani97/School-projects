package assignment1.polynomials.models;

import java.util.ArrayList;

public class Polinom {
	public ArrayList<Monom> monoms;

	public Polinom() {
		this.monoms = new ArrayList<Monom>();
	}

	public Polinom(ArrayList<Monom> monoms) {
		this.monoms = monoms;
	}

	public int getDegree() {
		return this.monoms.get(0).getExponent();
	}

}
