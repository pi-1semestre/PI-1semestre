class AnimalService {
    // URL apontando para a sua API de pets (localhost:8081)
    static BASE_URL = 'http://localhost:8081/api/v1/animals';

    // Método para enviar os dados do novo animal (POST)
    static async cadastrarAnimal(animalData) {
        try {
            const response = await fetch(this.BASE_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(animalData)
            });

            if (!response.ok) {
                throw new Error(`Erro ${response.status}: ${response.statusText}`);
            }

            return await response.json();
        } catch (error) {
            console.error('Erro ao cadastrar animal:', error);
            throw new Error('Não foi possível salvar o animal. Verifique a conexão com o servidor.');
        }
    }
}

// Aguarda o HTML carregar completamente
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM carregado, pronto para o cadastro de animais.');

    // Opcional: Verificar autenticação de ADM aqui se necessário
    if (typeof AuthService !== 'undefined' && AuthService.isLoggedIn) {
        if (!AuthService.isLoggedIn()) {
            window.location.href = 'userLogin.html';
            return;
        }
    }

    const form = document.getElementById('form-cadastro-pet');
    
    if (form) {
        form.addEventListener('submit', async function(event) {
            // Evita que a página recarregue do modo tradicional
            event.preventDefault(); 

            // Captura os valores digitados/selecionados no formulário
            const animalData = {
                nome: document.getElementById('nome_animal').value,
                idade: document.getElementById('idade').value,
                pelagem: document.getElementById('pelagem').value,
                sexo: document.getElementById('sexo').value,
                porte: document.getElementById('porte').value,
                especie: document.getElementById('especie').value,
                status: document.getElementById('status').value,
                imagem: document.getElementById('imagem').value
            };

            console.log('Tentando cadastrar o seguinte animal:', animalData);

            try {
                // Desativa o botão para evitar cliques duplos enquanto envia
                const btnEnviar = form.querySelector('.btn-enviar');
                btnEnviar.disabled = true;
                btnEnviar.textContent = 'Enviando...';

                // Envia para a API
                await AnimalService.cadastrarAnimal(animalData);

                // Feedback de sucesso
                alert('Animal cadastrado com sucesso!');
                
                // Redireciona o administrador de volta para a lista de animais cadastrados
                window.location.href = 'animais_cadastrados.html';

            } catch (error) {
                alert(`Erro ao cadastrar: ${error.message}`);
                
                // Reativa o botão em caso de erro para o usuário tentar novamente
                const btnEnviar = form.querySelector('.btn-enviar');
                btnEnviar.disabled = false;
                btnEnviar.textContent = 'Enviar';
            }
        });
    } else {
        console.error('Formulário "form-cadastro-pet" não foi encontrado no HTML.');
    }
});