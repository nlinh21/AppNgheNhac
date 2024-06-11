<?php
    require "connect.php";

    if (!empty($_POST['tukhoa'])) {
        $tu_khoa = $_POST['tukhoa'];
        if (!empty($tu_khoa)) {
            $query = "SELECT * FROM baihat WHERE lower(tenBaiHat) LIKE '%" . $tu_khoa . "%'";
            $data = mysqli_query($con, $query);

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

            $mangbaihat = array();
            while ($row = mysqli_fetch_assoc($data)) {
                array_push($mangbaihat, new Baihat(
                    $row['idBaiHat'],
                    $row['tenBaiHat'],
                    $row['hinhBaiHat'],
                    $row['caSi'],
                    $row['linkBaiHat'],
                    $row['luotThich']
                ));
            }

            echo json_encode($mangbaihat);
        } else {
            echo json_encode(["error" => "Keyword is empty"]);
        }
    } else {
        echo json_encode(["error" => "Keyword 'tukhoa' not provided"]);
    }
?>
