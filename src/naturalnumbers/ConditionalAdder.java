package naturalnumbers;

import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.exceptions.ConditionalAdderException;
import naturalnumbers.lists.INaturalNumberList;

public class ConditionalAdder extends Problem implements IProblem
{
	public ConditionalAdder(INaturalNumber[] numbers, IBitString membership, INaturalNumber conditionalSum)
			throws Exception
	{
		this(numbers, membership.toBooleanVariableArray(), conditionalSum);
	}

	public ConditionalAdder(INaturalNumber[] numbers, IBooleanVariable[] membership, INaturalNumber conditionalSum)
			throws Exception
	{
		if (numbers.length == 0 || membership.length == 0)
			throw (new ConditionalAdderException("numbers or bits array of zero length was passed to constructor."));
		if (numbers.length != membership.length)
			throw (new ConditionalAdderException(
					"numbers or bits arrays of different lengths were passed to constructor."));
		if (conditionalSum == null)
			throw (new ConditionalAdderException("A null conditionalSum variable was passed to constructor."));
		if (conditionalSum.size() == 0)
			throw (new ConditionalAdderException("A conditionalSum variable of size zero was passed to constructor."));

		INaturalNumber[] subAnswer = new INaturalNumber[numbers.length];
		INaturalNumber[] subTotal = new INaturalNumber[numbers.length];
		subTotal[0] = new NaturalNumber();
		subAnswer[0] = new NaturalNumber();

		IProblem[] stagingArray = new IProblem[2 * numbers.length + 1];
		int stagingIndex = 0;
		stagingArray[stagingIndex++] = new NaturalNumberBitMultiply(membership[0], numbers[0], subAnswer[0]);
		stagingArray[stagingIndex++] = new NaturalNumberEqualizer(subTotal[0], subAnswer[0]);
		for (int i = 1; i < numbers.length; i++)
		{
			subAnswer[i] = new NaturalNumber();
			subTotal[i] = new NaturalNumber();
			stagingArray[stagingIndex++] = new NaturalNumberBitMultiply(membership[i], numbers[i], subAnswer[i]);
			stagingArray[stagingIndex++] = new NaturalNumberAdder(subTotal[i - 1], subAnswer[i], subTotal[i]);
		}
		stagingArray[stagingIndex++] = new NaturalNumberEqualizer(subTotal[numbers.length - 1], conditionalSum);
		IProblem problem = new Conjunction(stagingArray);
		this.setClauses(problem.getClauses());
	}

	public ConditionalAdder(INaturalNumberList numbersArray, IBitString membership, INaturalNumber conditionalSum)
			throws Exception
	{
		this(numbersArray.getNaturalNumberArray(), membership.getBVArray(), conditionalSum);
	}

	public ConditionalAdder(INaturalNumberList numbersArray, IBooleanVariable[] membership,
			INaturalNumber conditionalSum) throws Exception
	{
		this(numbersArray.getNaturalNumberArray(), membership, conditionalSum);
	}
}