package com.cosme.common.guava2;

import com.cosme.common.SafeFunction;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 提供一些常用的List相关的工具函数
 *
 * @author peng.du
 */
public final class Lists2 {
    private Lists2() {
    }

    /**
     * 把{Key -> Value}的Map根据Key[]的顺序转化为Value[]，不存在的元素填充null
     */
    public static <K, V>
    List<V> newListFromKeyListAndValueMap(Collection<? extends K> ids, Map<? extends K, ? extends V> map) {
        List<V> values = Lists.newArrayListWithCapacity(ids.size());
        for (K id : ids) {
            values.add(map.get(id));
        }
        return values;
    }

    /**
     * 同Lists.transform但把lazy transform的行为关闭
     */
    public static <F, T> List<T> transform(Collection<F> fromList, Function<? super F, ? extends T> function) {
        Collection<? extends T> output = Collections2.transform(fromList, function);
        return Lists.newArrayList(output);
    }

    /**
     * 根据函数 A->B 把 A[] 转化为 B[], 并去除null值, B为List
     */
    public static <F, T> List<T> transformNonNull(Collection<F> fromList, SafeFunction<? super F, T> function) {
        if (CollectionUtils.isEmpty(fromList)) {
            return Collections.emptyList();
        }
        return fromList.stream()
                .map(function)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 把S[]转化为()[]
     */
    public static <S> List<Pair> transformToPair(List<? extends S> fromList, Function<? super S, Pair> function) {
        return transform(fromList, function);
    }

    /**
     * 同{@link Collections2#filter} 但把lazy行为关闭
     */
    public static <E> List<E> filter(List<E> unfiltered, Predicate<? super E> predicate) {
        Collection<E> output = Collections2.filter(unfiltered, predicate);
        return Lists.newArrayList(output);
    }

    /**
     * 滤除List中的null元素
     */
    public static <E> List<E> removeNull(List<E> unfiltered) {
        return filter(unfiltered, Predicates.notNull());
    }

    public static <T> List<T> page(List<T> list, int offset, int count) {
        int fromIndex = Math.min(offset, list.size());
        int toIndex = Math.min(offset + count, list.size());
        return Lists.newArrayList(list.subList(fromIndex, toIndex));
    }

}
