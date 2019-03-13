package assignment1.polynomials.models;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

	public static Polinom stringToPolinom(String str) {
		Polinom p = new Polinom();
		Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d?\\d?+x\\^\\d?\\d?+)|(?:\\d?\\d?+x)|(?:\\d?\\d?+)|(?:x)))");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			String s = matcher.group(1);
			if (s.matches("([+-]?\\d?\\d?x)")) {
				if (s.equals("x")) {
					p.monoms.add(new Monom(1, 1));
				} else if (s.equals("+x")) {
					p.monoms.add(new Monom(1, 1));
				} else if (s.equals("-x")) {
					p.monoms.add(new Monom(-1, 1));
				} else {
					p.monoms.add(new Monom(Integer.parseInt(s.substring(0, s.length() - 1)), 1));
				}
			}

			if (s.matches("([+-]?\\d\\d?)")) {
				p.monoms.add(new Monom(Integer.parseInt(s), 0));
			}

			if (s.matches("([+-]?\\d?\\d?x\\^[+-]?\\d+$)")) {
				if (s.startsWith("x^")) {
					p.monoms.add(new Monom(1, Integer.parseInt(s.substring(2, s.length()))));
				} else if (s.startsWith("+x^")) {
					p.monoms.add(new Monom(1, Integer.parseInt(s.substring(3, s.length()))));
				} else if (s.startsWith("-x^")) {
					p.monoms.add(new Monom(-1, Integer.parseInt(s.substring(3, s.length()))));
				} else {
					p.monoms.add(new Monom(Integer.parseInt(s.split("x\\^")[0]), Integer.parseInt(s.split("x\\^")[1])));
				}
			}

		}
		Collections.sort(p.monoms);
		int degree = p.getDegree();
		Polinom result = new Polinom();
		while (degree >= 0) {
			int sum = 0;
			for (Monom m : p.monoms) {
				if (m.getExponent() == degree) {
					sum += m.getCoeff();
				}
			}
			if (sum != 0) {
				result.monoms.add(new Monom(sum, degree));
			}
			degree--;
		}
		return result;
	}

	public static Polinom addPolinoms(Polinom p1, Polinom p2) {
		Polinom result = new Polinom();
		int degree, sum;
		if (p1.getDegree() > p2.getDegree()) {
			degree = p1.getDegree();
		} else {
			degree = p2.getDegree();
		}

		while (degree >= 0) {
			sum = 0;
			for (Monom m : p1.monoms) {
				if (m.getExponent() == degree) {
					sum += m.getCoeff();
				}
			}

			for (Monom m : p2.monoms) {
				if (m.getExponent() == degree) {
					sum += m.getCoeff();
				}
			}

			if (sum != 0) {
				result.monoms.add(new Monom(sum, degree));
			}
			degree--;
		}
		return result;
	}

	public static Polinom subtractPolinoms(Polinom p1, Polinom p2) {
		Polinom result = new Polinom();
		Polinom neg = p2;
		for (Monom m : neg.monoms) {
			m.setCoeff(m.getCoeff() * -1);
		}
		result = Operations.addPolinoms(p1, neg);
		return result;
	}

	public static Polinom multiplyPolinoms(Polinom p1, Polinom p2) {
		Polinom p = new Polinom();
		for (Monom m1 : p1.monoms) {
			for (Monom m2 : p2.monoms) {
				p.monoms.add(new Monom(m1.getCoeff() * m2.getCoeff(), m1.getExponent() + m2.getExponent()));
			}
		}
		Collections.sort(p.monoms);
		int degree = p.getDegree();
		Polinom result = new Polinom();
		while (degree >= 0) {
			int sum = 0;
			for (Monom m : p.monoms) {
				if (m.getExponent() == degree) {
					sum += m.getCoeff();
				}
			}
			if (sum != 0) {
				result.monoms.add(new Monom(sum, degree));
			}
			degree--;
		}
		return result;
	}

	public static Polinom derivatePolinom(Polinom p) {
		Polinom result = new Polinom();
		for (Monom m : p.monoms) {
			if (m.getExponent() - 1 >= 0)
				result.monoms.add(new Monom(m.getExponent() * m.getCoeff(), m.getExponent() - 1));
		}
		return result;
	}

	public static String polinomToString(Polinom p) {
		String result = "";
		for (Monom m : p.monoms) {
			if (m.getExponent() == 0) {
				if (m.getCoeff() > 0) {
					result += "+" + m.getCoeff();
				} else {
					result += m.getCoeff();
				}

			} else if (m.getExponent() == 1) {
				if (m.getCoeff() > 0) {
					if (m.getCoeff() == 1) {
						result += "+x";
					} else {
						result += "+" + m.getCoeff() + "x";
					}
				} else {
					if (m.getCoeff() == -1) {
						result += "-x";
					} else {
						result += m.getCoeff() + "x";
					}
				}

			} else {
				if (m.getCoeff() > 0) {
					if (m.getCoeff() == 1) {
						result += "+x^" + m.getExponent();
					} else {
						result += "+" + m.getCoeff() + "x^" + m.getExponent();
					}
				} else {
					if (m.getCoeff() == -1) {
						result += "-x^" + m.getExponent();
					} else {
						result += m.getCoeff() + "x^" + m.getExponent();
					}

				}
			}
		}
		if (result.startsWith("+")) {
			result = result.substring(1, result.length());
		}
		if (result.equals(""))
			result="0";
		return result;
	}

}
