import java.util.*;
public class Main {
	static int g[][];
	static double p[][];
	static int s;
	static int d;
public static double distance(double x1,double y1,double x2,double y2)
{
	double f = (x2-x1)*(x2-x1);
	double s = (y2-y1)*(y2-y1);
	double k = f + s;
	
	double dis = Math.sqrt(k);
	return dis;
}
public static boolean not_present(int tra[],int n,int k)
{
	for(int i=0;i<=n;i++)
	{
		if(k == tra[i])
			return false;
	}
	return true;
}
public static void main(String args[]) throws Exception
{
	//Assuming every thing in first quadrant
	Scanner sc  = new Scanner(System.in);
	System.out.println("Enter the number nodes");
	int n = sc.nextInt();
	g = new int[n][n];
	System.out.println("Enter the graph");
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			g[i][j] = sc.nextInt();
		}
	}
	System.out.println("Enter postion of each node");
	p = new double[n][2];
	for(int i=0;i<n;i++)
	{
		p[i][0]=sc.nextDouble();
		p[i][1]=sc.nextDouble();
	}
	System.out.println("Enter the source and destination");
	s = sc.nextInt();
	d = sc.nextInt();
	
	int tra[] = new int[n];//traversing path saved here
	tra[0]=s;
	int i=0;
	int c=0;
	while(true)
	{
		int neigh[] = new int[n];// finds out neighbours of each node
		int k=0;
		for(int j=0;j<n;j++)
		{
			if(g[tra[i]][j]==1)
			{
				neigh[k++] = j;
				if(j==d)
				{
					c++;
					break;
				}
			}
		}
		//System.out.println(tra[i]);
		if(c>0)
		{
			i++;
			tra[i]=d;
			break;
		}
		if(k==0)
		{
			System.out.println("route can't be found");
			break;
		}
		double disNeigh[] = new double[k];//saves distance of each neighbour from destination
		double min=Double.MAX_VALUE;
		int minj = 0;
		for(int j=0;j<k;j++)
		{
			disNeigh[j] = distance(p[d][0],p[d][1],p[neigh[j]][0],p[neigh[j]][1]);
			if(disNeigh[j]<min)
				{
				min = disNeigh[j];
				minj = neigh[j];
				}
		}
		double traDis = distance(p[tra[i]][0],p[tra[i]][1],p[d][0],p[d][1]);
		
		if(traDis>min)
		{
			i++;
			tra[i]=minj;
			
		}
		else
		{
			
			while(min>=traDis)
			{
				System.out.println("Perimeter forwarding");
				c = 0;
				k=0;
				for(int j=0;j<n;j++)
				{
					if(g[tra[i]][j]==1 && not_present(tra,i,j))
					{
						neigh[k++] = j;
						if(j==d)
						{
							c++;
							break;
						}
					}
				}
				
				if(c>0)
				{
					i++;
					tra[i]=d;
					break;
				}
				if(k==0)
				{	
					
					break;
				}
				if(p[neigh[0]][0]>p[tra[i]][0])
				{
					double minn = Double.MAX_VALUE;
					int minn2=Integer.MAX_VALUE;
					for(int j=0;j<k;j++)
					{
						if(p[neigh[j]][1]<minn)
						{
							
							minn = p[neigh[j]][1];
							minn2 = neigh[j];
						}
					}
					i++;
					tra[i] = minn2;
					
					min = distance(p[d][0],p[d][1],p[minn2][0],p[minn2][1]);
				}
				else
				{
					double max = -1;
					int max2=-1;
					for(int j=0;j<k;j++)
					{
						if(p[neigh[j]][1]>max)
						{
							max = p[neigh[j]][1];
							max2 = neigh[j];
						}
					}
					i++;
					tra[i] = max2;
					min = distance(p[d][0],p[d][1],p[max2][0],p[max2][1]);
				}
			}
			if(c>0)
			{
				break;
			}
			if(k==0)
			{
				System.out.println("route can't be found");
				break;
			}
		}
	}
	System.out.println("\nRoute is---");
	for(int k=0;k<=i;k++)
	{
		System.out.println(tra[k]);
	}
	sc.close();
}
}
