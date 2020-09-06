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
public class MagmaHomomorphismerDemo2 extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(5);
		
		System.out.println(new Date().getTime());
		Magma magma1 = new Magma(new int[][]
		// S3
				{
				{ 0, 0, 0 },
				{ 0, 1, 1 },
				{ 0, 2, 2 },
				{ 0, 3, 3 },
				{ 0, 4, 4 },
				{ 0, 5, 5 },
				{ 1, 0, 1 },
				{ 1, 1, 0 },
				{ 1, 2, 4 },
				{ 1, 3, 5 },
				{ 1, 4, 2 },
				{ 1, 5, 3 },
				{ 2, 0, 2 },
				{ 2, 1, 5 },
				{ 2, 2, 0 },
				{ 2, 3, 4 },
				{ 2, 4, 3 },
				{ 2, 5, 1 },
				{ 3, 0, 3 },
				{ 3, 1, 4 },
				{ 3, 2, 5 },
				{ 3, 3, 0 },
				{ 3, 4, 1 },
				{ 3, 5, 2 },
				{ 4, 0, 4 },
				{ 4, 1, 3 },
				{ 4, 2, 1 },
				{ 4, 3, 2 },
				{ 4, 4, 5 },
				{ 4, 5, 0 },
				{ 5, 0, 5 },
				{ 5, 1, 2 },
				{ 5, 2, 3 },
				{ 5, 3, 1 },
				{ 5, 4, 0 },
				{ 5, 5, 4 } });

		Magma magma2 = new Magma(new int[][]
		// Z2
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
		
		System.out.println(problem);
		
		System.out.println(problem.size());

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println(magma1);
			System.out.println(magma2);

			for (int i = 0; i < h.length; i++)
				System.out.println("h(" + i + ") = " + h[i]);
		}
		else
			System.out.println("There is no solution.");
		System.out.println(new Date().getTime());
	}
}
