/*
 * IBitStringList.java	1.0 05/04/28
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstringlists;

import java.util.ArrayList;

import bits.IBitString;
import bitstrings.BitString;

public interface IBitStringList
{
	boolean add(IBitString s001);

	void add(int j, BitString bitString);

	IBitString getBitString(int i) throws Exception;

	String getName();

	boolean isEmpty();

	void set(int i, BitString bitString);

	int size();

	IBitString[] toArray(IBitString[] iBitStrings);

	String toBits();

	ArrayList<IBitString> toList() throws Exception;
}