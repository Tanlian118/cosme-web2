package com.cosme.dao;

import com.cosme.web.entity.NewsEntity;
import com.cosme.web.queryParam.NewsQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 13:33
 **/
public interface NewsDAO {

    @Insert({"INSERT INTO cosme_news(news_image, news_title, content_summary, release_time, content)",
            "VALUES(#{entity.newsImage}, #{entity.newsTitle}, #{entity.contentSummary}, ",
            "#{entity.releaseTime},#{entity.content})"})
    int save(@Param("entity") NewsEntity newsEntity);

    @Update({"UPDATE cosme_news SET news_image= #{entity.newsImage}, news_title= #{entity.newsTitle},",
            "content_summary= #{entity.contentSummary}, release_time= #{entity.releaseTime}, content= #{entity.content}",
            "WHERE news_id= #{newId}"})
    int update(@Param("entity") NewsEntity newsEntity);

    @Update({"<script>",
            "UPDATE cosme_news SET status= -1",
            "WHERE news_id IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    int delete(@Param("ids") Set<Integer> newsIds);

    @Select({"<script>",
            "SELECT news_id, operater_id,  news_image, news_title, content_summary, release_time, content, update_time,create_time",
            "FROM cosme_news",
            "WHERE status = 1",
            "<if test='newsIds != null and newsIds.size() >0'>",
            "<foreach item='id' collection='newsIds' open='AND news_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size() >0'>",
            "<foreach item='id' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='startTime != null and endTime != null'>",
            "AND release_time <![CDATA[>=]]> #{startTime}",
            "AND release_time <![CDATA[<=]]> #{endTime}",
            "</if>",
            "ORDER BY update_time DESC",
            "<if test='needPagination == true'>",
            "LIMIT #{page},#{pageSize}",
            "</if>",
            "</script>"})
    List<NewsEntity> query(NewsQueryParam queryParam);

    @Select({"<script>",
            "SELECT count(*)",
            "FROM cosme_news",
            "WHERE status = 1",
            "<if test='newsIds != null and newsIds.size() >0'>",
            "<foreach item='id' collection='newsIds' open='AND news_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size() >0'>",
            "<foreach item='id' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "</script>"})
    int count(NewsQueryParam queryParam);

}
