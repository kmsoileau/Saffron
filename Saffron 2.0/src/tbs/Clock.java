/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 19, 2018
 */
package tbs;

public class Clock
{
	private Long lastStart;
	private boolean running;
	private Long totalElapsedTime;

	public Clock(String name)
	{
		super();
		this.totalElapsedTime = 0L;
		this.lastStart = 0L;
		this.running = false;
	}

	public Long getTotalElapsedTime()
	{
		return this.totalElapsedTime;
	}

	public boolean isRunning()
	{
		return this.running;
	}

	public void start()
	{
		if (running)
			return;
		this.lastStart = System.currentTimeMillis();
		this.running = true;
	}

	public void stop()
	{
		if (!running)
			return;
		this.totalElapsedTime += (System.currentTimeMillis() - this.lastStart);
		this.running = false;
	}
}
