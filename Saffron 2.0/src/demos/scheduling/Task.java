package demos.scheduling;

import java.util.Arrays;
import java.util.List;

import naturalnumbers.NaturalNumber;
import bits.INaturalNumber;

public class Task
{
	private long duration;

	// private long finish;
	private String name;
	private INaturalNumber NNStart;
	private INaturalNumber NNDuration;
	private INaturalNumber NNFinish;
	private List<Task> predecessors;

	// private long start;

	public Task(String name, long duration) throws Exception
	{
		this.name = name;
		this.duration = duration;
		this.NNStart = new NaturalNumber("Start-" + name);
		this.NNFinish = new NaturalNumber("Finish-" + name);
		this.NNDuration = new NaturalNumber("Duration-" + name);
	}

	public long getDuration()
	{
		return duration;
	}

	/*
	 * public long getFinish() { return finish; }
	 */
	public String getName()
	{
		return this.name;
	}

	public INaturalNumber getNNDuration()
	{
		return NNDuration;
	}

	public INaturalNumber getNNFinish()
	{
		return NNFinish;
	}

	public INaturalNumber getNNStart()
	{
		return NNStart;
	}

	public List<Task> getPredecessors()
	{
		return predecessors;
	}

	/*
	 * public long getStart() { return start; }
	 */
	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	/*
	 * public void setFinish(long decimal) { this.finish = decimal; }
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	public void setNNFinish(NaturalNumber nNFinish)
	{
		NNFinish = nNFinish;
	}

	public void setNNStart(NaturalNumber nNStart)
	{
		NNStart = nNStart;
	}

	public void setPredecessors(Task[] predecessors)
	{
		
		this.predecessors = Arrays.asList(predecessors);
	}
	/*
	 * public void setStart(long decimal) { this.start = decimal; }
	 */
	/*
	 * public String toString() { return "\nTask [name=" + name + ", start=" +
	 * start + ", duration=" + duration + ", finish=" + finish + "]"; }
	 */

	@Override
	public String toString()
	{
		return "\nTask [name=" + name + ", NNStart=" + NNStart + ", NNDuration="
				+ NNDuration + ", NNFinish=" + NNFinish + "]";
	}

}
