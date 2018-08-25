package com.cosme.dao;

import com.cosme.web.entity.ChannelEntity;
import com.cosme.web.queryParam.ChannelQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 15:01
 **/
public interface ChannelDAO {

    @Insert({"INSERT INTO cosme_channel( main_image, channel_title, japanese_title, weight, link_url)",
            "VALUES(#{entity.mainImage}, #{entity.channelTitle},",
            "#{entity.japaneseTitle}, #{entity.weight},#{entity.linkUrl})"})
    int save(@Param("entity") ChannelEntity channelEntity);


    @Update({"UPDATE cosme_channel SET main_image= #{entity.mainImage},",
            "channel_title= #{entity.channelTitle}",
            "japanese_title= #{entity.japaneseTitle},",
            "weight= #{entity.weight}, link_url=#{entity.linkUrl}",
            "WHERE channel_id =#{entity.channelId}"})
    int update(@Param("entity") ChannelEntity channelEntity);

    @Update({"<script>",
            "UPDATE cosme_channel SET status= -1",
            "WHERE channel_id IN",
            "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    int delete(@Param("ids") Set<Integer> channelIds);

    @Select({"<script>",
            "SELECT channel_id, operater_id, channel_title, japanese_title, main_image, weight, link_url, update_time",
            "FROM cosme_channel",
            "WHERE status=1 AND weight != 0",
            "<if test='channelIds != null and channelIds.size >0'>",
            "<foreach item='channelId' collection='channelIds' open='AND channel_id IN(' separator=',' close=')'>",
            "#{channelId}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size >0'>",
            "<foreach item='operaterId' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{operaterId}",
            "</foreach>",
            "</if>",
            "<if test='weights != null and weights.size >0'>",
            "<foreach item='weight' collection='weights' open='AND weight IN(' separator=',' close=')'>",
            "#{weight}",
            "</foreach>",
            "</if>",
            "ORDER BY weight DESC",
            "<if test='needPagination == true'>",
            "LIMIT #{page},#{pageSize}",
            "</if>",
            "</script>"})
    List<ChannelEntity> queryByParam(ChannelQueryParam channelQueryParam);

    @Select({"<script>",
            "SELECT count(*)",
            "FROM cosme_channel",
            "WHERE status=1 AND weight != 0",
            "<if test='channelIds != null and channelIds.size >0'>",
            "<foreach item='channelId' collection='channelIds' open='AND channel_id IN(' separator=',' close=')'>",
            "#{channelId}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size >0'>",
            "<foreach item='operaterId' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{operaterId}",
            "</foreach>",
            "</if>",
            "<if test='weights != null and weights.size >0'>",
            "<foreach item='weight' collection='weights' open='AND weight IN(' separator=',' close=')'>",
            "#{weight}",
            "</foreach>",
            "</if>",
            "</script>"})
    int  count( ChannelQueryParam channelQueryParam);
}
