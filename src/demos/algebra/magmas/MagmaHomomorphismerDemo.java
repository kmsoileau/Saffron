/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 19, 2019
 */
/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 11, 2019
 */
package demos.algebra.magmas;

import algebra.magmas.generalized.Magma;
import algebra.magmas.generalized.MagmaFixer;
import algebra.magmas.generalized.Magmaer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class MagmaHomomorphismerDemo
{
	public static void main(String[] args) throws Exception
	{
		Magma mgm1 = new Magma(new int[][]
		{
				{ 0, 0, 0 },
				{ 0, 3, 3 },
				{ 0, 2, 2 },
				{ 0, 1, 1 },
				{ 3, 0, 3 },
				{ 3, 3, 0 },
				{ 3, 2, 1 },
				{ 3, 1, 2 },
				{ 2, 0, 2 },
				{ 2, 3, 1 },
				{ 2, 2, 0 },
				{ 2, 1, 3 },
				{ 1, 0, 1 },
				{ 1, 3, 2 },
				{ 1, 2, 3 },
				{ 1, 1, 0 } });

		Magma mgm2 = new Magma(new int[][]
		{
				{ 0, 0, 0 },
				{ 0, 3, 3 },
				{ 0, 2, 2 },
				{ 0, 1, 1 },
				{ 3, 0, 3 },
				{ 3, 3, 0 },
				{ 3, 2, 1 },
				{ 3, 1, 2 },
				{ 2, 0, 2 },
				{ 2, 3, 1 },
				{ 2, 2, 0 },
				{ 2, 1, 3 },
				{ 1, 0, 1 },
				{ 1, 3, 2 },
				{ 1, 2, 3 },
				{ 1, 1, 0 } });

		int order = mgm1.getOrder();

		int index = 0;

		INaturalNumber[] X = new INaturalNumber[order];
		INaturalNumber[] Y = new INaturalNumber[order];

		INaturalNumber[] image = new INaturalNumber[order];

		for (int i = 0; i < order; i++)
		{
			X[i] = new NaturalNumber(i);
			Y[i] = new NaturalNumber(i);
			image[i] = new NaturalNumber();
		}

		INaturalNumber[][] dom = new INaturalNumber[order][order];
		INaturalNumber[][] cod = new INaturalNumber[order][order];

		for (int i = 0; i < order; i++)
		{
			dom[i] = new NaturalNumber[order];
			cod[i] = new NaturalNumber[order];
			for (int j = 0; j < order; j++)
			{
				dom[i][j] = new NaturalNumber();
				cod[i][j] = new NaturalNumber();
			}
		}

		IProblem[] fixer = new IProblem[order];

		IProblem fix1 = new MagmaFixer(mgm1);
		IProblem fix2 = new MagmaFixer(mgm2);

		IProblem[] p = new IProblem[order * order];

		for (int i = 0; i < order; i++)
		{
			fixer[i] = new Conjunction(new NaturalNumberFixer(X[i]), new NaturalNumberFixer(Y[i]));
		}

		for (int i = 0; i < order; i++)
		{
			for (int j = 0; j < order; j++)
			{
				/**
				 * dom[i][j]=i*j cod[i][j]=h(i)*h(j)
				 */

				p[index++] = new Conjunction(new Magmaer(mgm1, X[i], Y[j], dom[i][j]), // dom[i][j]=i*j
						new Magmaer(mgm2, image[i], image[j], cod[i][j]), // cod[i][j]=h(i)*h(j)
						new Disjunction(new IProblem[]
						{ new Conjunction(new NaturalNumberFixer(dom[i][j], 0),
								new NaturalNumberEqualizer(cod[i][j], image[0])),
								new Conjunction(new NaturalNumberFixer(dom[i][j], 1),
										new NaturalNumberEqualizer(cod[i][j], image[1])),
								new Conjunction(new NaturalNumberFixer(dom[i][j], 2),
										new NaturalNumberEqualizer(cod[i][j], image[2])),
								new Conjunction(new NaturalNumberFixer(dom[i][j], 3),
										new NaturalNumberEqualizer(cod[i][j], image[3])) }));
			}
		}

		IProblem problem = new Conjunction(fix1, fix2, new Conjunction(fixer), new Conjunction(p));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			for (int i = 0; i < order; i++)
				System.out.println(image[i]);

		}
		else
			System.out.println("There is no solution.");
	}
}
