package com.chenxiaoyang.controller;

import com.chenxiaoyang.config.FileUrlConfig;
import com.chenxiaoyang.entity.Advertisement;
import com.chenxiaoyang.service.IAdvertisementService;
import com.chenxiaoyang.utils.FileWriteLocalUtil;
import com.chenxiaoyang.utils.LogUtil;
import com.chenxiaoyang.utils.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 首页广告
 * @author chenxiaoyang
 */
@Controller
@RequestMapping("/advertisement")
@Scope(value = "prototype")
public class AdvertisementController {

    /** 首页广告 */
    @Autowired
    private IAdvertisementService advertisementService;


    /** 列表首页 */
    public void showList(HttpServletRequest request){
        List<Advertisement> advertisementList = advertisementService.queryAdvertisementList();
        request.setAttribute("advertisementList",advertisementList);
    }

    /** 列表首页 */
    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request){
        showList(request);
        return "homeAd";
    }

    /** 显示菜单 */
    @RequestMapping(value = "/showMenu")
    public String showMenu(HttpServletRequest request){
        showList(request);
        return "menu";
    }

    /** 显示页脚 */
    @RequestMapping(value = "/showFooter")
    public String showFooter(HttpServletRequest request){
        showList(request);
        return "footer";
    }

    /** 显示头部 */
    @RequestMapping(value = "/showHeader")
    public String showHeader(HttpServletRequest request){
        showList(request);
        return "header";
    }

    /** 删除首页广告 */
    @RequestMapping(value = "/deleteAdvertisement",method = RequestMethod.POST)
    public String delete(HttpServletRequest request){
        String id=StringUtil.stringNullHandle(request.getParameter("id"));
        int advertisementId = Integer.parseInt(id);
        boolean resultBoo=advertisementService.deleteAdvertisementById(advertisementId);
        if (resultBoo) {
            request.setAttribute("code", 1);
            request.setAttribute("message", "删除成功");
        }else {
            request.setAttribute("code", 2);
            request.setAttribute("message", "删除失败");
        }

        showList(request);
        return "homeAd";
    }

    /** 新增 */
    @RequestMapping(value = "/insertAdvertisement",method = RequestMethod.POST)
    public String insert(@Param("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String webUrl = StringUtil.stringNullHandle(request.getParameter("webUrl"));
        String wapUrl = StringUtil.stringNullHandle(request.getParameter("wapUrl"));
        String title = StringUtil.stringNullHandle(request.getParameter("title"));
        //上传到本地
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String datefile = sdf.format(new Date());

        String fileName = file.getOriginalFilename();     //获取上传文件的原名
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));      //获取文件后缀名
        String newfileName = datefile+fileSuffix;

        String filePath = FileUrlConfig.filePath;        //文件存储到本地的路径
        String sufacePlotAdress = filePath+newfileName;
        File getFile = new File(sufacePlotAdress);
        if (getFile.getParentFile().exists()){
            file.transferTo(getFile);     //把内存文件写到磁盘里
        }

        Advertisement advertisement = new Advertisement();
        advertisement.setSurfacePlotName(newfileName);
        advertisement.setTitle(title);
        advertisement.setWapUrl(wapUrl);
        advertisement.setWebUrl(webUrl);
        advertisement.setSurfacePlotAdress(newfileName);
        advertisement.setCreateTime(new Date());
        //boolan是基本类型而Boolean是boolean的封装类 会与数据库的bit类型数据不匹配,所以尽量不要用Boolean
        boolean addBoo =advertisementService.insertAdvertisement(advertisement);

        if (addBoo) {
            request.setAttribute("code", 1);
            request.setAttribute("message", "新增成功");
        }else {
            request.setAttribute("code", 2);
            request.setAttribute("message", "新增失败");
        }
        showList(request);
        return "homeAd";
    }

    /** 修改 */
    @RequestMapping(value = "/updateAdvertisement",method = RequestMethod.POST)
    public String update(@Param("file") MultipartFile file, HttpServletRequest request){
        String webUrl = StringUtil.stringNullHandle(request.getParameter("webUrl"));
        String wapUrl = StringUtil.stringNullHandle(request.getParameter("wapUrl"));
        String title = StringUtil.stringNullHandle(request.getParameter("title"));
        String id = StringUtil.stringNullHandle(request.getParameter("id"));

        Advertisement advertisement = new Advertisement();
        try {
            //如果不是一个空的文件
            if (file.getSize()!=0) {
                //上传到本地
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String datefile = sdf.format(new Date());
                String fileName = file.getOriginalFilename();     //获取上传文件的原名
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."));      //获取文件后缀名
                String newfileName = datefile + fileSuffix;
                //这里可以配置前缀
                String filePath = FileUrlConfig.filePath;       //文件存储到本地的路径
                String sufacePlotAdress = filePath + newfileName;
                File getFile = new File(sufacePlotAdress);
                if (getFile.getParentFile().exists()) {
                    file.transferTo(getFile);     //把内存文件写到磁盘里
                }
                advertisement.setSurfacePlotAdress(sufacePlotAdress);
                advertisement.setSurfacePlotName(newfileName);
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.printInfoLog("上传文件失败");
        }
        int advertisementId = Integer.valueOf(id);
        advertisement.setTitle(title);
        advertisement.setWapUrl(wapUrl);
        advertisement.setWebUrl(webUrl);
        advertisement.setId(advertisementId);
        boolean updateBoo = advertisementService.updateAdvertisement(advertisement);
        showList(request);

        if (updateBoo) {
            request.setAttribute("code", 1);
            request.setAttribute("message", "修改成功");
        }else {
            request.setAttribute("code", 2);
            request.setAttribute("message", "修改失败");
        }
        return "homeAd";
    }

    /** 上移*/
    @RequestMapping(value = "/shiftUp")
    public String shiftUp(HttpServletRequest request){
        int advertisementId = Integer.valueOf(StringUtil.stringNullHandle(request.getParameter("id")));
        //上移
        boolean moveBoo = advertisementService.moveUpAdvertisementList(advertisementId);
        if (moveBoo) {
            request.setAttribute("code", 1);
            request.setAttribute("message", "上移成功");
        } else {
            request.setAttribute("code", 2);
            request.setAttribute("message", "上移失败");
        }

        showList(request);
        return "homeAd";
    }

    /** 下移*/
    @RequestMapping(value = "/shiftDown")
    public String shiftDown(HttpServletRequest request){
        int advertisementId = Integer.valueOf(StringUtil.stringNullHandle(request.getParameter("id")));
        //下移
        boolean moveDown = advertisementService.moveDownAdvertisementList(advertisementId);
        if (moveDown) {
            request.setAttribute("code", 1);
            request.setAttribute("message", "下移成功");
        } else {
            request.setAttribute("code", 2);
            request.setAttribute("message", "下移失败");
        }
        showList(request);
        return "homeAd";
    }



}
