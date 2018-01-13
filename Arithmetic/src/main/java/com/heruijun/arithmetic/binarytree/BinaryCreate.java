package com.heruijun.arithmetic.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成二叉树
 * <p>
 * Created by heruijun on 2018/1/12.
 */

public class BinaryCreate {

    static List<Node> nodes = new ArrayList<>();
    private Node root = null;

    private void insertNode(Node node, Node newNode) {
        if (newNode.key < node.key) {
            if (node.left == null) {
                node.left = newNode;
            } else {
                insertNode(node.left, newNode);
            }
        } else {
            if (node.right == null) {
                node.right = newNode;
            } else {
                insertNode(node.right, newNode);
            }
        }
    }

    private void insert(Node newNode) {
        if (this.root == null) {
            this.root = newNode;
        } else {
            insertNode(root, newNode);
        }
    }

    static {
        Node n1 = new Node();
        n1.key = 8;
        Node n2 = new Node();
        n2.key = 3;
        Node n3 = new Node();
        n3.key = 10;
        Node n4 = new Node();
        n4.key = 1;
        Node n5 = new Node();
        n5.key = 6;
        Node n6 = new Node();
        n6.key = 14;
        Node n7 = new Node();
        n7.key = 4;
        Node n8 = new Node();
        n8.key = 7;
        Node n9 = new Node();
        n9.key = 13;

        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);
        nodes.add(n7);
        nodes.add(n8);
        nodes.add(n9);

        BinaryCreate bo = new BinaryCreate();
        for (Node n : nodes) {
            bo.insert(n);
        }
    }

    public static void main(String args[]) {
        System.out.print(nodes.get(0).right.right.key);
    }

    /**
     * 获取二叉树
     *
     * @return
     */
    public List<Node> getNodes() {
        return nodes;
    }
}