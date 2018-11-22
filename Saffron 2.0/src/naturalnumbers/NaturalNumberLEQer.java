package naturalnumbers;

/**
 * Copyright (c) 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

//Satisfied when X is less than or equal to Y
public class NaturalNumberLEQer extends Problem implements IProblem
{
	public NaturalNumberLEQer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		this.setClauses(new Conjunction(new NaturalNumberAdder(X,
				new NaturalNumber(), Y)).getClauses());
	}
}