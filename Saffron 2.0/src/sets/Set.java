package sets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import bits.BooleanVariable;
import bits.IBooleanVariable;

/**
 * A class which represents a subset. Set is essentially an association of a
 * collection of objects (its <b>support</b>) and corresponding
 * <code>IBooleanVariable</code>s. The objects whose
 * <code>IBooleanVariable</code>s evaluate to <code>true</code> form the members
 * of the Set. This class is useful when one is trying to find a subset of a
 * given set that satisfies given constraints.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/06
 */
public class Set
{
	private static int setIndex = 0;
	private HashMap<Object, IBooleanVariable> backing = new HashMap<Object, IBooleanVariable>();
	private String name;

	public Set()
	{
		this("Set-" + setIndex++);
	}

	public <T> Set(HashSet<T> hashSet) throws Exception
	{
		this.backing.clear();
		for (Object o : hashSet)
			this.addSupport(o);
	}

	public Set(Object[] supportArray) throws Exception
	{
		// Eventually: this(java.util.Set.of(supportArray));
		this(new HashSet<Object>(Arrays.asList(supportArray)));
	}

	public Set(String name)
	{
		super();
		this.name = name;
	}

	public void add(Object key, IBooleanVariable bv)
	{
		if (!backing.containsKey(key))
			backing.put(key, bv);
	}

	public void add(Object key, String name) throws Exception
	{
		if (!backing.containsKey(key))
		{
			backing.put(key, BooleanVariable.getBooleanVariable(name));
		}
	}

	public void addSupport(Object key) throws Exception
	{
		add(key, BooleanVariable.getBooleanVariable());
	}

	public IBooleanVariable contains(Object o)
	{
		return this.backing.get(o);
	}

	public HashMap<Object, IBooleanVariable> getBacking()
	{
		return backing;
	}

	public IBooleanVariable getIBooleanVariable(Object o)
	{
		return this.getBacking().get(o);
	}

	public String getName()
	{
		return this.name;
	}

	public java.util.Set<Object> getSupport()
	{
		return this.backing.keySet();
	}

	public IBooleanVariable put(Object o, IBooleanVariable bv)
	{
		return this.backing.put(o, bv);
	}

	public void setBacking(HashMap<Object, IBooleanVariable> backing)
	{
		this.backing = backing;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String toString()
	{
		String ret = "{ ";
		for (Object o : this.getBacking().keySet())
		{
			if (getIBooleanVariable(o).getValue())
				ret += o + " ";
		}
		return ret + "}";
	}
}
