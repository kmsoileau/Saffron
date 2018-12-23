package sets;

import java.util.HashMap;
import java.util.HashSet;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import exceptions.sets.SetException;

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

	public Set() throws SetException
	{
		this("Set-" + setIndex++);
	}

	public <T> Set(HashSet<T> hashSet) throws Exception
	{
		this();
		if (hashSet == null)
			throw new SetException(
					"Null passed to constructor as HashSet parameter.");
		this.backing.clear();
		for (Object o : hashSet)
			this.addSupport(o);
	}

	public Set(Object[] supportArray) throws Exception
	{
		// Eventually: this(java.util.Set.of(supportArray));
		this();
		if (supportArray == null)
			throw new SetException(
					"Null passed to constructor as HashSet parameter.");
		this.backing.clear();
		for (Object o : supportArray)
			this.addSupport(o);
	}

	public Set(String name) throws SetException
	{
		super();
		if (name == null)
			throw new SetException(
					"Null passed to constructor as name parameter.");
		this.name = name;
	}

	public <T> Set(String name, HashSet<T> hashSet) throws Exception
	{
		this(hashSet);
		if (name == null)
			throw new SetException(
					"Null passed to constructor as name parameter.");
		this.setName(name);
	}

	public Set(String name, Object[] supportArray) throws Exception
	{
		this(supportArray);
		if (name == null)
			throw new SetException(
					"Null passed to constructor as name parameter.");
		this.name = name;
	}

	public void add(Object key, IBooleanVariable bv) throws SetException
	{
		if (key == null)
			throw new SetException(
					"Null passed to add method as Object parameter.");
		if (!backing.containsKey(key))
			backing.put(key, bv);
	}

	public void add(Object key, String name) throws Exception
	{
		if (name == null)
			throw new SetException(
					"Null passed to add method as String parameter.");
		if (!backing.containsKey(key))
		{
			backing.put(key, BooleanVariable.getBooleanVariable(name));
		}
	}

	public void addSupport(Object key) throws Exception
	{
		if (key == null)
			throw new SetException(
					"Null passed to add method as Object parameter.");
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
			throws SetException
	{
		if (o == null)
			throw new SetException(
					"Null passed to put method as Object parameter.");
		if (bv == null)
			throw new SetException(
					"Null passed to put method as IBooleanVariable parameter.");
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
