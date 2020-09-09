package naturalnumbers;

import bits.BitUnequalizer;
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
 * @since Mar 4, 2005
 */
public class NaturalNumberUnequalizer extends Problem implements IProblem
{
	public NaturalNumberUnequalizer(INaturalNumber X, INaturalNumber Y) throws Exception
	{
		BitUnequalizer[] thba = new BitUnequalizer[NaturalNumber.getLength()];
		for (int i = 0; i < NaturalNumber.getLength(); i++)
			thba[i] = new BitUnequalizer(X.getBooleanVariable(i), Y.getBooleanVariable(i));
		IProblem p1 = new Disjunction(thba);
		this.setClauses(p1.getClauses());
	}
}