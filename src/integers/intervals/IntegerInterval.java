package integers.intervals;

import integers.IInteger;
import integers.Integer;

public class IntegerInterval implements IIntegerInterval
{
	static private int index = 0;
	
	private IInteger lower;
	private IInteger upper;

	public IntegerInterval() throws Exception
	{
		this("III-" + index++);
	}

	public IntegerInterval(String name) throws Exception
	{
		super();
		this.lower = new Integer(name + "_lower");
		this.upper = new Integer(name + "_upper");
	}

	@Override
	public IInteger getLower()
	{
		return lower;
	}

	@Override
	public IInteger getUpper()
	{
		return upper;
	}

	@Override
	public void setLower(IInteger lower)
	{
		this.lower = lower;
	}

	@Override
	public void setUpper(IInteger upper)
	{
		this.upper = upper;
	}

	@Override
	public String toString()
	{
		return "[" + lower + "," + upper + "]";
	}
}
