<?php
    require "Connect.php";
    class Theloai {
        function __construct($idtheloai, $idkeychude, $tentheloai, $hinhtheloai) {
            $this -> IdTheLoai = $idtheloai;
            $this -> IdKeyChuDe = $idkeychude;
            $this -> TenTheLoai = $tentheloai;
            $this -> HinhTheLoai = $hinhtheloai;
        }

    }
    class Chude {
        function __construct($idchude, $tenchude, $hinhchude){
            $this -> IdChuDe = $idchude;
            $this -> TenChuDe = $tenchude;
            $this -> HinhChuDe = $hinhchude;
        }
    }
    $arraytheloai = array();
    $arraychude = array();
    $querytheloai = "SELECT DISTINCT * FROM theloai ORDER BY rand() LIMIT 4";
    $datatheloai = mysqli_query ($con, $querytheloai);
    while ($row = mysqli_fetch_assoc($datatheloai)){
        array_push($arraytheloai, new Theloai($row['idTheLoai'],
                                            $row['idChuDe'],
                                            $row['tenTheLoai'],
                                            $row['hinhTheLoai'],
    ));
    }

    $querychude = "SELECT DISTINCT * FROM chude ORDER BY rand() LIMIT 4";
    $datachude = mysqli_query ($con, $querychude);
    while ($row = mysqli_fetch_assoc($datachude)) {
        array_push($arraychude, new Chude(
                                            $row['idChuDe'],
                                            $row['tenChuDe'],
                                            $row['hinhChuDe'],
    ));
    }
$arraychudetheloai = array('Theloai' => $arraytheloai, 'Chude' => $arraychude);
echo json_encode($arraytheloai);

?>