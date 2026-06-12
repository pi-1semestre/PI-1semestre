const grid = document.getElementById('pets-grid');
let banco = []; // Vai guardar a lista original que vem do banco

/**
 * URL da sua API de animais. 
 */
const API_URL = 'http://localhost:8081/api/v1/animals'; // <-- Adicione o /api/v1/ se for o padrão do seu projeto

/**
 * Função para remover acentos (ex: "Médio" -> "Medio") para facilitar o filtro
 */
function removerAcentos(texto) {
    return texto ? texto.normalize("NFD").replace(/[\u0300-\u036f]/g, "") : "";
}

/**
 * Carrega os dados da API ao abrir a página
 */
async function carregarDados() {
    try {
        const res = await fetch(API_URL);
        if (!res.ok) throw new Error("Erro ao buscar animais");
        
        banco = await res.json();
        
        // Renderiza apenas os animais que estão disponíveis (status === true)
        const animaisDisponiveis = banco.filter(a => a.status === true);
        renderizar(animaisDisponiveis);
        
    } catch (e) { 
        console.error(e);
        grid.innerHTML = '<p style="text-align:center; width:100%;">Erro ao conectar no servidor. Tente novamente mais tarde.</p>'; 
    }
}

/**
 * Monta os cards dos animais na tela
 */
function renderizar(lista) {
    grid.innerHTML = '';
    
    if (lista.length === 0) {
        grid.innerHTML = '<p style="text-align:center; width:100%; padding: 20px;">Nenhum amiguinho encontrado com esses filtros.</p>';
        return;
    }

    lista.forEach(a => {
        // Mapeando as variáveis exatamente como vêm do Java (Animal.java)
        const nome = a.nomeAnimal || 'Sem nome';
        const porte = a.porte?.porte || 'Não definido';
        const pelagem = a.pelagem?.pelagem || 'Não definida';
        const sexo = a.sexo?.sexo || 'Não definido'; // 'M' ou 'F'
        const imagem = a.imagem || './assets/imgs/placeholder.png';

        // Lógica visual para o ícone de sexo
        const isMacho = sexo.toUpperCase() === 'M';
        const classeSexo = isMacho ? 'M' : 'F';
        const iconeSexo = isMacho ? '♂' : '♀';
        const textoSexo = isMacho ? 'Macho' : 'Fêmea';

        const card = document.createElement('div');
        card.className = 'pet-card';
        card.innerHTML = `
            <img src="${imagem}" class="pet-img" alt="${nome}" onerror="this.onerror=null; this.src='./assets/imgs/placeholder.png';">
            <div class="pet-info">
                <div class="pet-header">
                    <h3>${nome}</h3>
                    <div class="gender-icon ${classeSexo}">${iconeSexo}</div>
                </div>
                <p><strong>Porte:</strong> ${porte}</p>
                <p><strong>Pelagem:</strong> ${pelagem}</p>
                <p><strong>Sexo:</strong> ${textoSexo}</p>
            </div>
        `;
        grid.appendChild(card);
    });
}

/**
 * Lógica do Botão "Procure um amiguinho" (Filtros)
 */
document.getElementById('btnFiltrar').addEventListener('click', () => {
    // Começa filtrando apenas os que têm status true (disponíveis para adoção)
    let filtrados = banco.filter(a => a.status === true);
    
    // 1. Filtro por Espécie (Checkboxes)
    const checkGato = document.getElementById('check-gato').checked;
    const checkCachorro = document.getElementById('check-cachorro').checked;
    
    if (checkGato || checkCachorro) {
        filtrados = filtrados.filter(a => {
            const especieAnimal = (a.especie?.especie || '').toLowerCase();
            if (checkGato && checkCachorro) return true; // Mostra ambos se os dois estiverem marcados
            if (checkGato && especieAnimal === 'gato') return true;
            if (checkCachorro && especieAnimal === 'cachorro') return true;
            return false;
        });
    }
    
    // 2. Filtro por Porte (Dropdown)
    const selectPorte = document.getElementById('filtro-porte').value;
    if (selectPorte) {
        filtrados = filtrados.filter(a => {
            // Remove acentos para garantir que "Médio" bata com "Medio"
            const porteBD = removerAcentos(a.porte?.porte).toLowerCase();
            const porteFiltro = removerAcentos(selectPorte).toLowerCase();
            return porteBD === porteFiltro;
        });
    }
    
    // 3. Filtro por Pelagem (Dropdown)
    const selectPelagem = document.getElementById('filtro-pelagem').value.toLowerCase();
    if (selectPelagem) {
        filtrados = filtrados.filter(a => {
            const pelagemBD = (a.pelagem?.pelagem || '').toLowerCase();
            return pelagemBD.includes(selectPelagem);
        });
    }
    
    // 4. Filtro por Sexo (Dropdown) - Mapeando "Macho/Femea" para "M/F"
    const selectSexo = document.getElementById('filtro-sexo').value;
    if (selectSexo) {
        const sexoEsperadoBD = selectSexo === 'Macho' ? 'm' : 'f';
        filtrados = filtrados.filter(a => {
            const sexoBD = (a.sexo?.sexo || '').toLowerCase();
            return sexoBD === sexoEsperadoBD;
        });
    }

    // Atualiza a tela com os animais filtrados
    renderizar(filtrados);
});

/**
 * Lógica do Menu Mobile (Responsividade)
 */
const btnMenu = document.getElementById("btnMenu");
const fecharMenu = document.getElementById("fecharMenu");
const menu = document.getElementById("menu");
const fundoMenu = document.getElementById("fundoMenu");

if (btnMenu) {
    btnMenu.addEventListener("click", () => {
        menu.classList.add("ativo");
        if(fundoMenu) fundoMenu.style.display = "block";
    });
}

const fechar = () => {
    menu.classList.remove("ativo");
    if(fundoMenu) fundoMenu.style.display = "none";
};

if (fecharMenu) fecharMenu.addEventListener("click", fechar);
if (fundoMenu) fundoMenu.addEventListener("click", fechar);

// Iniciar buscando os animais ao carregar a página
document.addEventListener('DOMContentLoaded', carregarDados);
