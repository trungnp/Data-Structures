/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author trungnp
 */
public class BinarySearchTree extends BinaryTreeBasis<Person>{

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(Person root) {
        super(root);
    }
    
    
    @Override
    public void setRootItem(Person newItem) {
        throw new UnsupportedOperationException();
    }
    
    public void insert(Person newPerson) {
        root = insertItem(root, newPerson);
    }
    
    public Person retriveItem(String searchKey) {
        return retriveItem(root, searchKey);
    }
    
    public void delete(String searchKey) {
        root = deleteItem(root, searchKey);
    }
    
    public void delete(Person item) {
        root = deleteItem(root, item.getID());
    }
    
    protected TreeNode<Person> insertItem(TreeNode<Person> tNode, Person newItem) {
        TreeNode newSubTree;
        if (tNode == null ) {
            tNode = new TreeNode<>(newItem, null, null);
            return tNode;
        }
        Person nodeItem = tNode.item;
        int a = newItem.compareTo(nodeItem);
        if(a < 0) {
            newSubTree = insertItem(tNode.leftChild, newItem);
            tNode.leftChild = newSubTree;
            return tNode;
        } else {
            newSubTree = insertItem(tNode.rightChild, newItem);
            tNode.rightChild = newSubTree;
            return tNode;
        }
//        return tNode;
    }
    
    protected Person retriveItem(TreeNode<Person> tNode, String searchKey) {
        Person treeItem;
        if(tNode == null)
            treeItem = null;
        else {
            Person nodeItem = tNode.item;
            if (searchKey.compareTo(nodeItem.getID()) == 0)
                treeItem = tNode.item;
            else if (searchKey.compareTo(nodeItem.getID()) < 0)
                treeItem = retriveItem(tNode.leftChild, searchKey);
            else 
                treeItem = retriveItem(tNode.rightChild, searchKey);
        }
        return treeItem;
    }
    
    protected TreeNode<Person> deleteItem(TreeNode<Person> tNode, String searchKey) {
        TreeNode newSubtree;
        if(tNode == null)
            throw new NoSuchElementException();
        else {
            Person nodeItem = tNode.item;
            if(searchKey.compareTo(nodeItem.getID()) == 0) {
                tNode = deleteNode(tNode);
            } else if (searchKey.compareTo(nodeItem.getID()) < 0) {
                newSubtree = deleteItem(tNode.leftChild, searchKey);
                tNode.leftChild = newSubtree;
            } else {
                newSubtree = deleteItem(tNode.rightChild, searchKey);
                tNode.rightChild = newSubtree;
            }
        }
        return tNode;
    }
    
    protected TreeNode<Person> deleteNode(TreeNode<Person> tNode) {
        Person replacementItem;
        if(tNode.leftChild == null && tNode.rightChild == null)
            return null;
        else if (tNode.leftChild == null)
            return tNode.rightChild;
        else if (tNode.rightChild == null)
            return tNode.leftChild;
        else {
            replacementItem = findLeftmost(tNode.rightChild);
            tNode.item = replacementItem;
            tNode.rightChild = deleteLeftmost(tNode.rightChild);
        }
        return tNode;
    }
    
    protected Person findLeftmost(TreeNode<Person> tNode) {
        if(tNode.leftChild == null)
            return tNode.item;
        else 
            return findLeftmost(tNode.leftChild);
    }
    
    protected TreeNode<Person> deleteLeftmost(TreeNode<Person> tNode) {
        if(tNode.leftChild == null)
            return tNode.rightChild;
        else {
            tNode.leftChild = deleteLeftmost(tNode.leftChild);
            return tNode;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner read = new Scanner(new File("/Users/trungnp/NetBeansProjects/Data-Structures/src/data/structures/person"));
        BinarySearchTree bstTree = new BinarySearchTree(/* new Person(read.nextLine()) */);
        while(read.hasNextLine()){
            bstTree.insert(new Person(read.nextLine()));
        }
//        System.out.println(bstTree.root.rightChild.item.toString());
        TreeIterator<Person> i = new TreeIterator<>(bstTree);
        i.setInorder();
        while(i.hasNext())
            System.out.println(i.next().toString());
//        System.out.println();
//        bstTree.delete("t");
//        bstTree.delete("z");
//        System.out.println(bstTree.root.leftChild.item.toString());
//        System.out.println();
//        i.setInorder();
//        while(i.hasNext())
//            System.out.println(i.next().toString());
    }
}

class TreeNode<T> {
    T item;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    public TreeNode(T newItem) {
        item = newItem;
        leftChild = null;
        rightChild = null;
    }
    
    public TreeNode(T newItem, TreeNode<T> left, TreeNode<T> right) {
        item = newItem;
        leftChild = left;
        rightChild = right;
    }
}

abstract class BinaryTreeBasis<T> {
    protected TreeNode<T> root;

    public BinaryTreeBasis() {
        root = null;
    }

    public BinaryTreeBasis(T root) {
        this.root = new TreeNode<>(root, null, null);
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public void makeEmpty(){
        root = null;
    }
    
    public T getRootItem() {
        if(root == null)
            throw new NoSuchElementException();
        else 
            return root.item;
    }
    
    public abstract void setRootItem(T newItem);
}

class BinaryTree<T> extends BinaryTreeBasis<T> {

    public BinaryTree() {
        super();
    }

    public BinaryTree(T root) {
        super(root);
    }
    
    public BinaryTree(T root, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        this.root = new TreeNode<>(root, null, null);
        attachtLeftSubtree(leftTree);
        attachtRightSubtree(rightTree);
    }
    
