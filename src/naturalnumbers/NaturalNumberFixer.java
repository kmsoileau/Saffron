package naturalnumbers;

import java.util.ArrayList;
import java.util.Collection;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.exceptions.NaturalNumberFixerException;

/**
 * <p>
 * An IProblem which constrains an INaturalNumber to a particular value.
 * </p>
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since May 21, 2009
 */
public class NaturalNumberFixer extends Problem implements IProblem
{
	public NaturalNumberFixer(ArrayList<NaturalNumberPair> pairs) throws Exception
	{
		IProblem[] p = new IProblem[pairs.size()];
		for (int i = 0; i < pairs.size(); i++)
		{
			p[i] = new NaturalNumberFixer(pairs.get(i));
		}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public NaturalNumberFixer(Collection<INaturalNumber> values) throws Exception
	{
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (INaturalNumber curr : values)
		{
			p.add(new NaturalNumberFixer(curr));
		}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public NaturalNumberFixer(INaturalNumber n) throws Exception
	{
		if (n == null)
			throw new NaturalNumberFixerException("A null INaturalNumber was passed to a constructor.");
		IProblem problem = new NaturalNumberBitFixer(n);
		this.setClauses(problem.getClauses());
	}

	public NaturalNumberFixer(INaturalNumber b, boolean[] bitArray) throws Exception
	{
		if (b == null)
			throw new NaturalNumberFixerException("A null INaturalNumber was passed to a constructor.");
		if (bitArray == null)
			throw new NaturalNumberFixerException("A null bitArray was passed to a constructor.");
		NaturalNumberBitFixer[] bnnbf = new NaturalNumberBitFixer[NaturalNumber.getLength()];
		int a = bitArray.length;
		if (a != bnnbf.length)
			throw (new NaturalNumberFixerException(
					"There is a size mismatch between the INaturalNumber and the parameter (boolean[] or long) passed to a constructor."));
		for (int i = 0; i < a; i++)
			bnnbf[i] = new NaturalNumberBitFixer(b, i, bitArray[i]);
		for (int i = a; i < NaturalNumber.getLength(); i++)
			bnnbf[i] = new NaturalNumberBitFixer(b, i, false);
		IProblem p = new Conjunction(bnnbf);
		this.setClauses(p.getClauses());
	}

	public NaturalNumberFixer(INaturalNumber b, long n) throws Exception
	{
		this(b, ((INumber) new Number(new Number(n), NaturalNumber.getLength())).getBitArray());
	}

	public NaturalNumberFixer(INaturalNumber b, String bitString) throws Exception
	{
		if (b == null)
			throw new NaturalNumberFixerException("A null INaturalNumber was passed to a constructor.");
		if (bitString == null)
			throw new NaturalNumberFixerException("A null bitString was passed to a constructor.");
		NaturalNumberBitFixer[] bnnbf = new NaturalNumberBitFixer[bitString.length()];
		for (int i = 0; i < bnnbf.length; i++)
			if (bitString.charAt(bnnbf.length - 1 - i) == '1')
				bnnbf[i] = new NaturalNumberBitFixer(b, i, true);
			else
				bnnbf[i] = new NaturalNumberBitFixer(b, i, false);
		IProblem p = new Conjunction(bnnbf);
		this.setClauses(p.getClauses());
	}

	public NaturalNumberFixer(INaturalNumber[] n1) throws Exception
	{
		IProblem[] p = new IProblem[n1.length];
		for (int i = 0; i < n1.length; i++)
			p[i] = new NaturalNumberFixer(n1[i]);
		this.setClauses(new Conjunction(p).getClauses());
	}

	public NaturalNumberFixer(NaturalNumberPair naturalNumberPair) throws Exception
	{
		IProblem problem = new Conjunction(new NaturalNumberFixer(naturalNumberPair.getFirst()),
				new NaturalNumberFixer(naturalNumberPair.getSecond()));
		this.setClauses(problem.getClauses());
	}

	public NaturalNumberFixer(NaturalNumberTriple naturalNumberTriple) throws Exception
	{
		IProblem problem = new Conjunction(new NaturalNumberFixer(naturalNumberTriple.getFirst()),
				new NaturalNumberFixer(naturalNumberTriple.getSecond()),
				new NaturalNumberFixer(naturalNumberTriple.getThird()));
		this.setClauses(problem.getClauses());
	}

	public NaturalNumberFixer(NaturalNumberTriple[] triples) throws Exception
	{
		IProblem[] p = new IProblem[triples.length];
		for (int i = 0; i < triples.length; i++)
		{
			p[i] = new NaturalNumberFixer(triples[i]);
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
