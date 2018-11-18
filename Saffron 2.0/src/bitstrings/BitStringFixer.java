/*
 * BitStringFixer.java	1.0 05/04/21
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitFixer;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bitstrings.exceptions.BitStringFixerException;

public class BitStringFixer extends Problem implements IProblem
{
	public BitStringFixer(IBitString target) throws Exception
	{
		BitStringBitFixer[] bnnbf = new BitStringBitFixer[target.size()];
		for (int i = 0; i < bnnbf.length; i++)
			bnnbf[i] = new BitStringBitFixer(target, i,
					target.getBooleanVariable(i).getValue());

		IProblem p = new Conjunction(bnnbf);
		this.setClauses(p.getClauses());
	}

	public BitStringFixer(IBitString target, boolean[] c) throws Exception
	{
		if (target.size() != c.length)
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			BitStringBitFixer[] bnnbf = new BitStringBitFixer[c.length];
			for (int i = 0; i < bnnbf.length; i++)
				bnnbf[i] = new BitStringBitFixer(target, i, c[i]);

			IProblem p = new Conjunction(bnnbf);
			this.setClauses(p.getClauses());
		}
	}

	public BitStringFixer(IBitString target, IBitString data) throws Exception
	{
		this(target, data.getBVArray());
	}

	public BitStringFixer(IBitString target, IBooleanVariable[] data)
			throws Exception
	{
		if (target.size() != data.length)
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			BitStringBitFixer[] bnnbf = new BitStringBitFixer[data.length];
			for (int i = 0; i < bnnbf.length; i++)
				bnnbf[i] = new BitStringBitFixer(target, i, data[i].getValue());

			IProblem p = new Conjunction(bnnbf);
			this.setClauses(p.getClauses());
		}
	}

	public BitStringFixer(IBitString target, String s) throws Exception
	{
		int j = Math.min(s.length(), target.size());
		IProblem[] p = new Problem[j];

		for (int i = 0; i < j; i++)
		{
			if (s.charAt(i) != '0' && s.charAt(i) != '1')
				throw new BitStringFixerException(
						"Attempted to fix an IBitString using improperly formatted data.");
			if (s.charAt(i) == '0')
				p[i] = new BitFixer(target.getBooleanVariable(i), false);
			if (s.charAt(i) == '1')
				p[i] = new BitFixer(target.getBooleanVariable(i), true);
		}
		IProblem pp = new Conjunction(p);
		this.setClauses(pp.getClauses());
	}
}