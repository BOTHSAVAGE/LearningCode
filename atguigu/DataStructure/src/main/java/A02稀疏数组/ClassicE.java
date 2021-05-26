package A02稀疏数组;

/**
 * 五子棋：判断游戏输赢，存盘保存继续上一句
 *
 * 正确：稀疏数组
 */
public class ClassicE {
    public static void main(String[] args) {
        //todo 稀疏数组
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        System.out.println("原始的二维数组");
        for(int[] row:chessArr1){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println("\n");
        }


        int sum,r,c;
        sum = r = c = 0;
        for(int[] row:chessArr1){
            r++;//计算行数
            for(int data:row){
                if(r==1){
                    c++;//计算列数，避免重复计算所以只计算第一行的
                }
                if(data!=0){
                    sum++;
                }
            }
        }

        //sum+1表示总行数，3为具体的具体的坐标+值
        int sparseArray[][] = new int[sum+1][3];
        //第一行存储稀疏矩阵的信息
        sparseArray[0][0] = c;//列数
        sparseArray[0][1] = r;//行数
        sparseArray[0][2] = sum;//多少个有效数据


        for(int i=0;i<r;i++) {
            for (int j = 0; j < c; j++) {
                if(chessArr1[i][j]!=0){
                    sparseArray[sum][0] = i;
                    sparseArray[sum][1] = j;
                    sparseArray[sum][2] = chessArr1[i][j];
                    sum--;
                }
            }
        }

        for(int[] row:sparseArray){
            for(int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println("\n");
        }
    }
}
