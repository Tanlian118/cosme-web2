package com.cosme.service.impl;

import com.cosme.common.BaseTransformer;
import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.dao.NewsDAO;
import com.cosme.service.NewsService;
import com.cosme.transformers.NewsTransformer;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.NewsDTO;
import com.cosme.web.entity.NewsEntity;
import com.cosme.web.queryParam.NewsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 13:29
 **/
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    public ResultDTO<Void> addOrUpdateNews(NewsDTO newsDTO) {
        NewsEntity newsEntity = BaseTransformer.convert(newsDTO, new NewsEntity());
        if (newsDTO.getNewsId() == null) {
            newsDAO.save(newsEntity);
            return ResultDTO.successfy();
        }
        newsDAO.update(newsEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteNews(Set<Integer> newsIds) {
        if (CollectionUtils.isEmpty(newsIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "newsIds为空");
        }
        newsDAO.delete(newsIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<NewsDTO> queryByParam(NewsQueryParam queryParam) {
        if (queryParam == null) {
            return PageModel.emptyModel();
        }
        List<NewsEntity> newsEntities = newsDAO.query(queryParam);
        List<NewsDTO> newsDTOs = Lists2.transform(newsEntities, NewsTransformer.ENTITY_TO_DTO);
        int count = newsDAO.count(queryParam);
        return PageModel.build(newsDTOs, count);
    }
}
