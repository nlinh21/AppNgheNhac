<?php

require "connect.php";


if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $email = $_POST["email"];
    $password = $_POST["password"];

    if ( isset($email) && isset($password)) {
        $hashed_password = password_hash($password, PASSWORD_DEFAULT);

        $sql = "INSERT INTO users (email, password) VALUES ( '$email', '$hashed_password')";

        if (mysqli_query($con, $sql)) {
            http_response_code(201);
            echo "Success";
        } else {
            http_response_code(201);
            echo "Error";
        }
    } else {
        http_response_code(201);
        echo "Validate";
    }
} else {
    http_response_code(405);
    echo json_encode(["message" => "Phương thức không được phép"]);
}

mysqli_close($con);
?>