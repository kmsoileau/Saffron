/*
 * IBitTable.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bittables;

import bits.IBooleanVariable;

public interface IBitTable
{
	IBooleanVariable getBooleanVariable(int i, int j);

	String getName();

	boolean isSameSizeAs(IBitTable b);

	int numberColumns();

	int numberRows();

	void setBooleanVariable(int i, int j, IBooleanVariable ib);
}