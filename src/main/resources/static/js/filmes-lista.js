$(document).ready(function () {
    $.ajax({
        url: "/api/filmes",
        method: "GET",
        success: function (filmes) {
            console.log("Filmes recebidos:", filmes);
            const container = $("#lista-filmes");
            container.empty();

            filmes.forEach(filme => {
                const card = `
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="/image/rodape.jpg" class="card-img-top" alt="Imagem Filme">
                        <div class="card-body text-center">
                            <h5 class="card-title">${filme.titulo}</h5>
                            <a href="/pages/detalhes.html?id=${filme.id}" class="btn btn-primary btn-sm">Detalhes</a>
                            <a href="/pages/avaliar.html?id=${filme.id}" class="btn btn-success btn-sm">Avaliar</a>
                            <button class="btn btn-danger btn-sm" onclick="deletarFilme(${filme.id})">Excluir</button>
                            <a href="/pages/editar-filme.html?id=${filme.id}" class="btn btn-warning btn-sm">Editar</a>
                        </div>
                    </div>
                </div>`;
                container.append(card);
            });
        },
        error: function () {
            alert("Erro ao carregar filmes");
        }
    });
});

function deletarFilme(id) {
    if (confirm("Tem certeza que deseja excluir este filme?")) {
        $.ajax({
            url: "/api/filmes/" + id,
            type: "DELETE",
            success: function () {
                alert("Filme excluído com sucesso!");
                location.reload();
            },
            error: function () {
                alert("Erro ao excluir filme.");
            }
        });
    }
}