/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 29, 2019
 */
package naturalnumbers;

import java.util.HashSet;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * 
 *
 */
public class IndexSwapper extends Problem implements IProblem
{
	public static HashSet<Integer> doSet(Integer[] validSwap)
	{
		HashSet<Integer> hs1 = new HashSet<Integer>();
		for (Integer n : validSwap)
			hs1.add(n);
		return hs1;
	}

	public IndexSwapper(INaturalNumber[] start, INaturalNumber[] end,
			INaturalNumber tile1, INaturalNumber tile1Index,
			INaturalNumber tile2, INaturalNumber tile2Index) throws Exception
	{
		IProblem[] p = new IProblem[start.length];
		for (int i = 0; i < start.length; i++)
		{
			INaturalNumber stile = start[i];
			INaturalNumber etile = end[i];
			// if stile==tile1 then tile2<-etile and tile1Index<-i;
			IProblem q1 = new Disjunction(new NaturalNumberUnequalizer(stile,
					tile1), new Conjunction(new NaturalNumberEqualizer(tile2,
					etile), new NaturalNumberFixer(tile1Index, i)));
			// if stile==tile2 then tile1<-etile and tile2Index<-i;
			IProblem q2 = new Disjunction(new NaturalNumberUnequalizer(stile,
					tile2), new Conjunction(new NaturalNumberEqualizer(tile1,
					etile), new NaturalNumberFixer(tile2Index, i)));
			// if stile!=tile1 and stile!=tile2 then
			// stile<-etile;
			IProblem q3 = new Disjunction(new NaturalNumberEqualizer(stile,
					tile1), new NaturalNumberEqualizer(stile, tile2),
					new NaturalNumberEqualizer(etile, stile));

			p[i] = new Conjunction(q1, q2, q3);
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
