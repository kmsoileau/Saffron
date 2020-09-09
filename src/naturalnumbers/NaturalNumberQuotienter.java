package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which implements an divider of two
 * NaturalNumbers.
 *
 * In one way to use this class, one passes INaturalNumber X, INaturalNumber Y,
 * INaturalNumber Z to the appropriate constructor. The NaturalNumberDivider
 * object produced is a Problem, and one may manipulate it using any of the
 * methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <code>Problem p = new NaturalNumberDivider(X,Y,Z);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <code>X == Z * Y</code>
 * </p>
 * .
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 08, 2006
 */
public class NaturalNumberQuotienter extends Problem implements IProblem
{
	public NaturalNumberQuotienter(INaturalNumber Dividend, INaturalNumber Divisor, INaturalNumber Quotient,
			INaturalNumber Remainder) throws Exception
	{
		INaturalNumber Product = new NaturalNumber();

		// Divisor*Quotient==Product
		IProblem p1 = new NaturalNumberMultiplier(Divisor, Quotient, Product);
		// Product+Remainder==Dividend i.e. Divisor*Quotient+Remainder==Dividend
		IProblem p2 = new NaturalNumberAdder(Product, Remainder, Dividend);
		// Remainder<Divisor
		IProblem p3 = new NaturalNumberStrictOrderer(Remainder, Divisor);

		this.setClauses(new Conjunction(p1, p2, p3).getClauses());
	}
}