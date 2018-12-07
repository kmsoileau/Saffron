package sets;

import bits.BooleanVariable;
import exceptions.sets.WeightedSetException;

public class WeightedSet
{
	private Set backingSet;

	public WeightedSet() throws Exception
	{
		this.backingSet = new Set();
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

	public void setBackingSet(Set backingSet)
	{
		this.backingSet = backingSet;
	}

	public String toString()
	{
		return "[" + backingSet + "]";
	}
}
