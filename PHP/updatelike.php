<?php
    require "connect.php";

    $luotthich = $_POST['luotThich'];
    $idbaihat = $_POST['idBaiHat'];

    $query = "SELECT luotThich FROM baihat WHERE idBaiHat = '$luotthich'";

    $dataluothich = mysqli_query($con, $query);
    $row = mysqli_fetch_assoc($dataluothich);
    $tongluotthich = $row['luotThich'];

    if (isset($luotthich)){
        $tongluotthich = $tongluotthich + $luotthich;
        $querysum = "UPDATE baihat SET luotThich = '$tongluotthich' WHERE idBaiHat= '$idbaihat'";
        $dataupdate = mysqli_query($con, $querysum);
        if ($dataupdate){
            echo "Success";
        }else{
            echo "Loi";
        }
    }
?>