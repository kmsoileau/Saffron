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

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.InjectiveNNtoNNMapper;
import naturalnumbers.NNtoNNMapper;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberPair;
import naturalnumbers.SingleValuedNNtoNNMapper;

public class InjectiveNNtoNNMapperDemo
{
	public static void main(String[] args) throws Exception
	{
		NNtoNNMapper map = new NNtoNNMapper();

		INaturalNumber NN0 = new NaturalNumber(0);
		INaturalNumber NN1 = new NaturalNumber(1);
		INaturalNumber NN2 = new NaturalNumber(2);
		INaturalNumber NN3 = new NaturalNumber(3);
		INaturalNumber NN4 = new NaturalNumber(4);
		INaturalNumber NN5 = new NaturalNumber(5);
		INaturalNumber NN6 = new NaturalNumber(6);
		INaturalNumber NN7 = new NaturalNumber(7);
		INaturalNumber NN8 = new NaturalNumber(8);
		INaturalNumber NN9 = new NaturalNumber(9);

		INaturalNumber[][] data = new INaturalNumber[][]
		{
				{ NN1, NN2 },
				{ NN2, NN3 },
				{ NN3, NN1 },
				{ NN4, NN5 },
				{ NN5, NN6 },
				{ NN6, NN7 },
				{ NN7, NN8 },
				{ NN8, NN9 } };

		NaturalNumberPair[] pairs = new NaturalNumberPair[data.length];
		for (int i = 0; i < data.length; i++)
			pairs[i] = new NaturalNumberPair(data[i][0], data[i][1]);

		IProblem fixers = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(NN0), new NaturalNumberFixer(NN1), new NaturalNumberFixer(NN2),
				new NaturalNumberFixer(NN3), new NaturalNumberFixer(NN4), new NaturalNumberFixer(NN5),
				new NaturalNumberFixer(NN6), new NaturalNumberFixer(NN7), new NaturalNumberFixer(NN8),
				new NaturalNumberFixer(NN9) });

		IProblem problem = new Conjunction(new IProblem[]
		{ fixers, new NaturalNumberFixer(map.getX(), 1), map, new SingleValuedNNtoNNMapper(map),
				new InjectiveNNtoNNMapper(map) });

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
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
