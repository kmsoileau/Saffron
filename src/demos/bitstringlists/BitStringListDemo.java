package demos.bitstringlists;

import bits.IBitString;
import bitstringlists.BitStringList;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

/**
 * <p>
 * Title: BitStringListDemo
 * </p>
 * <p>
 * Description: This is a sample application showing the use of the
 * BitStringList class.
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */
public class BitStringListDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList s1 = new BitStringList();
		System.out.println("s1.getName() = " + s1.getName());

		IBitStringList s2 = new BitStringList("y", new boolean[][]
		{
		{ true, false, true },
		{ false, true, false, true },
		{ true, false, false },
		{ false, true, false } });
		System.out.println("s2.getName() = " + s2.getName());
		for (int i = 0; i < s2.size(); i++)
			System.out.println("s2.getBitString(" + i + ") = "
					+ s2.getBitString(i));

		IBitStringList s3 = new BitStringList("s3list", new IBitString[]
		{ new BitString("001"), new BitString("010"), new BitString("110") });
		System.out.println("s3.getName() = " + s3.getName());
		for (int i = 0; i < s3.size(); i++)
			System.out.println("s3.getBitString(" + i + ") = "
					+ s3.getBitString(i));

		IBitStringList s4 = new BitStringList("s4list");
		System.out.println("s4.getName() = " + s4.getName());
		System.out.println("s4.size() = " + s4.size());

		IBitStringList s5 = new BitStringList("s5list");
		System.out.println("s5.getName() = " + s5.getName());
		System.out.println("s5.size() = " + s5.size());

		IBitString s001 = new BitString("001");
		IBitString s010 = new BitString("010");
		IBitString s110 = new BitString("110");
		IBitString s110x = s110;
		IBitString s001x = s001;
		IBitStringList s6 = new BitStringList("s6list", new IBitString[]
		{ s001, s110x, s001, new BitString(), s010, s001x, s110 });
		System.out.println("s6.getName() = " + s6.getName());
		System.out.println("s6.size() = " + s6.size());
		for (int i = 0; i < s6.size(); i++)
			System.out.println("s6.getBitString(" + i + ") = "
					+ s6.getBitString(i));
	}
}