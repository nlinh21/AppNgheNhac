<?php
require "connect.php";
$query = "SELECT DISTINCT *FROM playlist ORDER BY rand(" . date("Ymd"). ") LIMIT 3";
class PlaylistToday {
    function __construct($idplaylist, $ten, $hinhplaylist, $icon)
    {
        $this -> IdPlaylist = $idplaylist;
        $this -> Ten = $ten;
        $this -> HinhPlaylist = $hinhplaylist;
        $this -> Icon = $icon;
    }
}

$arrayplaylistfortoday = array();
$data = mysqli_query($con, $query);
while ($row = mysqli_fetch_assoc($data)) {
    array_push($arrayplaylistfortoday, new PlaylistToday($row['idPlaylist'],
                                                        $row['ten'],
                                                        $row['hinhNen'],
                                                        $row['icon'],
));
}
echo json_encode($arrayplaylistfortoday);

?>
