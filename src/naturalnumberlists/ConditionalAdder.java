package naturalnumberlists;

import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;

public class ConditionalAdder extends naturalnumbers.ConditionalAdder
{
	public ConditionalAdder(INaturalNumberList numbersArray,
			IBitString membership, INaturalNumber conditionalSum)
			throws Exception
	{
		super(numbersArray.getNaturalNumberArray(), membership.getBVArray(),
				conditionalSum);
	}

	public ConditionalAdder(INaturalNumberList numbersArray,
			IBooleanVariable[] membership, INaturalNumber conditionalSum)
			throws Exception
	{
		super(numbersArray.getNaturalNumberArray(), membership, conditionalSum);
	}
}