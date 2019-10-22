package com.cdd.concurrent;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * describe:
 *
 * @author yangfengshan
 * @date 2018/11/22
 */
public class SpliteratorDemo {
    public static final String SENTENCE =
            " Nel mezzo del cammin di nostra vita " +
                    "mi ritrovai in una selva oscura" +
                    " ché la dritta via era smarrita ";

    public static void main(String[] args) {
        System.out.println(countWordsIteratively(SENTENCE));
        //1, 使用range方法给定一个范围来创建一个基本类型的流。
        //每个字符 是一个流
        Stream<Character> characterStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        // characterStream.forEach(System.out::println);  //产生的流是顺序的  但是分割的位置是不确定的
        System.out.println(countWords(characterStream));
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println(countWords(stream));
    }


    /**
     * 计算字符串字数
     *
     * @param s
     * @return
     */
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.parallel().reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    static class WordCounter {
        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ?
                        this :
                        new WordCounter(counter, true);
            } else {
                return lastSpace ?
                        new WordCounter(counter + 1, false) :
                        this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter,
                    wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }

    static class WordCounterSpliterator implements Spliterator<Character> {
        private final String string;
        private int currentChar = 0;

        public WordCounterSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            //定义了拆分要遍历的数据
//            结构的逻辑。
            int currentSize = string.length() - currentChar;
            //拆分的下限
            if (currentSize < 10) {
                return null;
            }
            for (int splitPos = currentSize / 2 + currentChar;
                 splitPos < string.length(); splitPos++) {
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    Spliterator<Character> spliterator =
                            new WordCounterSpliterator(string.substring(currentChar,
                                    splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            //剩余要分割的数量
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
//            ORDERED（顺序就是String
//            中 各 个Character 的 次序 ）、 SIZED （estimatedSize 方 法的 返 回 值 是精 确 的 ）、
//            SUBSIZED（trySplit方法创建的其他Spliterator也有确切大小）、 NONNULL（String
//            中 不 能 有 为 null 的 Character ） 和 IMMUTABLE （ 在 解 析 String 时 不 能 再 添 加
//            Character，因为String本身是一个不可变类）的
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
}
