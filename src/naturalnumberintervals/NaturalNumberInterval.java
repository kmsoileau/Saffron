package naturalnumberintervals;

import naturalnumbers.NaturalNumber;
import bits.INaturalNumber;

public class NaturalNumberInterval implements INaturalNumberInterval
{
	private INaturalNumber lower;
	private INaturalNumber upper;

	public NaturalNumberInterval(String name) throws Exception
	{
		super();
		this.lower = new NaturalNumber(name + "_lower");
		this.upper = new NaturalNumber(name + "_upper");
	}

	public INaturalNumber getLower()
	{
		return lower;
	}

	public INaturalNumber getUpper()
	{
		return upper;
	}

	public void setLower(INaturalNumber lower)
	{
		this.lower = lower;
	}

	public void setUpper(INaturalNumber upper)
	{
		this.upper = upper;
	}

	public String toString()
	{
		return "[" + lower + "," + upper + "]";
	}
}
