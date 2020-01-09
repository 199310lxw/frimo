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
	
    $sql="UPDATE `user` SET `NickName`=[value-1],`UserName`=[value-2],`PhoneNumber`=[value-3],`Birthday`=[value-4],`Age`=[value-5],`Gender`=[value-6],`MaritalStatus`=[value-7],`City`=[value-8],
	`HomeTown`=[value-9],`QQNumber`=[value-10],`WeChatNumber`=[value-11],`PassWord`=[value-12],`HeadPicAddress`=[value-13] WHERE UserName ="

   if ($link->query($sql) === TRUE) {
            echo "刷新成功";
     } else {
    echo "Error: " . $sql . "<br>" . $link->error;
}
    }
    mysqli_close($link);
?>