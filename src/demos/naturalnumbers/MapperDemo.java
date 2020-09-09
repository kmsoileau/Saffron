/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 29, 2018
 */
package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Mapper;
import bits.Problem;
import bits.ProblemPair;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class MapperDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");

		ProblemPair[] array = new ProblemPair[]
		{ new ProblemPair(new NaturalNumberFixer(X, 0), new NaturalNumberFixer(Y, 1)),
				new ProblemPair(new NaturalNumberFixer(X, 1), new NaturalNumberFixer(Y, 3)),
				new ProblemPair(new NaturalNumberFixer(X, 2), new NaturalNumberFixer(Y, 13)) };
		IBooleanVariable[] b = new IBooleanVariable[array.length - 1];
		for (int i = 0; i < array.length - 1; i++)
			b[i] = BooleanVariable.getBooleanVariable("b" + i);

		Mapper mapper = new Mapper(array, b);

		IProblem problem = new Conjunction(new NaturalNumberFixer(X, 2), mapper);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);

			for (int i = 0; i < b.length; i++)
				System.out.println(b[i].getValue());
		}
		else
			System.out.println("No solution.");
	}
}
