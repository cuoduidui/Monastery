package com.cdd.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yangfengshan2
 * @date 2018/11/16
 */
public class StreamTest {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.asList(1, 5, 7, 4, 8, 3, 9, 5, 7, 3, 5, 9, 23, 53, 745, 546).parallelStream();
        stream.filter((a) -> {
            System.out.print("filter:" + a + "\n");
            return a > 5;
        }).map((a) -> {
            System.out.print("map:" + a + "\n");
            return String.valueOf(a);
        }).limit(3).forEach((a) -> System.out.print("each:" + a + "\n"));

        //打印日志 说明流中的元素是按需计算的
        //尽管filter和map是两个独立的操作，但它们合并到同一次遍历中了（我们把这种技术叫作循环合并）。
//        filter:1
//        filter:5
//        filter:7
//        map:7
//        each:7
//        filter:4
//        filter:8
//        map:8
//        each:8
//        filter:3
//        filter:9
//        map:9
//        each:9

        Stream<Integer> stream1 = Arrays.asList(1, 5, 7, 4, 8, 3, 9, 5, 7, 3, 5, 9, 23, 53, 745, 546).parallelStream();
        List<Object> list = Arrays.asList(stream1.filter((a) -> {
            System.out.print("filter1:" + a + "\n");
            return a > 5;
        }).map((a) -> {
            System.out.print("map1:" + a + "\n");
            return String.valueOf(a);
        }).limit(3).skip(1).toArray());

        System.out.print("list:" + list + "\n");
        // .stream()     串行流
        // .parallelStream()    并行流
//        skip(n) 跳过前n个数
//        distinct（）过滤重复的字符
        // flatmap(Arrays::stream)方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流。
        //map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流
        //看map 和    flatMap 的具体方法 你可以发现 返回的值 flatMap是返回一个  ? extends Stream<? extends R>
        //map返回    ? extends R 所以   使用flatMap后还可以继续这么操作
        // <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper);

//        给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
//        该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，
//         你可以用有两个元素的数组来代表数对
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<List<Integer>> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> Arrays.asList(new Integer[]{i, j}))
                        )
                        .collect(Collectors.toList());

        pairs.forEach(System.out::print);

//     原始类型流特化，专门支持处理数值流的方法
        //IntStream、 DoubleStream和LongStream，
        // 分别将流中的元素特化为int、 long和double，从而避免了暗含的装箱成本
        //将流转换为intStream
        IntStream intStream = numbers1.stream().mapToInt((a) -> a);
        //  将intStream流转换为    Stream
        Stream<Integer> stream2 = intStream.boxed();

        // Stream API提供了两个静态方法来从函数生成流： Stream.iterate和Stream.generate。
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        //iterate方法接受一个初始值（在这里是0），还有一个依次应用在每个产生的新值上的
//        Lambda（UnaryOperator<t>类型）

//        但generate不是依次对每个新生成的值应用函数的

    }
}
