<?php 
require "connect.php";

    class Baihat{
        function __construct($idbaihat, $tenbaihat, $hinhbaihat, $casi, $linkbaihat, $luotthich) {
            $this->Idbaihat = $idbaihat;
            $this->Tenbaihat = $tenbaihat;
            $this->Hinhbaihat = $hinhbaihat;
            $this->Casi = $casi;
            $this->Linkbaihat = $linkbaihat;
            $this->Luotthich = $luotthich;
        }
    }
    $query = "SELECT * FROM baihat ORDER BY Luotthich DESC LIMIT 5";
    $data = mysqli_query($con, $query);

    $mangbaihat = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangbaihat, new Baihat($row['idBaiHat']
                                                ,$row['tenBaiHat']
                                                ,$row['hinhBaiHat']
                                                ,$row['caSi']
                                                ,$row['linkBaiHat']
                                                ,$row['luotThich']));
    }


    echo json_encode($mangbaihat);
?>