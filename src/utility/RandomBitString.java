package utility;

public class RandomBitString
{
	public static String randomBitstring(int n)
	{
		char[] bits = new char[n];
		for (int i = 0; i < n; i++)
			bits[i] = Math.random() > .5 ? '1' : '0';
		return new String(bits);
	}
}
