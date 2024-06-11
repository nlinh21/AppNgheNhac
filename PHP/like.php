<?php 
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
    $databaihat = mysqli_query($con, $query);

    $mangbaihat = array();
    while ($row = mysqli_fetch_assoc($databaihat)){
        array_push($mangbaihat, new Baihat($row['IdBaiHat']
                                                ,$row['TenBaiHat']
                                                ,$row['HinhBaiHat']
                                                ,$row['CaSi']
                                                ,$row['LinkBaiHat']
                                                ,$row['LuotThich']));
    }


    echo json_encode($mangbaihat);
?>