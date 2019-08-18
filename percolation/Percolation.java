import java.lang.*;

public class Percolation {

    private int[][] sites;
    private long[]  id;
    private long[] sizeId;
    private int n;
    private final int Blocked = 0;
    private final int Opened = 1;
    private final int Full = 2;
    private int numberOfOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException();
        }

        this.n = n;
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
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        sites[row][col] = Opened;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
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
        return false;
    }

    // union 4 cell above, below, left and right
    public void union(int openedSite, int nearbySite)
    {

    }
    public void find()
    {

    }
    public void connected()
    {

    }
    public void count()
    {

    }

    public void root()
    {

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

    // test client (optional)
    public static void main(String[] args) {

    }
}
