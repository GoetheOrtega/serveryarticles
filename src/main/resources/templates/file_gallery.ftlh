<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
</head>
<body>
<ul id="filesList">
</ul>

<script>
    function updateFilesList() {
        $.ajax({
            type: "GET",
            url: "/files/all",
        })
            .done(function (response) {
                let filesList = $('#filesList');
                filesList.empty();
                response.forEach(function (file) {
                    let listItem = $('<li>');
                    listItem.append('<img src="/files/' + file.storageFileName + '" width="100">');
                    listItem.append('<p><strong>Title:</strong> ' + file.title + '</p>');
                    listItem.append('<p><strong>Description:</strong> ' + file.description + '</p>');
                    let likeLink = $('<a>').text('Like').attr('href', '#').click(function () {
                        likeFile(file.id);
                        return false; // Prevent default link behavior
                    });
                    listItem.append(likeLink);
                    filesList.append(listItem);
                });
            })
            .fail(function () {
                alert('Error');
            });
    }

    function likeFile(fileId) {
        $.ajax({
            type: "POST",
            url: "/files/like/" + fileId,
        })
            .done(function () {
                alert('Successful');
            })
            .fail(function () {
                alert('Error');
            });
    }

    // Load the list of files when the page loads
    $(document).ready(function () {
        updateFilesList();
    });
</script>
</body>
</html>
