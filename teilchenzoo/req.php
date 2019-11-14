<?php

$file = $_GET['file'];

echo file_get_contents("http://media.lukas-10100.de/teilchenzoo/" . $file);
