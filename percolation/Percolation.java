
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
        sys = new WeightedQuickUnionUF(n);
        id = new long [n*n + 10];      
        sites = new int [n + 1][n + 1];        
        id [0] = -1;
        
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                sites[row][col] = Blocked;
                int num = row*n + col + 1;
                id[num] = num;
            }
        }
        
        int lastrow = (n-1)*n + 1;
        for (int col = 0; col < n; col++)
        {
        	id[col] = 0;
        	id[lastrow + col] = n;
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
        
        // Find the root id of p
        // Union p with 4 nearby site
        // Check if p connect with top, set p is full
        // If ID before union == n-1 and site isFull => percolation
        
        int beforeRoot = sys.find(p);
        
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

        int afterRoot = sys.find(p);
        // How to know the site is connected with other site?
        
        // How to know the site is full ?
        if (beforeRoot == 0)
        {
        	sites[col][row] = Full;
        }
        
        if (beforeRoot == afterRoot)
        {
        	percolation = true;
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
    	check(row);
    	check(col);
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
        int row = id % n - 1;

        if (row == -1)
        {
            row = n - 1;
        }

        if (row < 0 || row >= n)
        {
            return -1;
        }

        return row;
    }

    // Change site to specific col
    public int GetCol(int id)
    {
        int row = GetRow(id);

        if (row < -1)
        {
            return -1;
        }

        int col = id - row * n - 1;

        if (col < 0 || col > n)
        {
            return -1;
        }

        return col;
    }
    
    public int GetSiteID(int row, int col)
    {
    	check(row);
    	check(col);
    	
    	return row*n + col + 1;
    }
    
    public int GetLeftOpenSiteID(int row, int col)
    {
    	int newrow = row;
    	int newcol = col - 1;
    	
    	if (isOpen(newrow, newcol))
    	{
    		int site = GetSiteID(newrow, newcol);
    		return site;
    	}
    	
    	return -1;
    }

    public int GetRightOpenSiteID(int row, int col)
    {
    	int newrow = row;
    	int newcol = col + 1;
    	
    	if (isOpen(newrow, newcol))
    	{
    		int site = GetSiteID(newrow, newcol);
    		return site;
    	}
    	
    	return -1;
    }
    
    public int GetAboveOpenSiteID(int row, int col)
    {
    	int newrow = row - 1;
    	int newcol = col;
    	
    	if (isOpen(newrow, newcol))
    	{
    		int site = GetSiteID(newrow, newcol);
    		return site;
    	}
    	
    	return -1;
    }
    
    public int GetBelowOpenSiteID(int row, int col)
    {
    	int newrow = row - 1;
    	int newcol = col;
    	
    	if (isOpen(newrow, newcol))
    	{
    		int site = GetSiteID(newrow, newcol);
    		return site;
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
