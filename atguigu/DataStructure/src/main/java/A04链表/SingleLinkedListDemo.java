package A04链表;

import jdk.nashorn.internal.ir.BlockLexicalContext;
import jdk.nashorn.internal.ir.TernaryNode;

import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1,"松江","及时雨"));
        singleLinkedList.add(new HeroNode(2,"松江1","及时雨1"));
        singleLinkedList.add(new HeroNode(3,"松江2","及时雨2"));
        singleLinkedList.add(new HeroNode(4,"松江3","及时雨3"));
        singleLinkedList.add(new HeroNode(6,"松江4","及时雨4"));

        singleLinkedList.addByOrder(new HeroNode(5,"松江4","及时雨4"));
//        singleLinkedList.addByOrder(new HeroNode(5,"松江4","及时雨4"));
        singleLinkedList.update(new HeroNode(4,"松江45","及时雨4"));


        singleLinkedList.showLinkedList();
        System.out.println(singleLinkedList.getLength(singleLinkedList.head));

        //测试倒数
        HeroNode res = SingleLinkedList.findLastIndexNode(singleLinkedList.head,3);
        System.out.println(res);

    }
}

class SingleLinkedList{
    //todo 先初始化一个头结点，头结点不要动
     static HeroNode head = new HeroNode(0,"","");







    //todo 百度面试题 从未到头打印单链表
    /**
     * 思路：
     * 1.上面的题的要求就是逆序打印单链表
     * 2.方式1：先将单链表进行反转操作，然后在遍历,这样做破坏了原来单链表的结构，所以不建议这样操作
     * 3 方式2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈先进先出的特点，就实现了逆序打印的效果
     */


    //从头到尾打印，方式二
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;//空链表
        }

        //创建一个栈，将各个节点压入
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点都压入
        while (cur != null){
            stack.push(cur);
            cur = cur.next;//后移
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }










     //todo 单链表的反转(腾讯的面试题)
    /**
     * 1.先定义一个节点 reverseHead = new HeroNode()
     * 2.从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放进新的链表的最前端
     * 3.原来的链表head.next = reverseHead.next
     *
     * 头插法
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个几点 就不需要反转
        if(head.next == null || head.next.next==null){
            return;
        }

        //定义辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前的节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，将其去吃，放在新的链表reverseHead的最前端

        while (cur!=null){
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;
            cur = next;//让cur指向下一个，cur后移
        }
        //将head.next 指向reverseHead.next 实现了单链表的反转
        head.next = reverseHead.next;
    }






    //todo 查找单链表中倒数第k个节点（新浪）
    /**
     *1.编写一个方法，接收head节点，同时接受一个index
     * 2 index代表的就是倒数第几
     * 3 先把链表从头到尾遍历，得到总长度
     * 4.  得到正序
     *
     */

    public  static HeroNode findLastIndexNode(HeroNode heroNode,int index){
        //如果链表为空，返回null
        if(head.next==null){
            return null;//没有找到
        }
        //第一个遍历得到链表的长度（节点个数）
        int size = getLength(head);

        //第二次遍历到size-index的这个位置
        //先做一个index校验
        if(index<=0 || index>size){
            return  null;
        }
        //定义一个辅助变量 for循环定位到倒数的index
        HeroNode cur = head.next;
        for(int i=0;i<size-index;i++){
            cur = cur.next;
        }
        return cur;

    }


































    /**
     *      * 获取单链表节点的个数
     *      *
     *      * 头结点去掉（不敢有没有头节点，头节点都不计算进去）
     * @param head  头结点，没有头指正
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next==null){//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;//todo head.next就体现出我没有用头结点
        while (cur!=null){
            length++;
            cur = cur.next;
        }
        return length;
    }


    /**
     * 删除节点
     * 一定要找到当前节点的前一个节点
     *
     * 我们先找到需要删除的这个节点的前一个节点temp
     * temp.next = temp.next.next
     *
     * 不删除的节点价格不会有其他的指向，将会被垃圾回收
     */
    public void del(int no){
        HeroNode temp = head;
        boolean flag  = false;//标志 表示是否找到该节点的上一个节点

        while (true){
            if(temp.next == null){//已经在链表的最后
                break;
            }
            if(temp.next.no == no){//说明找到了，此时temp代表的是待删除节点的上一个节点
                flag = true;
                break;
            }
            temp = temp.next; //temp后移
        }

        if(flag){//找到
            temp.next = temp.next.next;
        }else {
            System.out.println("没有找到");
        }
    }



    /**
     * 修改节点
     * 根据新的newHeroNode的no来修改
     */
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //寻找需要修改的节点
        HeroNode temp = head.next;
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








    //添加节点到单向列表
    /**
     * 思路，当不考虑编号的顺序时候
     * 找到当前链表的最后节点
     * 将最后这个节点的next指向新的节点
     */
    void add(HeroNode heroNode){
        //todo head节点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;

    }

    /**
     * 根据排名来插入
     * @param heroNode
     */
    void addByOrder(HeroNode heroNode){
        //todo 因为头结点不能动，所以通过temp
        //todo 因为是单链表，所以找的temp要是为与添加位置的前一个列表，要不然添加不进去
        HeroNode temp = head;
        boolean flag = false;//标志 表示添加的编号是否存在
        while (true){
            if(temp.next==null){
                break; //说明此时插入的元素再最后
            }
            if(temp.next.no>heroNode.no){//todo 下一个来比较而不是本身来比较
                break;//todo 位置找到了，就在temp的后面
            }else if (temp.next.no == heroNode.no){
                flag = true;//todo 要加的编号已经存在了
                break;
            }else {
                temp = temp.next;//todo 后移，相当于遍历
            }
        }
        //判断flag的值
        if(flag){
            //todo 不能添加，说明之前存在
            System.out.println("不能添加，之前的编号已经存在");
        }else {
            //todo 插入链表temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }


    //显示链表
    public void showLinkedList(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //todo 因为头结点不能动，所以需要一个辅助节点来遍历
        HeroNode temp = head.next;
        while (true){
            System.out.println(temp);
            //判断是否到链表的最后
            if(temp.next==null){
                break;
            }
            //后移
            temp = temp.next;
        }
    }
}


//节点
class HeroNode{
    public int no;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
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
