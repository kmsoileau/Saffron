package naturalnumbers;

import java.util.ArrayList;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
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
 * @since Mar 3, 2019
 */
public class SingleValuedNNtoNNMapper extends Problem implements IProblem
{
	public SingleValuedNNtoNNMapper(NNtoNNMapper map) throws Exception
	{
		ArrayList<NaturalNumberPair> list = map.getPairs();
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (NaturalNumberPair pr1 : list)
		{
			INaturalNumber Ni = pr1.getFirst();
			INaturalNumber Oi = pr1.getSecond();
			for (NaturalNumberPair pr2 : list)
			{
				INaturalNumber Nj = pr2.getFirst();
				INaturalNumber Oj = pr2.getSecond();

				p.add(new Disjunction(new NaturalNumberUnequalizer(Ni, Nj), new NaturalNumberEqualizer(Oi, Oj)));
			}
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
