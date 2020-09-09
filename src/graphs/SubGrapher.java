package graphs;

import java.util.ArrayList;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 2, 2019
 */
public class SubGrapher extends Problem implements IProblem
{
	public SubGrapher(IGraph G, IBitString membership, IGraph subG) throws Exception
	{
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < G.size(); i++)
			for (int j = 0; j < G.size(); j++)
			{
				p.add(new Conjunction(
						new Disjunction(new BitFixer(membership.getBooleanVariable(i), false),
								new BitFixer(membership.getBooleanVariable(j), false),
								new BitEqualizer(subG.getData(i, j), G.getData(i, j))),
						new Disjunction(
								new Conjunction(new BitFixer(membership.getBooleanVariable(i), true),
										new BitFixer(membership.getBooleanVariable(j), true)),
								new BitFixer(subG.getData(i, j), false))));
			}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public SubGrapher(IGraph G, IBooleanVariable[] membership, IGraph subG) throws Exception
	{
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < G.size(); i++)
			for (int j = 0; j < G.size(); j++)
			{
				p.add(new Conjunction(
						new Disjunction(new BitFixer(membership[i], false), new BitFixer(membership[j], false),
								new BitEqualizer(subG.getData(i, j), G.getData(i, j))),
						new Disjunction(
								new Conjunction(new BitFixer(membership[i], true), new BitFixer(membership[j], true)),
								new BitFixer(subG.getData(i, j), false))));
			}

		this.setClauses(new Conjunction(p).getClauses());
	}
}