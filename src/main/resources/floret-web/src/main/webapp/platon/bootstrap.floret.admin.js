/*--------------------------------------------------------------------------
 *  Copyright (c) 2010-2020, dennisit@163.com All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the suruonian developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 *  <dependency>
 *      <groupId>com.github.dennisit</groupId>
 *      <artifactId>floret</artifactId>
 *      <version>1.0.0</version>
 *  </dependency>
 *
 *  The code you see is generated by the generator "floret".
 *  // 生成路径
 *  String destPath = "/gen/";
 *  // 代码生成
 *  new FloretStrap().scanAnnoBorn(Settings.LOCATION_PATTERN, Settings.ACCEPT_SUFFIX, "com.dennisit", destPath, "UTF-8");
 *--------------------------------------------------------------------------
 */
var application = angular.module('application', ['ngRoute', 'ngSanitize', 'ui.router', 'tinyKit']);

application.config(function ($stateProvider, $urlRouterProvider) {

    <%
    for(bean in beans){
        if(beanLP.index==1) {
    %>
    // 默认路由策略
    $urlRouterProvider.otherwise('${nameUtil.firstToLower(bean.className)}/list');
    <%
        }
    }
    %>

    // 频道路由策略
    $stateProvider
    <%
    for(bean in beans){
    %>
            .state('${nameUtil.firstToLower(bean.className)}List', {
                url: '/${nameUtil.firstToLower(bean.className)}/list',
                views: {
                    'mainView': {
                        templateUrl: '/theme/drow/${bean.tableName}_list.html'
                    }
                }
            })
            .state('${nameUtil.firstToLower(bean.className)}Update', {
                url: '/${nameUtil.firstToLower(bean.className)}/detail/:id',
                views: {
                    'mainView': {
                        templateUrl: '/theme/drow/${bean.tableName}_edit.html'
                    }
                }
            })
            .state('${nameUtil.firstToLower(bean.className)}Add', {
                url: '/${nameUtil.firstToLower(bean.className)}/add',
                views: {
                    'mainView': {
                        templateUrl: '/theme/drow/${bean.tableName}_edit.html'
                    }
                }
            })
        <%
    }
    %>
    ;
});

/**
 * 初始化站点数据
 */
application.controller('settingInit', function ($rootScope, $scope, $http, $state, $stateParams) {
    console.log("初始化系统参数");
    $http.get("/settings.json").success(function (result, header, config, status) {
        console.log(result);
        $scope.title = result.title;
    });
});

<%
for(bean in beans){
%>
/**
 * 列表
 */
application.controller('${nameUtil.firstToLower(bean.className)}ListZone', function ($rootScope, $scope, $http, $state, $stateParams, $location, $compile, $httpAjax) {

    if(!$stateParams.page || $stateParams.page<=0){
        $stateParams.page = 1;
    }
    if(!$stateParams.size || $stateParams.size<=0){
        $stateParams.size = 20;
    }

    // 初始化查询参数
    $scope.pageNum = $stateParams.page;
    $scope.pageSize = $stateParams.size;
    $scope.pageTotal = 1;
    $scope.recordTotal = 1;
    $scope.search = {};

    console.log("查询参数: pageNum:" + $scope.pageNum + ",pageSize:" + $scope.pageSize + ",search:" + $scope.search);

    // 分页查询公用函数
    $scope.pageFunction = function(pageNum, pageSize){
        console.log("(page)分页参数: pageNum:" + pageNum + ", pageSize:" + pageSize);
        $httpAjax.get($http, "/${nameUtil.firstToLower(bean.className)}/list.json?page=" + pageNum + "&size=" + pageSize,
            $.param($scope.search),
            function (result) {
                console.log(result);
                $scope.items = result.items;
                $scope.pageTotal = result.pageTotal;
                $scope.pageNum = result.pageNum;
                $scope.pageSize = result.pageSize;
                $scope.recordTotal = result.recordTotal;
                // compile 服务端指令
                var pageHtml = $compile(result.pageHtml)($scope);
                angular.element('#pageHtml').html(pageHtml);

                // 重新渲染浏览器地址 根据需要开启
                // $location.path('/${nameUtil.firstToLower(bean.className)}List/list/' + $scope.pageSize + "/" + $scope.pageNum);

            }, function(result){
                console.log("请求出错,请稍后重试");
            });
    };

    // 这里对搜索控件单拎出来,一是模块清晰,二是页面处理上规避了ng-click='pageFunction({{pageNum}}, {{pageSize}})'加载时报错的问题
    $scope.searchFunction = function(){
        console.log("(search)分页参数: pageNum:" + $scope.pageNum + ", pageSize:" + $scope.pageSize);
        return $scope.pageFunction($scope.pageNum, $scope.pageSize);
    }

    // 页面加载时调用查询函数
    $scope.pageFunction($scope.pageNum, $scope.pageSize);

});

/**
 * 修改/添加
 */
application.controller('${nameUtil.firstToLower(bean.className)}DataZone', function ($rootScope, $scope, $http, $state, $stateParams, $httpAjax) {

    $scope.optUpdate = false;
    $scope.optInsert = false;

    // 如果id存在
    if($stateParams.id && $stateParams.id>0){
        console.log("修改");
        // 界面按钮控件打标
        $scope.optUpdate = true;

        // 根据Id获取对象
        $httpAjax.post($http, "/${nameUtil.firstToLower(bean.className)}/select.json?id=" + $stateParams.id, null,
                function (result) {
                    console.log(result);
                    $scope.item = result;
                },function(result){
                        console.log("请求出错,请稍后重试");
                });

        // 更新对象
        $scope.updateFunction = function() {
            // console.log($scope.item); Vs console.log($.param($scope.item));
            $httpAjax.post($http, "/${nameUtil.firstToLower(bean.className)}/update.json", $scope.item,
                function (result) {
                    console.log(result);
                    if (result > 0) {
                        alert("修改成功");
                        // 刷新页面
                        //$scope.loadData();
                    }
                }, function(result){
                    console.log("请求出错,请稍后重试");
                });
        }

    }else{
        console.log("添加");
        // 界面按钮控件打标
        $scope.optInsert = true;

        // 添加对象
        $scope.insertFunction = function(){
            console.log("添加");
            console.log($scope.item);
            $httpAjax.post($http, "/${nameUtil.firstToLower(bean.className)}/insert.json", $scope.item,
                function (result) {
                    console.log(result);
                    if (result > 0) {
                        alert("添加成功");
                    }
                    $scope.item={};
                },function(){
                    alert("添加失败,请稍后重试!");
                });
        }
    }

    // 返回按钮
    $scope.returnFunction = function(){
        // 返回上一页 $state.go($rootScope.previousState_name,$rootScope.previousState_params);
        history.back();
    }

});
<%
}
%>



angular.bootstrap(document, ['application']);