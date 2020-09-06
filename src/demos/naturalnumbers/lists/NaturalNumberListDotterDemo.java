/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 20, 2019
 */
package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListDotter;
import naturalnumbers.lists.NaturalNumberListFixer;

public class NaturalNumberListDotterDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(100);

		INaturalNumberList list1 = new NaturalNumberList(new INaturalNumber[]
		{ new NaturalNumber(2), new NaturalNumber(1), new NaturalNumber(3),
				new NaturalNumber(2), new NaturalNumber(1),
				new NaturalNumber(5), new NaturalNumber(0),
				new NaturalNumber(2), new NaturalNumber(3) });

		INaturalNumberList list2 = new NaturalNumberList(new INaturalNumber[]
		{ new NaturalNumber(1), new NaturalNumber(2), new NaturalNumber(3),
				new NaturalNumber(4), new NaturalNumber(5),
				new NaturalNumber(6), new NaturalNumber(7),
				new NaturalNumber(8), new NaturalNumber(9) });

		INaturalNumber dotProduct = new NaturalNumber();

		IProblemMessage s = new Conjunction(new NaturalNumberListFixer(list1),
				new NaturalNumberListFixer(list2), new NaturalNumberListDotter(
						list1, list2, dotProduct)).findModel(Problem
				.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("dotProduct = " + dotProduct);
		}
		else
			System.out.println("No solution.");
	}
}