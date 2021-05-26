package A03队列;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray circleArray = new CircleArray(10);
        circleArray.addQueue(1);
        circleArray.addQueue(2);
        circleArray.addQueue(3);
        circleArray.addQueue(4);
        circleArray.addQueue(5);
        circleArray.addQueue(6);
        circleArray.addQueue(7);
        circleArray.addQueue(8);
        circleArray.getQueue();
        circleArray.showQueue();

    }
}

class CircleArray{
    int maxSize;
    int rear;
    int front;
    int[] arr;

    public CircleArray(int arrMaxSize){

        this.maxSize = arrMaxSize;
        this.arr = new int[this.maxSize];
        this.front = 0;
        this.rear = 0;

    }

    public boolean isFull(){
        return (this.rear+1)%this.maxSize == this.front;
    }


    public boolean isEmpty(){
        return this.rear == this.front;//todo 重要条件
    }


    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;//先放，在加入
        //todo 因为rear指向最后一个元素的后一个位置
        //todo 后移要考虑取模
        rear = (rear+1)%maxSize;
    }


    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无法取出数据");
        }
        //todo front指向队列的第一个元素
        /**
         * 1.先把front保存到一个临时变量
         * 2.然后front往后移
         * 3.将临时保存的变量返回
         */
        int val = arr[front];
        front = (front+1)%maxSize;
        return val;
    }



    //显示所有元素
    public void showQueue(){
        if(isEmpty()){
            System.out.println("为空");
            return;
        }
        /**
         * 从front开始遍历，遍历到
         */
        for(int i=front;i<size();i++){
            System.out.println(arr[i%maxSize]);
        }

    }

    //求出当前队列的有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    //显示头数剧，不是取出
    int headQueue() throws Exception {
        //判断
        if(isEmpty()){
            throw new Exception("队列为空");
        }
        return arr[front];
    }





}
