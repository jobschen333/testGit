<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta charset="UTF-8">
<div class="header">
    <p class="h_title">
        <img src="<%=path %>/resources/images/logo_yaw.png" class="logo" />
        <img src="<%=path %>/resources/images/logo_aks.png" class="logo" />
        <span class="sljz">盛临九洲爱客盛后台</span>
    </p>
    <p class="h_side">
        <span class="user">您好，张三</span>
        <a href="#" class="out"><img src="<%=path %>/resources/images/out.png" />退出登录</a>
    </p>
</div>