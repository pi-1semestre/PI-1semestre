# Fluxograma do Sistema - Apoio Pet (PI 1º Semestre)

```mermaid
flowchart TD
    A[Usuário acessa o sistema] --> B{Tipo de usuário}
    B -->|Visitante| C[Páginas públicas]
    B -->|Administrador| D[Login]

    C --> C1[Início, Sobre Nós, Doações, Histórias]
    C --> C2[Adoção]
    C --> C3[Denúncias / formulário]
    C --> C4[Animais cadastrados]

    D --> D1[login_adm.html + login.js]
    D1 --> D2{/api/v1/administradores/login}
    D2 -->|ok| D3[cadastro_animal.html]
    D2 -->|erro| D4[Alerta de credencial inválida]

    D3 --> D5[Preenche formulário do animal]
    D5 --> D6[POST /api/v1/animals]
    D6 --> D7[(MySQL tb_animal)]

    C2 --> E1[GET /api/v1/animals]
    E1 --> E2[Filtros opcionais: espécie, porte, pelagem, sexo, status, nome]
    E2 --> E3[Renderiza cards de animais]

    C3 --> F1[POST /api/v1/mensagens]
    F1 --> F2[(MySQL tb_mensagem)]

    C4 --> G1[GET /api/v1/animals]
    G1 --> G2[Renderiza lista completa]
```



## Legenda

- **Retângulos:** Entidades JPA / Controllers / Services / Repositories
- **Setas sólidas:** Chamadas entre camadas
- **Setas tracejadas:** Relacionamentos JPA entre entidades
- **Caixas agrupadas:** Contexto lógico por módulo

## Descrição do Fluxo

1. **Visitante** acessa páginas públicas e navega por conteúdo institucional e adoção.
2. **Administrador** acessa `login_adm.html` e autentica via `login.js` consumindo `POST /api/v1/administradores/login`.
3. Após login, o admin pode cadastrar animais (`cadastro_animal.html`) enviando dados para `POST /api/v1/animals`.
4. A página de adoção consulta animais via `GET /api/v1/animals` com filtros opcionais.
5. Denúncias/relatos são enviados para `POST /api/v1/mensagens` e seus emails para `POST /api/v1/emails`.
6. Todas as requisições seguem o padrão REST: **Controller → Service → Repository → MySQL**.
