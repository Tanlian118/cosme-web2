package com.cosme.service;

import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.dto.ChannelDTO;
import com.cosme.web.queryParam.ChannelQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 14:55
 **/

public interface ChannelService {

    /**
     * 添加或修改频道信息
     * @param channelDTO
     * @return
     */
    ResultDTO<Void> addOrUpdateChannel(ChannelDTO channelDTO);

    /**
     * 删除频道信息
     * @param channelIds
     * @return
     */
    ResultDTO<Void> deleteChannel(Set<Integer> channelIds);

    /**
     * 查询频道信息
     * @param channelQueryParam
     * @return
     */
    PageModel<ChannelDTO> queryByParam(ChannelQueryParam channelQueryParam);
}
