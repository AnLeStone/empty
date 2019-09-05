import edu.princeton.cs.algs4.*;

public class Percolation {

    private int[][] sites;
    private long[] id;
    private int n;
    private final int Blocked = 0;
    private final int Opened = 1;
    private final int Full = 2;
    private int numberOfOpenSites = 0;
    private WeightedQuickUnionUF sys;
    private boolean percolation = false;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException();
        }

        this.n = n;
        sys = new WeightedQuickUnionUF(n*n);
        id = new long [n*n + 10];      
        sites = new int [n + 1][n + 1];        
        id [0] = -1;
        int count = 0;
        for (int row = 1; row <= n; row++)
        {
            for (int col = 1; col <= n; col++)
            {
                sites[row][col] = Blocked;
                id[count] = count;
                count++;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
    	check(row);
    	check(col);
    	
    	sites[row][col] = Opened;
        numberOfOpenSites++;
        
        int p = GetSiteID(row, col);
        
        // Union p with 4 nearby site
        
        int left = GetLeftOpenSiteID(row, col);
        int right = GetRightOpenSiteID(row, col);
        int above = GetAboveOpenSiteID(row, col);
        int below = GetBelowOpenSiteID(row, col);
        
        
        if (left >= 0)
        {
        	sys.union(p, left);
        }
        
        if (right >= 0)
        {
        	sys.union(p, right);
        }
        
        if (above >= 0)
        {
        	sys.union(p, above);
        }
        
        if (below >= 0)
        {
        	sys.union(p, below);
        }

        // Check if p connect with top, set p is full
        setFull();
        
        for (int i = 1; i <= n; i++)
        {
        	if (sites[n][i] == Full)
        	{
        		percolation = true;
        		break;
        	}
        }
    }
    
    
    void setFull()
    {
    	for (int col = 1; col <= n; col++)
    	{ 	
	    	if (!isOpen(1, col))
	    	{
	    		continue;
	    	}
	    	
	    	sites[1][col] = Full;
	    	int root = GetSiteID(1, col);
	    	
	    	for (int row = 2; row <= n; row++)
	    	{
	    		for (int col2 = 1; col2 <= n; col2++)
	    		{
	    			if (isOpen(row, col2) )
	    			{
	    				int id = GetSiteID(row, col2);
	    				
	    				if (sys.connected(root, id))
	    				{
	    					sites[row][col2] = Full;
	    				}
	    			}
	    		}
	    	}
	    	
    	}
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
    	check(row);
    	check(col);
    	
        if (sites[row][col] == Opened || sites[row][col] == Full )
        {
            return true;
        }

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {

        if (sites[row][col] == Full)
        {
            return true;
        }

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        numberOfOpenSites++;

        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return percolation;
    }

    // Change site to specific row
    public int GetRow(int id)
    {
        int row = id / n + 1;

        if (row > n)
        {
            row = n;
        }

        return row;
    }

    // Change site to specific col
    public int GetCol(int id)
    {
    	if (id != 0 && id % n == 0)
    	{
    		return n;
    	}
    		
        return id%n;
    }
    
    public int GetSiteID(int row, int col)
    {
    	row=row-1;
    	col=col-1;
    	
    	if (row < 0 || row >= n || col < 0 || col >= n)
    	{
    		return -1;
    	}
    	
    	return row*n + col;
    }
    
    public int GetLeftOpenSiteID(int row, int col)
    {
    	int newrow = row;
    	int newcol = col - 1;
    	
    	int site = GetSiteID(newrow, newcol);
    	if (site >= 0)
    	{
    		if (isOpen(newrow, newcol))
        	{
        		return site;
        	}
    	}
    	
    	return -1;
    }

    public int GetRightOpenSiteID(int row, int col)
    {
    	int newrow = row;
    	int newcol = col + 1;
    		
    	int site = GetSiteID(newrow, newcol);
    	if (site >= 0)
    	{
    		if (isOpen(newrow, newcol))
        	{
        		return site;
        	}
    	}

    	return -1;
    }
    
    public int GetAboveOpenSiteID(int row, int col)
    {
    	int newrow = row - 1;
    	int newcol = col;
    	
    	int site = GetSiteID(newrow, newcol);
    	if (site >= 0)
    	{
    		if (isOpen(newrow, newcol))
        	{
        		return site;
        	}
    	}
    	
    	return -1;
    }
    
    public int GetBelowOpenSiteID(int row, int col)
    {
    	int newrow = row + 1;
    	int newcol = col;
    	
    	int site = GetSiteID(newrow, newcol);
    	if (site >= 0)
    	{
    		if (isOpen(newrow, newcol))
        	{
        		return site;
        	}
    	}
    	
    	return -1;
    }
    
    private boolean check (int num)
    {
    	if (num <= 0 || num > n)
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	return true;
    }
    
    // test client (optional)
    public static void main(String[] args) {

    }
}
