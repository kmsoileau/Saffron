package in_development;

import bits.IBitString;
import bits.strings.BitString;
import sets.Set;

public class Subset
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	private final Object[] backing;
	private IBitString membership;

	public Subset(Object[] backing) throws Exception
	{
		this.backing = backing;
		this.membership = new BitString(backing.length);
	}

	public Object[] getBacking()
	{
		return backing;
	}

	public IBitString getMembership()
	{
		return membership;
	}

	public void setMembership(IBitString membership)
	{
		this.membership = membership;
	}

	// To do
	public Set toSet()
	{
		return null;
	}
}
