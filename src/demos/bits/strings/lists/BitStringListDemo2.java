package demos.bits.strings.lists;

import bits.IBitString;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.IBitStringList;

public class BitStringListDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList s1 = new BitStringList();
		System.out.println("s1.getName() = " + s1.getName());

		IBitString s001 = new BitString("001");
		IBitString s010 = new BitString("010");
		IBitString s110 = new BitString("110");
		IBitString s110x = s110;
		IBitString s001x = s001;

		s1.add(s001);
		s1.add(s010);
		s1.add(s110);
		s1.add(s110x);
		s1.add(s001x);

		System.out.println("s1.getName() = " + s1.getName());
		System.out.println("s1.size() = " + s1.size());
		for (int i = 0; i < s1.size(); i++)
			System.out.println("s1.getBitString(" + i + ") = " + s1.getBitString(i));
	}
}
