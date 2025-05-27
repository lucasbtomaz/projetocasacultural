$(document).ready(function () {
    $('#formCadastro').on('submit', function (event) {
        event.preventDefault(); 

        const filme = {
            titulo: $('input[name="titulo"]').val(),
            sinopse: $('input[name="sinopse"]').val(),
            genero: $('input[name="genero"]').val(),
            anoLancamento: parseInt($('input[name="anoLancamento"]').val())
        };

        $.ajax({
            url: '/api/filmes',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(filme),
            success: function (response) {
                alert("Filme cadastrado com sucesso!");
                window.location.href = window.location.origin + '/pages/lista.html';
            },
            error: function (xhr) {
                alert("Erro ao cadastrar filme: " + xhr.responseText);
            }
        });
    });

    $('#btnVoltar').on('click', function () {
        window.location.href = "/index.html";
    });

    function forcarCadastro() {
        $('#formCadastro').submit();
    }

    $('#btnForcarCadastro').on('click', function () {
        forcarCadastro();
    });
});