package tbs;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringIntersector;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/13
 */
public class HittingSetter extends Problem implements IProblem
{
	public HittingSetter(IBitString[] C, IBitString hittingSet) throws Exception
	{
		int problemIndex=0;
		IProblem[] problemArray=new IProblem[C.length];
		for(int i=0;i<C.length;i++)
			problemArray[problemIndex++]=new BitStringIntersector(C[i],hittingSet);
		this.setClauses(new Conjunction(problemArray).getClauses());
	}
}