package naturalnumbers;

import bits.INaturalNumber;

public class NaturalNumberTriple
{
	private INaturalNumber first;
	private INaturalNumber second;
	private INaturalNumber third;

	public NaturalNumberTriple(INaturalNumber first, INaturalNumber second,
			INaturalNumber third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public INaturalNumber getFirst()
	{
		return this.first;
	}

	public INaturalNumber getSecond()
	{
		return this.second;
	}

	public INaturalNumber getThird()
	{
		return this.third;
	}
}
