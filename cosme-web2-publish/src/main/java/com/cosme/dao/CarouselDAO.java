package com.cosme.dao;

import com.cosme.web.entity.CarouselEntity;
import com.cosme.web.queryParam.CarouselQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-22 17:13
 **/
public interface CarouselDAO {

    @Insert({"INSERT INTO cosme_carousel_image (carousel_image, weight, link_url)",
            "Values(#{entity.carouselImage}, #{entity.weight},",
            "#{entity.linkUrl})"})
    int save(@Param("entity") CarouselEntity carouselEntity);

    @Update({"UPDATE cosme_carousel_image SET carousel_image = #{entity.carouselImage},",
            "weight = #{entity.weight}, link_url = #{entity.linkUrl} ",
            "WHERE cosme_image_id = #{entity.cosmeImageId}"})
    int update(@Param("entity") CarouselEntity carouselEntity);

    @Update({"<script>",
            "UPDATE cosme_carousel_image SET status =-1",
            "WHERE cosme_image_id IN",
            "<foreach item='cosmeImageId' collection='cosmeImageIds' open='(' separator=',' close=')'>",
            "#{cosmeImageId}",
            "</foreach>",
            "</script>"})
    int delete(@Param("cosmeImageIds") Set<Integer> cosmeImageIds);


    @Select({"<script>",
            "SELECT cosme_image_id, carousel_image, weight, operater_id, link_url, update_time",
            "FROM cosme_carousel_image",
            "WHERE status = 1 AND weight != 0",
            "<if test='cosmeImageIds != null and cosmeImageIds.size() >0'>",
            "<foreach item='id' collection='cosmeImageIds' open='AND cosme_image_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size() >0'>",
            "<foreach item='id' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='weights != null and weights.size() >0'>",
            "<foreach item='id' collection='weights' open='AND weight IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "ORDER BY weight DESC",
            "<if test='needPagination == true'>",
            "LIMIT #{page},#{pageSize}",
            "</if>",
            "</script>"
    })
    List<CarouselEntity> queryCarousel(CarouselQueryParam queryParam);

    @Select({"<script>",
            "SELECT count(*)",
            "FROM cosme_carousel_image",
            "WHERE status = 1 AND weight != 0",
            "<if test='cosmeImageIds != null and cosmeImageIds.size() >0'>",
            "<foreach item='id' collection='cosmeImageIds' open='AND cosme_image_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='operaterIds != null and operaterIds.size() >0'>",
            "<foreach item='id' collection='operaterIds' open='AND operater_id IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "<if test='weights != null and weights.size() >0'>",
            "<foreach item='id' collection='weights' open='AND weight IN(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</if>",
            "</script>"
    })
    int count (CarouselQueryParam queryParam);
}
