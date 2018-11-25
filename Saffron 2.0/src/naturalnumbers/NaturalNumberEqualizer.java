package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.exceptions.NaturalNumberEqualizerException;
import bits.BitEqualizer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberEqualizer extends Problem implements IProblem
{
	public NaturalNumberEqualizer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		if (X == null || Y == null)
			throw new NaturalNumberEqualizerException(
					"A null conditionalSum variable was passed to constructor.");

		IProblem[] thba = new Problem[NaturalNumber.getLength()];
		for (int i = 0; i < NaturalNumber.getLength(); i++)
			thba[i] = new BitEqualizer(X.getBooleanVariable(i),
					Y.getBooleanVariable(i));

		IProblem p1 = new Conjunction(thba);
		this.setClauses(p1.getClauses());
	}
}