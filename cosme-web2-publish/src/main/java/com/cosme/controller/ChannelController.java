package com.cosme.controller;

import com.cosme.adapter.ChannelAdapter;
import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.param.ChannelListParam;
import com.cosme.web.request.ChannelAddRequest;
import com.cosme.web.vo.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 14:40
 **/
@RestController
@RequestMapping("/cm/channel")
public class ChannelController extends BaseController{

    @Autowired
    private ChannelAdapter channelAdapter;

    /**
     * 添加或修改频道信息
     * @param channelRequest
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> save(@RequestBody ChannelAddRequest channelRequest){
        return channelAdapter.addOrUpdate(channelRequest);
    }

    /**
     * 删除频道信息
     * @param channelIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> channelIds) {
       return channelAdapter.delete(channelIds);
    }

    /**
     * 查询频道列表
     * @param channellListParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<ChannelVO> list(ChannelListParam channellListParam, HttpServletRequest request, HttpServletResponse response) {
        String onlineUserId = getOnlineUserId(request, response);
        channellListParam.setOperaterId(onlineUserId);
        return channelAdapter.list(channellListParam);
    }
}
