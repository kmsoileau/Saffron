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
package bits.strings.lists;

import bits.IBitString;
import bits.IProblem;
import bits.Mapper;
import bits.Problem;
import bits.strings.BitStringFixer;

/**
 * 
 *
 */
public class BitStringListMapper extends Problem implements IProblem
{
	private IBitString domainVariable;
	private IBitString rangeVariable;

	public BitStringListMapper(IBitStringList L1, IBitStringList L2,
			IBitString domainVariable, IBitString rangeVariable)
			throws Exception
	{
		if (L1.size() == 0 || L2.size() == 0 || L1.size() != L2.size())
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			this.domainVariable = domainVariable;
			this.rangeVariable = rangeVariable;
			IProblem[] px = new Problem[L1.size()];
			IProblem[] py = new Problem[L2.size()];
			for (int i = 0; i < L1.size(); i++)
			{
				px[i] = new BitStringFixer(this.domainVariable,
						L1.getBitString(i));
				py[i] = new BitStringFixer(this.rangeVariable,
						L2.getBitString(i));
			}
			IProblem pcomb = new Mapper(px, py);
			this.setClauses(pcomb.getClauses());
		}
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
