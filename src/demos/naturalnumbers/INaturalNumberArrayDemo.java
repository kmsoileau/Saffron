/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Apr 5, 2019
 */
package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.INaturalNumberArray;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberTriple;

/**
 * 
 *
 */
public class INaturalNumberArrayDemo
{

	public static void main(String[] args) throws Exception
	{
		int[][] data = new int[][]
		{
		{ 3, 1, 7 },
		{ 1, 5, 8 },
		{ 4, 0, 9 },
		{ 1, 6, 0 },
		{ 5, 1, 1 },
		{ 9, 1, 2 },
		{ 2, 0, 3 },
		{ 6, 0, 4 } };

		NaturalNumberTriple[] triples = new NaturalNumberTriple[data.length];
		for (int i = 0; i < data.length; i++)
			triples[i] = new NaturalNumberTriple(new NaturalNumber(data[i][0]),
					new NaturalNumber(data[i][1]),
					new NaturalNumber(data[i][2]));

		INaturalNumberArray array = new INaturalNumberArray(triples);

		IProblemMessage s = new Conjunction(new NaturalNumberFixer(triples),
				new NaturalNumberFixer(array.getY(), 0), array)
				.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			// System.out.println("triples= " + map.getTriples());
			System.out.println("X= " + array.getX());
			System.out.println("Y= " + array.getY());
			System.out.println("Z= " + array.getZ());
		}
		else
			System.out.println("No solution.");
	}
}
