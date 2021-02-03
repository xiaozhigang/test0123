package travel.service.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import travel.dao.CategoryDao;
import travel.dao.impl.CategoryDaoImpl;
import travel.domain.Category;
import travel.service.CategoryService;
import travel.util.JedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {

        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categorys = jedis.zrangeWithScores("category",0,-1);

        List<Category> cs = null;

        if(categorys == null || categorys.size() == 0){
            System.out.println("get data from DB");
            cs = categoryDao.findAll();
            for(int i = 0; i < cs.size(); ++i){
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        }else{
            System.out.println("get data from redis");
            cs = new ArrayList<Category>();
            for(Tuple tuple : categorys){
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
