package demos.scheduling;

import java.util.ArrayList;
import java.util.List;

import naturalnumbers.NaturalNumber;
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
public class Scheduler
{
	private static IProblem jobSchedulingProblem;

	static IProblem doBindDurationsProblem(Task[] task) throws Exception
	{
		int numberTasks = task.length;
		IProblem[] bindDurationsProblem = new IProblem[numberTasks];
		for (int i = 0; i < numberTasks; i++)
			bindDurationsProblem[i] = new NaturalNumberFixer(
					task[i].getNNDuration(), task[i].getDuration());
		return new Conjunction(bindDurationsProblem);
	}
	
	static IProblem doTimeLimit(Task[] task, int limit) throws Exception
	{
		IProblem[] array=new IProblem[task.length];
		NaturalNumber limitNN = new NaturalNumber("Limit");
		IProblem limitProblem = new NaturalNumberFixer(limitNN,limit);
		for(int i=0;i<task.length;i++)
		{
			array[i]=new NaturalNumberOrderer(task[i].getNNFinish(),limitNN);
		}
		return new Conjunction(limitProblem,new Conjunction(array));
	}
	
	static IProblem doDurationRelation(Task task) throws Exception
	{
		return new NaturalNumberAdder(
				task.getNNStart(), task.getNNDuration(),
				task.getNNFinish());
	}
	
	static IProblem doPartitionProblem(IBooleanVariable[][] partition, Task[] task,
			Processor[] proc) throws Exception
	{
		int numberProcs = proc.length;
		int numberTasks = task.length;
		for (int i = 0; i < numberProcs; i++)
		{
			IBooleanVariable[] currentBin = partition[i];
			for (int j = 0; j < numberTasks; j++)
				currentBin[j] = BooleanVariable.getBooleanVariable("partition-"
						+ proc[i].getName() + "-" + task[j].getName());
		}
		return new BitArrayPartition(partition);
	}
	
	static ArrayList<IProblem> doPrecProblem(Task currSucc) throws Exception
	{
		List<Task> currSuccPreds = currSucc.getPredecessors();
		if (currSuccPreds == null)
			return null;
		ArrayList<IProblem> precProblem = new ArrayList<IProblem>();
		for (Task currPred : currSuccPreds)
		{
			precProblem.add(new NaturalNumberOrderer(
					currPred.getNNFinish(), currSucc.getNNStart()));
		}
		return precProblem;
	}
	
	public static IProblem getProblem()
	{
		return jobSchedulingProblem;
	}

	public static ArrayList<ArrayList<Task>> schedule(Task[] task,
			Processor[] proc, int timeLimit) throws Exception
	{
		int numberProcs = proc.length;
		int numberTasks = task.length;
		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[2 + 2 * numberTasks];

		// Partition Problem
		IBooleanVariable[][] partition = new IBooleanVariable[numberProcs][numberTasks];
		stagingArray[stagingIndex++] = doPartitionProblem(partition,task,
				proc);

		// Bind Durations Problem
		IProblem bindDurationsProblem = doBindDurationsProblem(task);
		stagingArray[stagingIndex++] = new Conjunction(bindDurationsProblem);

		
		// Impose Precedence Relations
		for (int i = 0; i < numberTasks; i++)
		{
			ArrayList<IProblem> precProblem = doPrecProblem(task[i]);
			if(precProblem==null)
				continue;
			stagingArray[stagingIndex++] = new Conjunction(precProblem);
		}

		// Impose Duration Relations
		for (int i = 0; i < numberTasks; i++)
		{
			stagingArray[stagingIndex++] = doDurationRelation(task[i]);
		}
		
		//Impose time limit to finish all tasks
		stagingArray[stagingIndex++] = doTimeLimit(task, timeLimit);

		jobSchedulingProblem = new Conjunction(stagingArray);

		List<IBooleanLiteral> blList = jobSchedulingProblem.findModel(Problem
				.defaultSolver());

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
			return solution;
		}
		else
			return null;
	}
}