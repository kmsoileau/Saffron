package naturalnumberinterval;

import bits.INaturalNumber;

public interface INaturalNumberInterval
{
	INaturalNumber getLower();

	INaturalNumber getUpper();

	void setLower(INaturalNumber lower);

	void setUpper(INaturalNumber upper);

	String toString();
}
