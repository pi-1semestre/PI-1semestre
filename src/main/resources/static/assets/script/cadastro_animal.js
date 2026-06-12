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

    const form = document.getElementById('form-cadastro-pet');
    
    if (form) {
        form.addEventListener('submit', async function(event) {
            // Evita que a página recarregue do modo tradicional
            event.preventDefault(); 

            // 1. Pega os valores dos campos
            const nomeInput = document.getElementById('nome_animal').value;
            const idadeInput = document.getElementById('idade').value;
            const imagemInput = document.getElementById('imagem').value;
            
            // Valores dos Dropdowns
            const sexoInput = document.getElementById('sexo').value; // 'M' ou 'F'
            const porteInput = document.getElementById('porte').value; 
            const especieInput = document.getElementById('especie').value; 
            const pelagemInput = document.getElementById('pelagem').value; 
            
            // Status: Convertendo a string do select para um boolean (true/false)
            // Se escolher "Disponível", envia true.
            const statusInput = document.getElementById('status').value === 'Disponível';

            // 2. Mapeamento dos IDs do Banco de Dados
            // COMO O JAVA ESPERA OBJETOS, PRECISAMOS ENVIAR O ID CORRESPONDENTE A CADA ESCOLHA.
            // Atenção: Ajuste esses IDs numéricos de acordo com o que está cadastrado nas suas tabelas do banco!
            
            // Mapeando Espécie (ex: 1 = Cachorro, 2 = Gato)
            let especieId = especieInput === 'Cachorro' ? 1 : 2;

            // Mapeando Sexo (ex: 1 = Macho, 2 = Fêmea)
            let sexoId = sexoInput === 'M' ? 1 : 2;

            // Mapeando Porte (ex: 1=Mini, 2=Pequeno, 3=Médio, 4=Grande, 5=Gigante)
            let porteId;
            if (porteInput === 'Pequeno') porteId = 2;
            else if (porteInput === 'Médio') porteId = 3;
            else if (porteInput === 'Grande') porteId = 4;
            else porteId = 3; // Padrão se não achar

            // Mapeando Pelagem (É um texto livre no input, mas o Java espera um ID.
            // Para não quebrar, vamos enviar um ID fixo por enquanto. O ideal seria ter um <select> de pelagens igual ao do filtro.)
            let pelagemId = 1; // Substitua por um ID válido da sua tabela tb_pelagem (ex: 1 = Branco)

            // Administrador (O Java exige que seja passado. Vamos usar o ID 1 provisoriamente)
            let adminId = 1;

            // 3. Monta o objeto final EXATAMENTE como a classe Animal.java espera
            const animalData = {
                nomeAnimal: nomeInput,
                idade: idadeInput,
                status: statusInput,
                imagem: imagemInput,
                
                // Entidades Relacionadas (O Spring Boot consegue associar apenas passando o ID)
                especie: { idEspecie: especieId },
                porte: { idPorte: porteId },
                pelagem: { idPelagem: pelagemId },
                sexo: { idSexo: sexoId },
                administrador: { idAdministrador: adminId }
            };

            console.log('Tentando cadastrar o seguinte animal:', JSON.stringify(animalData, null, 2));

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
