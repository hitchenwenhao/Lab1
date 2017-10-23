
package hushan;
import java.io.*;
package hushan;
import java.io.*;
import java.util.Random;

//hushan is a beautiful girl
public class huahan {
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



	public static void main(String[] args)throws IOException{
		// TODO �Զ����ɵķ������
		FileInputStream rf = new FileInputStream("D://tete.txt");

		byte[] buffer = new byte[rf.available()];	//��ȡ���ļ�β����̬��������
		while(rf.read(buffer)!=-1)
		{
		//	System.out.print(new String(buffer));
			continue;
		}
		txtStr = new String(buffer);
		rf.close();
		System.out.println(txtStr);
		String str0=txtStr.replaceAll("\n", " ");
		String str1 = str0.replaceAll("[\\pP\\p{Punct}]", " ");
        String reg = "[^a-zA-Z ]";
        String str2=str1.replaceAll(reg, "");
        String str3=str2.toLowerCase();
        String [] arr = str3.split("\\s+");   //arr�е�Ԫ���������ı��ļ��е�ÿ�����ʣ����ظ���
        int textsize,i,j,k,l;
        textsize=arr.length;                   //textsizeΪ��������ĳ���
        int flag;
        arr_=new String [100];
        arr_[0]=arr[0];
        for(j=1;j<textsize;j++)
        	{flag=1;
        	for(k=0;k<j;k++)
        		if(arr[k].equals(arr[j]))
        			{flag=0;break;}
            if(flag==1)                             //arr_Ϊ��˳������ظ����ʹ��ɵ�����
            	{size+=1;arr_[size-1]=arr[j];}}    //flag��ǵ��������еĵ�j�������Ƿ���֮ǰ�ĵ������ظ�
                          //size���������������ظ��ĵĵ��ʸ���
        adjmatrix=new int [size][size];
        for(i=0;i<textsize-1;i++)
        {
        	int m,n;
        	m=indexofword(arr[i]);
        	n=indexofword(arr[i+1]);
        	adjmatrix[m][n]+=1;

        }

        System.out.println("****************menu******************");
        System.out.println("�����ı�����������ͼ");
        System.out.println("չʾ����ͼ��D:\\chenwh\\output0");
        showDirectedGraph();
        System.out.println("��ѯ�ŽӴ�");
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
        System.out.println("�������ı�");
        System.out.println("Please enter the newtxt:");
       try{
        	BufferedReader newtxt_ =new BufferedReader(new InputStreamReader (System.in));
        	newtxt=newtxt_.readLine();
        	}catch(IOException e){}
        generateNewText(newtxt);
        System.out.println("��ѯ���·������ʾ��D:\\chenwh\\output1");
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
	  /* String shortestpath=calcShortestPath(s3,s4);
	  if(shortestpath.equals("Words exception!"))
		  System.out.println("Words exception!");

	  else if(shortestpath.equals("No path"))
		  System.out.println("No path");

	  else
	  {
		  String [] pathnumberarr = shortestpath.split("->");
	      int len=pathnumberarr.length;
	      String [] pathnamearr=new String [len];
	      for(int b=0;b<len;b++)
	      {
		      pathnamearr[b]=arr_[Integer.parseInt(pathnumberarr[b])];
		      System.out.println(pathnamearr[b]+" ");
	      }
	  }*/
	  System.out.println("������߲�����ͼ��D:\\chenwh\\output2");
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
}//��ѯ�ŽӴ�



	static void printBridgeWords(String word1, String word2){
		int i;
		int index_1,index_2;
		index_1=indexofword(word1);
		index_2=indexofword(word2);
		if (index_1==-1 || index_2==-1)
		{
			System.out.println("No \""+word1+"\" or \""+ word2+"\" in the graph!");
		}
		else if(queryBridgeWords(word1,word2)[0]==0)
		{
			System.out.println("No bridge words from \""+word1+"\" to \""+word2+"\"!");
		}
		else{
			System.out.println("The bridge words from \""+word1+"\" to \""+word2+"\"are:");
			for(i=1;i<=queryBridgeWords(word1,word2)[0];i++)
				{
					if(i<queryBridgeWords(word1,word2)[0])
						System.out.println(arr_[queryBridgeWords(word1,word2)[i]]+ ", and ");
					else{
						System.out.println(arr_[queryBridgeWords(word1,word2)[i]]+".");
					}
				}
			}

	}//����ŽӴ�



	static String generateNewText(String inputText) {
		 int i,length;
		 String [] newtxt = inputText.split("\\s+");
		 length=newtxt.length;
		 for(i=0;i<length-1;i++)
		 {
			 System.out.print(newtxt[i]+" ");
			 if(indexofword(newtxt[i])!=-1)
			{
			 if (queryBridgeWords(newtxt[i],newtxt[i+1])[0]!=0)
			 {
				 System.out.print(arr_[queryBridgeWords(newtxt[i],newtxt[i+1])[1]]+" ");
				 }
			 }
		  }
		 System.out.println(newtxt[length-1]);
		 return null;
	}//����bridge word�������ı�



