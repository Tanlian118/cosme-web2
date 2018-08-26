package com.cosme.adapter;

import com.cosme.common.BaseTransformer;
import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.service.NewsService;
import com.cosme.transformers.NewsTransformer;
import com.cosme.web.common.FixedPageSizeEnum;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.NewsDTO;
import com.cosme.web.queryParam.NewsQueryParam;
import com.cosme.web.param.NewsListParam;
import com.cosme.web.request.NewsAddRequest;
import com.cosme.web.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 13:24
 **/
@Service("newsAdapter")
public class NewsAdapter {

    @Autowired
    private NewsService newsService;


    public ResultDTO<Void> addOrUpdateNews(NewsAddRequest newsRequest) {
        ResultDTO<Void> resultDTO = checkParam(newsRequest);
        if (!resultDTO.isSuccess()) {
            return resultDTO;
        }
        NewsDTO newsDTO = BaseTransformer.convert(newsRequest, new NewsDTO());
        newsService.addOrUpdateNews(newsDTO);
        return ResultDTO.successfy();
    }

    private ResultDTO<Void> checkParam(NewsAddRequest newsRequest) {
        if (newsRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入相关信息");
        }
        if (!StringUtils.hasText(newsRequest.getNewsImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传新闻图片");
        }
        if (!StringUtils.hasText(newsRequest.getNewsTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入新闻标题");
        }
        if (newsRequest.getNewsTitle().length() > 30) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "标题需控制在30个字之间!");
        }
        if (!StringUtils.hasText(newsRequest.getContent())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入内容");
        }
        if (newsRequest.getContent().length() > 2000) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "内容字数需控制在2000个字内！");
        }
        if (!StringUtils.hasText(newsRequest.getContentSummary())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入内容简介");
        }
        if (newsRequest.getContentSummary().length() > 100) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "内容简介需控制在100个字内！");
        }
        if (newsRequest.getReleaseTime() == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择发布时间");
        }
        return ResultDTO.successfy();
    }

    public ResultDTO<Void> deleteNews(Set<Integer> newsIds) {
        if (CollectionUtils.isEmpty(newsIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选组要删除的信息");
        }
        newsService.deleteNews(newsIds);
        return ResultDTO.successfy();
    }

    public PageModel<NewsVO> listNews(NewsListParam listParam) {
        if (listParam == null) {
            return PageModel.emptyModel();
        }
        NewsQueryParam newsQueryParam = new NewsQueryParam();
        newsQueryParam.setNeedPagination(true);
        int pageSize = FixedPageSizeEnum.getByPageSize(listParam.getPageSize()).getPageSize();
        newsQueryParam.setPage(listParam.getPage()*pageSize);
        newsQueryParam.setPageSize(pageSize);
        newsQueryParam.setStartTime(listParam.getStartTime());
        newsQueryParam.setEndTime(listParam.getEndTime());
        PageModel<NewsDTO> pageModel = newsService.queryByParam(newsQueryParam);
        List<NewsDTO> newsDTOs = pageModel.getData();
        List<NewsVO> newsVOs = Lists2.transform(newsDTOs, NewsTransformer.DTO_TO_VO);
        int totalCount = pageModel.getTotalCount();
        return PageModel.build(newsVOs, totalCount, listParam.getPage(), pageSize);
    }
}
