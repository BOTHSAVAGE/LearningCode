package A03队列;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(10);
        while (true){
            System.out.println("选择序号进入对应的功能:");
            System.out.println("1.添加元素");
            System.out.println("2.取出元素");
            System.out.println("3.展示所有元素");
            System.out.println("4.展示头部数据");

            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入（1-4）：");
            int x = scanner.nextInt();
            switch (x){
                case 1:{
                    System.out.println("请输入插入队列数据：");
                    x = scanner.nextInt();
                    arrayQueue.addQueue(x);
                    break;
                }
                case 2:{
                    System.out.println(arrayQueue.getQueue());
                    break;
                }
                case 3:{
                    arrayQueue.showQueue();
                    break;
                }
                case 4:{
                    System.out.println("11--");
                    break;
                }
            }

        }
    }
}


class ArrayQueue{
    private int maxSize;
    private int front;//todo 指向队列的前一个位置
    private int rear;
    private int arr[];


    public ArrayQueue(int maxSize){
        this.front = this.rear = -1;//todo 默认都为-1
        this.maxSize = maxSize;
        arr =  new int[maxSize];
    }


    public boolean isFull(){
        return this.rear == maxSize - 1;
    }


    public boolean isEmpty(){
        return this.rear == this.front;//todo 重要条件
    }


    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;//todo 先加
        arr[rear] = n;
    }


    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无法取出数据");
        }
        front++;
        return arr[front];
    }

    void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无法取出数据");
        }
        for(int i=front+1;i<rear-front;i++){
            System.out.println(arr[i]);
        }
    }
}
