<?php

if($_SERVER["REQUEST_METHOD"]=="POST")
{
   require'conn.php';
   showStudent();

}

function showStudent()
{
global $connect;
$query="Select * from userdetails;";
$result=mysqli_query($connect,$query);
$numofrows=mysqli_num_rows($result);
$temp_array[]=array();

if($numofrows>0)
{
while($row=mysqli_fetch_assoc($result))
{
$temp_array[]=$row;
}
}

header('Content-Type:application/json');
echo json_encode(array("userdetails"=>$temp_array));
mysqli_close($connect);
}
