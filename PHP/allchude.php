<?php
    require "connect.php";

    $query = "SELECT * FROM chude ";
    class Theloai {
        function __construct($IdChuDe , $TenChuDe, $HinhChuDe) {
            $this->IdChuDe = $IdChuDe;
            $this->TenTheLoai = $TenChuDe;
            $this->HinhTheLoai = $HinhChuDe;
    
        }
    }

    $data = mysqli_query($con, $query);

    $mangchude = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangchude, new Theloai($row['idChuDe']
                                                ,$row['tenChuDe']
                                                ,$row['hinhChuDe']));
    }


    echo json_encode($mangchude);

?>