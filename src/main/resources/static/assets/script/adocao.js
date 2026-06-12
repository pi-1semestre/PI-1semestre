const grid = document.getElementById('pets-grid');
let banco = [];

// Função auxiliar para lidar com dados que podem ser objetos ou strings
function extrair(dado) { 
    return dado && typeof dado === 'object' ? (dado.nome || dado.descricao || '') : (dado || ''); 
}

async function carregarDados() {
    try {
        const res = await fetch('http://localhost:8081/api/v1/animals');
        banco = await res.json();
        // Renderiza apenas os animais com status "true" inicialmente
        renderizar(banco.filter(a => String(a.status) === 'true'));
    } catch (e) { 
        grid.innerHTML = '<p style="text-align:center; width:100%;">Erro ao conectar no servidor. Tente novamente mais tarde.</p>'; 
    }
}

function renderizar(lista) {
    grid.innerHTML = '';
    
    if (lista.length === 0) {
        grid.innerHTML = '<p style="text-align:center; width:100%; padding: 20px;">Nenhum amiguinho encontrado com esses filtros.</p>';
        return;
    }

    lista.forEach(a => {
        const card = document.createElement('div');
        card.className = 'pet-card';
        card.innerHTML = `
            <img src="${a.imagem || 'placeholder.png'}" class="pet-img" alt="${a.nome}">
            <div class="pet-info">
                <h3>${a.nome}</h3>
                <p><strong>Porte:</strong> ${extrair(a.porte)}</p>
                <p><strong>Pelagem:</strong> ${extrair(a.pelagem)}</p>
                <p><strong>Sexo:</strong> ${extrair(a.sexo)}</p>
            </div>`;
        grid.appendChild(card);
    });
}

// Botão de Busca
document.querySelector('.btn-search').addEventListener('click', () => {
    let f = banco.filter(a => String(a.status) === 'true');
    
    // Filtro por Espécie (Checkboxes)
    const g = document.getElementById('check-gato').checked;
    const c = document.getElementById('check-cachorro').checked;
    
    if (g || c) {
        f = f.filter(a => {
            const esp = extrair(a.especie).toLowerCase();
            if (g && c) return true; // Se ambos marcados, mostra tudo
            if (g) return esp === 'gato';
            if (c) return esp === 'cachorro';
            return false;
        });
    }
    
    // Filtros de Select
    const p = document.getElementById('filtro-porte').value;
    if (p) f = f.filter(a => extrair(a.porte).toLowerCase() === p.toLowerCase());
    
    const pel = document.getElementById('filtro-pelagem').value;
    if (pel) f = f.filter(a => extrair(a.pelagem).toLowerCase() === pel.toLowerCase());
    
    const s = document.getElementById('filtro-sexo').value;
    if (s) f = f.filter(a => extrair(a.sexo).toLowerCase() === s.toLowerCase());

    renderizar(f);
});

// Menu Mobile
const btnMenu = document.getElementById("btnMenu");
const fecharMenu = document.getElementById("fecharMenu");
const menu = document.getElementById("menu");
const fundoMenu = document.getElementById("fundoMenu");

btnMenu.addEventListener("click", () => {
    menu.classList.add("ativo");
    if(fundoMenu) fundoMenu.style.display = "block";
});

const fechar = () => {
    menu.classList.remove("ativo");
    if(fundoMenu) fundoMenu.style.display = "none";
};

fecharMenu.addEventListener("click", fechar);
if(fundoMenu) fundoMenu.addEventListener("click", fechar);

// Iniciar
carregarDados();