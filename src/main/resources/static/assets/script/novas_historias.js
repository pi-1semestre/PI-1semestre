/**
 * URL CORRETA da sua API no Spring Boot.
 */
const API_URL = 'http://localhost:8081/api/v1/mensagens';

/**
 * Busca as histórias da API.
 */
async function fetchStories() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error(`Erro na API: ${response.status}`);
        return await response.json();
    } catch (error) {
        console.error("❌ Falha ao buscar histórias.", error);
        return []; 
    }
}

/**
 * Exclui uma história do banco de dados
 */
async function handleDelete(id) {
    if (confirm(`Tem certeza que deseja excluir esta história?`)) {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert("✅ História excluída com sucesso!");
                // Remove o card da tela automaticamente
                const card = document.getElementById(`story-${id}`);
                if (card) card.remove();
            } else {
                throw new Error("Falha no servidor ao excluir.");
            }
        } catch (error) {
            console.error("❌ Erro ao deletar:", error);
            alert("Não foi possível excluir a história. Tente novamente.");
        }
    }
}

/**
 * Publica uma história (Atualiza o status para true)
 */
async function handlePublish(story) {
    const id = story.idMensagem || story.id_mensagem;
    const nome = story.nomeAnimal || story.nome_animal || 'este animal';

    if (confirm(`Deseja publicar a história de ${nome}?`)) {
        // Criamos uma cópia da história e mudamos o status para true (publicado)
        const updatedStory = { ...story, status: true };

        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedStory)
            });

            if (response.ok) {
                alert("🎉 História publicada com sucesso!");
                // Recarrega a página para atualizar o botão e a lista
                loadPage();
            } else {
                throw new Error("Falha no servidor ao publicar.");
            }
        } catch (error) {
            console.error("❌ Erro ao publicar:", error);
            alert("Não foi possível publicar a história.");
        }
    }
}

/**
 * Cria o HTML para um único card de história como um Elemento DOM.
 */
function createStoryCard(story) {
    const id = story.idMensagem || story.id_mensagem || '';
    const nome = story.nomeAnimal || story.nome_animal || 'Nome Desconhecido';
    const idade = story.idadeAnimal || story.idade_animal || '?';
    const descricao = story.descricao || 'Nenhuma descrição fornecida.';
    
    const placeholderImg = './assets/imgs/placeholder.png'; 
    const imagemAntes = story.imagemAntes || story.imagem_antes || placeholderImg;
    const imagemDepois = story.imagemDepois || story.imagem_depois || placeholderImg;
    
    const isPublished = story.status === true;

    // Criamos uma div real para podermos colocar os eventos de clique
    const card = document.createElement('div');
    card.className = 'story-card';
    card.id = `story-${id}`;

    card.innerHTML = `
        <div class="card-topo-vermelho">
            <div class="nome-pet">
                <span class="bolinha-amarela"></span>
                <span>${nome}</span>
            </div>
            <span>${idade} anos</span>
        </div>
        <div class="card-body">
            <div class="card-images">
                <div class="img-wrapper">
                    <img src="${imagemAntes}" alt="Foto de antes" onerror="this.onerror=null; this.src='${placeholderImg}';">
                    <span class="badge badge-antes">Antes</span>
                </div>
                <div class="img-wrapper">
                    <img src="${imagemDepois}" alt="Foto de depois" onerror="this.onerror=null; this.src='${placeholderImg}';">
                    <span class="badge badge-depois">Depois</span>
                </div>
            </div>
            <p class="card-text">${descricao}</p>
            <div class="card-actions">
                <button class="btn-action btn-publicar" ${isPublished ? 'disabled' : ''}>
                    ${isPublished ? 'Publicado' : 'Publicar'}
                </button>
                <button class="btn-action btn-deletar">Deletar</button>
            </div>
        </div>
    `;

    // 🔗 Adiciona os eventos de clique nos botões
    card.querySelector('.btn-deletar').addEventListener('click', () => handleDelete(id));
    
    // Só adiciona evento no botão de publicar se ele não estiver publicado ainda
    if (!isPublished) {
        card.querySelector('.btn-publicar').addEventListener('click', () => handlePublish(story));
    }

    return card;
}

/**
 * Lida com o Menu Mobile (Responsividade)
 */
function gerenciarMenuMobile() {
    const btnMenu = document.getElementById("btnMenu");
    const fecharMenu = document.getElementById("fecharMenu");
    const menu = document.getElementById("menu");
    const fundoMenu = document.getElementById("fundoMenu");

    if (btnMenu) {
        btnMenu.addEventListener("click", (e) => {
            e.preventDefault();
            menu.classList.add("ativo");
            fundoMenu.classList.add("ativo");
        });
    }

    const fecharPainel = (e) => {
        e.preventDefault();
        menu.classList.remove("ativo");
        fundoMenu.classList.remove("ativo");
    };

    if (fecharMenu && fundoMenu) {
        fecharMenu.addEventListener("click", fecharPainel);
        fundoMenu.addEventListener("click", fecharPainel);
    }
}

/**
 * Função principal: carrega os dados e preenche a página.
 */
async function loadPage() {
    gerenciarMenuMobile();

    const grid = document.getElementById('historias-grid');
    if (!grid) return;

    grid.innerHTML = '<p style="grid-column: 1 / -1; text-align: center;">Buscando histórias no banco de dados...</p>';
    
    const stories = await fetchStories();

    if (stories.length > 0) {
        grid.innerHTML = ''; 
        stories.forEach(story => {
            const cardElement = createStoryCard(story);
            grid.appendChild(cardElement); // Adiciona o card ao grid
        });
    } else {
        grid.innerHTML = '<p style="grid-column: 1 / -1; text-align: center; color: red;">Nenhuma história encontrada.</p>';
    }
}

document.addEventListener('DOMContentLoaded', loadPage);
