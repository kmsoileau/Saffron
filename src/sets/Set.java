package sets;

import bits.IBitString;
import bits.IBooleanVariable;
import bitstrings.BitString;
import exceptions.sets.SetException;

/**
 * A class which represents a subset. Set is essentially an association of a
 * collection of objects and corresponding <code>IBitString</code>s. The objects
 * whose corresponding <code>IBooleanVariable</code>s in the membership
 * <code>IBitString</code> evaluate to <code>true</code> form the members of the
 * Set. This class is useful when one is trying to find a subset of a given set
 * that satisfies given constraints.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/26
 */
public class Set
{
	private static int bitStringSize = 0;
	private static String[] elementNames = null;
	private static int setIndex = 0;

	public static String[] getElementNames()
	{
		return Set.elementNames;
	}

	public static int getSetSupportSize()
	{
		return Set.bitStringSize;
	}

	public static void setElementNames(String[] strings)
	{
		Set.elementNames = strings;
		Set.bitStringSize = strings.length;
	}

	private Object[] memberObjects;
	private IBitString membershipBitString;
	private String name;

	public Set() throws Exception
	{
		this("Set-" + setIndex++);
	}

	public Set(String name) throws Exception
	{
		if (name == null)
			throw new SetException(
					"Null passed to constructor as name parameter.");

		this.setName(name);
		this.membershipBitString = new BitString(Set.getSetSupportSize());
	}

	public IBooleanVariable contains(String elementName) throws Exception
	{
		String[] names = Set.getElementNames();
		for (int i = 0; i < Set.getSetSupportSize(); i++)
		{
			IBooleanVariable ww = this.membershipBitString
					.getBooleanVariable(i);
			if (names[i].compareTo(elementName) == 0)
				return ww;
		}
		return null;
	}

	public IBooleanVariable getBooleanVariable(int i) throws Exception
	{
		return this.membershipBitString.getBooleanVariable(i);
	}

	public Object[] getMemberObjects()
	{
		return memberObjects;
	}

	public IBitString getMembershipBitString()
	{
		return membershipBitString;
	}

	public String getName()
	{
		return name;
	}

	public void setMemberObjects(Object[] memberObjects)
	{
		this.memberObjects = memberObjects;
	}

	public void setMembershipBitString(IBitString membershipBitString)
	{
		this.membershipBitString = membershipBitString;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		String ret = "";
		for (int i = 0; i < Set.getSetSupportSize(); i++)
			try
			{
				if (membershipBitString.getBooleanVariable(i).getValue())
					ret += " " + Set.getElementNames()[i];
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return "[" + ret + " ]";
	}
}
