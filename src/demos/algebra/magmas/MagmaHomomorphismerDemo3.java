/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 20, 2019
 */
package demos.algebra.magmas;

import java.util.Date;

import algebra.magmas.generalized.Magma;
import algebra.magmas.generalized.MagmaFixer;
import algebra.magmas.generalized.MagmaHomomorphismer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class MagmaHomomorphismerDemo3 extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		long start = new Date().getTime();

		Magma magma1 = new Magma(new int[][]
		// Z2
				{
				{ 0, 0, 0 },
				{ 0, 1, 1 },
				{ 0, 2, 2 },
				{ 0, 3, 3 },
				{ 1, 0, 1 },
				{ 1, 1, 2 },
				{ 1, 2, 3 },
				{ 1, 3, 0 },
				{ 2, 0, 2 },
				{ 2, 1, 3 },
				{ 2, 2, 0 },
				{ 2, 3, 1 },
				{ 3, 0, 3 },
				{ 3, 1, 0 },
				{ 3, 2, 1 },
				{ 3, 3, 2 }, });

		Magma magma2 = new Magma(new int[][]
		// Z1
				{
				{ 0, 0, 0 },
				{ 0, 1, 1 },
				{ 1, 0, 1 },
				{ 1, 1, 0 }, });

		INaturalNumber[] h = new INaturalNumber[magma1.getOrder()];
		for (int i = 0; i < h.length; i++)
		{
			h[i] = new NaturalNumber();
		}

		IProblem fixers = new Conjunction(new MagmaFixer(magma1),
				new MagmaFixer(magma2));

		IProblem filters = new Conjunction(new NaturalNumberFixer(h[1], 1));

		IProblem problem = new Conjunction(fixers, new MagmaHomomorphismer(
				magma1, magma2, h), filters);

		System.out.println(new Date().getTime() - start);

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println(magma1);

			for (int i = 0; i < h.length; i++)
				System.out.println("h(" + i + ") = " + h[i]);
		}
		else
			System.out.println("There is no solution.");

		System.out.println(new Date().getTime() - start);
	}
}
