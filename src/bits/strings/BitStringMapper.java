/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 21, 2019
 */
package bits.strings;

import bits.IBitString;
import bits.IProblem;
import bits.Mapper;
import bits.Problem;
import bits.strings.lists.IBitStringList;

/**
 * 
 *
 */
public class BitStringMapper extends Problem implements IProblem
{
	private IBitString domainVariable;
	private IBitString rangeVariable;

	public BitStringMapper(IBitString[] ary1, IBitString[] ary2, IBitString X, IBitString Y) throws Exception
	{
		if (ary1.length == 0 || ary2.length == 0 || ary1.length != ary2.length)
			this.setClauses(unsolvableProblem().getClauses());
		else
		{
			this.domainVariable = X;
			this.rangeVariable = Y;
			IProblem[] px = new Problem[ary1.length];
			IProblem[] py = new Problem[ary2.length];
			for (int i = 0; i < ary1.length; i++)
			{
				px[i] = new BitStringFixer(this.domainVariable, ary1[i]);
				py[i] = new BitStringFixer(this.rangeVariable, ary2[i]);
			}
			IProblem pcomb = new Mapper(px, py);
			this.setClauses(pcomb.getClauses());
		}
	}

	public BitStringMapper(IBitStringList x, IBitStringList y, IBitString X, IBitString Y) throws Exception
	{
		this(x.toArray(), y.toArray(), X, Y);
	}

	public IBitString getDomainVariable()
	{
		return domainVariable;
	}

	public IBitString getRangeVariable()
	{
		return rangeVariable;
	}

	public void setDomainVariable(IBitString domainVariable)
	{
		this.domainVariable = domainVariable;
	}

	public void setRangeVariable(IBitString rangeVariable)
	{
		this.rangeVariable = rangeVariable;
	}
}
