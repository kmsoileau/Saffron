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
package in_development;

import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Mapper;
import bits.Problem;
import bits.ProblemDenier;

public class Noninjectioner extends Problem implements IProblem
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public Noninjectioner(Mapper mapper) throws Exception
	{
		IProblem[] r = new IProblem[mapper.size()];
		for (int i = 0; i < mapper.size(); i++)
		{
			r[i] = Problem.newProblem();
			IProblem ai = mapper.getDomain(i);
			IProblem bi = mapper.getCodomain(i);
			IProblem nbi = new ProblemDenier(bi);
			for (int j = i + 1; j < mapper.size(); j++)
			{
				IProblem aj = mapper.getDomain(j);
				IProblem bj = mapper.getCodomain(j);
				r[i] = new Disjunction(r[i], new Conjunction(ai, aj,
						new Disjunction(nbi, new ProblemDenier(bj))));
			}
		}
		this.setClauses(new Disjunction(r).getClauses());
	}
}
