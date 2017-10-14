package shiyan1;
import java.io.*;
import java.util.Random;

public class shiyan1 {
	public static String txtStr;
	public static int size=1;
	public static int [][]adjmatrix;
	public static String[]arr;
	public static String[]arr_;
	public static String s1="";
    public static String s2="";
    public static String s3="";
    public static String s4="";
    public static String newtxt="";
 
	public static void main(String[] args) throws IOException{
		// TODO 自动生成的方法存根
		FileInputStream rf = new FileInputStream("C://Users//94936//Desktop//tete.txt");



		byte[] buffer = new byte[rf.available()];	//读取到文件尾，动态生成数组
		while(rf.read(buffer)!=-1)
		{
		//	System.out.print(new String(buffer));
			continue;
		}
		txtStr = new String(buffer);
		rf.close();
		System.out.println(txtStr);
		String str0=txtStr.replaceAll("\n", " ");
		String str1 = str0.replaceAll("[\\pP\\p{Punct}]", "");   
        String reg = "[^a-zA-Z ]";
        String str2=str1.replaceAll(reg, "");
        String str3=str2.toLowerCase();
        String [] arr = str3.split("\\s+");   //arr中的元素依次是文本文件中的每个单词（有重复）
        int textsize,i,j,k,l;
        textsize=arr.length;                   //textsize为单词数组的长度              
        int flag;
        arr_=new String [100];
        arr_[0]=arr[0];                  
        for(j=1;j<textsize;j++)
        	{flag=1;
        	for(k=0;k<j;k++)
        		if(arr[k].equals(arr[j]))
        			{flag=0;break;}
            if(flag==1)                             //arr_为按顺序的无重复单词构成的数组
            	{size+=1;arr_[size-1]=arr[j];}}    //flag标记单词数组中的第j个单词是否与之前的单词有重复
                          //size代表单词数组中无重复的的单词个数
        
        for(l=0;l<size;l++){
        	System.out.println(arr_[l]);
        }
        adjmatrix=new int [size][size];             
        for(i=0;i<textsize-1;i++)
        {
        	int m,n;
        	m=indexofword(arr[i]);
        	n=indexofword(arr[i+1]);
        	adjmatrix[m][n]+=1;
        	
        }
        showDirectedGraph();
        System.out.println("****************menu******************");
        System.out.println("Please enter the frist words you want to inquire:");
        try{
        	BufferedReader in1 =new BufferedReader(new InputStreamReader (System.in));
        	s1=in1.readLine();
        	}catch(IOException e){}
        System.out.println("Please enter the second words you want to inquire:");
        try{
        	BufferedReader in2 =new BufferedReader(new InputStreamReader (System.in));
        	s2=in2.readLine();
        	}catch(IOException e){}
        printBridgeWords(s1,s2);
        System.out.println("Please enter the newtxt:");
        try{
        	BufferedReader newtxt_ =new BufferedReader(new InputStreamReader (System.in));
        	newtxt=newtxt_.readLine();
        	}catch(IOException e){}
        generateNewText(newtxt); 
	    System.out.println("Please enter the starting point of shortest path that you want to query:");
	   	try{
       		BufferedReader in3 =new BufferedReader(new InputStreamReader (System.in));
       		s3=in3.readLine();
       		}catch(IOException e){}
	   System.out.println("Please enter the ending point of shortest path that you want to query:");
	   try{
       		BufferedReader in4 =new BufferedReader(new InputStreamReader (System.in));
       		s4=in4.readLine();
       		}catch(IOException e){}
	   
	  calcShortestPath(s3,s4);
	  randomWalk();
	   
	   
		
	}
	
	
	
	public static int indexofword(String word)
    {
    	int i,flag=0;
    	for(i=0;i<size;i++)
    	{
    		if(arr_[i].equals(word))
    			{flag=1;break;}
    	}
    	if(flag==0)
    	    return -1;
    	else
    		return i;
    } //展示有向图
	
	
	
