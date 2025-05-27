$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const filmeId = urlParams.get("id");

    if (!filmeId || isNaN(filmeId)) {
        alert("Erro: ID do filme inválido.");
        return;
    }

    $.ajax({
        url: "/api/filmes/" + filmeId,
        method: "GET",
        success: function (filme) {
            $("#titulo").text(filme.titulo);
        },
        error: function () {
            alert("Erro ao carregar informações do filme.");
        }
    });

    $("form").submit(function (event) {
        event.preventDefault();

        const analise = {
            filmeId: filmeId,
            analise: $("#analise").val(),
            nota: parseInt($("#nota").val())
        };

        $.ajax({
            url: "/api/filmes/" + filmeId + "/avaliar",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                analise: $("#analise").val(),
                nota: parseInt($("#nota").val())
            }),
            success: function () {
                alert("Avaliação salva com sucesso!");
                window.location.href = "/pages/lista.html";
            },
            error: function (xhr) {
                alert("Erro ao salvar avaliação: " + xhr.responseText);
            }
        });


        $(".btn-secondary").on("click", function () {
            window.location.href = "/pages/lista.html";
        });
    });

});
