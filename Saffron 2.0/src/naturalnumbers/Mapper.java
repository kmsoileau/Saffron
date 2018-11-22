/*
 * Mapping.java	1.01 05/11/02
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package naturalnumbers;

import java.util.ArrayList;
import java.util.Vector;

import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.ProblemPair;
import bitstrings.BitStringFixer;
import naturalnumbers.exceptions.MapperException;

/**
 * <p>
 * Title: <tt>Mapper</code> class
 * </p>
 * <p>
 * Description: This class, an extension of <tt>Problem</code>, is created from
 * pairs of instances of <tt>Problem</code> passed to one of the <tt>Mapper</code>
 * constructors. An instance of <tt>Mapper</code> is satisfied if and only if for
 * some pair of <tt>Problem</code>'s, both <tt>Problem</code>'s are satisfied. For
 * instance, suppose we define
 * <p>
 * <tt>...</code>
 * <p>
 * <tt>IProblem[] ipr1=new IProblem[]{p7,p9,p2};</code>
 * <p>
 * <tt>IProblem[] ipr2=new IProblem[]{p3,p8,p1};
 * IBooleanVariable[] bool=new IBooleanVariable[]{b1,b2};
 * IProblem map1=new Mapper(ip1,ipr2,bool);
 * ...
 * </code>
 * </p>
 * <p>
 * Then <tt>map1</code> will be satisfied when
 * <p>
 * (1) <tt>p7</code> and <tt>p3</code> are both satisfied, or
 * <p>
 * (2) <tt>p9</code> and <tt>p8</code> are both satisfied, or
 * <p>
 * (3) <tt>p2</code> and <tt>p1</code> are both satisfied.
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.01
 */

public class Mapper extends Problem implements IProblem
{
	private static String binCount(int bitsrequired, long count)
			throws Exception
	{
		return reverse(new NaturalNumber(count).toBits()).substring(
				NaturalNumber.getLength() - bitsrequired);
	}

	private static IProblem map(IProblem[] p1, IProblem[] p2) throws Exception
	{
		if (p1 == null || p2 == null)
			throw new MapperException(
					"A null IProblem[] was passed to a constructor.");
		if (p1.length == 0 || p2.length == 0)
			throw new MapperException(
					"An IProblem[] of zero length was passed to a constructor.");
		if (p1.length != p2.length)
			throw new MapperException(
					"IProblem[]'s of differing lengths were passed to a constructor.");
		IBooleanVariable[] b = new IBooleanVariable[p1.length - 1];
		for (int i = 0; i < b.length; i++)
			b[i] = BooleanVariable.getBooleanVariable();
		return map(p1, p2, b);
	}

	private static IProblem map(IProblem[] p1, IProblem[] p2,
			IBooleanVariable[] b) throws Exception
	{
		if (p1 == null || p2 == null || b == null)
			throw new MapperException(
					"A null IProblem[] was passed to a constructor.");
		if (p1.length == 0 || p2.length == 0)
			throw new MapperException(
					"An IProblem[] of zero length was passed to a constructor.");
		if (p1.length != p2.length)
			throw new MapperException(
					"IProblem[]'s of differing lengths were passed to a constructor.");
		if (b.length != p1.length - 1)
			throw new MapperException(
					"IBooleanVariable[] of improper length was passed to a constructor. Proper length in this case is "
							+ (p1.length - 1) + ".");
		IProblem[] d = new IProblem[p1.length];
		for (int i = 0; i < d.length; i++)
			d[i] = new Conjunction(p1[i], p2[i]);
		IProblem e = new Disjunction(d, b);
		return e;
	}

	private static String reverse(String bits)
	{
		if (bits.length() == 1)
			return bits;
		return (reverse(bits.substring(1)).concat(bits.substring(0, 1)));
	}

	public Mapper(ArrayList<Integer> integerArray, IBitString index,
			INaturalNumber valNN) throws Exception
	{
		this(integerArray.toArray(new Integer[0]), index, valNN);
	}

	public Mapper(int[] integerArray, IBitString index, INaturalNumber valNN)
			throws Exception
	{
		int num = integerArray.length;
		ProblemPair[] pp = new ProblemPair[num];
		int bitsrequired = (int) (Math.ceil(Math.log(1d * num) / Math.log(2d)));
		for (int i = 0; i < pp.length; i++)
			pp[i] = new ProblemPair(new BitStringFixer(index, binCount(
					bitsrequired, i)), new NaturalNumberFixer(valNN,
					integerArray[i]));

		this.setClauses(new Mapper(pp).getClauses());
	}

	public Mapper(Integer[] integerArray, IBitString index, INaturalNumber valNN)
			throws Exception
	{
		int num = integerArray.length;
		ProblemPair[] pp = new ProblemPair[num];
		int bitsrequired = (int) (Math.ceil(Math.log(1d * num) / Math.log(2d)));
		for (int i = 0; i < pp.length; i++)
			pp[i] = new ProblemPair(new BitStringFixer(index, binCount(
					bitsrequired, i)), new NaturalNumberFixer(valNN,
					integerArray[i]));

		this.setClauses(new Mapper(pp).getClauses());
	}

	public Mapper(IProblem[] p1, IProblem[] p2) throws Exception
	{
		IProblem p = map(p1, p2);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	public Mapper(IProblem[] p1, IProblem[] p2, IBooleanVariable[] b)
			throws Exception
	{
		IProblem p = map(p1, p2, b);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	public Mapper(Long[] integerArray, IBitString index, INaturalNumber valNN)
			throws Exception
	{
		int num = integerArray.length;
		ProblemPair[] pp = new ProblemPair[num];
		int bitsrequired = (int) (Math.ceil(Math.log(1d * num) / Math.log(2d)));
		for (int i = 0; i < pp.length; i++)
			pp[i] = new ProblemPair(new BitStringFixer(index, binCount(
					bitsrequired, i)), new NaturalNumberFixer(valNN,
					integerArray[i]));

		this.setClauses(new Mapper(pp).getClauses());
	}

	public Mapper(ProblemPair[] pair) throws Exception
	{
		IProblem[] p1 = new IProblem[pair.length];
		IProblem[] p2 = new IProblem[pair.length];

		for (int i = 0; i < pair.length; i++)
		{
			p1[i] = pair[i].getFirst();
			p2[i] = pair[i].getSecond();
		}
		this.setClauses(new Mapper(p1, p2).getClauses());
	}

	public Mapper(ProblemPair[] pair, IBooleanVariable[] b) throws Exception
	{
		IProblem[] p1 = new IProblem[pair.length];
		IProblem[] p2 = new IProblem[pair.length];

		for (int i = 0; i < pair.length; i++)
		{
			p1[i] = pair[i].getFirst();
			p2[i] = pair[i].getSecond();
		}
		this.setClauses(new Mapper(p1, p2, b).getClauses());
	}

	public Mapper(Vector<Integer> integerArray, IBitString index,
			INaturalNumber valNN) throws Exception
	{
		this(integerArray.toArray(new Integer[0]), index, valNN);
	}
}