  	public static void showDirectedGraph(){
  		int i,j; 
  		for(i=0;i<size;i++)
          	for(j=0;j<size;j++)
          	{
          		if (adjmatrix[i][j]!=0)
          			System.out.println(arr_[i]+"->"+arr_[j]);
          	}
  	} 
  	
  	
  	
	static boolean  Edge_Existed(int index_1,int index_2)
	{
		if(adjmatrix[index_1][index_2]>0)
			return true;
		else
			return false;
	} 
	
	
	
	static int[] queryBridgeWords(String word1, String word2) {
		int index_1,index_2;
		int BridgeWordsNum=0; 
		int [] BridgeWordsIndex=new int[5];
		index_1=indexofword(word1);
		index_2=indexofword(word2);
		if (index_1==-1 || index_2==-1)
		{
			return BridgeWordsIndex;
		}
		int index;
		for(index=0;index<size;index++)
		{
			if(index!=index_1 && index!=index_2)
				if(Edge_Existed(index_1,index)) 
					if(Edge_Existed(index,index_2))
					{
						BridgeWordsIndex[BridgeWordsNum+1]=index;
						BridgeWordsNum+=1;
					}
		} 
		BridgeWordsIndex[0]=BridgeWordsNum;
		return BridgeWordsIndex;
}//查询桥接词
	
	
	static void printBridgeWords(String word1, String word2){
		int i;
		int index_1,index_2;
		index_1=indexofword(word1);
		index_2=indexofword(word2);
		if (index_1==-1 || index_2==-1)
		{
			if(index_1==-1 && index_2==-1)
			{
				System.out.println("No \""+word1+"\" and \""+ word2+"\" in the graph!");
			}
			else if(index_1==-1)
			{
			System.out.println("No \""+word1+"\" in the graph!");
			}
			else
			{
				System.out.println("No \""+word2+"\" in the graph!");
			}
			return ;
		}
		if(queryBridgeWords(word1,word2)[0]==0)
		{
			System.out.println("No bridge words from \""+word1+"\" to \""+word2+"\"!");
		}
		else{
			System.out.println("The bridge words from \""+word1+"\" to \""+word2+"\" are:");
			for(i=1;i<=queryBridgeWords(word1,word2)[0];i++)
				{
					if(i<queryBridgeWords(word1,word2)[0])
						System.out.println(arr_[queryBridgeWords(word1,word2)[i]]+ ", and ");
					else{
						System.out.println("1111111111111111111111111111");
						System.out.println("22222222222222222222222222222222");
						System.out.println("333333333333333333333333333333333");
						
						
						System.out.println(arr_[queryBridgeWords(word1,word2)[i]]+".");
					}
				}
			}
		
	}//输出桥接词
	
	
	static String generateNewText(String inputText) {
		 int i,length;
		 String [] newtxt = inputText.split("\\s+");
		 length=newtxt.length;
		 for(i=0;i<length-1;i++) 
		 {
			 System.out.print(newtxt[i]+" ");
			 if(indexofword(newtxt[i])!=-1)
			{
				 if (queryBridgeWords(newtxt[i],newtxt[i+1])[0]>=1)
				 {
					 System.out.print(arr_[queryBridgeWords(newtxt[i],newtxt[i+1])[1]]+" ");
				 
				 }
			 }
		  } 
		 System.out.println(newtxt[length-1]); 
		 return null;
	}//根据bridge word生成新文本
	
	
	static String calcShortestPath(String word1, String word2) {
			int A[][]=new int [size][size];
			String B[]=new String [size+1];
			String C[][]=new String [100][100];
			int number[]=new int [100];
			int path[][]=new int [size][size];
			int i,j,k,num=0;
			int index_1,index_2;
			index_1=indexofword(word1);
			index_2=indexofword(word2);
		    for(i=0;i<size;i++)
		    {
               for (j=0;j<size;j++)
               {
            	   if(adjmatrix[i][j]!=0)
            	   {
            		   A[i][j]=adjmatrix[i][j];
            	   }
            	   else
            	   {
            		   A[i][j]=100;
            	   }
            	   if (A[i][j] < 100) {
       		    	path[i][j] = i;
       		       }
       		       else {
       		       path[i][j] = -1;
       		       }
			   }
		    }
		    for(k=0;k<size;k++)
		    {
		       for(i=0;i<size;i++)
		      {
		         for(j=0;j<size;j++)
		         {
		             if(A[i][j]>(A[i][k]+A[k][j]))
		             {
		                   A[i][j]=A[i][k]+A[k][j];
		                   path [i][j]=k;
		        	 }
			     }
		 	  }
		    }
		    if(word2 == null || word2.isEmpty())
		    {
		    	int index=0;
		    	for(i=0;i<size;i++)
		    	{
		    		j=i;
		    		k=0;
		    		if(path[index_1][i]==-1)
				    {
				    	System.out.println("Sorry,there is no path between "+word1+" and "+arr_[i]);
				    	number[index]=k;
				    	index++;
				    	continue;
				    }
		    		else
		    		{
		    			while (true)	
		    			{
		    				if(arr_[path[index_1][i]].equals(arr_[index_1])==false)
		    				{
		    					C[j][k]=arr_[path[index_1][i]];
		    					i=path[index_1][i];
		    					k++;
		    				}
		    				else
		    				{
		    					break;
		    				}
		    			}
		    			i=j;
		    		}
	    			number[index]=k;
	    			index++;	
		    		}
		    	
		    	for(i=0;i<size;i++)
		    	{
		    		//System.out.print(number[i]);
		    		if(path[index_1][i]!=-1)
		    		{
		    		System.out.print(word1+"->");
		    		}
		    	for(j=number[i]-1;j>=0;j--)
		    		{
		    		if(path[index_1][i]!=-1)
		    		{
		    			System.out.print(C[i][j]+"->");
		    		}
		    		}
		    	if(path[index_1][i]!=-1)
		    	{
		    		System.out.println(arr_[i]);
		    	}
		    	}
		 
		    }
		    else
		    {
		    	if(path[index_1][index_2]==-1)
			    {
			    	System.out.println("Sorry,there is no path between two points!");
			    	return null;
			    }
			    else 
			    {
			    	while(path[index_1][index_2]!=index_1)
			    	{
			    		B[num]=arr_[path[index_1][index_2]];
			    		index_2=path[index_1][index_2];
			    		num++;
			    	}
			    	System.out.print(word1+"->");
			    	for(j=num-1;j>=0;j--)
			    	{
			    		System.out.print(B[j]+"->");
			    	}
			    	System.out.println(word2);
			    }
			   }
		    return null;
}
	
static String randomWalk(){
	 String random;
     int result1 = new Random().nextInt(size);
     random=arr_[result1];
     int m,j=0,a,b,number=0;
     int k=0;
     String txt[]=new String [size+2];
     m=indexofword(random);
     random=arr_[result1];
     System.out.println(result1);
     int A[][]=new int [size][size];
     for(a=0;a<size;a++)
     {
    	 for(b=0;b<size;b++)
    	 {
    		 A[a][b]=adjmatrix[a][b];
    	 }
     }
    while (k!=size-1)
     {
    	for(j=0;j<size-1;j++)
    	{
    		if(A[m][j]==1)
    		{
    			txt[number]=arr_[j];
    			System.out.print(arr_[j]+"->");
    			A[m][j]=A[m][j]+1;
    			m=j;
    			number++;
    			continue;
    		 }
    	}
    	k=j;
     }
    System.out.println(arr_[j]);
    StringBuffer s = new StringBuffer();
    for(int i = 0; i < number; i++)
    	{ 
    	s.append(txt[i]+" ");
    	}
    try{
    	FileWriter output =new FileWriter("C://Users//94936//Desktop//random.txt");
    	BufferedWriter bw=new BufferedWriter(output);
    	bw.write(s.toString());
    	bw.close();
    	}catch(IOException e){
    	e.printStackTrace();
    	}
    return null;//随机游走*/
    }
    
}
	


