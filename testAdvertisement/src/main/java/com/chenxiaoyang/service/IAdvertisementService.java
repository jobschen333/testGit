package com.chenxiaoyang.service;

import com.chenxiaoyang.entity.Advertisement;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页广告
 * @author chxy
 */

@Service("advertisementService")
public interface IAdvertisementService {

    /**
     * 查询首页广告列表信息
     * @return 操作成功:返回首页广告列表信息,失败: 返回null
     */
    List<Advertisement> queryAdvertisementList();

    /**
     * 通过id删除首页广告
     * @param id
     * @return 操作成功:true 操作失败:false
     */
    boolean deleteAdvertisementById(int id);

    /**
     * 新增首页广告
     * @param advertisement 广告
     * @return 操作成功:true 操作失败:false
     */
    boolean insertAdvertisement(Advertisement advertisement);

    /**
     * 修改首页广告
     * @param advertisement 广告
     * @return 返回true，失败：返回false
     */
    boolean updateAdvertisement(Advertisement advertisement);

    /**
     * 上下移首页广告
     * @param advertisement  移动
     * @param advertisementMoveBy 被移动
     * @return 操作成功:true 操作失败:false
     */
    boolean moveAdvertisementList(Advertisement advertisement, Advertisement advertisementMoveBy);

    /**
     * 上移
     * @param advertisementId 上移的Id
     * @return 操作成功:true 操作失败:false
     */
    boolean moveUpAdvertisementList(int advertisementId);

    /**
     * 下移
     * @param advertisementId 下移的Id
     * @return 操作成功:true 操作失败:false
     */
    boolean moveDownAdvertisementList(int advertisementId);
}
