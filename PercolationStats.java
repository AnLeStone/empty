
import edu.princeton.cs.algs4.*;
public class PercolationStats {
	
	int n;
	int trials;
	double [] x_array;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
    	if (n <= 0 || trials <= 0)
    	{
    		throw new java.lang.IllegalArgumentException();
    	}
    	
    	this.n = n;
    	this.trials = trials;
    	Percolation percolation;
    	Stopwatch watch;
    	int i = 0;
    	
    	while (i < trials)
    	{
    		watch = new Stopwatch();
    		percolation = new Percolation(n);
    		x_array = new double [n + 1];
    		
    		while (percolation.percolates() == false)
    		{    		
	    		int col = StdRandom.uniform(1, n);
	    		int row = StdRandom.uniform(1, n);
	    		
	    		percolation.open(col, row);
    		}
    		  		
    		x_array[i] = watch.elapsedTime();
    		i++;
    	}
    }

    // sample mean of percolation threshold
    public double mean()
    {
    	return StdStats.mean(x_array);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev()
    {
    	return StdStats.stddev(x_array);
    }
    
    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
    	return mean() - 1.96*stddev() / java.lang.Math.sqrt(trials);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
    	return mean() + 1.96*stddev() / java.lang.Math.sqrt(trials);
    }

    void print()
    {
    	System.out.println("mean					= "+ mean());
    	System.out.println("stddev					= "+ stddev());
    	System.out.println("95% condience interval	= [" + confidenceLo() + "," + confidenceHi() + "]");
    }
    
   // test client (see below)
   public static void main(String[] args)
   {
	   In in = new In(args[0]);      // input file
       int n = in.readInt();         // n-by-n percolation system
       int trials = in.readInt();
       
       new PercolationStats(n, trials);
   }
   
}
