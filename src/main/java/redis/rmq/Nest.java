package redis.rmq;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.TransactionBlock;
import redis.clients.jedis.Tuple;

public class Nest {
    private static final String COLON = ":";
    private StringBuilder sb;
    private String key;
    private Jedis jedis;

    public Nest(String key, Jedis jedis) {
        this.key = key;
        this.jedis = jedis;
    }

    public String key() {
        prefix();
        String generatedKey = sb.toString();
        generatedKey = generatedKey.substring(0, generatedKey.length() - 1);
        sb = null;
        return generatedKey;
    }

    private void prefix() {
        if (sb == null) {
            sb = new StringBuilder();
            sb.append(key);
            sb.append(COLON);
        }
    }

    public Nest cat(int id) {
        prefix();
        sb.append(id);
        sb.append(COLON);
        return this;
    }

    public Nest cat(String field) {
        prefix();
        sb.append(field);
        sb.append(COLON);
        return this;
    }

    // Redis Common Operations
    public String set(String value) {
        Jedis jedis = getResource();
        String set = jedis.set(key(), value);
        returnResource(jedis);
        return set;
    }

    public String get() {
        Jedis jedis = getResource();
        String string = jedis.get(key());
        returnResource(jedis);
        return string;
    }

    public Integer incr() {
        Jedis jedis = getResource();
        Integer incr = jedis.incr(key());
        returnResource(jedis);
        return incr;
    }

    public List<Object> multi(TransactionBlock transaction) {
        Jedis jedis = getResource();
        List<Object> multi = jedis.multi(transaction);
        returnResource(jedis);
        return multi;
    }

    public Integer del() {
        Jedis jedis = getResource();
        Integer del = jedis.del(key());
        returnResource(jedis);
        return del;
    }

    public Integer exists() {
        Jedis jedis = getResource();
        Integer exists = jedis.exists(key());
        returnResource(jedis);
        return exists;
    }

    // Redis Hash Operations
    public String hmset(Map<String, String> hash) {
        Jedis jedis = getResource();
        String hmset = jedis.hmset(key(), hash);
        returnResource(jedis);
        return hmset;
    }

    public Map<String, String> hgetAll() {
        Jedis jedis = getResource();
        Map<String, String> hgetAll = jedis.hgetAll(key());
        returnResource(jedis);
        return hgetAll;
    }

    public String hget(String field) {
        Jedis jedis = getResource();
        String value = jedis.hget(key(), field);
        returnResource(jedis);
        return value;
    }

    public Integer hdel(String field) {
        Jedis jedis = getResource();
        Integer hdel = jedis.hdel(key(), field);
        returnResource(jedis);
        return hdel;
    }

    public Integer hlen() {
        Jedis jedis = getResource();
        Integer hlen = jedis.hlen(key());
        returnResource(jedis);
        return hlen;
    }

    public Set<String> hkeys() {
        Jedis jedis = getResource();
        Set<String> hkeys = jedis.hkeys(key());
        returnResource(jedis);
        return hkeys;
    }

    // Redis Set Operations
    public Integer sadd(String member) {
        Jedis jedis = getResource();
        Integer reply = jedis.sadd(key(), member);
        returnResource(jedis);
        return reply;
    }

    public Integer srem(String member) {
        Jedis jedis = getResource();
        Integer reply = jedis.srem(key(), member);
        returnResource(jedis);
        return reply;
    }

    public Set<String> smembers() {
        Jedis jedis = getResource();
        Set<String> members = jedis.smembers(key());
        returnResource(jedis);
        return members;
    }

    // Redis List Operations
    public Integer rpush(String string) {
        Jedis jedis = getResource();
        Integer rpush = jedis.rpush(key(), string);
        returnResource(jedis);
        return rpush;
    }

    public String lset(int index, String value) {
        Jedis jedis = getResource();
        String lset = jedis.lset(key(), index, value);
        returnResource(jedis);
        return lset;
    }

    public String lindex(int index) {
        Jedis jedis = getResource();
        String lindex = jedis.lindex(key(), index);
        returnResource(jedis);
        return lindex;
    }

    public Integer llen() {
        Jedis jedis = getResource();
        Integer llen = jedis.llen(key());
        returnResource(jedis);
        return llen;
    }

    public Integer lrem(int count, String value) {
        Jedis jedis = getResource();
        Integer lrem = jedis.lrem(key(), count, value);
        returnResource(jedis);
        return lrem;
    }

    public List<String> lrange(int start, int end) {
        Jedis jedis = getResource();
        List<String> lrange = jedis.lrange(key(), start, end);
        returnResource(jedis);
        return lrange;
    }

    // Redis SortedSet Operations
    public Set<String> zrange(int start, int end) {
        Jedis jedis = getResource();
        Set<String> zrange = jedis.zrange(key(), start, end);
        returnResource(jedis);
        return zrange;
    }

    public Set<Tuple> zrangeByScoreWithScores(double min, double max,
            int offset, int count) {
        Jedis jedis = getResource();
        Set<Tuple> zrange = jedis.zrangeByScoreWithScores(key(), min, max,
                offset, count);
        returnResource(jedis);
        return zrange;
    }

    public Set<Tuple> zrangeWithScores(int start, int end) {
        Jedis jedis = getResource();
        Set<Tuple> zrange = jedis.zrangeWithScores(key(), start, end);
        returnResource(jedis);
        return zrange;
    }

    public Integer zadd(float score, String member) {
        Jedis jedis = getResource();
        Integer zadd = jedis.zadd(key(), score, member);
        returnResource(jedis);
        return zadd;
    }

    public Integer zcard() {
        Jedis jedis = getResource();
        Integer zadd = jedis.zcard(key());
        returnResource(jedis);
        return zadd;
    }

    public Double zscore(String member) {
        Jedis jedis = getResource();
        Double zadd = jedis.zscore(key(), member);
        returnResource(jedis);
        return zadd;
    }

    public Double zincrby(double score, String member) {
        Jedis jedis = getResource();
        Double zincrby = jedis.zincrby(key(), score, member);
        returnResource(jedis);
        return zincrby;
    }

    private void returnResource(final Jedis jedis) {
    }

    private Jedis getResource() {
        return jedis;
    }

    public String watch() {
        Jedis jedis = getResource();
        String result = jedis.watch(key());
        returnResource(jedis);
        return result;
    }

    public Integer publish(String message) {
        Jedis jedis = getResource();
        Integer result = jedis.publish(key(), message);
        returnResource(jedis);
        return result;
    }
}