package demos.scheduling;

import java.util.ArrayList;
import java.util.List;

import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
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
						+ proc[i].getName() + "-" + task[j].getName());
		}
		IProblem partitionProblem = new BitArrayPartition(partition);
		stagingArray[stagingIndex++] = partitionProblem;

		// Bind Durations Problem
		IProblem[] bindDurationsProblem = new IProblem[numberTasks];
		for (int i = 0; i < numberTasks; i++)
			bindDurationsProblem[i] = new NaturalNumberFixer(
					task[i].getNNDuration(), task[i].getDuration());
		stagingArray[stagingIndex++] = new Conjunction(bindDurationsProblem);

		// Impose Precedence Relations
		for (int i = 0; i < numberTasks; i++)
		{
			Task currSucc = task[i];
			ArrayList<IProblem> precProblem = new ArrayList<IProblem>();
			List<Task> currSuccPreds = currSucc.getPredecessors();
			if (currSuccPreds == null)
				continue;
			for (Task currPred : currSuccPreds)
			{
				precProblem.add(new NaturalNumberOrderer(
						currPred.getNNFinish(), currSucc.getNNStart()));
			}
			stagingArray[stagingIndex++] = new Conjunction(precProblem);
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
				// task[i].setStart(currStart);
				long currFinish = task[i].getNNFinish().toDecimal();
				// task[i].setFinish(currFinish);
			}
			return solution;
		}
		else
			return null;
	}
}