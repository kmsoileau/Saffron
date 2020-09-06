/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 25, 2019
 */
package demos.integers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import integers.IInteger;
import integers.Integer;
import integers.IntegerArrayMinner;
import integers.IntegerFixer;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class IntegerArrayMinnerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(8);
		IInteger[] d = new IInteger[]
		{ new Integer(1), new Integer(-3), new Integer(2), new Integer(1) };

		IInteger minIndex = new Integer();
		IInteger minEntry = new Integer();

		IProblemMessage s = new Conjunction(new IntegerFixer(d),
				new IntegerArrayMinner(d, minIndex, minEntry)).findModel(Problem
				.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("minIndex = " + minIndex);
			System.out.println("minEntry = " + minEntry);
		}
		else
			System.out.println("No solution.");
	}
}
