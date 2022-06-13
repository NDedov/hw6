package hw6;

import java.util.Random;

public class Main {

    public static class Tree {

        public static class TreeNode {
            private final int num;
            public TreeNode leftChild;
            public TreeNode rightChild;

            public TreeNode(int num) {
                this.num = num;
            }

            @Override
            public String toString() {
                return String.format("%d ", num);
            }
        }

        private TreeNode root;

        public Tree() {
            root = null;
        }

        public void insert(int num) {
            TreeNode node = new TreeNode(num);
            if (root == null) {
                root = node;
            } else {
                TreeNode current = root;
                TreeNode parent;
                while (true) {
                    parent = current;
                    if (num < current.num) {
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = node;
                            return;
                        }
                    } else if (num > current.num) {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = node;
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        private void inOrderTravers(TreeNode current) {
            if (current != null) {
                System.out.println(current);
                inOrderTravers(current.leftChild);
                inOrderTravers(current.rightChild);
            }
        }

        /**
         * метод проверки сбалансированности дерева, сравнивает левую и правую часть дерева, если глубина
         * отличается не более чем на 1, считаем дерево сбалансированным
         * @return результат
         */
        public boolean isBalanced() {
            int diff = findDepth(root.leftChild, 0) - findDepth(root.rightChild, 0);
            return diff == 0 || diff == -1 || diff == 1;
        }

        /**
         * Рекурсивный метод подсчета глубины дерева. Посчитывает глубину слева/справа - возвращает максимальную
         * @param current узел лоя подсчета
         * @param currentDepth текущая глубина
         * @return глубина дерева
         */
        private int findDepth(TreeNode current, int currentDepth) {
            int depthLeft = currentDepth;
            int depthRight = currentDepth;

            if (current == null)//на случай маленьких деревьев при первом запуске
                return currentDepth;

            if (current.leftChild != null)
                depthLeft = findDepth(current.leftChild, currentDepth + 1);
            if (current.rightChild != null)
                depthRight = findDepth(current.rightChild, currentDepth + 1);

            return (Math.max(depthLeft, depthRight));
        }

        public void displayTree() {
            inOrderTravers(root);
        }
    }

    public static void main(String[] args) {
        int forestLength = 200;//количество деревьев в лесу
        int treeNodesAmount = 100; // количество элементов в дереве
        int balancedAmount = 0;// количество сбалансирвоанных деревьев
        float balancedPercent; // процент сбалансированных

        Tree[] forest = new Tree[forestLength];
        Random rnd = new Random();

        for (int i = 0; i < forestLength; i++){
            forest[i] = new Tree();
            for (int j = 0; j < treeNodesAmount; j++)
                forest[i].insert(rnd.nextInt(200) - 100);
        }

        for (Tree tree: forest)
            if (tree.isBalanced())
                balancedAmount++;

        balancedPercent = 100 * (float)balancedAmount/forestLength;
        System.out.printf("Balanced trees percent is: %.2f",balancedPercent);
    }
}