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
package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberArrayMaxer;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class NaturalNumberArrayMaxerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		// NaturalNumber.setLargestNaturalNumber(10);

		// INaturalNumber[] d = new INaturalNumber[]
		// { new NaturalNumber(2), new NaturalNumber(1), new NaturalNumber(3),
		// new NaturalNumber(2), new NaturalNumber(1),
		// new NaturalNumber(5), new NaturalNumber(0),
		// new NaturalNumber(2), new NaturalNumber(3) };

		NaturalNumber.setLargestNaturalNumber(8);
		INaturalNumber[] d = new INaturalNumber[]
		{ new NaturalNumber(1), new NaturalNumber(0), new NaturalNumber(2), new NaturalNumber(1) };

		INaturalNumber maxIndex = new NaturalNumber();
		INaturalNumber maxEntry = new NaturalNumber();

		IProblemMessage s = new Conjunction(new NaturalNumberFixer(d),
				new NaturalNumberArrayMaxer(d, maxIndex, maxEntry)).findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("maxIndex = " + maxIndex);
			System.out.println("maxEntry = " + maxEntry);
		}
		else
			System.out.println("No solution.");
	}
}
