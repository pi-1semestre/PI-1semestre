class AnimalService {
    // URL apontando para a sua API de pets (localhost:3000)
    static BASE_URL = 'http://localhost:3000/api/pets';

    // Método para listar todos os animais
    static async listarAnimais() {
        try {
            const response = await fetch(this.BASE_URL);
            if (!response.ok) {
                throw new Error(`Erro ${response.status}: ${response.statusText}`);
            }
            return await response.json();
        } catch (error) {
            console.error('Erro ao buscar animais:', error);
            throw new Error('Não foi possível carregar os animais cadastrados. Verifique sua conexão.');
        }
    }

    // Método extra para o ADM poder excluir um animal do sistema
    static async excluirAnimal(id) {
        try {
            const response = await fetch(`${this.BASE_URL}/${id}`, {
                method: 'DELETE'
            });
            if (!response.ok) {
                throw new Error(`Erro ${response.status}: ${response.statusText}`);
            }
            return true;
        } catch (error) {
            console.error('Erro ao excluir animal:', error);
            throw new Error('Não foi possível excluir o animal do sistema.');
        }
    }
}

// Aguarda o DOM carregar completamente
document.addEventListener('DOMContentLoaded', async function() {
    console.log('DOM carregado, iniciando carregamento de animais no painel ADM...');

    await carregarAnimais();
});

async function carregarAnimais() {
    try {
        console.log('Iniciando carregamento de animais...');
        const animais = await AnimalService.listarAnimais();
        console.log('Animais carregados:', animais);

        const mainElement = document.querySelector('main');
        if (!mainElement) {
            console.error('Elemento main não encontrado!');
            return;
        }

        // Limpar conteúdo de loading/anterior
        mainElement.innerHTML = '';

        if (animais.length === 0) {
            mainElement.innerHTML = '<div class="aviso">Nenhum animal cadastrado no sistema.</div>';
            return;
        }

        // Adicionar animais dinamicamente em formato de Gerenciamento (Card Adm)
        animais.forEach(animal => {
            const animalDiv = document.createElement('div');
            animalDiv.className = 'animal-adm-card'; 

            // Adapte as propriedades (animal.especie, animal.idade) de acordo com o seu JSON do back-end
            animalDiv.innerHTML = `
                <div class="info">
                    <h1>ID: #${animal.id} - ${animal.nome}</h1>
                    <h2><i class="fa-solid fa-paw"></i> ${animal.especie} (${animal.raca || 'Sem raça definida'})</h2>
                    <p>Idade: ${animal.idade || 'Não informada'} | Status: <strong>${animal.status || 'Disponível'}</strong></p>
                </div>
                <div class="acoes-adm">
                    <button class="btn-editar" onclick="editarAnimal(${animal.id})">
                        <i class="fa-solid fa-pen-to-square"></i> EDITAR
                    </button>
                    <button class="btn-excluir" onclick="deletarAnimal(${animal.id}, '${animal.nome}')">
                        <i class="fa-solid fa-trash"></i> EXCLUIR
                    </button>
                </div>
            `;
            mainElement.appendChild(animalDiv);
        });

        console.log('Animais renderizados com sucesso no painel ADM!');

    } catch (error) {
        console.error('Erro ao carregar animais:', error);

        const mainElement = document.querySelector('main');
        if (mainElement) {
            mainElement.innerHTML = `
                <div class="erro">
                    <p>Erro ao carregar painel de animais: ${error.message}</p>
                    <button onclick="carregarAnimais()" style="margin-top: 1rem; padding: 0.5rem 1rem; background: #8B7355; color: white; border: none; border-radius: 5px; cursor: pointer;">
                        Tentar Novamente
                    </button>
                </div>
            `;
        }
    }
}

// Função executada ao clicar em EDITAR
function editarAnimal(animalId) {
    console.log('Redirecionando para editar o animal:', animalId);
    // Salva o ID que o administrador quer editar para resgatar na página de formulário
    localStorage.setItem('animalParaEditar', animalId);
    window.location.href = 'editarAnimal.html'; // Altere para o nome da sua página de edição
}

// Função executada ao clicar em EXCLUIR
async function deletarAnimal(animalId, nomeAnimal) {
    const confirmar = confirm(`Tem certeza que deseja excluir permanentemente o pet "${nomeAnimal}" do sistema?`);
    
    if (confirmar) {
        try {
            console.log('Deletando animal de ID:', animalId);
            await AnimalService.excluirAnimal(animalId);
            alert('Animal removido com sucesso!');
            
            // Recarrega a lista atualizada após a exclusão
            await carregarAnimais();
        } catch (error) {
            alert(`Erro ao excluir: ${error.message}`);
        }
    }
}