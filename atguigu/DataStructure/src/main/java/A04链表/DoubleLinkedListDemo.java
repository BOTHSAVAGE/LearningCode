package A04链表;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}


class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }

    //遍历双向链表

    //add
    void add(HeroNode2 heroNode){
        //todo head节点不能动，因此需要一个辅助变量temp
        HeroNode2 temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        //当退出while循环的时候，temp就指向链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    //update(修改和双向节点一样)
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //寻找需要修改的节点
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if(temp == null){
                break;//已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;//
        }
        //根据flag来判断是否找到了已经要修改的节点
        if(flag == true){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.println("没有找到编号%d的节点，不能修改");

        }
    }

    //del
    /**
     * 对于双向链表，我们可以直接找到要删除的节点
     * 找到后自我删除即可
     */
    public void del(int no){
        //判断当前链表是否为空
        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag  = false;//标志 表示是否找到该节点的上一个节点

        while (true){
            if(temp == null){//已经在链表的最后
                break;
            }
            if(temp.no == no){//说明找到了，此时temp代表的是待删除节点的上一个节点
                flag = true;
                break;
            }
            temp = temp.next; //temp后移
        }

        if(flag){//找到
            temp.pre.next  = temp.next;
            if(temp.next!=null){
                temp.next.pre = temp.pre;//如果temp为最后一个节点，那么不判断的话会有空指针异常
            }
        }else {
            System.out.println("没有找到");
        }
    }



}

class HeroNode2{
    public int no;
    String name;
    String nickName;
    HeroNode2 next; //默认为null
    HeroNode2 pre;  //默认为null

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' ;
//                ", next=" + next +
//                '}';
    }
}

