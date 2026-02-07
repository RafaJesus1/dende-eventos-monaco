📅 dende-eventos-monaco

Sistema de gerenciamento de eventos desenvolvido em Kotlin para o desafio de Desenvolvimento Mobile Nativo. O foco deste projeto é a lógica de programação linear e estruturação de dados em memória.


🚀 Sobre o Desafio

O objetivo é criar um gerenciador de eventos via console (CLI) que opere sem persistência de dados (banco de dados) e sem interface gráfica, utilizando apenas os recursos nativos da linguagem Kotlin.

Para que o projeto seja validado, todos os colaboradores devem seguir estas diretrizes:

Lógica Linear: O fluxo deve ser contínuo. Não é permitido o uso de funções ou métodos externos ao fluxo principal para processamento de lógica.

Estrutura de Dados: É permitido (e recomendado) o uso de data classes e enums, porém, não podem conter funções.

Memória Volátil: Todos os dados (usuários, eventos, ingressos) devem ser mantidos apenas em listas/coleções durante a execução do programa.


🛠️ User Stories (Backlog de Tarefas)
A implementação deve cobrir as seguintes funcionalidades:

Gestão de Usuários
Cadastro (Comum/Organizador): Diferenciação entre pessoa física e jurídica (CNPJ/Razão Social para empresas). E-mail é o identificador único.

Perfil: Visualização detalhada (calculando idade exata em Anos, Meses e Dias) e edição de dados (exceto e-mail).

Status: Inativação e Reativação de contas (Organizadores só inativam se não tiverem eventos ativos).

Gestão de Eventos
Cadastro de Evento: Controle de datas (não retroativas), duração mínima de 30 min, tipos (Social, Cultural, etc.), modalidade e preços.

Ciclo de Vida: Ativação, Alteração e Desativação (com regra de reembolso obrigatório se houver ingressos vendidos).

Listagem: Organizadores veem seus próprios eventos; Usuários comuns veem o Feed (apenas ativos, com estoque e ordenados por data/nome).

Ingressos
Compra: Lógica de evento principal (venda casada de ingressos se houver vínculo).

Cancelamento: Estorno baseado na taxa configurada no evento.

Agenda: Listagem de ingressos comprados, priorizando eventos futuros e ativos.


💻 Como Colaborar

Para mantermos o alinhamento, siga estes passos:

Clone o repositório: git clone <url-do-repo>

Crie uma branch para sua tarefa: git checkout -b feature/nome-da-task

Siga o padrão de código: Utilize camelCase para variáveis e mantenha as data classes limpas.

Teste no Console: Como não há interface, certifique-se de que os prints no console estão legíveis para o tutor.

Dúvidas? Consultem o docs [https://docs.google.com/document/d/1j6VWL6rSWxSMtxxS90BOgNp6XhDr7xsdA8pm9WTDkrQ/edit?usp=sharing] ou o barema [https://docs.google.com/spreadsheets/d/1HqedU09n_u-5SLp5yi7pbcFoOqQbBRqqEPkNWVea7Ek/edit?usp=sharing]


Grupo:
- Rafael Jesus
- Levi Ferreira
- Luiz Gustavo
- João Gabriel
