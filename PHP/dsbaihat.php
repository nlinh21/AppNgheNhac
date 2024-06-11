<?php 
     require "connect.php";
            
class Baihat {
    function __construct($idbaihat ,$tenbaihat , $hinhbaihat, $casi, $linkbaihat, $luotthich) {
        $this->Idbaihat = $idbaihat;
        $this->Tenbaihat = $tenbaihat;
        $this->Hinhbaihat = $hinhbaihat;
        $this->Casi = $casi;
        $this->Linkbaihat = $linkbaihat;
        $this->Luotthich = $luotthich;

    }
}

$arraydanhsachbaihat = array();
$idquangcao = "1";


    if (strlen($idquangcao) > 0) {
        $queryquangcao = "SELECT * FROM quangcao WHERE Id = '$idquangcao'";
        $dataquangcao = mysqli_query($con, $queryquangcao);
        $rowquangcao = mysqli_fetch_assoc($dataquangcao);
        $id = $rowquangcao['idBaiHat'];
        $query = "SELECT * FROM baihat WHERE Idbaihat = '$id'";
    }

    $data = mysqli_query($con, $query);
    while ($row = mysqli_fetch_assoc($data)){
        array_push($arraydanhsachbaihat, new Baihat($row['idBaiHat']
                                                ,$row['tenBaiHat']
                                                ,$row['hinhBaiHat']
                                                ,$row['caSi']
                                                ,$row['linkBaiHat']
                                                ,$row['luotThich']));
    }


    echo json_encode($arraydanhsachbaihat);

?>