/**
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2019/02/10
 */

package integers;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import naturalnumbers.NaturalNumber;

public class Integer implements IInteger
{
	private static long intCount;

	public static long getIntCount()
	{
		return intCount;
	}

	public static int getLength()
	{
		return INaturalNumber.DEFAULTLENGTH;
	}

	private INaturalNumber absValue;
	private String name;

	private IBooleanVariable sign;

	// public Integer(long n) throws Exception
	// {
	// this("Integer-" + nNCount++, n);
	// }

	public Integer() throws Exception
	{
		this("Integer-" + intCount++, BooleanVariable.getBooleanVariable(), new NaturalNumber());
	}

	// private Integer(INaturalNumber absValue) throws Exception
	// {
	// this("Integer-" + nNCount++, BooleanVariable.getBooleanVariable(),
	// absValue);
	// }

	// public Integer(String name, IBitString data) throws Exception
	// {
	// super(name, data.getBVArray(NaturalNumber.getLength()));
	// }

	public Integer(IBooleanVariable sign, INaturalNumber absValue) throws Exception
	{
		this("Integer-" + intCount++, sign, absValue);
	}

	public Integer(long n) throws Exception
	{
		this("Integer-" + intCount++, n);
	}

	public Integer(String name) throws Exception
	{
		this(name, BooleanVariable.getBooleanVariable(), new NaturalNumber());
	}

	public Integer(String name, IBooleanVariable sign, INaturalNumber absValue) throws Exception
	{
		this.name = name;
		this.sign = sign;
		this.absValue = absValue;
	}

	public Integer(String name, long n) throws Exception
	{
		this.setName(name);
		this.setAbsValue(new NaturalNumber(Math.abs(n)));
		this.setSign(BooleanVariable.getBooleanVariable(n >= 0));
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false; // this is never equal to null.
		if (!(o instanceof Integer))
			return false;
		else
			return (this.getName().compareTo(((Integer) o).getName())) == 0;
	}

	@Override
	public INaturalNumber getAbsValue()
	{
		return absValue;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public IBooleanVariable getSign()
	{
		return sign;
	}

	@Override
	public long getValue()
	{
		long av = this.getAbsValue().getValue();
		boolean sg = this.getSign().getValue();
		if (sg)
			return av;
		else
			return -av;
	}

	public void setAbsValue(INaturalNumber absValue)
	{
		this.absValue = absValue;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	// public long toDecimal()
	// {
	// long abs = this.absValue.getValue();
	// return this.sign.getValue() ? abs : -abs;
	// }

	public void setSign(IBooleanVariable sign)
	{
		this.sign = sign;
	}

	@Override
	public String toString()
	{
		return "" + this.getValue();
	}
}
