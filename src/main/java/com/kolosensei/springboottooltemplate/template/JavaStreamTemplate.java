package com.kolosensei.springboottooltemplate.template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//静态导入
import static java.util.stream.Collectors.*;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/1/20 19:49
 * @description: java8流式处理的使用实例
 */
public class JavaStreamTemplate {

    private static List<String> list1 = new ArrayList<>();
    private static List<String> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());

    public static void main(String[] args) {
        initList();
        list1 = list1.parallelStream().filter(v -> !v.equals("sd")).collect(toList());
        System.out.println(list1);
//        createStream();
//        transferStream();
//        terminalStream();
//        createOptional();
//        useOptional();
//        collectResultAsCollection();
//        collectResultAsMap();
//        groupAndPartition();
//        handleGroupedResult();
//        reduceResult();
//        fundamentalStream();
//        parallelStream();
    }

    /**
     * 初始化list
     */
    private static void initList() {
        list1.add("a");
        list1.add("sdkfajlkweljfda");
        list1.add("sd");
        list1.add("sd");
        list1.add("智障");

        list2.add("b");
        list2.add("sadfa");
        list2.add("sds");
        list2.add("24");
        list2.add("asdgah");

        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(4);
    }

    /**
     * 常见的各种创建流的方法
     */
    private static void createStream() {
        //从list创建流,所有collection接口都可以使用此方法创建
        long count1 = list1.stream().filter(v -> v.length() > 3).count();
        //创建并行执行的流，所有collection接口都可以使用此方法创建
        long count2 = list2.parallelStream().filter(v -> v.length() > 3).count();
        //使用静态的Stream.of创建,可变长参数，可以传入任意数量引元的流
        Stream<List<String>> stream = Stream.of(list1,list2);
        long count3 = stream.distinct().count();
        //使用Arrays.stream获取
        Arrays.stream("s,a,b".split(","),0,2);
        //创建一个不包含任何元素的流
        Stream<Object> empty = Stream.empty();
        //创建无限流
        //获取无限常量值的流
        Stream<String> generate = Stream.generate(() -> "Echo");
        //获取无限随机数的流
        Stream<Double> generate1 = Stream.generate(Math::random);
        //产生无限序列
        Stream<BigInteger> iterate = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
    }

    /**
     * 将一个字符串切割为包含单个字符的流
     * @param s
     * @return
     */
    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i,i+1));
        }
        return result.stream();
    }

    /**
     * 常见的转换流的操作
     */
    private static void transferStream() {
        //使用filter进行过滤，filter中引元需要返回boolean值，可视作多对一
        long count = list1.stream().filter(v -> v.length() > 5).count();
        //使用map，对元素中的每一个元素进行转换，一对一
        Stream<String> stringStream = list1.stream().map(v -> v.substring(2));
        //使用flatmap，可以将元素产生的结果连在一起，一对多
        Stream<String> stringStream1 = list1.stream().flatMap(s -> letters(s));
        //获取包含100个随机数的流
        Stream<Double> limit = Stream.generate(Math::random).limit(100);
        //跳过第一个元素
        Stream<String> skip = Stream.of("sdlf sdaf d".split("\\PL+")).skip(1);
        //连接两个流
        Stream<String> concat = Stream.concat(letters("hellow"), letters("world"));
        //剔除重复元素
        Stream<String> distinct = Stream.of("hello", "hello", "hello", "world", "sda", "hello").distinct();
        //对流中的元素排序，可以接受comparable元素，或者comparator
        Stream<String> sorted = list1.stream().sorted(Comparator.comparing(String::length).reversed());
        //peek会产生一个六，它的元素和原来的流相同，但每次获取一个元素时，都会调用一个函数
        Object[] objects = Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println("fetching " + e)).limit(20).toArray();
    }

    /**
     * 终结流的操做，只有使用此操作之后，前面的转换才会生效计算
     */
    private static void terminalStream() {
        //统计流中的元素数量
        long count = list1.stream().count();
        //max,min返回最大值和最小值，返回的是一个Optional<T>的值，其中要么包含答案，要么没有任何值
        Optional<String> max = list1.stream().max(String::compareToIgnoreCase);
        System.out.println("max: "+max.orElse(""));
        //获取第一个以Q开头的元素
        Optional<String> q = list1.stream().filter(s -> s.startsWith("Q")).findFirst();
        //获取任意一个以Q开头的元素
        Optional<String> q1 = list1.parallelStream().filter(s -> s.startsWith("Q")).findAny();
        //如果想知道是否存在匹配，择优anyMatch,allMatch,noneMatch
        boolean q2 = list1.parallelStream().anyMatch(s -> s.startsWith("Q"));
    }

    /**
     * Optional类型的使用范例，optional可以更安全的处理null
     */
    private static void useOptional() {
        //创建一个Optional对象
        Optional<String> max = list1.stream().filter(v -> v.startsWith("Q")).max(String::compareToIgnoreCase);
        //为空时，返回""
        String s = max.orElse("");
        //为空时，自行计算返回值
        String s1 = max.orElseGet(() -> Locale.getDefault().getDisplayName());
        //为空时，抛出异常
        String s2 = max.orElseThrow(IllegalStateException::new);
        //如果存在，则会把值传给指定的方法
        max.ifPresent(v -> System.out.println(v));
        //如果存在，则添加到某个collection中
        max.ifPresent(list2::add);
        max.ifPresent(v -> list2.add(v));
        //added中具有三种值，true,false，或者为空
        Optional<Boolean> added = max.map(list2::add);
        //获取optional的值，或者跑出一个NoSuchElementException
        String s3 = max.get();
    }

    /**
     * 求倒数
     * @param x
     * @return
     */
    private static Optional<Double> inverse(Double x) {
        return x==0 ? Optional.empty() : Optional.of(1/x);
    }

    /**
     * 创建optional对象
     */
    private static void createOptional() {
        //产生一个空optional
        Optional<Object> empty = Optional.empty();
        //产生一个具有给定值的optional,如果为空则抛出NullPointerException
        Optional<String> sdk = Optional.of("sdk");
        //产生一个具有给定值的optional,如果为空则产生一个空的optional
        Optional<Object> o = Optional.ofNullable(null);
        //todo 还有一种使用flatmap的方式创建，但是比较复杂，这里暂时不做举例，可参考《Java核心技术卷2》P 14
    }

    /**
     * 收集结果，处理为Collection
     */
    private static void collectResultAsCollection() {
        //以任意顺序遍历各个元素
        list1.stream().parallel().forEach(System.out::println);
        //有序的遍历各个元素
        list1.stream().forEachOrdered(System.out::println);
        Stream<String> stringStream = list1.stream().filter(v -> v.length() > 3);
        //将结果转换为数组
        String[] strings = stringStream.toArray(String[]::new);
        //将结果转换为list
        List<String> collect = stringStream.collect(Collectors.toList());
        //将结果转换为set
        Set<String> collect1 = stringStream.collect(Collectors.toSet());
        //将结果转换为指定的集合类型
        TreeSet<String> collect2 = stringStream.collect(Collectors.toCollection(TreeSet::new));
        //结果元素间增加指定的分隔符
        String result = stringStream.collect(Collectors.joining(","));
        //获取流结果的总和、平均值、最大值最小值summarizing(Int|Long|Double)
        IntSummaryStatistics collect3 = list2.stream().collect(Collectors.summarizingInt(String::length));
        collect3.getAverage();
        collect3.getCount();
        //使用迭代器遍历
        Iterator<Integer> iterator = list3.stream().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 收集结果，处理为map
     */
    private static void collectResultAsMap() {
        //将元素收集到映射表中，使用tomap，其中Function.identity()作用为返回输入元素本身
        //除了tomap，还有toConcurrentMap用于产生并发映射表
        Map<Integer, String> collect4 = list2.stream().collect(Collectors.toMap(String::length, Function.identity()));
        //当多个元素有冲突的键时
        Map<String, String> collect5 = locales.collect(Collectors.toMap(
                //key
                Locale::getDisplayLanguage,
                //value
                l -> l.getDisplayLanguage(l),
                // 如果存在冲突时的处理方案
                (existingValue, newValue) -> existingValue));
        //此处生成了某个国家的所有语言，key 国家名称,value 该国家所使用语言的set
        Map<String, Set<String>> collect6 = locales.collect(Collectors.toMap(
                Locale::getDisplayCountry,
                l -> Collections.singleton(l.getDisplayLanguage()),
                (a, b) -> {
                    //合并a,b
                    Set<String> union = new HashSet<>(a);
                    union.addAll(b);
                    return union;
                }
        ));
        //指定构造器为treemap
        Map<String, String> collect7 = locales.collect(Collectors.toMap(
                //key
                Locale::getDisplayLanguage,
                //value
                l -> l.getDisplayLanguage(l),
                // 如果存在冲突时的处理方案
                (existingValue, newValue) -> existingValue,
                TreeMap::new));
    }

    /**
     * 分组和分区
     */
    private static void groupAndPartition() {
        //使用国家groupingBy来进行分组，也可以使用并发的 groupingByConcurrent
        Map<String, List<Locale>> collect = locales.collect(Collectors.groupingBy(Locale::getCountry));
        //当分类函数使用的是断言函数（即返回boolean时），使用partitioningBy更高效
        Map<Boolean, List<Locale>> en = locales.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        List<Locale> locales = en.get(true);
    }

    /**
     * 被groupingBy会产生一个映射表，每个值都是一个列表，可以按照需求来处理这些列表
     * //静态导入
     * import static java.util.stream.Collectors.*;便于使用相关的方法
     */
    private static void handleGroupedResult() {
        //将结果处理为一个set
        Map<String, Set<Locale>> collect = locales.collect(groupingBy(Locale::getCountry, toSet()));
        //统计元素格式
        Map<String, Long> collect1 = locales.collect(groupingBy(Locale::getCountry, counting()));
        //更多用法请参考《Java核心技术卷2》P24
        Map<String, Set<String>> collect2 = locales.collect(groupingBy(Locale::getDisplayCountry, mapping(Locale::getDisplayLanguage, toSet())));
    }

    /**
     * 对结果进行约简操作
     */
    private static void reduceResult() {
        //统计流中每个字符串的长度，而后将他们的长度累加
        // (total1, total2) -> total1 + total2的作用在于计算被并行化的时候，需要将计算的结果合并
        Integer reduce = list1.stream().parallel().reduce(0, (total, word) -> total + word.length(), (total1, total2) -> total1 + total2);
    }

    /**
     * 基本类型流
     */
    private static void fundamentalStream() {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        int[] values = new int[] {2,3,4,5,6};
        IntStream stream = Arrays.stream(values, 2, 4);
        IntStream range = IntStream.range(0, 100);
        IntStream intStream1 = IntStream.rangeClosed(0, 100);
        IntStream intStream2 = list2.stream().mapToInt(String::length);
        Stream<Integer> boxed = IntStream.range(0, 100).boxed();
    }

    private static void parallelStream() {

    }
}
