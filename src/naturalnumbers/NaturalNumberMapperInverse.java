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
public class NaturalNumberMapperInverse extends Problem implements IProblem
{
	public NaturalNumberMapperInverse(NaturalNumberMapper f, INaturalNumber X,
			INaturalNumber Y) throws Exception
	{
		IProblem prob = new Conjunction(new Problem[]
		{ new NaturalNumberEqualizer(Y, f.getDomainVariable()), f,
				new NaturalNumberEqualizer(f.getRangeVariable(), X) });
		this.setClauses(prob.getClauses());
	}
}