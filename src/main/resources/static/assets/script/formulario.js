document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('formCadastro');

    if (!form) {
        console.error('Formulário com ID "formCadastro" não foi encontrado no HTML.');
        return;
    }

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const nome = document.getElementById('nome').value.trim();
        const idade = document.getElementById('idade').value.trim();
        const email = document.getElementById('email').value.trim();

        // Montando o JSON exatamente na estrutura que a entidade Voluntario espera
        const dadosCadastro = {
            nome: nome,
            idade: Number(idade),
            emailVoluntario: {
                email: email
            }
        };

        fetch('http://127.0.0.1:8081/api/v1/voluntarios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosCadastro)
        })
        .then(res => {
            if (!res.ok) {
                throw new Error('Cadastro não pôde ser realizado no servidor.');
            }
            return res.json();
        })
        .then(data => {
            console.log('Sucesso:', data);
            alert('Cadastro realizado com sucesso!');
            form.reset(); // Limpa os campos do formulário
        })
        .catch(err => {
            console.error('Erro na requisição:', err);
            alert('Erro ao realizar cadastro: ' + err.message);
        });
    });
});