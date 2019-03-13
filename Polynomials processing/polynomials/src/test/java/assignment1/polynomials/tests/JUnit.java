package assignment1.polynomials.tests;

import org.junit.jupiter.api.Test;
import assignment1.polynomials.models.Operations;
import assignment1.polynomials.models.Polinom;
import junit.framework.TestCase;

public class JUnit extends TestCase {

	@Test
	public void testAdd() {
		Polinom p1 = Operations.stringToPolinom("x^3-7x^2+2x+1");
		Polinom p2 = Operations.stringToPolinom("x^4+2x^3-8");
		Polinom p = Operations.addPolinoms(p1, p2);
		Polinom expectedResult = Operations.stringToPolinom("x^4+3x^3-7x^2+2x-7");
		for (int i = 0; i < 5; i++) {
			assertEquals(p.monoms.get(i).getCoeff(), expectedResult.monoms.get(i).getCoeff());
			assertEquals(p.monoms.get(i).getExponent(), expectedResult.monoms.get(i).getExponent());
		}
	}

	@Test
	public void testSub() {
		Polinom p1 = Operations.stringToPolinom("x^3-7x^2+2x+1");
		Polinom p2 = Operations.stringToPolinom("x^4+2x^3-8");
		Polinom p = Operations.subtractPolinoms(p1, p2);
		Polinom expectedResult = Operations.stringToPolinom("-x^4-x^3-7x^2+2x+9");
		for (int i = 0; i < 5; i++) {
			assertEquals(p.monoms.get(i).getCoeff(), expectedResult.monoms.get(i).getCoeff());
			assertEquals(p.monoms.get(i).getExponent(), expectedResult.monoms.get(i).getExponent());
		}
	}
	
	@Test
	public void testMul() {
		Polinom p1 = Operations.stringToPolinom("x^3-7x^2+2x+1");
		Polinom p2 = Operations.stringToPolinom("x^4+2x^3-8");
		Polinom p = Operations.multiplyPolinoms(p1, p2);
		Polinom expectedResult = Operations.stringToPolinom("x^7-5x^6-12x^5+5x^4-6x^3+56x^2-16x-8");
		for (int i = 0; i < 8; i++) {
			assertEquals(p.monoms.get(i).getCoeff(), expectedResult.monoms.get(i).getCoeff());
			assertEquals(p.monoms.get(i).getExponent(), expectedResult.monoms.get(i).getExponent());
		}
	}
	
	@Test
	public void testDer() {
		Polinom p1 = Operations.stringToPolinom("x^3-7x^2+2x+1");
		Polinom p = Operations.derivatePolinom(p1);
		Polinom expectedResult = Operations.stringToPolinom("3x^2-14x+2");
		for (int i = 0; i < 3; i++) {
			assertEquals(p.monoms.get(i).getCoeff(), expectedResult.monoms.get(i).getCoeff());
			assertEquals(p.monoms.get(i).getExponent(), expectedResult.monoms.get(i).getExponent());
		}
	}

}
