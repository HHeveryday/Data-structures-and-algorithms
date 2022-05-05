package nqc_sparsearr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现原始二维数组转为稀疏数组存入文件中，再实现从文件中读取稀疏数组转为原始数组
 * 模拟棋盘，0代表没有棋子，1代表白子，2代表蓝子
 */
public class SparseArray {
    public static void main(String[] args) {
        //1、创建原始的二维数组
        int[][] arr = new int[11][11];
        //2、注入数据
        arr[2][3] = 1;
        arr[3][4] = 2;
        arr[3][6] = 2;
        arr[3][9] = 2;
        //3、遍历原始数组，记录有效信息
        System.out.println("===================原数组========================");
        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
                if (anInt != 0){
                    ++sum;
                }
            }
            System.out.println();
        }
        //4、创建稀疏数组
        int[][] parseArr = new int[sum + 1][3];
        //5、注入有效信息
        //先将第一行的记录信息录入
        parseArr[0][0] = 11;
        parseArr[0][1] = 11;
        parseArr[0][2] = sum;
        //然后将具体的位置信息以及值的信息录入
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 11; j++) {
                if (arr[i][j] != 0){
                    ++count;
                    parseArr[count][0] = i;
                    parseArr[count][1] = j;
                    parseArr[count][2] = arr[i][j];
                }
            }
        }
        System.out.println("===================得到的稀疏数组=======================");
        for (int i = 0; i < parseArr.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(parseArr[i][j] + "\t");
            }
            System.out.println();
        }

        //6、将稀疏数组存入文件中
        try(PrintStream ps = new PrintStream("./data.txt")) {
            for (int i = 0; i < parseArr.length; i++) {
                ps.print(parseArr[i][0]+"\t" + parseArr[i][1]+"\t" + parseArr[i][2]+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        //7、将文件中的数据读入稀疏数组中
        //创建集合，用于存储从文件中读取一行的内容
        List<String> list = new ArrayList<>();
        try (
                BufferedReader is = new BufferedReader(new FileReader("./data.txt"))
                ){
            String  len;
            while ((len = is.readLine()) != null){
                list.add(len.trim());
            }

            //遍历，将集合中的数据存入
            int[][] parseArr2 = new int[list.size()][3];
            for (int j = 0; j < list.size(); j++) {
                //将得到的字符串按照制表符进行分割
                String[] chars = list.get(j).split("\t");
                parseArr2[j][0] = Integer.parseInt(chars[0]);
                parseArr2[j][1] = Integer.parseInt(chars[1]);
                parseArr2[j][2] = Integer.parseInt(chars[2]);
            }

            //遍历从文件中加载的稀疏数组
            System.out.println("==============从文件中加载的稀疏数组内容===================");

            for (int i = 0; i < parseArr2.length; i++) {
                System.out.println(parseArr2[i][0]+"\t"+parseArr2[i][1]+"\t"+parseArr2[i][2]+"\t");
            }

            //8，将稀疏数组恢复为正常的数组

            //a、读取数组的大小信息
            int[][] arr_old = new int[parseArr2[0][0]][parseArr2[0][1]];
            //b、遍历稀疏数组，将信息存入原始数组
            for (int i = 1; i < parseArr2.length; i++) {
                arr_old[parseArr2[i][0]][parseArr2[i][1]] = parseArr2[i][2];
            }

            //c、遍历恢复原数组
            System.out.println("==========原数组==============");
            for (int[] ints : arr_old) {
                for (int anInt : ints) {
                    System.out.print(anInt + "\t");
                }
                System.out.println();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
