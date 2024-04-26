<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/sign.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/menu.css">
</head>
<body>

<script>
    let currentPage = 0; // Inicializar la página actual
    let pageSize = 4; // Establecer el tamaño de la página

    let refetch = () => {
        let query = $('#search').val(); // Obtener el valor de búsqueda
        $.ajax({
            type: "GET",
            url: "/papers/service/search",
            data: { page: currentPage, size: pageSize, q: query }, // Pasar el parámetro de búsqueda correctamente como 'q'
            success: function(response) {
                renderTable(response.content, $('#table'));
                renderPagination(response.totalPages);
            },
            dataType: "json",
            contentType: "application/json"
        });
    }

    function renderTable(services, table) {
        let innerHtml = '<tr>\n' +
            '               <th>id</th>' +
            '               <th>Name</th>' +
            '               <th>Cost</th>' +
            '               <th>Date</th>' +
            '            </tr>';

        for (let i = 0; i < services.length; i++) {
            innerHtml += '<tr>';
            innerHtml += '  <td>' + services[i]['id'] + '</td>';
            innerHtml += '  <td>' + services[i]['name'] + '</td>';
            innerHtml += '  <td>' + services[i]['cost'] + '</td>';
            innerHtml += '  <td>' + services[i]['date'] + '</td>';
            innerHtml += '</tr>';
        }

        table.html(innerHtml);
    }

    function renderPagination(totalPages) {
        let paginationHtml = '<div class="pagination">';
        for (let i = 0; i < totalPages; i++) {
            paginationHtml += '<a href="#" onclick="changePage(' + i + ')">' + (i + 1) + '</a>';
        }
        paginationHtml += '</div>';

        $('#pagination').html(paginationHtml);
    }

    function changePage(page) {
        currentPage = page;
        refetch();
    }

    function sendUser(name, cost, date) {
        let data = {
            "name": name,
            "cost": cost,
            "date": date
        };

        $.ajax({
            type: "POST",
            url: "/services",
            data: JSON.stringify(data),
            success: function(response) {
                // Limpiar los campos del formulario después de enviarlos
                $('#name').val('');
                $('#cost').val('');
                $('#date').val('');
                // Recargar los datos de la tabla
                refetch();
            },
            dataType: "json",
            contentType: "application/json"
        });
    }

    $(document).ready(function() {
        $('#searchBtn').click(function() {
            refetch(); // Volver a cargar los datos cuando se hace clic en el botón de búsqueda
        });

        $('#search').keypress(function(e) {
            if(e.which == 13) {
                refetch(); // Volver a cargar los datos cuando se presiona la tecla Enter en el campo de búsqueda
            }
        });
    });

    // Llamar inicialmente para cargar los datos de la tabla
    refetch();
</script>

<div class="container">
    <h1>Insert</h1>
    <hr>
    <label><b>Name</b></label>
    <input type="text" id="name" placeholder="Name">
    <label><b>Cost</b></label>
    <input type="text" id="cost" placeholder="Cost">
    <label><b>Time</b></label>
    <input type="text" id="date" placeholder="Time">
    <hr>
    <button class="button" onclick="sendUser($('#name').val(), $('#cost').val(), $('#date').val())">Добавить</button>
</div>

<div>
    <input type="text" id="search" placeholder="Search">
    <button id="searchBtn">Search</button>
</div>

<div>
    <table id="table">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>cost</th>
            <th>date</th>
        </tr>
    </table>
</div>

<div id="pagination"></div>

</body>
</html>

