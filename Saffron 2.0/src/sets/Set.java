package sets;

import java.util.HashMap;

import bits.BooleanVariable;
import bits.IBooleanVariable;

public class Set
{
	private HashMap<Object, IBooleanVariable> backing = new HashMap<Object, IBooleanVariable>();

	public void add(Object key) throws Exception
	{
		add(key, BooleanVariable.getBooleanVariable());
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

	public HashMap<Object, IBooleanVariable> getBacking()
	{
		return backing;
	}

	public IBooleanVariable getIBooleanVariable(Object o)
	{
		return this.getBacking().get(o);
	}

	public void setBacking(HashMap<Object, IBooleanVariable> backing)
	{
		this.backing = backing;
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
