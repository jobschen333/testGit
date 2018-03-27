package com.chenxiaoyang.dao;

import com.chenxiaoyang.entity.Advertisement;

import java.util.List;

/**
 * 首页广告
 * @author chenxy
 */
public interface IAdvertisementDao {

    /**
     * 查询首页广告列表
     * @return 操作成功: 返回首页广告列表 , 操作失败: 返回null
     */
    List<Advertisement> queryAdvertisementList() ;

    /**
     * 通过id删除首页广告
      *@param id
     * @return 操作成功:true 操作失败:false
     */
    boolean deleteAdvertisementById(int id);

    /**
     * 新增首页广告
     * @param advertisement
     * @return 操作成功:true 操作失败:false
     */
    boolean insertAdvertisement(Advertisement advertisement);

    /**
     * 修改首页广告
     * @param advertisement
     * @return 返回true，失败：返回false
     */
    boolean updateAdvertisement(Advertisement advertisement);
}
