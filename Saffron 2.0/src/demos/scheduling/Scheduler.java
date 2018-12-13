package demos.scheduling;

import java.util.ArrayList;
import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import bits.BitArrayPartition;
import bits.BitFixer;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Partition;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/11/22
 */
public class Scheduler extends Problem implements IProblem
{
	static IProblem doBindDurationsProblem(Task[] task) throws Exception
	{
		int numberTasks = task.length;
		IProblem[] bindDurationsProblem = new IProblem[numberTasks];
		for (int i = 0; i < numberTasks; i++)
			bindDurationsProblem[i] = new NaturalNumberFixer(
					task[i].getNNDuration(), task[i].getDuration());
		return new Conjunction(bindDurationsProblem);
	}

	static IProblem doDurationRelation(Task task) throws Exception
	{
		return new NaturalNumberAdder(task.getNNStart(), task.getNNDuration(),
				task.getNNFinish());
	}

	static IProblem[] doInProcessorPrecProblem(Task[] task, Processor[] proc,
			Partition partition) throws Exception
	{
		IProblem[] array = new IProblem[proc.length * task.length * task.length];
		int arrayCounter = 0;
		for (int i = 0; i < proc.length; i++)
		{
			IBooleanVariable[] currentProc = partition.getSet(i);
			for (int j = 0; j < task.length; j++)
			{
				for (int k = j + 1; k < task.length; k++)
				{
					/*
					 * Either !currentProc[j] or !currentProc[k] or
					 * task[j].getNNFinish() <= task[k].getNNStart() or
					 * task[k].getNNFinish() <= task[j].getNNStart()
					 */
					array[arrayCounter++] = new Disjunction(new BitFixer(
							currentProc[j], false), new BitFixer(
							currentProc[k], false), new NaturalNumberOrderer(
							task[j].getNNFinish(), task[k].getNNStart()),
							new NaturalNumberOrderer(task[k].getNNFinish(),
									task[j].getNNStart()));
				}
			}
		}
		return array;
	}

	static IProblem doPartitionProblem(Task[] task, Processor[] proc,
			Partition partition) throws Exception
	{
		int numberProcs = proc.length;
		int numberTasks = task.length;
		for (int i = 0; i < numberProcs; i++)
		{
			IBooleanVariable[] currentBin = partition.getSet(i);
			for (int j = 0; j < numberTasks; j++)
				currentBin[j] = BooleanVariable.getBooleanVariable("partition-"
						+ proc[i].getName() + "-" + task[j].getName());
		}
		return new BitArrayPartition(partition);
	}

	static ArrayList<IProblem> doPrecProblem(Task currSucc) throws Exception
	{
		List<Task> currSuccPreds = currSucc.getPredecessors();
		String pList = currSucc.predList();
		if (pList != null)
			System.out.println(currSucc.getName() + " has preds: " + pList);
		if (currSuccPreds == null)
			return null;
		ArrayList<IProblem> precProblem = new ArrayList<IProblem>();
		for (Task currPred : currSuccPreds)
		{
			precProblem.add(new NaturalNumberOrderer(currPred.getNNFinish(),
					currSucc.getNNStart()));
		}
		return precProblem;
	}

	static IProblem doTimeLimit(Task[] task, int limit) throws Exception
	{
		IProblem[] array = new IProblem[task.length];
		NaturalNumber limitNN = new NaturalNumber("Limit");
		IProblem limitProblem = new NaturalNumberFixer(limitNN, limit);
		for (int i = 0; i < task.length; i++)
		{
			array[i] = new NaturalNumberOrderer(task[i].getNNFinish(), limitNN);
		}
		return new Conjunction(limitProblem, new Conjunction(array));
	}

	public Scheduler(Task[] task, Processor[] proc, int timeLimit,
			Partition partition) throws Exception
	{
		int numberTasks = task.length;
		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[1 + 2 + 2 * numberTasks];
		// Partition Problem
		stagingArray[stagingIndex++] = doPartitionProblem(task, proc, partition);

		// Bind Durations Problem
		IProblem bindDurationsProblem = doBindDurationsProblem(task);
		stagingArray[stagingIndex++] = new Conjunction(bindDurationsProblem);

		// Impose Precedence Relations
		for (int i = 0; i < numberTasks; i++)
		{
			ArrayList<IProblem> precProblem = doPrecProblem(task[i]);
			if (precProblem == null)
				continue;
			stagingArray[stagingIndex++] = new Conjunction(precProblem);
		}

		// Impose one job at a time per processor constraint
		stagingArray[stagingIndex++] = new Conjunction(
				doInProcessorPrecProblem(task, proc, partition));

		// Impose Duration Relations
		for (int i = 0; i < numberTasks; i++)
		{
			stagingArray[stagingIndex++] = doDurationRelation(task[i]);
		}

		// Impose time limit to finish all tasks
		stagingArray[stagingIndex++] = doTimeLimit(task, timeLimit);

		this.setClauses(new Conjunction(stagingArray).getClauses());
	}
}