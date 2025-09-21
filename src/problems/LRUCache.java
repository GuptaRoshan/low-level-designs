package problems;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> map;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
            return map.getOrDefault(key, null);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            // update if cache contains they key, after update LinkedHashMap updates the order
            if (map.containsKey(key)) {
                map.put(key, value);
                return;
            }

            // If the cache does not contain the key, check the size and update
            if (map.size() >= capacity) {
                K eldestKey = map.keySet().iterator().next();
                map.remove(eldestKey);
            }

            map.put(key, value);

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache); // {1=A, 2=B, 3=C}

        System.out.println(cache.get(1));
        cache.put(4, "D"); // evicts 2 (the LRU)
        System.out.println(cache); // {3=C, 1=A, 4=D}
    }
}
