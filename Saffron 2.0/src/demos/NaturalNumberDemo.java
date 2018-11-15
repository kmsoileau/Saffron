package demos;

import bits.IBitString;
import bits.Number;
import bitstrings.BitString;
import naturalnumbers.NaturalNumber;

/**
 * <p>
 * Title: TBS
 * </p>
 * <p>
 * Description: TBS
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

public class NaturalNumberDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(6);
		Number n = new Number(56L);
		IBitString ib = new BitString("noname", n.getBitArray());
		NaturalNumber NaturalNumber1 = new NaturalNumber("kerry", ib);
		String s = NaturalNumber1.toString();
		System.out.println(s);
	}
}