package naturalnumbers;

import bits.INaturalNumber;

public class WeightedObject
{
	private String name;
	private INaturalNumber weight;
	private Integer weightValue;

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

	public Integer getWeightValue()
	{
		// TODO Auto-generated method stub
		return this.weightValue;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setWeight(INaturalNumber weight)
	{
		this.weight = weight;
	}

	public void setWeightValue(Integer weightValue)
	{
		this.weightValue = weightValue;
	}

	public String toString()
	{
		return "<" + this.getName() + ":" + this.getWeight() + ">";
	}
}
