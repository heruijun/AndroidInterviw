package com.heruijun.arithmetic.binarytree;

/**
 * 中序遍历
 * <p>
 * Created by heruijun on 2018/1/12.
 */

public class InOrderCenter {

    private static Node root;
    private static OnNodeFind onNodeFind;

    private interface OnNodeFind {
        void callback(int key);
    }

    private void inOrderTraverseNode(Node node, OnNodeFind onNodeFind) {
        this.onNodeFind = onNodeFind;
        if (node != null) {
            inOrderTraverseNode(node.left, onNodeFind);
            if (onNodeFind != null) {
                this.onNodeFind.callback(node.key);
            }
            inOrderTraverseNode(node.right, onNodeFind);
        }
    }

    private void inOrderTraverse(OnNodeFind onNodeFind) {
        inOrderTraverseNode(root, onNodeFind);
    }

    public static void main(String[] args) {
        BinaryCreate bc = new BinaryCreate();
        root = bc.getNodes().get(0);

        InOrderCenter ic = new InOrderCenter();
        ic.inOrderTraverse(new OnNodeFind() {
            @Override
            public void callback(int key) {
                System.out.println(key);
            }
        });
    }

}
