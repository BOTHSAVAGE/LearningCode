package A04链表;

public class Josepfu {
    public static void main(String[] args) {
        CirecleSingleLinkedList cirecleSingleLinkedList = new CirecleSingleLinkedList();
        cirecleSingleLinkedList.addBoy(5);
        cirecleSingleLinkedList.showBoy();

        cirecleSingleLinkedList.countBoy(1,2,5);//2-4-1-5-3

    }
}




class CirecleSingleLinkedList{
    private Boy first = new Boy(-1);

    /**
     *
     * @param nums  直接告诉加几个
     */
    public void addBoy(int nums){
        //nums 做一个数据校验
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }

        //辅助指针，帮助构建
        Boy curBoy = null;

        //用for循环来创建我们的环形链表
        for (int i=1;i<=nums;i++){
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i==1){

                first = boy;
                first.setNext(first);//构成环形链表
                curBoy = first; //让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }


    //遍历
    public void showBoy(){
        //判断链表是否为空
        if(first==null){
            System.out.println("没有");
            return;
        }
        //因为first不能动，还是需要辅助指针
        Boy curBoy = first;
        while (true){
            System.out.format("小孩的编号 %d \n",curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有几个小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        //数据校验
        if(first == null || startNo<1 || startNo>nums){
            System.out.println("参数有误");
            return;
        }
        //创建要给辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //需求创建一个辅助指针（变量）的helper，事先应该指向环形链表最后的这个节点
        while (true){
            if(helper.getNext() == first){
                break;//说明helper指向最后小孩的节点
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j=0;j < startNo - 1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true){
            if(helper == first){
                //说明圈中只有一个节点
                break;
            }
            //first和helper指针同时移动countNum-1
            for(int j=0;j<countNum-1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时候first指向的节点，就是要出圈的节点
            System.out.format("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.format("最后留在圈中的小孩编号%d \n",helper.getNo());
    }


}



class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
