<?php
    require "connect.php";

    class Quangcao {
        function __construct($idquangcao, $hinhanh, $noidung, $idbaihat, $tenbaihat, $hinhbaihat) {
            $this->Id = $idquangcao;
            $this->HinhAnh = $hinhanh;
            $this->NoiDung = $noidung;
            $this->Idbaihat = $idbaihat;
            $this->TenBaiHat = $tenbaihat;
            $this->HinhBaiHat = $hinhbaihat;
        }
    }

    $query = "SELECT quangcao.Id, quangcao.HinhAnh, quangcao.NoiDung, quangcao.Idbaihat, baihat.TenBaiHat, baihat.HinhBaiHat
             FROM `quangcao` INNER JOIN `baihat` ON quangcao.IdbaiHat = baihat.IdBaiHat;";
    $data = mysqli_query($con, $query);

    $mangquangcao = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangquangcao, new Quangcao($row['Id']
                                                ,$row['HinhAnh']
                                                ,$row['NoiDung']
                                                ,$row['Idbaihat']
                                                ,$row['TenBaiHat']
                                                ,$row['HinhBaiHat']));
    }


    echo json_encode($mangquangcao);

?>
