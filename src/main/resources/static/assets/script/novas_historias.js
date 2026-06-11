// --- Mecânica Interativa do Menu Mobile (Blindada) ---
function gerenciarMenuMobile() {
    const btnMenu = document.getElementById("btnMenu");
    const fecharMenu = document.getElementById("fecharMenu");
    const menu = document.getElementById("menu");
    const fundoMenu = document.getElementById("fundoMenu");

    // Verifica se achou os elementos (mostra no console F12 se faltar algo)
    if (!btnMenu || !fecharMenu || !menu || !fundoMenu) {
        console.error("❌ ERRO: Elementos do menu não encontrados no HTML!");
        return;
    } else {
        console.log("✅ Menu Mobile configurado com sucesso.");
    }

    // Ação de ABRIR o menu
    btnMenu.addEventListener("click", function(e) {
        e.preventDefault();
        menu.classList.add("ativo");
        fundoMenu.classList.add("ativo");
    });

    // Função única para FECHAR o menu
    function fecharPainel(e) {
        e.preventDefault();
        menu.classList.remove("ativo");
        fundoMenu.classList.remove("ativo");
    }

    // Ação de FECHAR (clicando no X ou no fundo escuro)
    fecharMenu.addEventListener("click", fecharPainel);
    fundoMenu.addEventListener("click", fecharPainel);
}

// --- Inicializador Seguro (Espera a página carregar 100%) ---
document.addEventListener("DOMContentLoaded", function() {
    console.log("🚀 Página carregada. Iniciando scripts...");
    
    // 1. Inicia o menu
    gerenciarMenuMobile();
    
    // 2. Inicia o botão do filtro
    inicializarFiltroToggle();
    
    // 3. Carrega os cards do banco
    carregarDadosDoPainel();
});