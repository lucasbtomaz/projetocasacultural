$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const filmeId = urlParams.get("id");

    if (!filmeId || isNaN(filmeId)) {
        alert("Erro: ID do filme inválido.");
        window.location.href = "/pages/lista.html"; 
        return;
    }

    // Carrega os detalhes do filme para edição
    $.ajax({
        url: "/api/filmes/" + filmeId,
        method: "GET",
        success: function (filme) {
            $("#filmeId").val(filme.id);
            $("#titulo").val(filme.titulo);
            $("#sinopse").val(filme.sinopse);
            $("#genero").val(filme.genero);
            $("#anoLancamento").val(filme.anoLancamento);
        },
        error: function () {
            alert("Erro ao carregar dados do filme.");
            window.location.href = "/pages/lista.html";
        }
    });

    // Salvar a edição do filme
    $("#formEditarFilme").submit(function (event) {
        event.preventDefault();

        const filmeAtualizado = {
            titulo: $("#titulo").val(),
            sinopse: $("#sinopse").val(),
            genero: $("#genero").val(),
            anoLancamento: parseInt($("#anoLancamento").val())
        };

        $.ajax({
            url: "/api/filmes/" + filmeId,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(filmeAtualizado),
            success: function () {
                alert("Filme atualizado com sucesso!");
                window.location.href = "/pages/lista.html"; 
            },
            error: function (xhr) {
                alert("Erro ao atualizar filme: " + xhr.responseText);
            }
        });
    });
});