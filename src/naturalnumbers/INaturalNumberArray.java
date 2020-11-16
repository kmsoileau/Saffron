package naturalnumbers;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;

public class INaturalNumberArray extends Problem implements IProblem
{
	private INaturalNumber X;
	private INaturalNumber Y;
	private INaturalNumber Z;

	public INaturalNumberArray(NaturalNumberTriple[] triple) throws Exception
	{
		if (triple == null)
			throw new NNtoNNMapperException("Null NaturalNumberTriple array was passed to a constructor.");

		this.X = new NaturalNumber();
		this.Y = new NaturalNumber();
		this.Z = new NaturalNumber();

		IProblem[] p = new IProblem[triple.length];
		for (int i = 0; i < triple.length; i++)
		{
			INaturalNumber first = triple[i].getFirst();
			INaturalNumber second = triple[i].getSecond();
			INaturalNumber third = triple[i].getThird();

			p[i] = new Conjunction(new NaturalNumberEqualizer(X, first), new NaturalNumberEqualizer(Y, second),
					new NaturalNumberEqualizer(Z, third));
		}

		this.setClauses(new Disjunction(p).getClauses());
	}

	public INaturalNumber getX()
	{
		return X;
	}

	public INaturalNumber getY()
	{
		return Y;
	}

	public INaturalNumber getZ()
	{
		return Z;
	}
}