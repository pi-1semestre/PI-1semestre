class AnimalService {
    // URL da sua API de pets
    static BASE_URL = 'http://localhost:3000/api/pets';

    // Método para buscar os animais cadastrados
    static async listarAnimais() {
        try {
            const response = await fetch(this.BASE_URL);
            if (!response.ok) {
                throw new Error(`Erro ${response.status}: ${response.statusText}`);
            }
            return await response.json();
        } catch (error) {
            console.error('Erro ao buscar animais:', error);
            throw new Error('Não foi possível carregar os animaizinhos. Verifique sua conexão.');
        }
    }
}

// Armazena a lista vinda da API globalmente para filtrar sem precisar fazer novas requisições
let bancoDeAnimais = [];

// Aguarda o DOM carregar completamente
document.addEventListener('DOMContentLoaded', async function() {
    console.log('DOM carregado, iniciando carregamento de animais para adoção...');
    
    await inicializarPets();
    configurarFiltros();
});

// Busca os dados iniciais
async function inicializarPets() {
    try {
        const gridElement = document.getElementById('pets-grid');
        if (!gridElement) return;

        bancoDeAnimais = await AnimalService.listarAnimais();
        console.log('Animais recebidos da API:', bancoDeAnimais);

        // Para a página do cliente, filtramos para mostrar APENAS animais com status "Disponível"
        const disponiveis = bancoDeAnimais.filter(animal => animal.status === 'Disponível');
        
        renderizarAnimais(disponiveis);

    } catch (error) {
        console.error('Erro ao inicializar página:', error);
        const gridElement = document.getElementById('pets-grid');
        if (gridElement) {
            gridElement.innerHTML = `
                <div class="erro">
                    <p>${error.message}</p>
                    <button onclick="inicializarPets()" style="margin-top: 1rem; padding: 0.5rem 1rem; background: #2E6F2E; color: white; border: none; border-radius: 5px; cursor: pointer;">
                        Tentar Novamente
                    </button>
                </div>
            `;
        }
    }
}

// Renderiza os cartões dinamicamente no HTML
function renderizarAnimais(animais) {
    const gridElement = document.getElementById('pets-grid');
    if (!gridElement) return;

    // Limpa a mensagem de "Carregando..."
    gridElement.innerHTML = '';

    if (animais.length === 0) {
        gridElement.innerHTML = '<div class="aviso">Nenhum amiguinho disponível com esses filtros no momento. 🐾</div>';
        return;
    }

    animais.forEach(animal => {
        const petCard = document.createElement('div');
        petCard.className = 'pet-card'; // Certifique-se de estilizar essa classe no seu adocao.css

        // Prepara um link do WhatsApp com mensagem automática contendo o nome do pet
        const mensagemWhats = encodeURIComponent(`Olá! Vi o(a) ${animal.nome} no site Apoio Pet e gostaria de saber mais sobre o processo de adoção.`);
        const linkWhatsapp = `https://wa.me/554832314000?text=${mensagemWhats}`;

        petCard.innerHTML = `
            <div class="pet-img-container" style="width: 100%; height: 200px; overflow: hidden; border-radius: 8px;">
                <img src="${animal.imagem || './assets/imgs/placeholder.png'}" alt="Foto de ${animal.nome}" style="width: 100%; height: 100%; object-fit: cover;">
            </div>
            <div class="pet-info" style="padding: 15px 5px;">
                <h2 style="color: #4F4F4F; font-size: 22px; margin-bottom: 5px;">${animal.nome}</h2>
                <p style="font-size: 14px; color: #777; margin-bottom: 10px;">
                    <strong>${animal.especie}</strong> • ${animal.idade} • ${animal.sexo === 'M' ? 'Macho' : 'Fêmea'}
                </p>
                <p style="font-size: 13px; color: #555; margin-bottom: 15px;">
                    Porte ${animal.porte} e pelagem ${animal.pelagem.toLowerCase()}.
                </p>
                <a href="${linkWhatsapp}" target="_blank" class="btn-adotar" style="display: block; text-align: center; background-color: #2E6F2E; color: white; padding: 10px; border-radius: 6px; text-decoration: none; font-weight: 600;">
                    Quero Adotar!
                </a>
            </div>
        `;
        gridElement.appendChild(petCard);
    });
}

// Configura a ação do botão "Procure um amiguinho"
function configurarFiltros() {
    const btnSearch = document.querySelector('.btn-search');
    const checkGato = document.getElementById('check-gato');
    const checkCachorro = document.getElementById('check-cachorro');

    if (!btnSearch) return;

    btnSearch.addEventListener('click', function() {
        const querGato = checkGato ? checkGato.checked : false;
        const querCachorro = checkCachorro ? checkCachorro.checked : false;

        // Sempre partimos apenas dos animais que estão com status "Disponível"
        let resultadoFiltrado = bancoDeAnimais.filter(animal => animal.status === 'Disponível');

        // Se o usuário selecionou apenas Gato
        if (querGato && !querCachorro) {
            resultadoFiltrado = resultadoFiltrado.filter(animal => animal.especie.toLowerCase() === 'gato');
        } 
        // Se o usuário selecionou apenas Cachorro
        else if (querCachorro && !querGato) {
            resultadoFiltrado = resultadoFiltrado.filter(animal => animal.especie.toLowerCase() === 'cachorro');
        }
        // Se nenhum estiver marcado (ou ambos estiverem), mostra todos os disponíveis
        
        renderizarAnimais(resultadoFiltrado);
    });
}