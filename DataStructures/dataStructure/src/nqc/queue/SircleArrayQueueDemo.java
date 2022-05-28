package nqc.queue;

import java.util.Scanner;

/**
 * 编写了一个用数组实现的简单队列，只能使用一次
 */

public class SircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试队列
        SircleArrayQueue saq = new SircleArrayQueue(3);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===========================测试=============================");
            System.out.println("1、入队");
            System.out.println("2、出队");
            System.out.println("3、展示当前队列");
            System.out.println("4、退出");
            System.out.println("请选择：");
            String choose = sc.next();
            switch (choose) {
                case "1":
                    System.out.println("请输入要入队的数据：");
                    int data = sc.nextInt();
                    saq.addQueue(data);
                    break;
                case "2":
                    try {
                        int res = saq.getQueue();
                        System.out.println("出队的数据为：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    saq.showQueue();
                    break;
                case "4":
                    System.out.println("退出成功~");
                    return;
                default:
                    System.out.println("输入有误，请重新输入~");
                    break;
            }
        }

    }
}


//使用数组模拟队列，编写一个ArrayQueue类
class SircleArrayQueue {
    private int maxSize;
    private int front;//队列头，指向队列第一个元素
    private int rear;//队列尾，指向队列最后一个元素的后一位，即队列能容纳的最大元素个数为maxSize - 1
    private int[] arr;//模拟队列的数组
    private int count;

    //提供一个构建简单队列的构造器
    SircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断数列是否为满
    public boolean isFull() {
        if ((rear + 1) % maxSize == front) {
            return true;
        }
        return false;
    }

    //判断数列是否为空
    public boolean isempty() {
        if (rear == front) {
            return true;
        }
        return false;
    }

    //入队操作
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满，无法继续添加数据");
            return;
        }
        arr[rear] = data;
        rear = (rear + 1) % maxSize;
        count++;
        System.out.println("数据添加成功~");
    }

    //出队操作
    public int getQueue() {
        if (isempty()) {
            throw new RuntimeException("队列为空，无法继续出队操作~");
        }
        int val = arr[front];
        front = (front + 1) % maxSize;
        count--;
        return val;
    }

    //展示队列
    public void showQueue() {
        if (isempty()) {
            System.out.println("当前队列为空，无数据可以展示~");
            return;
        }
        int count1 = front;
        System.out.print("队列中的元素为：");
        for (int i = 0; i < count; i++) {
            System.out.print(arr[count1] + "\t");
            count1 = (count1 + 1) % maxSize;
        }
        System.out.println();
    }
}
