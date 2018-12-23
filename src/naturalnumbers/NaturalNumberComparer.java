package naturalnumbers;

/**
 * Copyright (c) 2005 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

//Satisfied when X is STRICTLY LESS THAN Y.
public class NaturalNumberComparer extends Problem implements IProblem
{
	public NaturalNumberComparer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		INaturalNumber Z = new NaturalNumber();
		this.setClauses(new Conjunction(new NaturalNumberAdder(X, Z, Y),
				new NaturalNumberPositiver(Z)).getClauses());
	}
}