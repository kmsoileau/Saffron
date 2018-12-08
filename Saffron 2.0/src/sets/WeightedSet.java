package sets;

import bits.BooleanVariable;
import exceptions.sets.WeightedSetException;

public class WeightedSet
{
	private static int objectIndex = 0;
	private Set backingSet;
	private String name;

	public WeightedSet() throws Exception
	{
		this("WeightedSet" + objectIndex++, new Integer[0]);
	}

	public WeightedSet(Integer[] data) throws Exception
	{
		this("WS" + objectIndex++, data);
	}

	public WeightedSet(String name) throws Exception
	{
		this(name, new Integer[0]);
	}

	public WeightedSet(String name, Integer[] data) throws Exception
	{
		this.name = name;
		this.backingSet = new Set();
		for (int i = 0; i < data.length; i++)
		{
			WeightedObject curr = new WeightedObject(name + "_" + i);
			curr.setWeightValue(data[i]);
			this.addSupport(curr);
		}
	}

	public void addSupport(Object key) throws Exception
	{
		if (key instanceof WeightedObject)
			backingSet.add(key, BooleanVariable.getBooleanVariable());
		else
			throw new WeightedSetException(
					"Attempted to add an Object not of WeightedObject type.");
	}

	public Set getBackingSet()
	{
		return backingSet;
	}

	public String getName()
	{
		return name;
	}

	public java.util.Set<Object> getSupport()
	{
		return this.backingSet.getSupport();
	}

	public void setBackingSet(Set backingSet)
	{
		this.backingSet = backingSet;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String toString()
	{
		return backingSet.toString();
	}
}
