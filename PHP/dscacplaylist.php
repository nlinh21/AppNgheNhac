<?php
    require "connect.php";
    $query = "SELECT * FROM playlist";
    $data = mysqli_query($con, $query);


    class Danhsachplaylist {
        function __construct($idplaylist, $ten, $hinhnen, $icon){
            $this -> IdPlaylist = $idplaylist;
            $this -> Ten = $ten;
            $this -> HinhPlaylist = $hinhnen;
            $this -> Icon = $icon;
        }

    }
    $arrayplaylist = array();
    while ($row = mysqli_fetch_assoc($data)) {
        array_push($arrayplaylist, new Danhsachplaylist ($row['idPlaylist'],
                                                        $row['ten'],
                                                        $row['hinhNen'],
                                                        $row['icon']));
    }
    echo json_encode($arrayplaylist);

?>