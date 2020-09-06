/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 19, 2019
 */
package bits.strings;

import bits.IBitString;
import bits.IBooleanVariable;

/**
 * 
 *
 */
public class RangeSubset
{
	private IBitString membership;
	private int top;

	public RangeSubset(int top) throws Exception
	{
		this.top = top;
		this.membership = new BitString(top + 1);
	}

	public IBooleanVariable getBV(int i) throws Exception
	{
		return membership.getBooleanVariable(i);
	}

	public IBitString getMembership()
	{
		return membership;
	}

	public int getTop()
	{
		return top;
	}

	public void setMembership(IBitString membership)
	{
		this.membership = membership;
	}

	public void setTop(int top)
	{
		this.top = top;
	}

	public int size()
	{
		return top + 1;
	}

	@Override
	public String toString()
	{
		return "RangeSubset [membership=" + membership + ", top=" + top + "]";
	}
}
