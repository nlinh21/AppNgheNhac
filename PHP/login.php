<?php

require "connect.php";

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $email = $_POST["email"];
    $password = $_POST["password"];

    if (isset($email) && isset($password)) {
        $sql = "SELECT * FROM users WHERE email = '$email'";
        $result = mysqli_query($con, $sql);

        if ($result && mysqli_num_rows($result) > 0) {
            $user = mysqli_fetch_assoc($result);

            if (password_verify($password, $user['password'])) {
                http_response_code(200);
                echo "Success";
            } else {
                http_response_code(200);
                echo "Password";
            }
        } else {
            http_response_code(200);
            echo "Email";
        }
    } else {
        http_response_code(200);
        echo  "Validate";
    }
} else {
    http_response_code(405);
    echo  "Phương thức không được phép";
}

mysqli_close($con);
?>
