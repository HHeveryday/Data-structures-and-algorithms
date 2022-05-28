package nqc.linkedlist;

import org.junit.jupiter.api.Test;

/**
 * 实现没有按照编号排序的单链表
 */

public class SingleLinkedListDemo {

    @Test
    public void testAdd(){
        TestNode n1 = new TestNode(1,"宋江","及时雨");
        TestNode n2 = new TestNode(2,"卢俊义","玉麒麟");
        TestNode n3 = new TestNode(3,"吴用","智多星");
        TestNode n4 = new TestNode(4,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.addNode(n2);
        singleLinkedList.addNode(n1);
        singleLinkedList.addNode(n4);
        singleLinkedList.addNode(n3);
        singleLinkedList.showLinkedList();
    }

    @Test
    public void testAddByOder(){
        TestNode n1 = new TestNode(1,"宋江","及时雨");
        TestNode n2 = new TestNode(2,"卢俊义","玉麒麟");
        TestNode n3 = new TestNode(3,"吴用","智多星");
        TestNode n4 = new TestNode(4,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.addByOrder(n2);
        singleLinkedList.addByOrder(n1);
        singleLinkedList.addByOrder(n4);
        singleLinkedList.addByOrder(n3);
        singleLinkedList.showLinkedList();
    }

    @Test
    public void testUpdate(){
        TestNode n1 = new TestNode(1,"宋江","及时雨");
        TestNode n2 = new TestNode(2,"卢俊义","玉麒麟");
        TestNode n3 = new TestNode(3,"吴用","智多星");
        TestNode n4 = new TestNode(4,"林冲","豹子头");
        TestNode n5 = new TestNode(5,"林冲1","豹子头1");
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.addNode(n2);
        singleLinkedList.addNode(n1);
        singleLinkedList.addNode(n4);
        singleLinkedList.addNode(n3);
        singleLinkedList.showLinkedList();
        singleLinkedList.update(n5);
        singleLinkedList.showLinkedList();
    }

    @Test
    public void testDeleteById(){
        TestNode n1 = new TestNode(1,"宋江","及时雨");
        TestNode n2 = new TestNode(2,"卢俊义","玉麒麟");
        TestNode n3 = new TestNode(3,"吴用","智多星");
        TestNode n4 = new TestNode(4,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.addByOrder(n2);
        singleLinkedList.addByOrder(n1);
        singleLinkedList.addByOrder(n4);
        singleLinkedList.addByOrder(n3);
        singleLinkedList.showLinkedList();
        singleLinkedList.deleteById(4);
        singleLinkedList.showLinkedList();
    }


}

/*
  单链表类，用于初始化单链表
 */
class SingleLinkedList{
    //定义头结点，不存放任何数据
    private TestNode head = new TestNode(0,"","");

    /**
     * 基础的插入操作
     * @param node
     */
    public void addNode(TestNode node){
        //添加新的节点到链表末尾
        //a. 定义一个辅助变量指针，用于遍历到链表末尾
        //b. 在末尾添加新的节点
        TestNode temp = head;
        while (true){
            if (temp.next == null) break;//已经找到最后一个节点的位置，退出循环
            temp = temp.next;//当前节点不为末尾节点，将temp后移
        }
        temp.next = node;
    }

    /**
     * 按编号升序插入元素，并且不能插入相同编号的节点
     * @param node
     */
    public void addByOrder(TestNode node){
        //辅助变量指针，用于遍历
        TestNode temp = head;
        boolean flag = false;//链表中是否已经存在要插入的数据编号，默认不存在
        while (true){
            if (temp.next == null){
                //说明已经到了链表尾部
                break;
            }else if (temp.next.num > node.num){
                //说明temp所指向的节点应该为要插入节点的前一个节点
                break;
            }else if (temp.next.num == node.num){
                //说明已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.printf("当前要插入编号为%d的数据在链表中已经存在，不能进行插入操作~\n",node.num);
        }else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 根据编号修改节点
     * @param newNode
     */
    public void update(TestNode newNode){
        //判断当前链表是否为空
        if (head.next == null){
            System.out.println("当前链表为空~");
        }
        //辅助变量指针
        TestNode temp = head.next;
        //是否存在该编号的节点,默认不存在
        boolean flag = false;
        while (true){
            if (temp == null){
                //说明已经到链表末尾
                break;
            }
            if (temp.num == newNode.num){
                //找到了要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
            System.out.println("修改成功~");
        }else {
            System.out.printf("要修改编号为%d的节点不存在~\n",newNode.num);
        }
    }

    /**
     * 根据编号删除节点
     */
    public void deleteById(int id){
        //判断当前链表是否为空
        if (head.next == null){
            System.out.println("当前链表为空~");
        }
        //辅助变量指针
        TestNode temp = head;
        //是否存在该编号的节点,默认不存在
        boolean flag = false;
        while (true){
            if (temp.next.num == id){
                //已经找到要删除节点的前一个节点
                flag = true;
                break;
            }
            if (temp.next.next == null){
                //已经为最后一个节点的前一个节点，而最后一个节点的编号已经验证，所以可以退出
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //进行删除操作
            temp.next = temp.next.next;
            System.out.println("删除成功~");
        }else {
            System.out.printf("要删除编号为%d的节点不存在~",id);
        }
    }

    public void showLinkedList(){
        //判断当前链表是否为空
        if (head.next == null){
            System.out.println("当前链表中无数据~");
            return;
        }
        //有数据，定义一个辅助变量指针，用于遍历
        TestNode temp = head.next;
        while (true){
            System.out.println(temp);
            if (temp.next == null) break;//判断下一个节点是否存在，不存在则结束遍历
            temp = temp.next;//存在，将辅助变量指针后移
        }
    }

}

/**
 * 定义节点对象，包含数据域和地址域
 */
class TestNode{
    //数据域
    public int num;
    public String name;
    public String nickName;
    //地址域，相当于指针，指向下一个节点
    public TestNode next;

    public TestNode(int num, String name, String nickName) {
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "TestNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName+
                '}';
    }
}