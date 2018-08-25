package com.cosme.service;

import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.dto.NewsDTO;
import com.cosme.web.queryParam.NewsQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 13:27
 **/
public interface NewsService {

    /**
     * 添加或修改新闻
     * @param newsDTO
     */
    ResultDTO<Void> addOrUpdateNews(NewsDTO newsDTO);

    /**
     * 删除新闻信息
     * @param newsIds
     * @return
     */
    ResultDTO<Void> deleteNews(Set<Integer> newsIds);

    /**
     * 查询新闻信息
     * @param newsQueryParam
     * @return
     */
    PageModel<NewsDTO> queryByParam(NewsQueryParam newsQueryParam);
}
