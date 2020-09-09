/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 22, 2019
 */
package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.BitArrayTotaler;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListSearcher;

/**
 * 
 *
 */
public class NaturalNumberListSearcherDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(50);

		INaturalNumberList data = new NaturalNumberList(new INaturalNumber[]
		{ new NaturalNumber(32), new NaturalNumber(6), new NaturalNumber(41), new NaturalNumber(41),
				new NaturalNumber(27), new NaturalNumber(3), new NaturalNumber(31), new NaturalNumber(49),
				new NaturalNumber(31), new NaturalNumber(48), new NaturalNumber(34), new NaturalNumber(27),
				new NaturalNumber(13), new NaturalNumber(27), new NaturalNumber(21), new NaturalNumber(42),
				new NaturalNumber(38), new NaturalNumber(0), new NaturalNumber(24), new NaturalNumber(41),
				new NaturalNumber(10), new NaturalNumber(2), new NaturalNumber(11), new NaturalNumber(22),
				new NaturalNumber(39), new NaturalNumber(3), new NaturalNumber(47), new NaturalNumber(25),
				new NaturalNumber(27), new NaturalNumber(30), new NaturalNumber(27), new NaturalNumber(35),
				new NaturalNumber(37), new NaturalNumber(22) });

		INaturalNumber searchFor = new NaturalNumber(27);

		IBooleanVariable[] result = new IBooleanVariable[data.size()];
		for (int i = 0; i < data.size(); i++)
		{
			result[i] = BooleanVariable.getBooleanVariable();
		}

		INaturalNumber occurrences = new NaturalNumber();
		IProblem problem = new Conjunction(
				new Conjunction(new NaturalNumberFixer(searchFor), new NaturalNumberListFixer(data)),
				new NaturalNumberListSearcher(data, searchFor, result), new BitArrayTotaler(result, occurrences));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());

		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("Searching for " + searchFor);
			for (int i = 0; i < data.size(); i++)
			{
				System.out.print("\n");
				if (result[i].getValue())
					System.out.print("\t");
				System.out.print(data.getNaturalNumber(i));
			}
			System.out.println("\n\nFound " + occurrences + " occurrences.");
		}
		else
			System.out.println("No solution.");
	}
}
