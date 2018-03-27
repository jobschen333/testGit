package com.chenxiaoyang.dao.impl;

import com.chenxiaoyang.dao.IAdvertisementDao;
import com.chenxiaoyang.entity.Advertisement;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 首页广告
 * @author chxy
 */
public class AdvertisementDaoImpl implements IAdvertisementDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询首页广告
     * @return 操作成功:返回首页广告信息, 失败: 返回null
     */
    @Override
    public List<Advertisement> queryAdvertisementList() {
        List<Advertisement> list = null;
        try{
            list = sqlSessionTemplate.selectList("queryAdvertisementList");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 删除首页广告
     * @param id 首页广告Id
     * @return 操作成功：返回true，失败：返回false
     */
    @Override
    public boolean deleteAdvertisementById(int id) {
        int resultNumber = 0;

        try{
            resultNumber = sqlSessionTemplate.delete("deleteAdvertisementById", id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (resultNumber > 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 新增首页广告
     * @param advertisement 首页广告
     * @return 操作成功：返回true，失败：返回false
     */
    @Override
    public boolean insertAdvertisement(Advertisement advertisement) {
        int resultNumber = 0;
        try{
            resultNumber = sqlSessionTemplate.insert("insertAdvertisement", advertisement);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(resultNumber > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 修改首页广告
     * @param advertisement 首页广告
     * @return 操作成功：返回true，失败：返回false
     */
    @Override
    public boolean updateAdvertisement(Advertisement advertisement) {
        int resultNumber = 0;

        try{
            resultNumber = sqlSessionTemplate.update("updateAdvertisement", advertisement);
        }catch(Exception e){
            e.printStackTrace();
        }
        if (resultNumber > 0){
            return true;
        }else {
            return false;
        }
    }
}
