    const btnMenu = document.getElementById("btnMenu");
    const fecharMenu = document.getElementById("fecharMenu");
    const menu = document.getElementById("menu");
    const fundoMenu = document.getElementById("fundoMenu");

    function abrirMenu() {
        menu.classList.add("ativo");
        fundoMenu.classList.add("ativo");
    }

    function fecharMenuLateral() {
        menu.classList.remove("ativo");
        fundoMenu.classList.remove("ativo");
    }

    btnMenu.addEventListener("click", abrirMenu);
    fecharMenu.addEventListener("click", fecharMenuLateral);
    fundoMenu.addEventListener("click", fecharMenuLateral);

    const linksMenu = document.querySelectorAll(".menu a");

    linksMenu.forEach(function (link) {
        link.addEventListener("click", fecharMenuLateral);
    });