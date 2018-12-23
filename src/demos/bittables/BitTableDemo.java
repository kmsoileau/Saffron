package demos.bittables;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BooleanVariable;
import bittables.BitTable;
import bittables.IBitTable;

public class BitTableDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitTable bt = new BitTable("Kerry", new BooleanVariable[7][13]);
		for (int row = 0; row < bt.numberRows(); row++)
			for (int column = 0; column < bt.numberColumns(); column++)
				bt.setBooleanVariable(row, column,
						BooleanVariable.getBooleanVariable());
		System.out.println(bt);
	}
}