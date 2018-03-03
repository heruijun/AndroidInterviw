package com.heruijun.arithmetic.binarytree;

/**
 * 前序遍历
 * <p>
 * Created by heruijun on 2018/1/12.
 */

public class PreOrderCenter {

    private static Node root;
    private static OnNodeFind onNodeFind;

    private interface OnNodeFind {
        void callback(int key);
    }

    private void preOrderTraverseNode(Node node, OnNodeFind onNodeFind) {
        this.onNodeFind = onNodeFind;
        if (node != null) {
            if (onNodeFind != null) {
                this.onNodeFind.callback(node.key);
            }
            preOrderTraverseNode(node.left, onNodeFind);
            preOrderTraverseNode(node.right, onNodeFind);
        }
    }

    private void preOrderTraverse(OnNodeFind onNodeFind) {
        preOrderTraverseNode(root, onNodeFind);
    }

    public static void main(String[] args) {
        BinaryCreate bc = new BinaryCreate();
        root = bc.getNodes().get(0);

        PreOrderCenter ic = new PreOrderCenter();
        ic.preOrderTraverse(new OnNodeFind() {
            @Override
            public void callback(int key) {
                System.out.println(key);
            }
        });
    }

}
