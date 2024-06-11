<?php
    require "connect.php";

    $query = "SELECT * FROM playlist ";
    class BXH {
        function __construct($idplaylist, $ten, $hinhanh, $icon) {
            $this->Id = $idplaylist;
            $this->Ten = $ten;
            $this->Hinhanh = $hinhanh;
            $this->Icon = $icon;
        }
    }

    $data = mysqli_query($con, $query);

    $mangbxh = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangbxh, new BXH($row['idPlaylist']
                                                ,$row['ten']
                                                ,$row['hinhNen']
                                                ,$row['icon']));
    }


    echo json_encode($mangbxh);

?>