/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 17, 2019
 */
package graphs.generalized;

import java.util.ArrayList;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import graphs.IGeneralizedBitString;

/**
 * 
 *
 */
public class SubGrapher extends Problem implements IProblem
{
	public SubGrapher(IGraph G, IGeneralizedBitString membership,
			IGraph subG) throws Exception
	{
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (INaturalNumber i : G.getVertices().values())
			for (INaturalNumber j : G.getVertices().values())
			{
				p.add(new Conjunction(new Disjunction(new BitFixer(membership
						.getBooleanVariable(i), false), new BitFixer(membership
						.getBooleanVariable(j), false), new BitEqualizer(subG
						.getData(i, j), G.getData(i, j))), new Disjunction(
						new Conjunction(new BitFixer(membership
								.getBooleanVariable(i), true), new BitFixer(
								membership.getBooleanVariable(j), true)),
						new BitFixer(subG.getData(i, j), false))));
			}

		this.setClauses(new Conjunction(p).getClauses());
	}

}
