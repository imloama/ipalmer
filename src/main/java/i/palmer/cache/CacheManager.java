package i.palmer.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class CacheManager {
    private final static Logger logger = LoggerFactory.getLogger(CacheManager.class);

    List<CacheEventListener> listeners = new ArrayList<CacheEventListener>();

    /**
     * Returns a cached item. Can return null if not found.
     * @param group group of caches - this is a name of a table for which query results are cached
     * @param key key of the item.
     * @return a cached item. Can return null if not found.
     */
    public abstract Object getCache(String group, String key);

    /**
     * Adds item to cache. 
     *
     * @param group group name of cache.
     * @param key key of the item.
     * @param cache cache item to add to cache.
     */
    public abstract void addCache(String group, String key, Object cache);


    public abstract void doFlush(CacheEvent event);


    /**
     * Flash cache.
     *
     * @param event type of caches to flush.
     */
    public final void flush(CacheEvent event){        
        doFlush(event);
        for(CacheEventListener listener: listeners){
            try{
                listener.onFlush(event);
            }catch(Throwable e){
                logger.warn("failed to propagate cache event: " + event + "  to listener: " + listener, e);
            }
        }
        @SuppressWarnings("unused")
		String message = event.getType() == CacheEvent.CacheEventType.ALL? "all caches": "table: " + event.getGroup(); 
        //LogFilter.log(logger, "Cache purged: " + message);
    }

    public final void addCacheEventListener(CacheEventListener listener){
        listeners.add(listener);
    }
    
    public final void removeCacheEventListener(CacheEventListener listener){
        listeners.remove(listener);
    }

    public final void removeAllCacheEventListeners(){
        listeners = new ArrayList<CacheEventListener>();
    }
}
