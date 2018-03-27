<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="<%=path %>/resources/images/icon.ico" type="image/x-ico" />

    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/homeAd.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/public.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/resources/css/simpleTips.css" />

    <title>首页广告</title>
</head>
<body>
<div id="header"></div>


<div class="content">
    <div id="menu"></div>

    <div class="contentRight">
        <div class="caption">
            <span class="title">首页广告</span>
            <p class="add">新增广告</p>
        </div>

        <table class="table" cellspacing="0" cellpadding="0">
            <tr class="tableTitle">
                <td class="location">位置</td>
                <td class="time">添加时间</td>
                <td class="adTitle">标题</td>
                <td class="pic">封面图111</td>
                <td class="link">web链接</td>
                <td class="link">wap链接</td>
                <td class="operate">操作</td>
            </tr>
            <c:forEach items="${advertisementList}" var="advertisement" varStatus="status">
                <tr class="tableInfo">
                    <td class="location">${ status.index+1}</td>
                    <td class="time"><fmt:formatDate value="${advertisement.createTime}" /></td>
                    <td class="adTitle">${advertisement.title}</td>
                    <td class="pic"><img src="<%=basePath %>resources/images/${advertisement.surfacePlotAdress}" /></td>
                    <td class="link"><a href="${advertisement.webUrl}">${advertisement.webUrl}</a></td>
                    <td class="link"><a href="${advertisement.wapUrl}">${advertisement.wapUrl}</a></td>
                    <td class="operate">
                        <c:if test="${advertisementList.size()!=1}">
                            <c:choose>
                                <c:when test="${status.last}">    <!--如果 -->
                                    <input type="text" value="上&nbsp;移" class="adUp" onclick="shiftUp('${advertisement.id}')" onfocus="this.blur()" />
                                </c:when>
                                <c:when test="${status.first}">    <!--如果 -->
                                    <input type="text" value="下&nbsp;移" class="adDown" onclick="shiftDown('${advertisement.id}')" onfocus="this.blur()" />
                                </c:when>
                                <c:otherwise>  <!--否则 -->
                                    <input type="text" value="上&nbsp;移" class="adUp" onclick="shiftUp('${advertisement.id}')" onfocus="this.blur()" />
                                    <input type="text" value="下&nbsp;移" class="adDown" onclick="shiftDown('${advertisement.id}')" onfocus="this.blur()" />

                                </c:otherwise>
                            </c:choose>
                        </c:if>


                        <input type="text" value="修&nbsp;改" class="change" onfocus="this.blur()" onclick="showUpdatePage('${advertisement.id}');" />
                        <input type="text" value="删&nbsp;除" class="delete" onfocus="this.blur()" onclick="deleteAdvertisement('${advertisement.id}');"/>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>


<div id="footer"></div>


