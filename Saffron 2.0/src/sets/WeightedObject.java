package sets;

import naturalnumbers.NaturalNumber;
import bits.INaturalNumber;

public class WeightedObject
{
	private Object name;
	private INaturalNumber weight;

	public WeightedObject() throws Exception
	{
		this(new NaturalNumber());
	}
	
	public WeightedObject(INaturalNumber weight)
	{
		super();
		this.weight = weight;
	}

	public WeightedObject(String name) throws Exception
	{
		this();
		this.name = name;
	}

	public Object getName()
	{
		return name;
	}

	public INaturalNumber getWeight()
	{
		return weight;
	}

	public void setName(Object name)
	{
		this.name = name;
	}

	public void setWeight(INaturalNumber weight)
	{
		this.weight = weight;
	}

	public String toString()
	{
		return "[" + weight + "]";
	}
}
