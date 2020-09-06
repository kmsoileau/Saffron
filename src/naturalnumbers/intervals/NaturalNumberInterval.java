package naturalnumbers.intervals;

import bits.INaturalNumber;
import naturalnumbers.NaturalNumber;

public class NaturalNumberInterval implements INaturalNumberInterval
{
	static private int index = 0;
	private INaturalNumber lower;

	private INaturalNumber upper;

	public NaturalNumberInterval() throws Exception
	{
		this("NNI-" + index++);
	}

	public NaturalNumberInterval(String name) throws Exception
	{
		super();
		this.lower = new NaturalNumber(name + "_lower");
		this.upper = new NaturalNumber(name + "_upper");
	}

	@Override
	public INaturalNumber getLower()
	{
		return lower;
	}

	@Override
	public INaturalNumber getUpper()
	{
		return upper;
	}

	@Override
	public void setLower(INaturalNumber lower)
	{
		this.lower = lower;
	}

	@Override
	public void setUpper(INaturalNumber upper)
	{
		this.upper = upper;
	}

	@Override
	public String toString()
	{
		return "[" + lower + "," + upper + "]";
	}
}
