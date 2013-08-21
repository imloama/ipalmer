package i.palmer.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EHCacheManager extends CacheManager{
	private Logger logger = LoggerFactory.getLogger(getClass());
    private net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create();

    @Override
    public Object getCache(String group, String key) {
        try {
            createIfMissing(group);
            Cache c = cacheManager.getCache(group);
            return c.get(key) == null ? null : c.get(key).getObjectValue();
        } catch (Throwable e) {
            logger.warn(e.toString(), e);
            return null;
        }
    }

    private void createIfMissing(String group) {
        //double-checked synchronization is broken in Java, but this should work just fine.
        if (cacheManager.getCache(group) == null) {
            try{
                cacheManager.addCache(group);
            }catch(net.sf.ehcache.ObjectExistsException ignore){}
        }
    }

    @Override
    public void addCache(String group, String key, Object cache) {
        createIfMissing(group);
        cacheManager.getCache(group).put(new Element(key, cache));
    }

    @Override
    public void doFlush(CacheEvent event) {

        if (event.getType().equals(CacheEvent.CacheEventType.ALL)) {
            cacheManager.removeAllCaches();//.removalAll();
        } else if (event.getType().equals(CacheEvent.CacheEventType.GROUP)) {
            cacheManager.removeCache(event.getGroup());
        }
    }
}