<div class="mask">
    <div class="mask_content">

        <form id="deleteForm" action="<%=path %>/advertisement/deleteAdvertisement.htm" method="post">
            <div class="delete_pop">
                <p class="popTitle">删除操作</p>
                <p class="popTips"><img src="<%=path %>/resources/images/tips.png" class="tipsImg" />确定删除？</p>

                <input type="hidden" id="id" name="id" >

                <div class="buttons">
                    <input type="text" value="取&nbsp;消" class="cancel" onfocus="this.blur()" />
                    <input type="text" value="确&nbsp;定" class="yes" onfocus="this.blur()" onclick="delect()" />
                </div>
            </div>
        </form>

        <form id="shiftUpForm" action="<%=path %>/advertisement/shiftUp.htm" method="post">
            <div class="delete_pop">
                <input type="hidden" id="shiftUpId" name="id" >
            </div>
        </form>

        <form id="shiftDownForm" action="<%=path %>/advertisement/shiftDown.htm" method="post">
            <div class="delete_pop">
                <input type="hidden" id="shiftDownId" name="id" >
            </div>
        </form>

        <!-- enctype="multipart/form-data"，否则springmvc就会解析失败-->
        <form id="insertForm" action="<%=path %>/advertisement/insertAdvertisement.htm" enctype="multipart/form-data" method="post">
            <div class="add_pop">
                <p class="popTitle">新增广告</p>
                <p class="popInput">
                    <label class="popName">广告图片<span class="star">*</span></label>

                    <span class="popPic">
                        <input type="text" name="sufacePlotAdress"  id="changead_t1" class="choosePic" placeholder="请选择文件" onfocus="this.blur()" />
                        <input type="text"  onclick="document.getElementById('changead_a1').click();"  value="选择文件" class="choose_button" onfocus="this.blur();" />
                        <input type="file" name="file" class="file" id="changead_a1" onchange="document.getElementById('changead_t1').value = this.value;" />
                    </span>
                </p>
                <p class="popInput">
                    <label class="popName">标题名称<span class="star">*</span></label>
                    <input type="text" class="entry" name="title" id="title" placeholder="广告标题名称，2~16个字符" />
                </p>
                <p class="popInput">
                    <label class="popName">web链接地址<span class="star"></span></label>
                    <input type="text" class="entry" name="webUrl" id="webUrl" placeholder="链接地址，非必填" />
                </p>
                <p class="popInput">
                    <label class="popName">wap链接地址<span class="star"></span></label>
                    <input type="text" class="entry" name="wapUrl" id="wapUrl" placeholder="链接地址，非必填" />
                </p>

                <div class="buttons">
                    <input type="text" value="取&nbsp;消" class="cancel" onfocus="this.blur()" />
                    <input type="text" value="确&nbsp;定" class="yes" onfocus="this.blur()" onclick="insert()" />
                </div>
            </div>
        </form>
        <form id="updateForm" action="<%=path %>/advertisement/updateAdvertisement.htm" enctype="multipart/form-data" method="post">
            <div class="change_pop">
                <p class="popTitle">修改广告</p>
                <p class="popInput">
                    <label class="popName">广告图片</label>

                    <input type="hidden" id="updateId" name="id" >
                    <span class="popPic">
                        <input type="text" id="changead_t2"  class="choosePic" placeholder="该项不修改时，请勿上传" onfocus="this.blur()" />
                        <input type="text"  onclick="document.getElementById('changead_a2').click();"  value="选择文件" class="choose_button" onfocus="this.blur();" />
                        <input type="file" class="file" name="file" id="changead_a2" onchange="document.getElementById('changead_t2').value = this.value;" />
                    </span>
                </p>
                <p class="popInput">
                    <label class="popName">标题名称<span class="star">*</span></label>
                    <input type="text" name="title" id="updateTitle" class="entry" placeholder="广告标题名称，2~16个字符" />
                </p>
                <p class="popInput">
                    <label class="popName">web链接地址<span class="star"></span></label>
                    <input type="text" name="webUrl" id="updateWebUrl" class="entry" placeholder="链接地址，非必填" />
                </p>
                <p class="popInput">
                    <label class="popName">wap链接地址<span class="star"></span></label>
                    <input type="text" name="wapUrl" id="updateWapUrl" class="entry" placeholder="链接地址，非必填" />
                </p>

                <div class="buttons">
                    <input type="text" value="取&nbsp;消" class="cancel" onfocus="this.blur()" />
                    <input type="text" value="确&nbsp;定" class="yes" onfocus="this.blur()" onclick="update()" />
                </div>
            </div>
        </form>

    </div>
</div>

<script type="text/javascript" src="<%=path %>/resources/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/public.js"></script>
<script type="text/javascript" src="<%=path %>/resources/simpleTips.js"></script>

<script type="text/javascript">
    var popObj;
    $(function(){
        $(".delete").click(function(){
            $(".mask").fadeIn();
            $(".delete_pop").fadeIn();
            popObj = ".delete_pop"
        });
        $(".add").click(function(){
            $(".mask").fadeIn();
            $(".add_pop").fadeIn();
            popObj = ".add_pop"
        });
        $(".change").click(function(){
            $(".mask").fadeIn();
            $(".change_pop").fadeIn();
            popObj = ".change_pop"
        });
        $(".cancel").click(function(){
            $(".mask").fadeOut("fast");
            $(popObj).fadeOut("fast");
        });
        $(".yes").click(function(){
            $(".mask").fadeOut("fast");
            $(popObj).fadeOut("fast");
        });
    });

    //赋值操作
    function showUpdatePage(id){
        document.getElementById("updateId").value = id;
    }
    function deleteAdvertisement(id){
        document.getElementById("id").value = id;
    }

    /** 删除一条首页广告记录*/
    function delect(){
        $("#deleteForm").submit();

    }

    /** 新增一条首页广告记录*/
    function insert(){
        var title = $("#title").val();
        if (title == "" || title == "null"){
            alert("请输入广告标题!")
            return;
        }
        //验证title字符长度
        if(title.length<2|| title.length>16){
            alert("请输入字符串长度2-16的字符!");
            return;
        }
        $("#insertForm").submit();
    }

    /** 修改一条首页广告 */
    function update(){
        var title = $("#updateTitle").val();
        //验证title不为空
        if (title == "" || title == "null"){
            alert("请输入广告标题!")
            return;
        }
        //验证title字符长度
        if (title.length<2 || title.length>16){
            alert("请输入字符串长度2-16的字符!");
            return;
        }
        $("#updateForm").submit();

    }
    /** 上移*/
    function shiftUp(id){
        document.getElementById("shiftUpId").value = id;
        $("#shiftUpForm").submit();
    }
    /** 下移*/
    function shiftDown(id){
        document.getElementById("shiftDownId").value = id;
        $("#shiftDownForm").submit();
    }

</script>

</body>
</html>