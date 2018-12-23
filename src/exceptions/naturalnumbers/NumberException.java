package exceptions.naturalnumbers;

/**
 * <p>
 * Title: NumberException
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NumberException extends Exception
{
	private static final long serialVersionUID = -1795477908886248025L;

	public NumberException(String s)
	{
		super(s);
	}
}