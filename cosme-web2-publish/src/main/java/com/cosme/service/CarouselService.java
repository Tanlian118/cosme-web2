package com.cosme.service;

import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.dto.CarouselDTO;
import com.cosme.web.queryParam.CarouselQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-22 17:10
 **/
public interface CarouselService {


    /**
     * 添加或修改轮播图
     * @param carouselDTO
     */
    ResultDTO<Void> saveOrUpdate(CarouselDTO carouselDTO);

    /**
     * 删除轮播图信息
     * @param carouselIds
     */
    ResultDTO<Void> deleteCarousel(Set<Integer> carouselIds);

    /**
     * 查询轮播图信息
     * @param queryParam
     * @return
     */
    PageModel<CarouselDTO> queryCarousel(CarouselQueryParam queryParam);
}
