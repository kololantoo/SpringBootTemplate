package com.kolosensei.springboottooltemplate.template;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/2/18 13:39
 * @description: java collection 使用实例
 */
public class CollectionTemplate {

    public static void main(String[] args) {
        listTemplate();
        setTemplate();
        mapTemplate();
        queueTemplate();
        safeCollectionTemplate();
    }

    /**
     * java常用List类实例
     */
    private static void listTemplate() {
        //ArrayList基于数组实现，线程不安全，fail-fast机制，扩容后增加50%
        List<String> list = new ArrayList<>();
        //Vector基于数组实现，线程安全，内部每个方法都上锁，开销大，效率低，扩容后增加一倍
        Vector<String> vector = new Vector<>();
        //LinkedList基于双向链表实现，允许存储任何元素，所以操作都可以表现为双向性，线程不安全
        List<String> linkedList = new LinkedList<>();
        //继承自vector，后进先出，更推荐使用Deque
        Stack<String> stack = new Stack<>();
        Deque<String> deque = new ArrayDeque<>();
    }

    /**
     * java常用Set类实例
     */
    private static void setTemplate() {
        //由哈希表支持（实际上是hashmap的一个实例），不能保证集合迭代顺序，线程不安全，fail-fast
        Set<String> set = new HashSet<>();
        //基于TreeMap的NavigableSet实现，元素使用自然排序，或者创建时提供的comparator排序，线程不安全，fail-fast，基本操作log(n)时间成本
        Set<String> treeSet = new TreeSet<>();
        //Set接口的Hash表和LinkedList的实现,线程不安全。影响元素：初始容量，加载因子。高的初始容量开销比hashset小
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
    }

    /**
     * java常用Map类实例
     */
    private static void mapTemplate() {
        //基于哈希表原理，允许空的key-value，线程不安全：初始容量，加载因子
        Map<String,String> map = new HashMap<>();
        //基于NavigableMap的红黑树，使用key自然排序，或定制Comparator排序，线程不安全，fail-fast，log(n)访问开销
        Map<String,String> treeMap = new TreeMap<>();
        //map接口的哈希表和链表顺序，和hashmap区别在于维护了一个双向链表，定义了遍历顺序，线程不安全，fail-fast
        Map<String,String> linkedhashMap = new LinkedHashMap<>();
        //允许非空对象做键值，线程安全
        Map<String,String> hashtable = new Hashtable<>();
        //多线程，高并发
        Map<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        //无序，线程不安全，fail-fast
        Map<String,String> identityHashMap = new IdentityHashMap<>();
        //基于哈希表的map基础实现，带有弱键，entry不再使用时会自动移除，常用作缓存
        Map<String,String> weakHashMap = new WeakHashMap<>();
    }

    /**
     * java常用Queue类实例
     */
    private static void queueTemplate() {
        //AbstractQueue的实现类，使用元素自然排序或Comparator排序，不允许null,线程不安全，iterator方法不能保证顺序遍历元素。若需要有序遍历，可使用Arrays.sort(pq.toArray())
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        //线程安全
        PriorityBlockingQueue<String> pbq = new PriorityBlockingQueue<>();
    }

    /**
     * java常用线程安全Collection类实例
     */
    private static void safeCollectionTemplate() {
        CopyOnWriteArrayList<String > copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        ConcurrentHashMap<String ,String> concurrentHashMap = new ConcurrentHashMap<>();
        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
    }
}
