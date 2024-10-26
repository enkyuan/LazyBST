public class LazyBST {

    private TreeNode root;

    private class TreeNode {
        int key;
        TreeNode leftChild;
        TreeNode rightChild;
        boolean deleted;

        TreeNode(int key) {
            this.key = key;
            this.deleted = false;
        }
    }

    public boolean insert(int key) throws IllegalArgumentException {
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("IllegalArgumentException raised");
        }
        root = insert(root, key);
        return !root.deleted;
    }

    private TreeNode insert(TreeNode node, int key) {
        if (node == null) {
            return new TreeNode(key);
        }
        if (key < node.key) {
            node.leftChild = insert(node.leftChild, key);
        } else if (key > node.key) {
            node.rightChild = insert(node.rightChild, key);
        } else {
            if (node.deleted) {
                node.deleted = false;
            }
        }
        return node;
    }

    public boolean delete(int key) throws IllegalArgumentException {
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("IllegalArgumentException raised");
        }
        return delete(root, key);
    }

    private boolean delete(TreeNode node, int key) {
        if (node == null) {
            return false;
        }
        if (key < node.key) {
            return delete(node.leftChild, key);
        } else if (key > node.key) {
            return delete(node.rightChild, key);
        } else {
            if (!node.deleted) {
                node.deleted = true;
                return true;
            }
            return false;
        }
    }

    public int findMin() {
        return findMin(root);
    }

    private int findMin(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int leftMin = findMin(node.leftChild);
        if (leftMin != -1) {
            return leftMin;
        }
        if (!node.deleted) {
            return node.key;
        }
        return findMin(node.rightChild);
    }

    public int findMax() {
        return findMax(root);
    }

    private int findMax(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int rightMax = findMax(node.rightChild);
        if (rightMax != -1) {
            return rightMax;
        }
        if (!node.deleted) {
            return node.key;
        }
        return findMax(node.leftChild);
    }

    public boolean contains(int key) throws IllegalArgumentException {
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("Key must be between 1 and 99");
        }
        return contains(root, key);
    }

    private boolean contains(TreeNode node, int key) {
        if (node == null) {
            return false;
        }
        if (key < node.key) {
            return contains(node.leftChild, key);
        } else if (key > node.key) {
            return contains(node.rightChild, key);
        } else {
            return !node.deleted;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString().trim();
    }

    private void toString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        if (node.deleted) {
            sb.append("*");
        }
        sb.append(node.key).append(" ");
        toString(node.leftChild, sb);
        toString(node.rightChild, sb);
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    public int size() {
        return size(root);
    }

    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.leftChild) + size(node.rightChild);
    }
}
