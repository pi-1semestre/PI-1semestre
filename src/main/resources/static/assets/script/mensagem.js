document.addEventListener("DOMContentLoaded", function () {
    const formMensagem = document.getElementById("formMensagem");


    formMensagem.addEventListener("submit", async function (event) {
        event.preventDefault();

        const nomeAnimal = document.getElementById("nomeAnimal").value.trim();
        const idadeAnimal = Number(document.getElementById("idadeAnimal").value);
        const descricao = document.getElementById("descricao").value.trim();
        const imagemAntes = document.getElementById("imagemAntes").value.trim();
        const imagemDepois = document.getElementById("imagemDepois").value.trim();

        const mensagem = {
            descricao: descricao,
            status: true,
            imagemAntes: imagemAntes,
            imagemDepois: imagemDepois,
            nomeAnimal: nomeAnimal,
            idadeAnimal: idadeAnimal
        };

        try {
            const response = await fetch("http://127.0.0.1:8081/api/v1/mensagens", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(mensagem)
            });

            if (!response.ok) {
                const erroTexto = await response.text();
                console.error("Erro do backend:", erroTexto);
                throw new Error("Erro ao cadastrar mensagem.");
            }

            const data = await response.json();

            alert("História enviada com sucesso!");
            console.log("Mensagem cadastrada:", data);
            formMensagem.reset();

        } catch (error) {
            console.error("Erro completo:", error);
            alert("Erro ao enviar. Tente novamente mais tarde.");
        }
    });
});