    @Override
    public void setRootItem(T newItem) {
        if(root != null)
            root.item = newItem;
        else
            root = new TreeNode<>(newItem, null, null);
    }

    public void attachtLeft(T leftTree) {
        if(!isEmpty() && root.leftChild == null)
            root.leftChild = new TreeNode<>(leftTree, null, null);
    }

    public void attachRight(T rightTree) {
        if(!isEmpty() && root.rightChild == null)
            root.rightChild = new TreeNode<>(rightTree, null, null);
    }
    
    public void attachtLeftSubtree(BinaryTree<T> leftTree) {
        if(isEmpty())
            throw new NoSuchElementException();
        else if(root.leftChild != null)
            throw new IllegalStateException("Cannot overwrite left subtree");
        else {
            root.leftChild = leftTree.root;
            leftTree.makeEmpty();
        }
    }
    
    public void attachtRightSubtree(BinaryTree<T> rightTree) {
        if(isEmpty())
            throw new NoSuchElementException();
        else if(root.rightChild != null)
            throw new IllegalStateException("Cannot overwrite left subtree");
        else {
            root.rightChild = rightTree.root;
            rightTree.makeEmpty();
        }
    }
    
    protected BinaryTree(TreeNode<T> rootNode) {
        root = rootNode;
    }
    
    public BinaryTree<T> detachLeftSubtree() {
        if(isEmpty())
            throw new NoSuchElementException();
        else {
            BinaryTree<T> leftTree = new BinaryTree<>(root.leftChild);
            root.leftChild = null;
            return leftTree;
        }
    }
    
    public BinaryTree<T> detachRightSubtree() {
        if(isEmpty())
            throw new NoSuchElementException();
        else {
            BinaryTree<T> rightTree = new BinaryTree<>(root.rightChild);
            root.rightChild = null;
            return rightTree;
        }
    }
    
//    public static void main(String[] args) {
//        BinaryTree<Integer> tree1 = new BinaryTree<>();
//        BinaryTree<Integer> tree2 = new BinaryTree<>();
//        BinaryTree<Integer> tree3 = new BinaryTree<>(70);
//        BinaryTree<Integer> tree4 = new BinaryTree<>();
//        
//        tree1.setRootItem(40);
//        tree1.attachtLeft(30);
//        tree1.attachRight(50);
//        
//        tree2.setRootItem(20);
//        tree2.attachtLeft(10);
//        tree2.attachtRightSubtree(tree1);
//        
//        BinaryTree<Integer> binTree = new BinaryTree<>(60, tree2, tree3);
//        TreeIterator<Integer> btIterator = new TreeIterator<>(binTree);
//        btIterator.setInorder();
//        
//        while(btIterator.hasNext()) {
//            System.out.print(btIterator.next() + " ");
//        }
//        System.out.println();
//        
//        BinaryTree<Integer> leftTree = binTree.detachLeftSubtree();
//        TreeIterator<Integer> leftIterator = new TreeIterator<>(leftTree);
//        leftIterator.setInorder();
//        
//        while(leftIterator.hasNext()) {
//            System.out.print(leftIterator.next() + " ");
//        }
//        System.out.println();
//        
//        btIterator.setInorder();
//        
//        while(btIterator.hasNext()) {
//            System.out.print(btIterator.next() + " ");
//        }
//    }
}

class TreeIterator<T> implements Iterator<T> {
    private BinaryTreeBasis<T> binTree;
    private BinarySearchTree searchTree;
    private TreeNode<T> currentNode;
    private LinkedList<TreeNode<T>> queue;
    
//    public TreeIterator(BinarySearchTree search){
//        searchTree = search;
//        currentNode = null;
//        queue = new LinkedList<TreeNode<T>>();
//    }

    public TreeIterator(BinaryTreeBasis<T> bTree) {
        binTree = bTree;
        currentNode = null;
        queue = new LinkedList<TreeNode<T>>();
    }
    
    

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        if(queue.isEmpty())
            throw new NoSuchElementException();
        currentNode = queue.remove();
        return currentNode.item;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    public void setInorder(){
        queue.clear();
        inorder(binTree.root);
    }
    
    public void setPreorder(){
        queue.clear();
        preorder(binTree.root);
    }
    
    public void setPostorder(){
        queue.clear();
        postorder(binTree.root);
    }

    private void inorder(TreeNode<T> root) {
        if(root != null) {
            inorder(root.leftChild);
            queue.add(root);
            inorder(root.rightChild);
        }
    }

    private void preorder(TreeNode<T> root) {
        if(root != null){
            queue.add(root);
            preorder(root.leftChild);
            preorder(root.rightChild);
        }
    }

    private void postorder(TreeNode<T> root) {
        if(root != null){
            postorder(root.leftChild);
            postorder(root.rightChild);
            queue.add(root);
        }
    }
}

class Person implements Comparable{
    private String id;
    private String name;
    private String phoneNumber;
    private String address;

    public Person(String s) {
        String[] input = s.split(" ");
        this.id = input[0];
        this.name = input[1];
        this.phoneNumber = input[2];
        this.address = input[3];
    }
    
    public String getID(){
        return id;
    }
    
    public String toString(){
        return "ID: " +id+ " || Name: " +name+ " || Phone: " +phoneNumber+ " || Address: " +address;
    }

    @Override
    public int compareTo(Object other) {
        return this.id.compareTo(((Person)other).id);
    }
}