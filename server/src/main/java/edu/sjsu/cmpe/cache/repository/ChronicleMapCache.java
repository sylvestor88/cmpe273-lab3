package edu.sjsu.cmpe.cache.repository;

import edu.sjsu.cmpe.cache.domain.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ChronicleMapCache implements CacheInterface {
    private ConcurrentMap<Long, Entry> map;

    public ChronicleMapCache(ConcurrentMap<Long, Entry> entries) {
        map = entries;
    }

    @Override
    public Entry save(Entry newEntry) {
        checkNotNull(newEntry, "Cannot have newEntry instance as null");
        map.putIfAbsent(newEntry.getKey(), newEntry);
        return newEntry;
    }

    @Override
    public Entry get(Long key) {
        checkArgument(key > 0,
                "Key %s is not valid, value greater than zero is expected", key);
        return map.get(key);
    }

    @Override
    public List<Entry> getAll() {
        return new ArrayList<Entry>(map.values());
    }
}
