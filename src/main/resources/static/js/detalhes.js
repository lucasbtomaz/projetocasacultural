$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const filmeId = urlParams.get("id");

    if (!filmeId || isNaN(filmeId)) {
        alert("Erro: ID do filme inválido.");
        return;
    }

    // Busca os detalhes do filme e carrega as avaliações
    $.ajax({
        url: "/api/filmes/" + filmeId + "/detalhes",
        method: "GET",
        success: function (dados) {
            console.log("Dados recebidos da API:", dados);

            $("#titulo").text(dados.filme.titulo);
            $("#sinopse").text(dados.filme.sinopse);
            $("#genero").text(dados.filme.genero);
            $("#anoLancamento").text(dados.filme.anoLancamento);
            $("#media").text(dados.media.toFixed(1));

            exibirEstrelas(dados.media);

            const tabelaAnalises = $("#tabelaAnalises tbody");
            tabelaAnalises.empty();

            if (dados.analises.length === 0) {
                tabelaAnalises.append(`<tr><td colspan="3">Nenhuma avaliação encontrada.</td></tr>`);
            } else {
                dados.analises.forEach(analise => {
                    tabelaAnalises.append(`
                        <tr>
                            <td>${analise.analise}</td>
                            <td>${analise.nota}</td>
                            <td>
                                <a href="/pages/editar-analise.html?id=${analise.id}&filmeId=${filmeId}" class="btn btn-warning btn-sm">Editar</a>
                                <button class="btn btn-danger btn-sm" onclick="excluirAnalise(${analise.id}, ${filmeId})">
                                    Excluir
                                </button>
                            </td>
                        </tr>
                    `);
                });
            }
        },
        error: function (xhr) {
            alert("Erro ao carregar detalhes do filme: " + xhr.responseText);
        }
    });
});

// Função para excluir uma análise
function excluirAnalise(analiseId, filmeId) {
    if (confirm("Tem certeza que deseja excluir esta análise?")) {
        $.ajax({
            url: "/api/filmes/" + filmeId + "/analises/" + analiseId,
            type: "DELETE",
            success: function () {
                alert("Análise excluída com sucesso!");
                location.reload();
            },
            error: function (xhr) {
                alert("Erro ao excluir análise: " + xhr.responseText);
                console.log("Erro ao excluir análise:", xhr);
            }
        });
    }
}


function exibirEstrelas(media) {
    const estrelasContainer = $("#media-estrelas");
    estrelasContainer.empty();
    
    for (let i = 1; i <= 5; i++) {
        if (i <= Math.floor(media)) { 
            estrelasContainer.append('<i class="fa fa-star checked"></i>');
        } else {
            estrelasContainer.append('<i class="fa fa-star"></i>');
        }
    }
}