package com.campus.mybais.mapper;


import com.campus.dao.pojo.News;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class NewsMapper {

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public NewsMapper(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<News> findNewsList(Map<String,Object> param){
        return this.sqlSessionTemplate.selectList("findNewsList",param);
    }
    public Integer addNews(News news){
        return this.sqlSessionTemplate.insert("insertNewsSelective",news);
    }

    public News findNewsById(Integer id){
        return this.sqlSessionTemplate.selectOne("findNewsById",id);
    }
    public Integer editNews(News news){
        return this.sqlSessionTemplate.update("updateNewsByIdSelective",news);
    }
    public Integer deleteNews(Integer id){
        return this.sqlSessionTemplate.delete("deleteNewsById",id);
    }
}