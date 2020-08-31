package naturalnumbers;

import bits.Conjunction;
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
 * @since Mar 4, 2005
 */
public class NaturalNumberMapperComposition extends Problem implements IProblem
{
	public NaturalNumberMapperComposition(NaturalNumberMapper first,
			NaturalNumberMapper second, INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		IProblem prob = new Conjunction(new Problem[]
		{
				new NaturalNumberEqualizer(X, first.getDomainVariable()),
				first,
				new NaturalNumberEqualizer(first.getRangeVariable(),
						second.getDomainVariable()), second,
				new NaturalNumberEqualizer(second.getRangeVariable(), Y), });
		this.setClauses(prob.getClauses());
	}
}