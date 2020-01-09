<?php
header("Content-Type: text/html; charset=utf-8");  
$link = mysqli_connect(   
 'localhost',    /* The host to connect to 连接MySQL地址 */ 
 'root',         /* The user to connect as 连接MySQL用户名 */ 
 '',       /* The password to use 连接MySQL密码 */  
 'frimo');    /* The default database to query 连接数据库名称*/
 
 if (!$link){  
	 printf("Can't connect to MySQL Server. Errorcode: %s ", mysqli_connect_error());
	 exit; 
 }else{ 
     $UserName = $_GET["UserName"];
     $PassWord = $_GET["PassWord"];
   
    mysqli_query($link,'set names utf8');    //解决中文乱码的问题
	
	$sql="INSERT INTO `user`(`NickName`, `UserName`, `PhoneNumber`, `Birthday`, `Age`, `Gender`, `MaritalStatus`, `City`, `HomeTown`, `QQNumber`, `WeChatNumber`, `PassWord`, `HeadPicAddress`) 
	VALUES ('',$UserName,'','','','','','','','','',$PassWord,'')";
     if ($link->query($sql) === TRUE) {
          
			var_json('success','200',"注册成功");
     } else {
     var_json('error','201',"用户已存在");
}
    }
    mysqli_close($link);
	
	/**
* 比较标准的接口输出函数
* @param string  $info 消息
* @param integer $code 接口错误码，很关键的参数
* @param array   $data 附加数据
* $param string  $location 重定向
* @return array
*/
function var_json($info = '', $code = 200, $data ='') {
    $out['code'] = $code ?: 200;
    $out['info'] = $info ?: ($out['code'] ? 'error' : 'success');
    $out['data'] = $data ?: '注册成功';
    header('Content-Type: application/json; charset=utf-8');
    echo json_encode($out, JSON_HEX_TAG);
    exit(0);
}
?>