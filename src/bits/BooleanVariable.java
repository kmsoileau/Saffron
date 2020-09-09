package bits;

import java.util.HashSet;

import bits.exceptions.BooleanVariableException;

/**
 * A class which represents a Boolean variable. BooleanVariable is essentially a
 * named boolean variable. NOTE: Alternative implementations of BooleanVariable
 * must not only implement IBooleanVariable, but must also ensure that any two
 * instances of BooleanVariable are equal if and only if they have the same
 * name.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Aug 26, 2004
 */
public class BooleanVariable implements IBooleanVariable
{
	/**
	 * The current number of BooleanVariable objects that were constructed without a
	 * specified name, and for which a application-generated name was assigned.
	 */
	private static long boolCount;

	/**
	 * A Set containing every current BooleanVariable object.
	 */
	private static HashSet<IBooleanVariable> instances = new HashSet<IBooleanVariable>();

	/**
	 * A factory method. It constructs a new IBooleanVariable with name
	 * <code>"BooleanVariable-" + boolCount</code> and value <code>false</code>.
	 * 
	 * @return A new IBooleanVariable.
	 * @throws BooleanVariableException if n == null or n=="".
	 */
	public static IBooleanVariable getBooleanVariable() throws Exception
	{
		return new BooleanVariable();
	}

	/**
	 * A factory method. It constructs a new IBooleanVariable with name
	 * <code>"BooleanVariable-" + boolCount</code> and value <code>x</code>.
	 * 
	 * @return A new IBooleanVariable.
	 * @throws BooleanVariableException if n == null or n=="".
	 * @param x A boolean
	 */
	public static IBooleanVariable getBooleanVariable(boolean x) throws Exception
	{
		IBooleanVariable res = BooleanVariable.getBooleanVariable();
		res.setValue(x);
		return res;
	}

	/**
	 * A factory method. It constructs a new IBooleanVariable with name
	 * <code>n</code> and value <code>false</code>.
	 * 
	 * @return A new IBooleanVariable.
	 * @throws BooleanVariableException if n == null or n=="".
	 * @param n A String
	 */
	public static IBooleanVariable getBooleanVariable(String n) throws Exception
	{
		if (n == null || "".compareTo(n) == 0)
			throw new BooleanVariableException("Empty string or null was passed to getBooleanVariable method.");

		Object[] inarray = BooleanVariable.getInstances().toArray();
		for (int i = 0; i < inarray.length; i++)
		{
			IBooleanVariable nx = (IBooleanVariable) (inarray[i]);
			if (n.compareTo(nx.getName()) == 0)
				return nx;
		}
		return new BooleanVariable(n);
	}

	/**
	 * A factory method. It constructs a new IBooleanVariable with name
	 * <code>n</code> and value <code>x</code>.
	 * 
	 * @return A new IBooleanVariable.
	 * @throws BooleanVariableException if n == null or n=="".
	 * @param n A String
	 * @param x A boolean
	 */
	public static IBooleanVariable getBooleanVariable(String n, boolean x) throws Exception
	{
		if (n == null || "".compareTo(n) == 0)
			throw new BooleanVariableException("Null or empty String passed to getBooleanVariable method.");
		else
		{
			IBooleanVariable res = BooleanVariable.getBooleanVariable(n);
			res.setValue(x);
			return res;
		}
	}

	/**
	 * @return the Set of <code>instances</code>.
	 */
	public static HashSet<IBooleanVariable> getInstances()
	{
		return instances;
	}

	/**
	 * The name of the IBooleanVariable.
	 */
	private String name;

	/**
	 * The value of the IBooleanVariable.
	 */
	private boolean value;

	/**
	 * Constructs a new IBooleanVariable with name
	 * <code>"BooleanVariable-" + boolCount</code> and value <code>false</code>.
	 */
	private BooleanVariable()
	{
		this.name = "BooleanVariable-" + boolCount;
		boolCount++;
		this.setValue(false);
		BooleanVariable.getInstances().add(this);
	}

	/**
	 * Constructs a new IBooleanVariable with name <code>n</code> and value
	 * <code>false</code>.
	 * 
	 * @return A new IBooleanVariable.
	 * @throws BooleanVariableException if n == null or n=="".
	 */
	private BooleanVariable(String n) throws Exception
	{
		this(n, false);
	}

	/**
	 * Constructs a new IBooleanVariable with name <code>n</code> and value
	 * <code>x</code>.
	 * 
	 * @throws BooleanVariableException if n == null or n=="".
	 */
	private BooleanVariable(String n, boolean x) throws Exception
	{
		if (n == null || "".compareTo(n) == 0)
			throw new BooleanVariableException("Null or empty string was passed to a constructor.");
		this.setName(n);
		this.setValue(x);
		BooleanVariable.getInstances().add(this);
	}

	/**
	 * The result of comparing two IBooleanVariables x and y is exactly the same as
	 * the result of comparing their names.
	 * 
	 * @return <code>x.getName().compareTo(y.getName()).</code>
	 */
	@Override
	public int compareTo(Object o)
	{
		if (o == null)
			return -1;
		String thisName = this.getName();
		String oName = ((IBooleanVariable) o).getName();
		return thisName.compareTo(oName);
	}

	/**
	 * Two IBooleanVariables x and y are equal if and only if
	 * x.getName().compareTo(y.getName())==0.
	 * 
	 * @return <code>true</code> if this.getName().compareTo(o.getName())==0.
	 */
	@Override
	public boolean equals(Object anObject)
	{
		if (anObject == null)
			return false; // this is never equal to null.
		if (anObject instanceof IBooleanVariable)
		{
			BooleanVariable bv = (BooleanVariable) anObject;
			if (this.getName().compareTo(bv.getName()) == 0)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	/**
	 * @return the name as String.
	 */
	@Override
	public String getName()
	{
		return this.name;
	}

	/**
	 * Returns true if <code>value==true</code>, otherwise returns false.
	 *
	 * @return a boolean: <code>true</code> or <code>false</code>.
	 */
	@Override
	public boolean getValue()
	{
		return this.value;
	}

	/**
	 * Sets the name of the IBooleanVariable.
	 * 
	 * @param name A String
	 * @throws BooleanVariableException if name == null or name=="".
	 */
	public void setName(String name) throws BooleanVariableException
	{
		if (name == null || "".compareTo(name) == 0)
			throw new BooleanVariableException("Null or empty String passed to setName method.");
		this.name = name;
	}

	/**
	 * Sets the logical value: <code>true</code> or <code>false</code>.
	 * 
	 * @param x A boolean
	 */
	@Override
	public void setValue(boolean x)
	{
		this.value = x;
	}

	@Override
	public String toBit()
	{
		return this.value ? "1" : "0";
	}

	/**
	 * Returns a string representation of the IBooleanVariable.
	 */
	@Override
	public String toString()
	{
		return "<" + getName() + "=" + this.getValue() + ">";
	}

}