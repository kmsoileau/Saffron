package naturalnumbers;

import bits.INaturalNumber;

public class WeightedObject
{
	private String name;
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

	public void setName(String name)
	{
		this.name = name;
	}

	public void setWeight(INaturalNumber weight)
	{
		this.weight = weight;
	}

	public String toString()
	{
		return "<" + this.getName() + ":" + this.getWeight() + ">";
	}
}
