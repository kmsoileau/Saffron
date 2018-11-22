package demos.scheduling;

/**
 * 
 * ConditionalAdderDemo.java	1.0 2018/11/05
 * Copyright 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import bits.BitArrayPartition;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class Scheduler
{
	private static IProblem jobSchedulingProblem;

	public static IProblem getProblem()
	{
		return jobSchedulingProblem;
	}

	public static ArrayList<ArrayList<Task>> schedule(Task[] task,
			Processor[] proc) throws Exception
	{
		long startTimeMillis = System.currentTimeMillis();
		int numberProcs = proc.length;
		int numberTasks = task.length;
		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[1 + 1 + numberTasks
				* numberTasks * numberProcs + numberTasks];

		// Partition Problem
		IBooleanVariable[][] partition = new IBooleanVariable[numberProcs][numberTasks];
		for (int i = 0; i < numberProcs; i++)
		{
			IBooleanVariable[] currentBin = partition[i];
			for (int j = 0; j < numberTasks; j++)
				currentBin[j] = BooleanVariable.getBooleanVariable("partition-"
						+ proc[i].getName() + "-" + task[j]);
		}
		IProblem partitionProblem = new BitArrayPartition(partition);
		stagingArray[stagingIndex++] = partitionProblem;

		// Start and Finish Times NaturalNumbers
		// INaturalNumber[] start = new INaturalNumber[numberTasks];
		// for (int i = 0; i < numberTasks; i++)
		// task[i].start[i] = new NaturalNumber("Start-" + task[i].getName());
		// INaturalNumber[] finish = new INaturalNumber[numberTasks];
		// for (int i = 0; i < finish.length; i++)
		// finish[i] = new NaturalNumber("Finish-" + task[i].getName());

		// Durations NaturalNumbers
		// NaturalNumber[] duration = new INaturalNumber[numberTasks];
		// for (int i = 0; i < duration.length; i++)
		// duration[i] = new NaturalNumber("Duration-" + task[i].getDuration());

		// Bind Durations Problem
		IProblem[] bindDurationsProblem = new IProblem[numberTasks];
		for (int i = 0; i < numberTasks; i++)
			bindDurationsProblem[i] = new NaturalNumberFixer(
					task[i].getNNDuration(), task[i].getDuration());
		stagingArray[stagingIndex++] = new Conjunction(bindDurationsProblem);

		// Impose Precedence Relations
		for (int i = 0; i < numberTasks; i++)
		{
			Task currentPostTask = task[i];
			System.out.println("Current post task = "
					+ currentPostTask.getName());
			List<Task> currPreds = currentPostTask.getPredecessors();
			System.out.println("Predecessors = " + currPreds);
			if (currPreds == null)
				continue;
			for (int j = 0; j < numberTasks && currPreds.contains(task[j]); j++)
			{
				Task currentPreTask = task[j];
				INaturalNumber currentPostTaskStart = currentPostTask
						.getNNStart();
				INaturalNumber currentPreTaskFinish = currentPreTask
						.getNNFinish();
				System.out.println("Constraining "
						+ currentPreTaskFinish.getName()
						+ " to be not greater than "
						+ currentPostTaskStart.getName());
				IProblem constrain = new NaturalNumberOrderer(
						currentPreTaskFinish, currentPostTaskStart);
				for (int k = 0; k < numberProcs; k++)
				{
					// Task i and j are not both assigned to procecessor k, or j
					// finishes before i starts.
					IBooleanVariable[] currentProcessorTaskAssignments = partition[k];
					stagingArray[stagingIndex++] = new Disjunction(
							new BitFixer(currentProcessorTaskAssignments[i],
									false), new BitFixer(
									currentProcessorTaskAssignments[j], false),
							constrain);
				}
			}
		}

		// Impose Duration Relations
		for (int i = 0; i < numberTasks; i++)
		{
			stagingArray[stagingIndex++] = new NaturalNumberAdder(
					task[i].getNNStart(), task[i].getNNDuration(),
					task[i].getNNFinish());
		}

		jobSchedulingProblem = new Conjunction(stagingArray);

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tSolving SAT problem...");
		List<IBooleanLiteral> blList = jobSchedulingProblem.findModel(Problem
				.defaultSolver());

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tReturning solution...");
		if (blList != null && blList.size() > 0)
		{
			BooleanLiteral.interpret(blList);
			ArrayList<ArrayList<Task>> solution = new ArrayList<ArrayList<Task>>();
			for (int i = 0; i < numberProcs; i++)
			{
				IBooleanVariable[] currentProc = partition[i];
				ArrayList<Task> currentProcAssignments = new ArrayList<Task>();
				for (int j = 0; j < numberTasks; j++)
					if (currentProc[j].getValue())
						currentProcAssignments.add(task[j]);
				solution.add(currentProcAssignments);
			}
			for (int i = 0; i < numberTasks; i++)
			{
				long currStart = task[i].getNNStart().toDecimal();
				task[i].setStart(currStart);
				long currFinish = task[i].getNNFinish().toDecimal();
				task[i].setFinish(currFinish);
			}
			BooleanLiteral.reset(blList);
			// System.out.println((System.currentTimeMillis() - startTimeMillis)
			// / 1000. + ":" + "Finis");
			return solution;
		}
		else
			return null;
	}
}

/*
 * public class Scheduler { private static IProblem jobSchedulingProblem;
 * 
 * public static IProblem getProblem() { return jobSchedulingProblem; }
 * 
 * public static ArrayList<ArrayList<Task>> schedule(Task[] task, Processor[]
 * proc) throws Exception { long startTimeMillis = System.currentTimeMillis();
 * int numberProcs = proc.length; int numberTasks = task.length; int
 * stagingIndex = 0; IProblem[] stagingArray = new IProblem[1 + 1 +
 * numberTasks*numberTasks*numberProcs];
 * 
 * // Partition Problem IBooleanVariable[][] partition = new
 * IBooleanVariable[numberProcs][numberTasks]; for (int i = 0; i < numberProcs;
 * i++) { IBooleanVariable[] currentBin = partition[i]; for (int j = 0; j <
 * numberTasks; j++) currentBin[j] =
 * BooleanVariable.getBooleanVariable("partition-" + proc[i].getName() + "-" +
 * task[j]); } IProblem partitionProblem = new BitArrayPartition(partition);
 * stagingArray[stagingIndex++] = partitionProblem;
 * 
 * // Start and Finish Times NaturalNumbers INaturalNumber[] start = new
 * INaturalNumber[numberTasks]; for (int i = 0; i < start.length; i++) start[i]
 * = new NaturalNumber("Start-" + task[i].getName()); INaturalNumber[] finish =
 * new INaturalNumber[numberTasks]; for (int i = 0; i < finish.length; i++)
 * finish[i] = new NaturalNumber("Finish-" + task[i].getName());
 * 
 * // Durations NaturalNumbers INaturalNumber[] duration = new
 * INaturalNumber[numberTasks]; for (int i = 0; i < duration.length; i++)
 * duration[i] = new NaturalNumber("Duration-" + task[i].getDuration());
 * 
 * // Bind Durations Problem IProblem[] bindDurationsProblem = new
 * IProblem[duration.length]; for (int i = 0; i < duration.length; i++)
 * bindDurationsProblem[i] = (IProblem) new NaturalNumberFixer( duration[i],
 * task[i].getDuration()); stagingArray[stagingIndex++] = new
 * Conjunction(bindDurationsProblem);
 * 
 * for(int i=0;i<numberTasks;i++) { for(int j=0;j<numberTasks;j++) { for(int
 * k=0;k<numberProcs;k++) { stagingArray[stagingIndex++] =new Disjunction( new
 * BitFixer(partition[k][i],false), new BitFixer(partition[k][j],false), new
 * NaturalNumberOrderer(finish[j],start[i]) ); } } }
 * 
 * jobSchedulingProblem = new Conjunction(stagingArray);
 * 
 * System.out.println((System.currentTimeMillis() - startTimeMillis) / 1000. +
 * ":" + "\tSolving SAT problem..."); List<IBooleanLiteral> blList =
 * jobSchedulingProblem.findModel(Problem .defaultSolver());
 * 
 * System.out.println((System.currentTimeMillis() - startTimeMillis) / 1000. +
 * ":" + "\tReturning solution..."); if (blList != null && blList.size() > 0) {
 * BooleanLiteral.interpret(blList); ArrayList<ArrayList<Task>> solution = new
 * ArrayList<ArrayList<Task>>(); for (int i = 0; i < numberProcs; i++) {
 * IBooleanVariable[] currentProc = partition[i]; ArrayList<Task>
 * currentProcAssignments = new ArrayList<Task>(); for (int j = 0; j <
 * numberTasks; j++) if (currentProc[j].getValue())
 * currentProcAssignments.add(task[j]); solution.add(currentProcAssignments); }
 * for (int i = 0; i < numberTasks; i++) { System.out.println(start[i]);
 * System.out.println(finish[i]); } BooleanLiteral.reset(blList);
 * //System.out.println((System.currentTimeMillis() - startTimeMillis) // /
 * 1000. + ":" + "Finis"); return solution; } else return null; } }
 */