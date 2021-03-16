public class BSTree {
    private Node root;

    public BSTree() {
        root = null;
    }

    /*
     * insert takes a key value and adds it into the BST, returning a reference to the newly added node
     * or returns null if the node already exists in the tree
     */
    public Node insert(int k) {
        Node node = new Node(k);
        if (root == null) {
            root = node;
            return node;
        }

        Node curr = root;
        while (true) {
            if (k < curr.getKey()) { //k < curr, go left
                if (curr.getLeft() == null) { //if left is empty, insert node
                    curr.setLeft(node);
                    node.setParent(curr);
                    return node;
                } else { //else traverse left
                    curr = curr.getLeft();
                }
            } else if (k > curr.getKey()) {//k > curr, go right
                if (curr.getRight() == null) { //if right is empty, insert node
                    curr.setRight(node);
                    node.setParent(curr);
                    return node;
                } else { //else traverse right
                    curr = curr.getRight();
                }
            } else { //else k == curr
                System.out.println("The given key already exists.");
                return null;
            }
        }
    }

    /*
     * Deletes the node with the give key value k and transplants it with a qualifying node, if necessary.
     * If node to be deleted is a leaf, just replace it with null.
     * If node has only left subtree, find largest value of left subtree to transplant.
     * If node has only right subtree, OR, left and right subtree, find smallest value of right subtree for transplant.
     */
    public Node delete(int k) {
        Node n = search(k);
        if(n == null){ //n was not existing, return null
            System.out.println("No such a key");
            return null;
        }
        //if n is a leaf
        else if (n.getLeft() == null && n.getRight() == null) {
            transplant(n, null);
        }
        //if n only has left subtree
        else if (n.getRight() == null) {
            Node b = n.getLeft(); //Move left subtree up one
            transplant(n, b);
        }
        //if n only has right subtree, OR n has two children
        //we find and transplant the same value for both scenarios
        else {
            Node b = min(n.getRight()); //get min value from right subtree

            //if b parent is not previous node, we have to prepare the previous subtrees of b
            //to take the place of b
            if(b.getParent() != n){
                transplant(b, b.getRight());
            }

            //insert b into the spot of n
            transplant(n,b);
        }

        System.out.println("The given key has been successfully deleted.");
        return n;

    }

    /*
    Cuts out node a from the tree and inserts node b into that empty spot.
     */
    public void transplant(Node a, Node b) {
        Node parent = a.getParent();
        if (parent == null) {
            root = b;
        } else if (parent.getLeft() == a) {
            parent.setLeft(b);
        } else { //if parent.getRight() == a
            parent.setRight(b);
        }


        //Cut off the connection between b and it's current parent
        if (b != null) { //b is null iff node a is a leaf
            parent = b.getParent();
            if(parent!=null) {
                if (parent.getLeft() == b) {
                    parent.setLeft(null);
                } else if (parent.getRight() == b) {
                    parent.setRight(null);
                }
            }

            //Insert b into the spot of node a
            b.setLeft(a.getLeft());
            b.setRight(a.getRight());
            b.setParent(a.getParent());

            if(b.getLeft() != null){
                b.getLeft().setParent(b);
            }
            if(b.getRight() != null){
                b.getRight().setParent(b);
            }

            //Prepare node a for garbage collection
            a.setLeft(null);
            a.setRight(null);
            a.setParent(null);
        }

    }

    /*
     * Search takes a key value and returns the node with that given key,
     * or returns null if no such key exists.
     */
    public Node search(int k) {
        Node curr = root;
        while (curr != null && curr.getKey() != k) {
            if (k < curr.getKey()) { //if less than, go left
                curr = curr.getLeft();
            } else { //else it must be greater than, go right
                curr = curr.getRight();
            }
        }

        return curr;
    }

    /*
     * In Order traversal goes to the leftmost node, then begins printing
     * the node values as it traverses back to the root, then down the right side of the tree.
     */
    public void inOrder(Node n) {
        if (n != null) { //recursive condition
            inOrder(n.getLeft()); //continue going left before anything else
            System.out.print(n.getKey() + " ");
            inOrder(n.getRight());
        }
    }

    /*
     * Pre Order traversal begins at the root/subroot, printing the values of each node as it goes left.
     * Then prints any right nodes on it's way back to the root/subroot and traverses the right side of the tree.
     */
    public void preOrder(Node n) {
        if (n != null) {
            System.out.print(n.getKey() + " "); //Print value of each node as you encounter it
            preOrder(n.getLeft());
            preOrder(n.getRight());
        }
    }

    //Post Order first travels to the very bottom of the left side of the tree before traveling up,
    //prioritizing the bottom-left most nodes, then bottom right.
    public void postOrder(Node n) {
        if (n != null) {
            postOrder(n.getLeft());
            postOrder(n.getRight());
            System.out.print(n.getKey() + " ");
        }
    }

    //Level Order traversal prints out the nodes in the order that they logically
    //appear on the Binary Search Tree.
    public void levelOrder(Node n) {
        Queue q = new Queue();
        q.enqueue(n);
        while (q.getSize() > 0) {
            Node curr = q.dequeue();
            if (curr.getLeft() != null) {
                q.enqueue(curr.getLeft());
            }
            if (curr.getRight() != null) {
                q.enqueue(curr.getRight());
            }
            System.out.print(curr.getKey() + " ");
        }
    }

    //Returns the smallest value from the subtree, which will always be the leftmost tree node.
    public Node min(Node n) {
        while (n.getLeft() != null) {
            n = n.getLeft();
        }
        return n;
    }

    public Node max(Node n) {
        while (n.getRight() != null) {
            n = n.getRight();
        }
        return n;
    }

    //Finds the next successor node to the given subtree node.
    //Returns null if no such successor exists
    public Node successor(Node n){
        if(n.getRight() != null){ //default case
            return min(n.getRight());
        }
        //else, find n's smallest ancestor in which n is in the left subtree
        Node par = n.getParent();
        while(par!=null && par.getRight() == n){
            n = par; //move n up one level in the tree
            par = n.getParent();
        }
        return par;
    }

    //Finds the previous predecessor node to the given subtree node.
    //Returns null if no such predecessor exists
    public Node predecessor(Node n){
        if(n.getLeft() != null){
            return min(n.getLeft());
        }
        Node parent = n.getParent();
        while(parent != null && parent.getLeft() == n){
            n = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    public Node getRoot() {
        return root;
    }
}