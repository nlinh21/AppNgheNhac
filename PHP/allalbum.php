<?php 
     require "connect.php";
     $query = "SELECT * FROM album  ";
    $dataalbum = mysqli_query($con, $query);
class album {
    function __construct($IdAlbum ,$TenAlbum , $TenCaSiAlbum, $HinhAlbum) {
        $this->IdAlbum = $IdAlbum;
        $this->TenAlbum = $TenAlbum;
        $this->TenCaSiAlbum = $TenCaSiAlbum;
        $this->HinhAlbum = $HinhAlbum;

    }
}

$mangalbum = array();
    while ($row = mysqli_fetch_assoc($dataalbum)){
        array_push($mangalbum, new album($row['idAlbum']
                                                ,$row['tenAlbum']
                                                ,$row['tenCaSiAlbum']
                                                ,$row['hinhAlbum']));
    }



    echo json_encode($mangalbum);

?>