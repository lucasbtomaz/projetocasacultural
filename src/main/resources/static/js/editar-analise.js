$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const filmeId = urlParams.get("filmeId");
    const analiseId = urlParams.get("id");

    if (!filmeId || !analiseId || isNaN(filmeId) || isNaN(analiseId)) {
        alert("Erro: ID inválido.");
        window.location.href = "/pages/lista.html";
        return;
    }

    // Carrega a análise
    $.ajax({
        url: "/api/filmes/" + filmeId + "/analises/" + analiseId,
        method: "GET",
        success: function (analise) {
            console.log("Dados da análise recebidos:", analise);
            $("#analiseId").val(analise.id);
            $("#filmeId").val(filmeId);
            $("#analise").val(analise.analise);
            $("#nota").val(analise.nota);

            $("#btnCancelar").attr("href", "/pages/detalhes.html?id=" + filmeId);
        },
        error: function () {
            alert("Erro ao carregar análise.");
            window.location.href = "/pages/lista.html";
        }
    });

    // Salva as alterações via PUT
    $("#formEditarAnalise").submit(function (event) {
        event.preventDefault();

        const analiseAtualizada = {
            analise: $("#analise").val(),
            nota: parseInt($("#nota").val())
        };

        $.ajax({
            url: "/api/filmes/" + filmeId + "/analises/" + analiseId,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(analiseAtualizada),
            success: function () {
                alert("Análise atualizada com sucesso!");
                window.location.href = "/pages/detalhes.html?id=" + filmeId;
            },
            error: function (xhr) {
                alert("Erro ao atualizar análise: " + xhr.responseText);
            }
        });
    });
});