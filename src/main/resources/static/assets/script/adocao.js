class PetService {
    static BASE_URL = 'http://localhost:3000/api/pets';

    // A função agora recebe as espécies que queremos buscar (ex: ['Gato', 'Cachorro'])
    static async listarPets(especiesSelecionadas = []) {
        try {
            let url = this.BASE_URL;
            
            // Se o usuário marcou alguma caixinha, adicionamos isso na URL
            if (especiesSelecionadas.length > 0) {
                // Fica assim: http://localhost:3000/api/pets?especies=Gato,Cachorro
                url += `?especies=${especiesSelecionadas.join(',')}`;
            }

            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`Erro ${response.status}: ${response.statusText}`);
            }
            return await response.json();
        } catch (error) {
            console.error('Erro ao buscar pets:', error);
            throw new Error('Não foi possível carregar os amiguinhos. O servidor está ligado?');
        }
    }
}

// Quando a página carrega, busca todos os animais
document.addEventListener('DOMContentLoaded', async function() {
    await carregarPets(); // Carrega tudo por padrão

    // Configura o clique do botão de busca
    const btnSearch = document.querySelector('.btn-search');
    if (btnSearch) {
        btnSearch.addEventListener('click', realizarBusca);
    }
});

// Função que lê as caixinhas e faz a pesquisa
function realizarBusca() {
    const checkGato = document.getElementById('check-gato').checked;
    const checkCachorro = document.getElementById('check-cachorro').checked;

    let arrayBusca = [];
    
    // Pega o nome exato que usamos na coluna "especie" do banco de dados
    if (checkGato) arrayBusca.push('Gato');
    if (checkCachorro) arrayBusca.push('Cachorro');

    // Manda recarregar os animais aplicando o filtro
    carregarPets(arrayBusca);
}

// Função que desenha os animais na tela
async function carregarPets(filtroEspecies = []) {
    const gridElement = document.getElementById('pets-grid');
    gridElement.innerHTML = '<div class="loading" style="text-align: center; width: 100%;">Buscando amiguinhos...</div>';

    try {
        // Chama a API passando os filtros
        const pets = await PetService.listarPets(filtroEspecies);

        gridElement.innerHTML = '';

        if (pets.length === 0) {
            gridElement.innerHTML = '<div class="erro" style="text-align: center; width: 100%;">Nenhum animal desse tipo encontrado no momento. 🐾</div>';
            return;
        }

        // Desenha os cards dinamicamente
        pets.forEach(pet => {
            const petDiv = document.createElement('div');
            petDiv.className = 'pet-card';

            let iconeSexo = pet.sexo === 'F' 
                ? '<img src="./imgs/femea-icon.png" alt="Fêmea" class="icone-sexo" style="width: 20px;">' 
                : '<img src="./imgs/macho-icon.png" alt="Macho" class="icone-sexo" style="width: 20px;">';

            petDiv.innerHTML = `
                <img src="${pet.imagem_url}" alt="Foto de ${pet.nome}" class="pet-img" style="width: 100%; height: 200px; object-fit: cover; border-radius: 8px 8px 0 0;">
                <div class="pet-info" style="padding: 15px;">
                    <div class="pet-header" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
                        <h3 style="margin: 0; color: #2b4c7e;">${pet.nome}</h3>
                        ${iconeSexo}
                    </div>
                    <p style="margin: 5px 0;"><strong>Espécie:</strong> ${pet.especie}</p>
                    <p style="margin: 5px 0;"><strong>Porte:</strong> ${pet.porte}</p>
                    <p style="margin: 5px 0;"><strong>Idade:</strong> ${pet.idade}</p>
                    <p style="margin: 5px 0;"><strong>Pelagem:</strong> ${pet.pelagem}</p>
                </div>
            `;
            
            gridElement.appendChild(petDiv);
        });

    } catch (error) {
        console.error('Erro:', error);
        gridElement.innerHTML = `
            <div class="erro" style="text-align: center; width: 100%;">
                <p>Erro ao carregar amiguinhos: ${error.message}</p>
                <button onclick="carregarPets()" style="margin-top: 1rem; padding: 0.5rem 1rem; background: #3b629b; color: white; border: none; border-radius: 5px; cursor: pointer;">Tentar Novamente</button>
            </div>
        `;
    }
}