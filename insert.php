<?php

if($_SERVER["REQUEST_METHOD"]=="POST")
{
   require'conn.php';
   createStudent();

}

function createStudent()
{
global $connect;
$username=$_POST["username"];
$password=$_POST["password"];
$mailid=$_POST["mailid"];
$phoneno=$_POSTs["phoneno"];

$query="INSERT INTO userdetails(username,password,mailid,phoneno) VALUES('$username','$password','$mailid','$phoneno');";
mysqli_query($connect,$query);
mysqli_close($connect);
}

function showStudent()
{
global $connect;
$result=mysqli_query($connect,$query);

$temp_array[]=$row;
if(mysqli_num_rows($result)>0)
{
header('Content-Type:application/Json');
echo json_encode(array("students"=>$temp_array));
mysqli_close($connect);
}
}
?>
