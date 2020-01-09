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
	
	 $tj1 = " UserName = '{$UserName}' ";
	 $tj2 = " PassWord = '{$PassWord}' ";
	
	$sql = "select * from user where {$tj1} and {$tj2}";  
	
  
	//假设这是数据源，如MySQL
    $data = array();
	if ($result = mysqli_query($link, $sql)) 
    {  
         /* Fetch the results of the query 返回查询的结果 */ 
	    while( $row = mysqli_fetch_assoc($result) ){     	   
		   $data[$UserName] = array(
		   'NickName'=>$row['NickName'],
		   'UserName'=>$row['UserName'],
		   'PhoneNumber'=>$row['PhoneNumber'],
		   'Birthday'=>$row['Birthday'],
		   'Age'=>$row['Age'],
		   'Gender'=>$row['Gender'],
		   'MaritalStatus'=>$row['MaritalStatus'],
		   'City'=>$row['City'],
		   'HomeTown'=>$row['HomeTown'],
		   'QQNumber'=>$row['QQNumber'],
		   'WeChatNumber'=>$row['WeChatNumber'],
		   'HeadPicAddress'=>$row['HeadPicAddress'],
		   'PhoneNumber'=>$row['PhoneNumber'],);
	        var_json('success','200',$data[$UserName] );
	    }   
         /* Destroy the result set and free the memory used for it 结束查询释放内存 */
        mysqli_free_result($result); 
 
    }else{
	    echo '用户不存在';
    }
    mysqli_close($link);
}

/**
* 比较标准的接口输出函数
* @param string  $info 消息
* @param integer $code 接口错误码，很关键的参数
* @param array   $data 附加数据
* $param string  $location 重定向
* @return array
*/
function var_json($info = '', $code = 200, $data = array()) {
    $out['code'] = $code ?: 200;
    $out['info'] = $info ?: ($out['code'] ? 'error' : 'success');
    $out['data'] = $data ?: array();
    header('Content-Type: application/json; charset=utf-8');
    echo json_encode($out, JSON_HEX_TAG);
    exit(0);
}
?>