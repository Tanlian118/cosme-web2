package com.cosme.common.guava2;

import com.cosme.common.SafeFunction;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 提供一些常用的Set相关的工具函数
 *
 * @author peng.du
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Sets2 {

    public static <F, T> Set<T> transform(Collection<F> fromList, Function<? super F, T> function) {
        Collection<T> output = Collections2.transform(fromList, function);
        return Sets.newHashSet(output);
    }

    /**
     * 根据函数 A->B 把 A[] 转化为 B[], 并去除null值, B为Set
     */
    public static <F, T> Set<T> transformNonNull(Collection<F> fromList, SafeFunction<? super F, T> function) {
        if (CollectionUtils.isEmpty(fromList)) {
            return Collections.emptySet();
        }
        return fromList.stream()
                .map(function)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toSet());
    }

    public static <E> Set<E> filter(Collection<E> unfiltered, Predicate<? super E> predicate) {
        Collection<E> output = Collections2.filter(unfiltered, predicate);
        return Sets.newHashSet(output);
    }

    /**
     * 返回一个不可变的集合，这个集合的contains检验永远返回真除非入参为null
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> newAlwaysContainsSet() {
        //noinspection unchecked
        return AlwaysContainsSet.INSTANCE;
    }

    @SuppressWarnings("rawtypes")
    private static class AlwaysContainsSet extends HashSet implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = -5675394681895337973L;

        @SuppressWarnings({"unchecked"})
        public static final Set INSTANCE = Collections.unmodifiableSet(new AlwaysContainsSet());

        @Override
        public boolean contains(Object o) {
            return o != null;
        }

    }
}
