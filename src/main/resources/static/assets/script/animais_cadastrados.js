class AnimalService {
    static BASE_URL = 'http://localhost:8081/api/v1/animals';

    static async listarAnimais() {
        try {
            const response = await fetch(this.BASE_URL);
            if (!response.ok) throw new Error(`Erro: ${response.status}`);
            return await response.json();
        } catch (error) {
            console.error('Erro ao buscar animais:', error);
            return [];
        }
    }

    static async excluirAnimal(id) {
        const response = await fetch(`${this.BASE_URL}/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error(`Erro ao excluir: ${response.status}`);
        return true;
    }
}

document.addEventListener('DOMContentLoaded', async function() {
    await carregarAnimais();
    gerenciarMenuMobile();
});

async function carregarAnimais() {
    const grid = document.getElementById('grid-animais');
    if (!grid) return;

    grid.innerHTML = '<p style="text-align:center; width:100%;">Carregando...</p>';
    const animais = await AnimalService.listarAnimais();

    grid.innerHTML = '';
    if (animais.length === 0) {
        grid.innerHTML = '<p style="text-align:center; width:100%;">Nenhum animal cadastrado no sistema.</p>';
        return;
    }

    animais.forEach(animal => {
        // CORREÇÃO DOS NOMES: Usando as variáveis que o Java manda!
        const id = animal.idAnimal;
        const nome = animal.nomeAnimal || 'Sem nome';
        const idade = animal.idade || 'indeterminado';
        const porte = animal.porte?.porte || 'Não definido';
        const pelagem = animal.pelagem?.pelagem || 'Não definida';
        const sexo = animal.sexo?.sexo || 'F';
        const imagem = animal.imagem || './assets/imgs/placeholder.png';

        const isFemea = sexo.toUpperCase() === 'F';
        const iconeSexo = isFemea ? '♀' : '♂';
        const classeSexo = isFemea ? 'femea' : 'macho';

        const card = document.createElement('div');
        card.className = 'pet-card';
        card.id = `animal-card-${id}`;

        card.innerHTML = `
            <div class="img-wrapper">
                <img src="${imagem}" alt="${nome}" onerror="this.onerror=null; this.src='./assets/imgs/placeholder.png';">
            </div>
            <div class="pet-info">
                <div class="pet-header">
                    <h3>${nome}</h3>
                    <div class="gender-icon ${classeSexo}">${iconeSexo}</div>
                </div>
                <p><strong>Porte:</strong> ${porte}</p>
                <p><strong>Idade:</strong> ${idade}</p>
                <p><strong>Pelagem:</strong> ${pelagem}</p>
            </div>
            <div class="card-actions">
                <button class="btn-action btn-editar" onclick="editarAnimal(${id})">Editar</button>
                <button class="btn-action btn-excluir" onclick="deletarAnimal(${id}, '${nome}')">Excluir</button>
            </div>
        `;
        grid.appendChild(card);
    });
}

function editarAnimal(animalId) {
    // Redireciona para a página de edição com o ID na URL
    window.location.href = `cadastro_animal.html?id=${animalId}`;
}

async function deletarAnimal(animalId, nomeAnimal) {
    if (confirm(`Tem certeza que deseja excluir o pet "${nomeAnimal}" do sistema?`)) {
        try {
            await AnimalService.excluirAnimal(animalId);
            alert('Animal removido com sucesso!');
            // Remove o card da tela
            document.getElementById(`animal-card-${animalId}`).remove();
        } catch (error) {
            alert(`Não foi possível excluir o animal.`);
            console.error(error);
        }
    }
}

// Menu Mobile
function gerenciarMenuMobile() {
    const btnMenu = document.getElementById("btnMenu");
    const fecharMenu = document.getElementById("fecharMenu");
    const menu = document.getElementById("menu");
    const fundoMenu = document.getElementById("fundoMenu");

    if (btnMenu) btnMenu.addEventListener("click", () => { menu.classList.add("ativo"); if(fundoMenu) fundoMenu.classList.add("ativo"); });
    const fechar = () => { menu.classList.remove("ativo"); if(fundoMenu) fundoMenu.classList.remove("ativo"); };
    if (fecharMenu) fecharMenu.addEventListener("click", fechar);
    if (fundoMenu) fundoMenu.addEventListener("click", fechar);
}
