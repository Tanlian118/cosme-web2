package com.cosme.controller;

import com.cosme.adapter.NewsAdapter;
import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.param.NewsListParam;
import com.cosme.web.request.NewsAddRequest;
import com.cosme.web.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 13:21
 **/
@RestController
@RequestMapping("/cm/news")
public class newsController {

    @Autowired
    private NewsAdapter newsAdapter;

    /**
     * 添加或修改新闻信息
     * @param newsRequest
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> save(@RequestBody NewsAddRequest newsRequest) {
        return newsAdapter.addOrUpdateNews(newsRequest);
    }

    /**
     * 删除新闻信息
     * @param newsIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> newsIds) {
        return newsAdapter.deleteNews(newsIds);
    }

    /**
     * 查询新闻列表
     * @param newsListParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<NewsVO> list(NewsListParam newsListParam) {
        return newsAdapter.listNews(newsListParam);
    }



}
