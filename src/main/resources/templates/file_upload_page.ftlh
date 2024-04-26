<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title id="page-title">Upload File</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
<div>
    <input type="file" id="file" name="file" placeholder="File Name..."> <input type="hidden" id="file_hidden"> <input type="text" id="title" placeholder="Title..."> <input type="text" id="description" placeholder="Description..."> <button onclick="sendFile()">Upload File</button> <div class="filename"></div>
</div>
<div id="photo"></div>
<div id="filesList"></div>
<script>
    function sendFile() {
        let formData = new FormData();
        let files = $('#file')[0].files;
        [].forEach.call(files, function (file, i, files) {
            formData.append("file", file);
        });
        let title = $('#title').val();
        formData.append("title", title);
        let description = $('#description').val();
        formData.append("description", description);

        $.ajax({
            type: "POST",
            url: "/files",
            data: formData,
            processData: false,
            contentType: false
        })
            .done(function (response) {
                let fileUrl = 'http://localhost:8080/files/' + response.storageFileName;
                // Cambiamos el nombre de la imagen por el título
                $('#photo').append('<img src="' + fileUrl + '" width="100" alt="' + title + '">');
                updateFilesList();
            })
            .fail(function () {
                alert('Error');
            });
    }

    function updateFilesList() {
        $.ajax({
            type: "GET",
            url: "/files/all",
        })
            .done(function (response) {
                let filesList = $('#filesList');
                filesList.empty();
                response.forEach(function (file) {
                    if(file !== null && file.type !== null) {
                        let fileItem = $('<div>');
                        fileItem.append('<img src="/files/' + file.storageFileName + '" width="100" alt="' + file.title + '">');
                        fileItem.append('<a href="/files/' + file.storageFileName + '">' + file.title + '</a> - ' + file.description);
                        filesList.append(fileItem);
                    }
                });
            })
            .fail(function () {
                alert('Error');
            });
    }

    $(document).ready(function () {
        updateFilesList();
    });

    $('#title').on('input', function() {
        $('#page-title').text($(this).val());
    });
</script>
</body>
</html>
