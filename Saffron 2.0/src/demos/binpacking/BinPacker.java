/**
 * 
 * ConditionalAdderDemo.java	1.0 2018/11/05
 * Copyright 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos.binpacking;

import java.util.ArrayList;
import java.util.List;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class BinPacker
{
	private static IProblem binPackingProblem;

	public static IProblem getProblem()
	{
		return binPackingProblem;
	}

	public static ArrayList<ArrayList<Item>> pack(Item[] items, Bin[] bins)
			throws Exception
	{
		long startTimeMillis = System.currentTimeMillis();
		int numberBins = bins.length;
		int numberItems = items.length;
		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[1 + 3 * numberBins + numberItems];

		long maxBin = Integer.MIN_VALUE;
		for (int i = 0; i < numberBins; i++)
			if (bins[i].getCapacity() > maxBin)
				maxBin = bins[i].getCapacity();
		NaturalNumber.setLargestNaturalNumber(maxBin);

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tBuilding partitionProblem...");
		IBooleanVariable[][] partition = new IBooleanVariable[numberBins][numberItems];
		for (int i = 0; i < numberBins; i++)
		{
			IBooleanVariable[] currentBin = partition[i];
			for (int j = 0; j < numberItems; j++)
				currentBin[j] = BooleanVariable.getBooleanVariable("partition-"
						+ i + "-" + j);
		}
		IProblem partitionProblem = new BitArrayPartition(partition);
		stagingArray[stagingIndex++] = partitionProblem;

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tBuilding adderProblem...");
		INaturalNumber[] itemSizeNaturalNumberArray = new INaturalNumber[numberItems];
		for (int i = 0; i < numberItems; i++)
			itemSizeNaturalNumberArray[i] = new NaturalNumber(
					items[i].getName());
		INaturalNumber[] condSum = new INaturalNumber[numberBins];
		IProblem[] adderProblemArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
		{
			System.out.println((System.currentTimeMillis() - startTimeMillis)
					/ 1000. + ":" + "\t\t\tBuilding adderProblemArray[" + i
					+ "]...");
			condSum[i] = new NaturalNumber("NNCondSum-" + i);
			adderProblemArray[i] = new ConditionalAdder(
					itemSizeNaturalNumberArray, partition[i], condSum[i]);
			// System.out.println((System.currentTimeMillis()-startTimeMillis)/1000.+":"+"stagingArray.add(adderProblemArray["+i+"]");
			stagingArray[stagingIndex++] = adderProblemArray[i];
		}

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tBuilding binFitterProblem...");
		INaturalNumber[] binCapacityNNArray = new INaturalNumber[numberBins];
		for (int i = 0; i < numberBins; i++)
			binCapacityNNArray[i] = new NaturalNumber("BinSize-" + i);
		IProblem[] binFitProblemArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
		{
			binFitProblemArray[i] = new NaturalNumberOrderer(condSum[i],
					binCapacityNNArray[i]);
			stagingArray[stagingIndex++] = binFitProblemArray[i];
		}

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tBuilding binCapacityFixerProblem...");
		IProblem[] binCapacityProblemArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
		{
			binCapacityProblemArray[i] = new NaturalNumberFixer(
					binCapacityNNArray[i], bins[i].getCapacity());
			stagingArray[stagingIndex++] = binCapacityProblemArray[i];
		}

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tBuilding sizesProblem...");
		IProblem[] sizesProblemArray = new IProblem[numberItems];
		for (int i = 0; i < numberItems; i++)
		{
			sizesProblemArray[i] = new NaturalNumberFixer(
					itemSizeNaturalNumberArray[i], items[i].getSize());
			stagingArray[stagingIndex++] = sizesProblemArray[i];
		}

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tBuilding binPackingProblem...");
		binPackingProblem = new Conjunction(stagingArray);

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tSolving SAT problem...");
		List<IBooleanLiteral> blList = binPackingProblem.findModel(Problem
				.defaultSolver());

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tReturning solution...");
		if (blList != null && blList.size() > 0)
		{
			BooleanLiteral.interpret(blList);
			ArrayList<ArrayList<Item>> solution = new ArrayList<ArrayList<Item>>();
			for (int i = 0; i < numberBins; i++)
			{
				IBooleanVariable[] currentBin = partition[i];
				ArrayList<Item> currentBinContents = new ArrayList<Item>();
				for (int j = 0; j < numberItems; j++)
					if (currentBin[j].getValue())
						currentBinContents.add(items[j]);
				solution.add(currentBinContents);
			}
			BooleanLiteral.reset(blList);
			System.out.println((System.currentTimeMillis() - startTimeMillis)
					/ 1000. + ":" + "Finis");
			return solution;
		}
		else
			return null;
	}
}