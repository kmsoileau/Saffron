/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 14, 2019
 */
package algebra.magmas.generalized;

import java.util.HashMap;

import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.ProblemTriplet;
import bits.TriMapper;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberFixer;

/**
 * 
 *
 */
public class Magmaer extends Problem implements IProblem
{
	public Magmaer(int[][] opTable, INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		ProblemTriplet[] p = new ProblemTriplet[opTable.length];
		for (int i = 0; i < opTable.length; i++)
		{
			p[i] = new ProblemTriplet(new NaturalNumberFixer(X, opTable[i][0]),
					new NaturalNumberFixer(Y, opTable[i][1]),
					new NaturalNumberFixer(Z, opTable[i][2]));
		}

		this.setClauses(new TriMapper(p).getClauses());
	}

	public Magmaer(Magma mgm, INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		int order = mgm.getOrder();
		HashMap<INaturalNumber, HashMap<INaturalNumber, INaturalNumber>> comp = mgm
				.getComposition();
		int index = 0;

		ProblemTriplet[] p = new ProblemTriplet[order * order];
		for (INaturalNumber i : comp.keySet())
		{
			HashMap<INaturalNumber, INaturalNumber> iset = comp.get(i);
			for (INaturalNumber j : iset.keySet())
			{
				p[index++] = new ProblemTriplet(
						new NaturalNumberEqualizer(X, i),
						new NaturalNumberEqualizer(Y, j),
						new NaturalNumberEqualizer(Z, iset.get(j)));
			}
		}

		this.setClauses(new TriMapper(p).getClauses());
	}
}