	static String calcShortestPath(String word1, String word2) {
		int start=indexofword(word1);
		int end=indexofword(word2);
		String s = null;
		if(start==-1||end==-1)
			s="Words exception!";

		else
		{
			int weight[][]=new int[size][size];
			String[] path=new String[size];
		    for(int a=0;a<size;a++)
		    {
		    	if(a!=start)
		    		path[a]=start+"->"+a;
		    }
			//��Ŵ�start��������������·�����ַ�����ʾ
			for(int i=0;i<size;i++)
				for(int j=0;j<size;j++)
				{
					weight[i][j]=adjmatrix[i][j];
					if(i!=j&&weight[i][j]==0)
						weight[i][j]=999;
				}
			int[] shortPath = new int[size];//��Ŵ�start��������������·��
			int[] visited = new int[size];  //��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����
	        shortPath[start] = 0;
	        visited[start] = 1; //��ʼ��
	        for(int count = 1;count <size;count++)
	        {
	            //ѡ��һ�������ʼ����start�����δ��Ƕ���
	            int k = -1;
	            int dmin = Integer.MAX_VALUE;

	            for(int i = 0;i < size;i++)
	            {
	                if(visited[i] == 0 && weight[start][i] < dmin)
	                {
	                    dmin = weight[start][i];
	                    k = i;

	                }
	            }



	            //����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
	            shortPath[k] = dmin;
	            visited[k] = 1;
	            if(k==end)
	            	{s= path[k];break;}

	            //��kΪ�м�㣬������start��δ���ʸ���ľ���
	            for(int i = 0;i < size;i++)
	            {
	                if(visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i])
	                {
	                    weight[start][i] = weight[start][k] + weight[k][i];
	                    path[i]=path[k]+"->"+i;
	                }
	            }
	        }
	        if(visited[end]==0)
	        	return "No path!";
	        else{
	          String [] pathnumberarr = s.split("->");
			  int len=pathnumberarr.length;
			  String [] pathnamearr=new String [len];
			  for(int b=0;b<len;b++)
			  {
				  pathnamearr[b]=arr_[Integer.parseInt(pathnumberarr[b])];
			  }

			  GraphViz gv = new GraphViz();
		      gv.addln(gv.start_graph());
		      int i,j;
			  for(i=0;i<size;i++)
	      	  for(j=0;j<size;j++)
	      	  {
	      		 if (adjmatrix[i][j]!=0)
	      			gv.addln(arr_[i]+"->"+arr_[j]+"[label="+adjmatrix[i][j]+"]");
	      	  }
			  for(int c=0;c<len-1;c++)
			  {
				  gv.addln(pathnamearr[c]+"->"+ pathnamearr[c+1]+"[color=blue]");
			  }
	          gv.addln(gv.end_graph());
	          System.out.println(gv.getDotSource());
	          String type = "gif";
	          File out = new File("D:\\chenwh\\output1." + type);    // Windows
	          gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );

		      }
		}
		return s;

	}




static int Randomindex(int start,int choice)
{
	int num=-1,flag,index=-1;
	for(int i=0;i<size;i++)
	{
		flag=0;
		index+=1;
		if(adjmatrix[start][i]>0)
			{num+=1;flag=1;}

		if(num==choice&&flag==1)
		{
			break;
		}
	}
	return index;
}



static void randomWalk(){
     int Vertexindex = new Random().nextInt(size);
     int count,nextVertexindex,nextVertexnum,number=0;
     int EdgePassedBy[][]=new int [size][size];
     int VertexPassedBy[]=new int [100];
     for(int l=0;l<100;l++)
     {
    	 VertexPassedBy[l]=size;
     }
     while(true)
     {
    	 boolean endFlag=true;
    	 VertexPassedBy[number]=Vertexindex;
    	 count=0;
    	 for(int i:adjmatrix[Vertexindex])
    	 {
    		 if(i>0)
    			 {count++;endFlag=false;}
    	 }

    	 if(endFlag==true)
    	 {
    		 break;
    	 }
    	 number+=1;
    	 nextVertexnum=new Random().nextInt(count);
    	 nextVertexindex=Randomindex(Vertexindex,nextVertexnum);
    	 if(EdgePassedBy[Vertexindex][nextVertexindex]==1)
    		 break;
    	 EdgePassedBy[Vertexindex][nextVertexindex]=1;
    	 Vertexindex=nextVertexindex;
     }
     GraphViz gv = new GraphViz();
     gv.addln(gv.start_graph());
     int i,j,k;
     for(i=0;i<size;i++)
       	{for(j=0;j<size;j++)
       	{
       		if (adjmatrix[i][j]!=0)
       			gv.addln(arr_[i]+"->"+arr_[j]+"[label="+adjmatrix[i][j]+"]");
       	}
       	}
     for(k=0;k<100;k++)
     {
    	 if(VertexPassedBy[k]<size&&VertexPassedBy[k+1]<size)
    		 gv.addln(arr_[VertexPassedBy[k]]+"->"+arr_[VertexPassedBy[k+1]]+"[color=red]");
    	 else
    		 break;
     }
    gv.addln(gv.end_graph());
    System.out.println(gv.getDotSource());
    String type = "gif";
    File out = new File("D:\\chenwh\\output2." + type);    // Windows
    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );

    }




static void showDirectedGraph()
{
	GraphViz gv = new GraphViz();
    gv.addln(gv.start_graph());
    int i,j;
		for(i=0;i<size;i++)
      	for(j=0;j<size;j++)
      	{
      		if (adjmatrix[i][j]!=0)
      			gv.addln(arr_[i]+"->"+arr_[j]+"[label="+adjmatrix[i][j]+"]");
      	}
    gv.addln(gv.end_graph());
    System.out.println(gv.getDotSource());
    String type = "gif";
    File out = new File("D:\\chenwh\\output0." + type);    // Windows
    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
}

}


//chenwenhao is a handsome boy
