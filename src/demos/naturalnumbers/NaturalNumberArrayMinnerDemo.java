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
import naturalnumbers.NaturalNumberArrayMinner;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class NaturalNumberArrayMinnerDemo extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(10);

		INaturalNumber[] d = new INaturalNumber[]
		{ new NaturalNumber(2), new NaturalNumber(1), new NaturalNumber(3), new NaturalNumber(2), new NaturalNumber(1),
				new NaturalNumber(5), new NaturalNumber(0), new NaturalNumber(2), new NaturalNumber(3) };

		INaturalNumber minIndex = new NaturalNumber();
		INaturalNumber minEntry = new NaturalNumber();

		IProblemMessage s = new Conjunction(new NaturalNumberFixer(d),
				new NaturalNumberArrayMinner(d, minIndex, minEntry)).findModel(Problem.defaultSolver());
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
