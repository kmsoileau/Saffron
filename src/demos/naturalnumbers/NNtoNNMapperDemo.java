/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2019
 */
package demos.naturalnumbers;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NNtoNNMapper;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberPair;

public class NNtoNNMapperDemo
{
	public static void main(String[] args) throws Exception
	{
		int[][] data = new int[][]
		{
				{ 3, 1 },
				{ 1, 5 },
				{ 4, 0 },
				{ 1, 6 },
				{ 5, 1 },
				{ 9, 1 },
				{ 2, 0 },
				{ 6, 0 }, };

		ArrayList<NaturalNumberPair> pairs = new ArrayList<NaturalNumberPair>();
		for (int i = 0; i < data.length; i++)
			pairs.add(new NaturalNumberPair(new NaturalNumber(data[i][0]), new NaturalNumber(data[i][1])));

		NNtoNNMapper map = new NNtoNNMapper(pairs);

		IProblemMessage s = new Conjunction(new NaturalNumberFixer(pairs), new NaturalNumberFixer(map.getX(), 3), map)
				.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("pairs= " + map.getPairs());
			System.out.println("X= " + map.getX());
			System.out.println("Y= " + map.getY());
		}
		else
			System.out.println("No solution.");
	}
}
