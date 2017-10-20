package hushan;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Random;
/**.

 * set default mock parameter.（方法说明）
 * @throws Exception if has error(异常说明)
 * @throws IOExcuption
 * if an error occurred

 */
/**
 * @author 明
 *
 */
/**
 * @author 明
 *
 */
final class Hushan {
    private static String txtStr;
    private static int size = 1;
    private static int[][]adjmatrix;
    private static String[]arr1;
    private static String s1 = "";
    private static String s2 = "";
    private static String s3 = "";
    private static String s4 = "";
    private static String newtxt = "";
    private Hushan() {
    }

    /**.

     * set default mock parameter.（方法说明）
     * @param args is a steal veritable(参数说明)

     * @throws Exception if has error(异常说明)
     * @throws IOException
     * if an error occurred
     */

    public static void main(String[] args)throws IOException {
        //自动生成的方法存根
        FileInputStream rf = new FileInputStream("F:\\baoming.txt");
        byte[] buffer = new byte[rf.available()]; //读取到文件尾，动态生成数组
        while (rf.read(buffer) != -1) {
            System.out.print(new String(buffer));
            continue;
        }
        txtStr = new String(buffer);
        rf.close();
        System.out.println(txtStr);
        String str0 = txtStr.replaceAll("\n", " ");
        String str1 = str0.replaceAll("[\\pP\\p{Punct}]", " ");
        String reg = "[^a-zA-Z ]";
        String str2 = str1.replaceAll(reg, "");
        String str3 = str2.toLowerCase();
        String[] arr = str3.split("\\s+");   //arr中的元素依次是文本文件中的每个单词（有重复）
        int textsize, i, j, k, l;
        textsize = arr.length;                   //textsize为单词数组的长度
        int flag;
        arr1 = new String[100];
        arr1[0] = arr[0];
        for (j = 1; j < textsize; j++) {
            flag = 1;
            for (k = 0; k < j; k++) {
                if (arr[k].equals(arr[j])) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                //flag标记单词数组中的第j个单词是否与之前的单词有重复
                size += 1; arr1[size - 1] = arr[j];
            }
        }
        //size代表单词数组中无重复的的单词个数
        adjmatrix = new int[size][size];
        for (i = 0; i < textsize - 1; i++) {
            int m, n;
            m = indexofword(arr[i]);
            n = indexofword(arr[i + 1]);
            adjmatrix[m][n] += 1;

        }

        System.out.println("****************menu******************");
        System.out.println("读入文本并生成有向图");
        System.out.println("展示有向图于f/ming");
        showDirectedGraph();
        System.out.println("查询桥接词");
        System.out.println("Please enter the frist words you want to inquire:");
        try {
            BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
            s1 = in1.readLine();
        } catch (IOException e) { }
        System.out.println("Please enter the second words you want to inquire:");

        try {
            BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
            s2 = in2.readLine();
        } catch (IOException e) { }
        printBridgeWords(s1, s2);
        System.out.println("生成新文本");
        System.out.println("Please enter the newtxt:");
        try {
            final BufferedReader newtxt1 = new BufferedReader(new InputStreamReader(System.in));
            newtxt = newtxt1.readLine();
        } catch (IOException e) { }
        generateNewText(newtxt);
        System.out.println("查询最短路径并显示与f/ming");
        System.out.println("Please enter the starting point of shortest path that you want to query:");
        try {
            BufferedReader in3 = new BufferedReader(new InputStreamReader(System.in));
            s3 = in3.readLine();
        } catch (IOException e) { }
        System.out.println("Please enter the ending point of shortest path that you want to query:");
        try {
            BufferedReader in4 = new BufferedReader(new InputStreamReader(System.in));
            s4 = in4.readLine();
        } catch (IOException e) { }
        System.out.println("随机游走并生成图于f/ming");
        randomWalk();

    }



    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)
     * @param word
     * @return
     */
    static int indexofword(String word) {
        int i, flag = 0;
        for (i = 0; i < size; i++) {
            if (arr1[i].equals(word)) {
                flag = 1; break;
                }
        }
        if (flag == 0) {
            return -1;
        } else {
            return i;
        }
    }
    /**.
     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)
     * @param index1
     * @param index2
     * @return
     */
    static boolean  edgeExisted(int index1, int index2) {
        if (adjmatrix[index1][index2] > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)
     * @param word1
     * @param word2
     * @return
     */
    static int[] queryBridgeWords(String word1, String word2) {
        int index1, index2;
        int bridgeWordsNum = 0;
        int[] bridgeWordsIndex = new int[5];
        index1 = indexofword(word1);
        index2 = indexofword(word2);
        if (index1 == -1 || index2 == -1) {
            return bridgeWordsIndex;
        }
        int index;
        for (index = 0; index < size; index++) {
            if (index != index1 && index != index2) {
                if (edgeExisted(index1, index)) {
                    if (edgeExisted(index, index2)) {
                        bridgeWordsIndex[bridgeWordsNum + 1] = index;
                        bridgeWordsNum += 1;
                    }
                }
            }
        }
        bridgeWordsIndex[0] = bridgeWordsNum;
        return bridgeWordsIndex;
    } //查询桥接词


    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)
     * @param word1
     * @param word2
     */
    static void printBridgeWords(String word1, String word2) {
        int i;
        int index1, index2;
        index1 = indexofword(word1);
        index2 = indexofword(word2);
        if (index1 == -1 || index2 == -1) {
            System.out.println("No \"" + word1 + "\" or \"" + word2 + "\" in the graph!");
        } else if (queryBridgeWords(word1, word2)[0] == 0) {
            System.out.println("No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!");
        } else {
            System.out.println("The bridge words from \"" + word1 + "\" to \"" + word2 + "\"are:");
            for (i = 1; i <= queryBridgeWords(word1, word2)[0]; i++) {
                if (i < queryBridgeWords(word1, word2)[0]) {
                    System.out.println(arr1[queryBridgeWords(word1, word2)[i]] + ", and ");
                } else {
                    System.out.println(arr1[queryBridgeWords(word1, word2)[i]] + ".");
                }
            }
        }

    } //输出桥接词


    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)
     * @param inputText
     * @return
     */
    static String generateNewText(String inputText) {
        int i, length;
        String[] newtxt = inputText.split("\\s+");
        length = newtxt.length;
        for (i = 0; i < length - 1; i++) {
            System.out.print(newtxt[i] + " ");
            if (indexofword(newtxt[i]) != -1) {
                if (queryBridgeWords(newtxt[i], newtxt[i + 1])[0] != 0) {
                    System.out.print(arr1[queryBridgeWords(newtxt[i], newtxt[i + 1])[1]] + " ");
                }
            }
        }
        System.out.println(newtxt[length - 1]);
        return null;
    } //根据bridge word生成新文本


    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)
     * @param word1
     * @param word2
     * @return
     */
    static String calcShortestPath(String word1, String word2) {
        int start = indexofword(word1);
        final int end = indexofword(word2);
        String s = null;
        if (start == -1 || end == -1) {
            s = "Words exception!";
        } else {
            int[][] weight = new int[size][size];
            String[] path = new String[size];
            for (int a = 0; a < size; a++) {
                if (a != start) {
                    path[a] = start + "->" + a;
                }
            }
            //存放从start到其他各点的最短路径的字符串表示
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    weight[i][j] = adjmatrix[i][j];
                    if (i != j && weight[i][j] == 0) {
                        weight[i][j] = 999;
                    }
                }
            }
            int[] shortPath = new int[size]; //存放从start到其他各点的最短路径
            int[] visited = new int[size];  //标记当前该顶点的最短路径是否已经求出,1表示已求出
            shortPath[start] = 0;
            visited[start] = 1; //初始化
            for (int count = 1; count < size; count++) {
                //选出一个距离初始顶点start最近的未标记顶点
                int k = -1;
                int dmin = Integer.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    if (visited[i] == 0 && weight[start][i] < dmin) {
                        dmin = weight[start][i];
                        k = i;

                    }
                }



                //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
                shortPath[k] = dmin;
                visited[k] = 1;
                if (k == end) {
                    s = path[k]; break;
                    }

                //以k为中间点，修正从start到未访问各点的距离
                for (int i = 0; i < size; i++) {
                    if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
                        weight[start][i] = weight[start][k] + weight[k][i];
                        path[i] = path[k] + "->" + i;
                    }
                }
            }
            if (visited[end] == 0) {
                return "No path!";
            } else {
                String[] pathnumberarr = s.split("->");
                int len = pathnumberarr.length;
                String[] pathnamearr = new String[len];
                for (int b = 0; b < len; b++) {
                    pathnamearr[b] = arr1[Integer.parseInt(pathnumberarr[b])];
                }
                GraphViz gv = new GraphViz();
                gv.addln(gv.startgraph());
                int i, j;
                for (i = 0; i < size; i++) {
                    for (j = 0; j < size; j++) {
                        if (adjmatrix[i][j] != 0) {
                            gv.addln(arr1[i] + "->" + arr1[j] + "[label=" + adjmatrix[i][j] + "]");
                        }
                    }
                }
                for (int c = 0; c < len - 1; c++) {
                    gv.addln(pathnamearr[c] + "->" + pathnamearr[c + 1] + "[color=blue]");
                }
                gv.addln(gv.endgraph());
                System.out.println(gv.getDotSource());
                String type = "gif";
                File out = new File("F:\\ming\\output3." + type);    // Windows
                gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);

            }
        }
        return s;

    }


    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)
     * @param start
     * @param choice
     * @return
     */
    static int randomindex(int start, int choice) {
        int num = -1, flag, index = -1;
        for (int i = 0; i < size; i++) {
            flag = 0;
            index += 1;
            if (adjmatrix[start][i] > 0) {
                num += 1; flag = 1;
                }
            if (num == choice && flag == 1) {
                break;
            }
        }
        return index;
    }


    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)

     */
    static void randomWalk() {
        int vertexindex = new Random().nextInt(size);
        int count, nextVertexindex, nextVertexnum, number = 0;
        int[][] edgePassedBy = new int[size][size];
        int[] vertexPassedBy = new int[100];
        for (int l = 0; l < 100; l++) {
            vertexPassedBy[l] = size;
        }
        while (true) {
            boolean endFlag = true;
            vertexPassedBy[number] = vertexindex;
            count = 0;
            for (int i:adjmatrix[vertexindex]) {
                if (i > 0) {
                    count++; endFlag = false;
                    }
            }
            if (endFlag) {
                break;
            }
            number += 1;
            nextVertexnum = new Random().nextInt(count);
            nextVertexindex = randomindex(vertexindex, nextVertexnum);
            if (edgePassedBy[vertexindex][nextVertexindex] == 1) {
                break;
            }
            edgePassedBy[vertexindex][nextVertexindex] = 1;
            vertexindex = nextVertexindex;
        }
        GraphViz gv = new GraphViz();
        gv.addln(gv.startgraph());
        int i, j, k;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (adjmatrix[i][j] != 0) {
                    gv.addln(arr1[i] + "->" + arr1[j] + "[label=" + adjmatrix[i][j] + "]");
                }
            }
        }
        for (k = 0; k < 100; k++) {
            if (vertexPassedBy[k] < size && vertexPassedBy[k + 1] < size) {
                gv.addln(arr1[vertexPassedBy[k]] + "->" + arr1[vertexPassedBy[k + 1]] + "[color=red]");
            } else {
                break;
            }
        }
        gv.addln(gv.endgraph());
        System.out.println(gv.getDotSource());
        String type = "gif";
        File out = new File("F:\\ming\\output2." + type);    // Windows
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);

    }


    /**.

     * set default mock parameter.（方法说明）
     * @throws Exception if has error(异常说明)

     */
    static void showDirectedGraph() {
        GraphViz gv = new GraphViz();
        gv.addln(gv.startgraph());
        int i, j;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (adjmatrix[i][j] != 0) {
                    gv.addln(arr1[i] + "->" + arr1[j] + "[label=" + adjmatrix[i][j] + "]");
                }
            }
        }
        gv.addln(gv.endgraph());
        System.out.println(gv.getDotSource());
        String type = "gif";
        File out = new File("F:\\ming\\output0." + type);    // Windows
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

}

