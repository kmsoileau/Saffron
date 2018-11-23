package demos;

import bitstringlists.BinaryBitStringListOperationer;
import bitstrings.BitString;

public class BinaryBitStringListOperationerDemo
{
	public static void main(String[] args) throws Exception
	{
		BinaryBitStringListOperationer o = new BinaryBitStringListOperationer();
		o.addRule(new BitString("a", "0"), new BitString("b", "0"),
				new BitString("c", "0"));
		o.addRule(new BitString("a", "0"), new BitString("b", "1"),
				new BitString("c", "1"));
		o.addRule(new BitString("a", "1"), new BitString("b", "0"),
				new BitString("c", "1"));
		o.addRule(new BitString("a", "1"), new BitString("b", "1"),
				new BitString("c", "0"));
		System.out.println(o);
	}
}
