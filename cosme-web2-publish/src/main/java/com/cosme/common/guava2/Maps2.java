package com.cosme.common.guava2;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.TreeMultimap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 提供一些常用的Map相关的工具函数
 *
 * @author peng.du
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Maps2 {

    /**
     * 根据函数 A->B 把 A[] 转化为 {B -> A}的Map
     */
    public static <K, V> Map<K, V> newMapWithValue(Collection<? extends V> coll,
                                                   Function<V, K> valueToKeyFunction) {
        return newMapWithValue(coll, valueToKeyFunction, v -> v);
    }
    /**
     * 根据函数 A->B和A->C 把 A[] 转化为 {B -> C}的Map
     */
    public static <O, K, V> Map<K, V> newMapWithValue(Collection<? extends O> coll,
                                                                         Function<O, K> objToKeyFunction,
                                                                         Function<O, V> objToValueFunction) {
        if (coll == null || coll.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, V> results = Maps.newHashMapWithExpectedSize(coll.size());
        for (O obj : coll) {
            K key = objToKeyFunction.apply(obj);
            V value = objToValueFunction.apply(obj);
            results.put(key, value);
        }
        return results;
    }
    /**
     * 根据函数 A->B 把 A[] 转化为 {B -> A[]}的Map，其中A[]是ArrayList
     */
    public static <K, V> ArrayListMultimap<K, V> newMultimapWithValue(Collection<? extends V> coll,
                                                                      Function<V, K> valueToKeyFunction) {
        return newMultimapWithValue(coll, valueToKeyFunction, v -> v);
    }
    /**
     * 根据函数 A->B和A->C 把 A[] 转化为 {B -> C[]}的Map
     */
    public static <O, K, V> ArrayListMultimap<K, V> newMultimapWithValue(Collection<? extends O> coll,
                                                                         Function<O, K> objToKeyFunction,
                                                                         Function<O, V> objToValueFunction) {
        ArrayListMultimap<K, V> result = ArrayListMultimap.create();
        if (coll == null || coll.isEmpty()) {
            return result;
        }
        for (O obj : coll) {
            K key = objToKeyFunction.apply(obj);
            V value = objToValueFunction.apply(obj);
            result.put(key, value);
        }
        return result;
    }
    /**
     * 根据函数 A->B 把 A[] 转化为 {B -> A[]}的Map，其中A[]是HashSet
     */
    public static <K, V> HashMultimap<K, V> newHashMultimapWithValue(Collection<? extends V> coll, Function<V, K> valueToKeyFunction) {
        return newHashMultimapWithValue(coll, valueToKeyFunction, v -> v);
    }
    /**
     * 根据函数 A->B和A->C 把 A[] 转化为 {B -> C[]}的Map，其中C[]是HashSet
     */
    public static <O, K, V> HashMultimap<K, V> newHashMultimapWithValue(Collection<? extends O> coll,
                                                                        Function<O, K> objToKeyFunction,
                                                                        Function<O, V> objToValueFunction) {
        HashMultimap<K, V> result = HashMultimap.create();
        if (coll == null || coll.isEmpty()) {
            return result;
        }
        for (O obj : coll) {
            K key = objToKeyFunction.apply(obj);
            V value = objToValueFunction.apply(obj);
            result.put(key, value);
        }
        return result;
    }
    /**
     * 根据函数 A->B 把 A[] 转化为 {B -> A[]}的Map，其中A[]是TreeSet，按自然方式排序
     */
    public static <K extends Comparable, V extends Comparable> TreeMultimap<K, V> newTreeMultimapWithValue(Collection<? extends V> coll, Function<V, K> valueToKeyFunction) {
        return newTreeMultimapWithValue(coll, valueToKeyFunction, Comparator.<K>naturalOrder(), Comparator.<V>naturalOrder());
    }
    /**
     * 根据函数 A->B 把 A[] 转化为 {B -> A[]}的Map，其中A[]是TreeSet，可自定义排序方式
     */
    public static <K extends Comparable, V extends Comparable> TreeMultimap<K, V> newTreeMultimapWithValue(Collection<? extends V> coll,
                                                                     Function<V, K> valueToKeyFunction,
                                                                     Comparator<? super K> keyComparator,
                                                                     Comparator<? super V> valueComparator) {
        return newTreeMultimapWithValue(coll, valueToKeyFunction, v -> v, keyComparator, valueComparator);
    }
    /**
     * 根据函数 A->B和A->C 把 A[] 转化为 {B -> C[]}的Map，其中C[]是TreeSet
     */
    public static <O, K, V> TreeMultimap<K, V> newTreeMultimapWithValue(Collection<? extends O> coll,
                                                                        Function<O, K> objToKeyFunction,
                                                                        Function<O, V> objToValueFunction,
                                                                        Comparator<? super K> keyComparator,
                                                                        Comparator<? super V> valueComparator) {
        TreeMultimap<K, V> result = TreeMultimap.create(keyComparator, valueComparator);
        if (coll == null || coll.isEmpty()) {
            return result;
        }
        for (O obj : coll) {
            K key = objToKeyFunction.apply(obj);
            V value = objToValueFunction.apply(obj);
            result.put(key, value);
        }
        return result;
    }
    /**
     * 根据函数 A->B 把 A[] 转化为 {A -> B}的Map
     */
    public static <K, V> Map<K, V> newMapWithKey(Collection<? extends K> coll,
                                                 Function<K, V> keyToValueFunction) {
        if (coll == null || coll.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, V> results = Maps.newHashMapWithExpectedSize(coll.size());
        for (K key : coll) {
            V value = keyToValueFunction.apply(key);
            results.put(key, value);
        }
        return results;
    }
    /**
     * 把A[] 转化为 {0:A[0],1:A[1],...}
     */
    public static <V> Map<Integer, V> newMapWithIndex(Collection<? extends V> coll) {
        if (coll == null || coll.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<Integer, V> results = Maps.newHashMapWithExpectedSize(coll.size());
        int i = 0;
        for (V value : coll) {
            results.put(i++, value);
        }
        return results;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map mGet(Collection keys, Map map) {
        Map mGetMap = Maps.newHashMap();
        for (Object key : keys) {
            mGetMap.put(key, map.get(key));
        }
        return mGetMap;
    }

    public static Map<String, String> getStringMap(Map<String, Object> objectMap) {
        if (objectMap == null || objectMap.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, String> stringMap = Maps.newHashMap();
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            stringMap.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return stringMap;
    }

    public static Map<String, Object> getObjectMap(Map<String, String> stringMap) {
        if (stringMap == null || stringMap.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Object> objectMap = Maps.newHashMap();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            objectMap.put(entry.getKey(), entry.getValue());
        }
        return objectMap;
    }

    public static <K, V, M> Map<K, M> associate(Map<K, V> map1, Map<V, M> map2) {
        if (map1 == null || map1.isEmpty() || map2 == null || map2.isEmpty()) {
            return Collections.emptyMap();
        }
        return map1.entrySet().stream()
                .filter(v -> map2.containsKey(v.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, v->map2.get(v.getValue())));
    }
}
