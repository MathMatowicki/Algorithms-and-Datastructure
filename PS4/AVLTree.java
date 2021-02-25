import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class AVLTree {

    public class Node {
        int key;
        int height;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;

    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public Node getRoot() {
        return root;
    }

    public int height() {
        return root == null ? -1 : root.height;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.key > key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    public Node mostLeftChild(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            System.out.println(current.key);
            current = current.left;

        }
        return current;
    }

    public Node mostRightChild(Node node) {
        Node current = node;
        /* loop down to find the rightmost leaf */
        while (current.right != null) {
            current = current.right;
            System.out.println(current.key);
        }
        return current;
    }

    public void traversePreOrder(StringBuilder sb, String padding, String pointer, Node node) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.key);
            sb.append("\n");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            paddingBuilder.append("│  ");

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.right != null) ? "├──" : "└──";

            traversePreOrder(sb, paddingForBoth, pointerForLeft, node.left);
            traversePreOrder(sb, paddingForBoth, pointerForRight, node.right);
        }
    }

    public void printAndSaveTree(PrintStream os, AVLTree tree) throws IOException {
        StringBuilder sb = new StringBuilder();
        traversePreOrder(sb,"","", tree.getRoot());
        os.print(sb.toString());
        FileOutputStream outputStream = new FileOutputStream("avlout.txt");
        byte[] strToBytes = sb.toString().getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }

    private Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node n) {
        return n == null ? -1 : n.height;
    }

    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }


    public int countInRange(Node node, int low, int high) {
        /* Return case */
        if (node == null) return 0;
        /* If current node is in the range, then increment counter and recur for left and right children of it  */
        if (node.key >= low && node.key <= high)
            return 1 + this.countInRange(node.left, low, high) + this.countInRange(node.right, low, high);
            /* If value of node is smaller than low, then recur for right child */
        else if (node.key < low)
            return this.countInRange(node.right, low, high);
            /* Otherwise */
        else
            return this.countInRange(node.left, low, high);
    }
}