package naturalnumbers;

import bits.INaturalNumber;
import bits.IProblemMessage;

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
public class Problem extends bits.Problem
{
	public IProblemMessage[] findTwoModels(INaturalNumber n) throws Exception
	{
		return findTwoModels(n.getBVArray());
	}
}
