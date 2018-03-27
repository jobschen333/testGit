package com.chenxiaoyang.service.impl;

import com.chenxiaoyang.dao.IAdvertisementDao;
import com.chenxiaoyang.entity.Advertisement;
import com.chenxiaoyang.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * 首页广告
 * @author chxy
 */
@Service
public class AdvertisementServiceImpl implements IAdvertisementService{

    /** 首页广告*/
    @Autowired
    private IAdvertisementDao advertisementDao;

    /**
     * 查找首页广告
     * @return 操作成功:返回首页广告列表信息,失败: 返回null
     */
    @Override
    public List<Advertisement> queryAdvertisementList() {
        List list = advertisementDao.queryAdvertisementList();
        return list;
    }

    /**
     * 通过id删除首页广告
     * @param id 广告id
     * @return 操作成功:true 操作失败:false
     */
    @Override
    public boolean deleteAdvertisementById(int id) {
        return  advertisementDao.deleteAdvertisementById(id);
    }

    /**
     * 新增首页广告
     * @param advertisement 广告
     * @return 操作成功:true 操作失败:false
     */
    @Override
    public boolean insertAdvertisement(Advertisement advertisement) {
        return advertisementDao.insertAdvertisement(advertisement);
    }

    /**
     * 修改首页广告
     * @param advertisement 广告
     * @return 返回true，失败：返回false
     */
    @Override
    public boolean updateAdvertisement(Advertisement advertisement) {
        return  advertisementDao.updateAdvertisement(advertisement);
    }

    /**
     * 上下移首页广告
     * @param advertisement  移动
     * @param advertisementMoveBy 被移动
     * @return 操作成功:true 操作失败:false
     */
    @Transactional
    @Override
    public boolean moveAdvertisementList(Advertisement advertisement, Advertisement advertisementMoveBy) {
        //业务执行状态
        boolean executeSuccess = true;
        //这里逻辑
        boolean updateBoo=advertisementDao.updateAdvertisement(advertisement);
        if (updateBoo){
            boolean updateBooBy = advertisementDao.updateAdvertisement(advertisementMoveBy);
            if (!updateBooBy){
                executeSuccess = false;
            }
        }

        //数据回滚
        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return executeSuccess;
    }

    /**
     * 上移
     * @param advertisementId 上移的Id
     * @return 操作成功:true 操作失败:false
     */
    @Override
    public boolean moveUpAdvertisementList(int advertisementId) {
        List<Advertisement> advertisementList=advertisementDao.queryAdvertisementList();

        boolean executeSuccess = true;
        //只有大于2两数据以上时才会有上下移操作
        if (advertisementList.size()>1){
            for (int i=0;i<advertisementList.size();i++){
                if (advertisementList.get(i).getId()==advertisementId&&advertisementList.get(i-1)!=null){
                    //定义属性
                    Advertisement advertisement ;
                    Advertisement advertisementMoveBy ;
                    advertisement = advertisementList.get(i);
                    advertisementMoveBy = advertisementList.get(i-1);
                    //交换属性
                    int id = advertisement.getId();
                    int idMoveBy = advertisementMoveBy.getId();
                    advertisement.setId(idMoveBy);
                    advertisementMoveBy.setId(id);
                    boolean updateBoo = advertisementDao.updateAdvertisement(advertisement);
                    if (updateBoo) {
                        boolean updateBooBy = advertisementDao.updateAdvertisement(advertisementMoveBy);
                        if (!updateBooBy){
                            executeSuccess = false;
                        }
                    }
                    break;
                }
            }
        }

        //数据回滚
        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return executeSuccess;
    }

    /**
     * 下移
     * @param advertisementId 下移的Id
     * @return 操作成功:true 操作失败:false
     */
    @Override
    public boolean moveDownAdvertisementList(int advertisementId) {
        List<Advertisement> advertisementList=advertisementDao.queryAdvertisementList();

        boolean executeSuccess = true;
        //只有大于2两数据以上时才会有上下移操作
        if (advertisementList.size()>1){
            for (int i=0;i<advertisementList.size();i++){
                if (advertisementList.get(i).getId()==advertisementId&&advertisementList.get(i+1)!=null) {
                    //定义属性
                    Advertisement advertisement ;
                    Advertisement advertisementMoveBy ;
                    advertisement = advertisementList.get(i);
                    advertisementMoveBy = advertisementList.get(i+1);
                    //交换属性
                    int id = advertisement.getId();
                    int idMoveBy = advertisementMoveBy.getId();
                    advertisement.setId(idMoveBy);
                    advertisementMoveBy.setId(id);
                    boolean updateBoo = advertisementDao.updateAdvertisement(advertisement);
                    if (updateBoo){
                        boolean updateBooBy = advertisementDao.updateAdvertisement(advertisementMoveBy);
                        if (!updateBooBy){
                            executeSuccess = false;
                        }
                    }
                    break;
                }
            }
        }
        //数据回滚
        if (!executeSuccess){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return executeSuccess;
    }


}
