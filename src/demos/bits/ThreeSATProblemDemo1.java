/*
 * ThreeSATProblemDemo.java	1.0 05/05/04
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
/**
 * <p>Title: BitStringListSorterDemo</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos.bits;

import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;

public class ThreeSATProblemDemo1
{
	public static void main(String[] args) throws Exception
	{
		Clause[] clause = new Clause[5];
		IBooleanVariable[] bva = new IBooleanVariable[5];

		clause[0] = new Clause();
		clause[1] = new Clause();
		clause[2] = new Clause();
		clause[3] = new Clause();
		clause[4] = new Clause();

		bva[0] = BooleanVariable.getBooleanVariable("foo30");
		bva[1] = BooleanVariable.getBooleanVariable("foo11");
		bva[2] = BooleanVariable.getBooleanVariable("foo23");
		bva[3] = BooleanVariable.getBooleanVariable("foo83");
		bva[4] = BooleanVariable.getBooleanVariable("foo87");

		clause[0].orNot(bva[0]);
		System.out.println(clause[0]);
		System.out.println(clause[0].ThreeSATProblem());

		clause[1].orNot(bva[0]);
		clause[1].or(bva[1]);
		System.out.println(clause[1]);
		System.out.println(clause[1].ThreeSATProblem());

		clause[2].orNot(bva[0]);
		clause[2].or(bva[1]);
		clause[2].or(bva[2]);
		System.out.println(clause[2]);
		System.out.println(clause[2].ThreeSATProblem());

		clause[3].orNot(bva[0]);
		clause[3].or(bva[1]);
		clause[3].or(bva[2]);
		clause[3].or(bva[3]);
		System.out.println(clause[3]);
		System.out.println(clause[3].ThreeSATProblem());

		clause[4].orNot(bva[0]);
		clause[4].or(bva[1]);
		clause[4].or(bva[2]);
		clause[4].or(bva[3]);
		clause[4].orNot(bva[4]);
		System.out.println(clause[4]);
		System.out.println(clause[4].ThreeSATProblem());
	}
}