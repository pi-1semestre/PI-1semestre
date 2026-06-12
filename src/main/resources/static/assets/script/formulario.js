document.addEventListener('DOMContentLoaded', () => {

    const form = document.getElementById('formCadastro');

    form.addEventListener('submit', async function(event) {

        
        event.preventDefault();

        try {

            const nome = document.getElementById('nome').value.trim();
            const idade = document.getElementById('idade').value.trim();
            const email = document.getElementById('email').value.trim();

            /* Cria o email */
            const respostaEmail = await fetch(
                'http://127.0.0.1:8081/api/v1/emailVoluntarios',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        email: email
                    })
                }
            );

            if (!respostaEmail.ok) {
                throw new Error('Erro ao cadastrar email.');
            }

            const emailCriado = await respostaEmail.json();

            /* Cria o voluntário */
            const respostaVoluntario = await fetch(
                'http://127.0.0.1:8081/api/v1/voluntarios',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        nome: nome,
                        idade: Number(idade),
                        emailVoluntario: {
                            idEmailVoluntario:
                                emailCriado.idEmailVoluntario
                        }
                    })
                }
            );

            if (!respostaVoluntario.ok) {
                throw new Error('Erro ao cadastrar voluntário.');
            }

            alert('Cadastro realizado com sucesso!');

            form.reset();

        } catch (erro) {

            console.error(erro);

            alert(erro.message);

        }

    });

});