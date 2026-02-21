

enum class TipoEvento {
    SOCIAL,
    CORPORATIVO,
    ACADÊMICO,
    CULTURAL,
    ENTRETERIMENTO,
    RELIGIOSOS,
    ESPORTIVOS,
    FEIRA,
    CONGRESSO,
    OFICINA,
    CURSO,
    TREINAMENTO,
    AULA,
    SEMINÁRIO,
    PALESTRA,
    SHOW,
    FESTIVAL,
    EXPOSIÇÃO,
    RETIRO,
    CULTO,
    CELEBRAÇÃO,
    CAMPEONATO,
    CORRIDA,
    OUTROS
}

enum class ModalidadeEvento {
    PRESENCIAL,
    REMOTO,
    HÍBRIDO
}

fun main() {
    data class Evento(
        var nomeEvento: String,
        var descricaoEvento: String,
        var periodoRealizacao: String,
        var tipoEvento: TipoEvento,
        var modalidadeEvento: ModalidadeEvento,
        var capacidadeMaximaPessoas: Int,
        var localEvento: String,
        var estaAtivo: Boolean = false,
        var precoUnitarioIngresso: Double = 0.0,
        var estonarValorIngresso: Boolean = false,
        var taxaEstorno: Double = 0.0,

        val emailOrganizador: String,

        var ingressosVendidos: Int = 0
    )

    open class Usuario(
        var nome: String,
        var dataNascimento: String,
        var sexo: String,
        val email: String,
        var senha: String,

    )

    data class Ingresso(
        val evento: Evento,
        val emailUsuario: String,
        var cancelado: Boolean = false
    )

    class UsuarioOrganizador(
        nome: String,
        email: String,
        senha: String,
        dataNascimento: String,
        sexo: String,

        var cnpj: String? = null,
        var razaoSocial: String? = null,
        var nomeFantasia: String? = null

    ) : Usuario(nome, dataNascimento, sexo, email, senha)

    val listaDeUsuarios: MutableList<Usuario> = mutableListOf()

    val listaUsuariosInativos: MutableList<Usuario> = mutableListOf()

    val listaDeEventos: MutableList<Evento> = mutableListOf()

    val listaEventosDesativados: MutableList<Evento> = mutableListOf()

    val listaFeedEventos: MutableList<Evento> = mutableListOf()

    var listaDeIngressos: MutableList<Ingresso> = mutableListOf()



    //Variáveis: Usuários Comuns e Organizadores
    var nomeUsuario: String
    var dataNascimentoUsuario: String
    var sexoUsuario: String
    var emailUsuario: String
    var senhaUsuario: String

    //Variáveis: Apenas Organizadores cadastrados como empresas.
    var cnpjEmpresa: String? = null
    var razaoSocialEmpresa: String? = null
    var nomeFantasiaEmpresa: String? = null

    //Variáveis: Eventos
    var nomeEvento: String
    var descricaoEvento: String
    var periodoRealizacao: String
    var tipoEvento: TipoEvento
    var modalidadeEvento: ModalidadeEvento
    var capacidadeMaximaPessoas: Int
    var localEvento: String
    var estaAtivo: Boolean = false
    var precoUnitarioIngresso: Double? = null
    var estonarValorIngresso: Boolean = false
    var taxaEstorno: Double = 0.0

    //Variável: Menu
    val menu = """
    ====================================================
              SISTEMA DE GESTÃO DE EVENTOS              
    ====================================================
        
    --- GESTÃO DE USUÁRIOS ---
    1.  Cadastrar Usuário Comum
    2.  Cadastrar Usuário Organizador
    3.  Alterar Perfil
    4.  Visualizar Perfil
    5.  Inativar Usuário
    6.  Reativar Usuário
    
    --- GESTÃO DE EVENTOS ---
    7.  Cadastrar Evento
    8.  Alterar Evento
    9.  Ativar Evento
    10. Desativar Evento
    11. Listar Meus Eventos
    12. Feed de Eventos Ativos
    
    --- INGRESSOS ---
    13. Comprar Ingresso
    14. Cancelar Ingresso
    15. Listar Meus Ingressos 
        
    0.  Sair
    ====================================================
    Escolha uma opção: 
    """

    do {
        print(menu)
        val opcao = readln()

        when (opcao) {
            "1" -> {
                println("======= CADASTRO USUÁRIO =======")

                println("Digite seu nome: ")
                nomeUsuario = readln()

                print("Digite sua data de nascimento (dd/mm/aaaa): ")
                dataNascimentoUsuario = readln()

                println("Digite seu sexo: ")
                sexoUsuario = readln()

                println("Digite seu melhor email: ")
                emailUsuario = readln()

                val emailExiste = listaDeUsuarios.any { usuario ->
                    usuario.email == emailUsuario
                }

                when (emailExiste) {
                    true -> {
                        println("ERRO: Este e-mail já está sendo usado por outra pessoa!")
                        continue
                    }

                    false -> {
                        print("Digite a senha: ")
                        senhaUsuario = readln()
                    }
                }

                val novoUsuario = Usuario(
                    nome = nomeUsuario,
                    email = emailUsuario,
                    senha = senhaUsuario,
                    sexo = sexoUsuario,
                    dataNascimento = dataNascimentoUsuario
                )

                listaDeUsuarios.add(novoUsuario)

                println("Usuário criado com sucesso!")
            }

            "2" -> {
                println("======= CADASTRO ORGANIZADOR =======")

                println("Será cadastrado como empresa? (Sim ou Não)")
                val seEmpresa = readln().lowercase()

                when (seEmpresa) {
                    "sim" -> {
                        println("Digite o CNPJ da empresa: ")
                        cnpjEmpresa = readln()

                        println("Digite a razão social da empresa: ")
                        razaoSocialEmpresa = readln()

                        println("Digite o nome fantasia da empresa: ")
                        nomeFantasiaEmpresa = readln()
                    }

                    "não" -> {

                    }

                    else -> {
                        println("Error. Comando inválido!")
                        continue
                    }
                }

                println("Digite seu nome: ")
                nomeUsuario = readln()

                print("Digite sua data de nascimento (dd/mm/aaaa): ")
                dataNascimentoUsuario = readln()

                println("Digite seu sexo: ")
                sexoUsuario = readln()

                println("Digite seu melhor email: ")
                emailUsuario = readln()

                val emailExiste = listaDeUsuarios.any { usuario ->
                    usuario.email == emailUsuario
                }

                if (emailExiste) {
                    println("ERRO: Este e-mail já está sendo usado por outra pessoa!")
                    continue

                } else {
                    print("Digite a senha: ")
                    senhaUsuario = readln()

                }

                val novoUsuarioOrganizador = UsuarioOrganizador(
                    nome = nomeUsuario,
                    email = emailUsuario,
                    senha = senhaUsuario,
                    sexo = sexoUsuario,
                    dataNascimento = dataNascimentoUsuario,

                    cnpj = cnpjEmpresa,
                    razaoSocial = razaoSocialEmpresa,
                    nomeFantasia = nomeFantasiaEmpresa
                )

                listaDeUsuarios.add(novoUsuarioOrganizador)

                println("Usuário Organizador criado com sucesso!")
            }

            "3" -> {
                println("======= ALTERAR DADOS DO USUÁRIO =======")

                if (listaDeUsuarios.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    println("Cadastre um usuário para poder fazer a alteração")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val encontrarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                if (encontrarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                }

                println("Pressione ENTER vazio para manter o valor atual.")
                println("O e-mail (${encontrarUsuario.email}) NÃO pode ser alterado.")

                print("Nome atual: ${encontrarUsuario.nome}")
                println("Alterar para: ")
                nomeUsuario = readln()
                if (nomeUsuario.isNotEmpty()) {
                    encontrarUsuario.nome = nomeUsuario
                    println("Alteração feita com sucesso")
                }

                print("Sexo atual: ${encontrarUsuario.sexo}")
                println("Alterar para: ")
                sexoUsuario = readln()
                if (sexoUsuario.isNotEmpty()) {
                    encontrarUsuario.nome = sexoUsuario
                    println("Alteração feita com sucesso")
                }

                print("Data de nascimento atual: ${encontrarUsuario.dataNascimento}")
                println("Alterar para: ")
                dataNascimentoUsuario = readln()
                if (dataNascimentoUsuario.isNotEmpty()) {
                    encontrarUsuario.nome = dataNascimentoUsuario
                    println("Alteração feita com sucesso")
                }

                print("Senha atual: ${encontrarUsuario.senha}")
                senhaUsuario = readln()
                if (senhaUsuario.isNotEmpty()) {
                    encontrarUsuario.nome = senhaUsuario
                    println("Alteração feita com sucesso")
                }
            }

            "4" -> {
                println("======= VISUALIZAR PERFIL DO USUÁRIO =======")

                if (listaDeUsuarios.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                val encontrarUsuario = listaDeUsuarios.filter { it.email == validarEmail }

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                } else {
                    encontrarUsuario.forEach { usuario ->
                        println("Nome: ${usuario.nome}")

                        val separarDataNascimento = usuario.dataNascimento.split("/")
                        val diaNasc = separarDataNascimento[0].toInt()
                        val mesNasc = separarDataNascimento[1].toInt()
                        val anoNasc = separarDataNascimento[2].toInt()

                        var idade = 2026 - anoNasc

                        println("Data de Nascimento: ${usuario.dataNascimento} (Idade: %d)".format(idade))

                        println("E-mail: ${usuario.email}")

                        if (usuario is UsuarioOrganizador) {
                            println("CNPJ da empresa: ${usuario.cnpj}")
                            println("Razão social da empresa: ${usuario.razaoSocial}")
                            println("Nome fantasia da empresa: ${usuario.nomeFantasia}")
                        }
                    }
                }
            }

            "5" -> {
                println("======= INATIVAR USUÁRIO =======")

                if (listaDeUsuarios.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                }

                println("Deseja inativar o usuário '${validarUsuario.nome}'? (Sim ou Não)")
                val escolhaUsuario = readln()

                when {
                    !escolhaUsuario.equals("sim", ignoreCase = true) -> {

                    }

                    validarUsuario is UsuarioOrganizador && listaDeEventos.any { it.emailOrganizador == validarUsuario.email && it.estaAtivo } -> {
                        println("ERRO: Operação negada!")
                        println("O organizador possui eventos ativos pendentes.")
                        println("Cancele ou conclua seus eventos antes de inativar a conta.")

                    }

                    else -> {
                        listaDeUsuarios.remove(validarUsuario)
                        listaUsuariosInativos.add(validarUsuario)
                        println("Usuário inativado com sucesso!")
                    }
                }
            }

            "6" -> {
                println("======= REATIVAR USUÁRIO =======")

                if (listaUsuariosInativos.isEmpty()) {
                    println("Nenhum usuário encontrado na lista de inativos.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaUsuariosInativos.find { it.email == validarEmail && it.senha == validarSenha }

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                }

                println("Deseja inativar o usuário '${validarUsuario.nome}'? (Sim ou Não)")
                val escolhaUsuario = readln().lowercase()

                when (escolhaUsuario) {
                    "sim" -> {
                        listaUsuariosInativos.remove(validarUsuario)
                        listaDeUsuarios.add(validarUsuario)
                    }

                    "não" -> {
                        continue
                    }

                    else -> {
                        println("Comando inválido. Tente novamente.")
                    }
                }
            }

            "7" -> {
                println("======= CADASTRAR EVENTO =======")

                if (listaDeUsuarios.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    println("Cadastre um usuário, que seja organizador, antes de cadastrar um evento.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }


                when (validarUsuario) {
                    null -> {
                        println("Usuário não encontrado.")
                        println("Verifique seu e-mail e senha novamente.")
                        continue
                    }

                    is UsuarioOrganizador -> {
                        println("======= CADASTRAR EVENTO =======")

                        println("Digite o nome do evento: ")
                        nomeEvento = readln()

                        println("Digite a descrição do evento: ")
                        descricaoEvento = readln()

                        println("Digite o período de realização do evento: ")
                        periodoRealizacao = readln()

                        var i = 1
                        println("Opções disponíveis do tipo de evento: ")
                        TipoEvento.values().forEach {
                            println("$i - $it")
                            i++
                        }

                        println("Escolha o tipo de acordo com as opções disponíveis: ")

                        val tipoEventoEscolhido = readln().uppercase()
                        tipoEvento = TipoEvento.valueOf(tipoEventoEscolhido)

                        i = 1
                        println("Escolha a modalidade do evento: ")
                        ModalidadeEvento.values().forEach {
                            println("$i - $it")
                            i++
                        }

                        val tipoModalidadeEscolhida = readln().uppercase()
                        modalidadeEvento = ModalidadeEvento.valueOf(tipoModalidadeEscolhida)

                        println("Capacidade máxima do evento: ")
                        capacidadeMaximaPessoas = readln().toIntOrNull() ?: 0

                        println("Informe o local do evento: ")
                        localEvento = readln()

                        println("Informe o preço unitário do ingresso: ")
                        precoUnitarioIngresso = readln().toDoubleOrNull() ?: 0.0

                        println("O usuário poderá solicitar estorno do valor? ")
                        var liberarOpcaoEstorno = readln().lowercase()

                        when (liberarOpcaoEstorno) {
                            "sim" -> {
                                estonarValorIngresso = true
                                println("Informe a taxa de estorno: ")
                                taxaEstorno = readln().toDoubleOrNull() ?: 1.0
                            }

                            "não" -> estonarValorIngresso = false
                            else -> {
                                println("Comando Inválido. O valor padrão para o estorno será como NÃO.")
                                println("Entre na opção de editar evento e faça alteração.")
                                estonarValorIngresso = false
                            }
                        }

                        val novoEvento = Evento(
                            nomeEvento = nomeEvento,
                            descricaoEvento = descricaoEvento,
                            periodoRealizacao = periodoRealizacao,
                            tipoEvento = tipoEvento,
                            modalidadeEvento = modalidadeEvento,
                            capacidadeMaximaPessoas = capacidadeMaximaPessoas,
                            localEvento = localEvento,
                            estaAtivo = estaAtivo,
                            precoUnitarioIngresso = precoUnitarioIngresso,
                            estonarValorIngresso = estonarValorIngresso,
                            taxaEstorno = taxaEstorno,

                            emailOrganizador = validarEmail
                        )

                        listaDeEventos.add(novoEvento)
                    }

                    else -> {
                        println("Torne-se organizador para poder criar eventos.")
                        continue
                    }
                }
            }

            "8" -> {
                println("======= ALTERAR EVENTO =======")

                if (listaDeUsuarios.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    println("Cadastre um usuário, que seja organizador, antes de cadastrar um evento.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }


                when (validarUsuario) {
                    null -> {
                        println("Usuário não encontrado.")
                        println("Verifique seu e-mail e senha novamente.")
                        continue
                    }

                    is UsuarioOrganizador -> {
                    }

                    else -> {
                        println("Torne-se organizador para poder criar eventos.")
                        continue
                    }
                }
            }

            "9" -> {
                println("======= ATIVAR EVENTO =======")

                if (listaDeEventos.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    println("Cadastre um usuário, que seja organizador, antes de cadastrar um evento.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                when (validarUsuario) {
                    null -> {
                        println("Usuário não encontrado.")
                        println("Verifique seu e-mail e senha novamente.")
                        continue
                    }

                    is UsuarioOrganizador -> {
                        val listarEventos = listaDeEventos.filter { it.emailOrganizador == validarEmail }

                        var i = 1
                        listarEventos.forEach { evento ->
                            print("Evento ${i}")
                            println("Nome: ${evento.nomeEvento}")
                            println("Data: ${evento.periodoRealizacao}")
                            println("Local: ${evento.localEvento}")
                            println("Preço Unitário: ${"%.2f".format(evento.precoUnitarioIngresso)}")
                            println("Capacidade máxima de pessoas: ${evento.capacidadeMaximaPessoas}")
                            println("-------------------------")
                            i++
                        }

                        println("Digite o nome do evento que deseja ativar: ")
                        val escolhaEvento = readln()

                        val encontrarEvento = listaDeEventos.find { it.nomeEvento == escolhaEvento }

                        if (encontrarEvento != null) {
                            when (encontrarEvento.estaAtivo) {
                                true -> {
                                    println("O evento já está no feed eventos")
                                }

                                false -> {
                                    listaFeedEventos.add(encontrarEvento)
                                    encontrarEvento.estaAtivo = true
                                    println("Evento adicionado no feed com sucesso!")
                                }
                            }
                        } else {
                            println("Não pode ser vazio.")
                            continue
                        }
                    }

                    else -> {
                        println("Torne-se organizador para poder criar eventos.")
                        continue
                    }
                }
            }

            "10" -> {
                println("======= DESATIVAR EVENTO =======")

                if (listaDeEventos.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    println("Cadastre um usuário, que seja organizador, antes de cadastrar um evento.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                val listarEventos = listaDeEventos.filter { it.emailOrganizador == validarEmail }

                when (validarUsuario) {
                    null -> {
                        println("Usuário não encontrado.")
                        println("Verifique seu e-mail e senha novamente.")
                        continue
                    }

                    is UsuarioOrganizador -> {
                        var i = 1
                        listarEventos.forEach { evento ->
                            print("Evento ${i}")
                            println("Nome: ${evento.nomeEvento}")
                            println("Data: ${evento.periodoRealizacao}")
                            println("Local: ${evento.localEvento}")
                            println("Preço Unitário: ${"%.2f".format(evento.precoUnitarioIngresso)}")
                            println("Capacidade máxima de pessoas: ${evento.capacidadeMaximaPessoas}")
                            println("-------------------------")
                            i++
                        }

                        println("Digite o nome do evento que deseja ativar: ")
                        val escolhaEvento = readln()

                        val encontrarEvento = listaDeEventos.find { it.nomeEvento == escolhaEvento }

                        if (encontrarEvento != null) {
                            when (encontrarEvento.estaAtivo) {
                                true -> {
                                    println("O evento não pode ser desativado!")
                                }

                                false -> {
                                    listaEventosDesativados.add(encontrarEvento)
                                    println("Evento desativado com sucesso!")
                                }
                            }
                        }
                    }

                    else -> {
                        println("Torne-se organizador para poder criar eventos.")
                        continue
                    }
                }
            }

            "11" -> {
                println("======= LISTAR EVENTOS =======")

                if (listaDeEventos.isEmpty()) {
                    println("Nenhum evento encontrado.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                val listarEventos = listaDeEventos.filter { it.emailOrganizador == validarEmail }

                when (validarUsuario) {
                    null -> {
                        println("Usuário não encontrado.")
                        println("Verifique seu e-mail e senha novamente.")
                        continue
                    }

                    is UsuarioOrganizador -> {
                        println("--- LISTA DE EVENTOS ---")

                        listarEventos.forEach { evento ->
                            println("Nome: ${evento.nomeEvento}")
                            println("Data: ${evento.periodoRealizacao}")
                            println("Local: ${evento.localEvento}")
                            println("Preço Unitário: ${"%.2f".format(evento.precoUnitarioIngresso)}")
                            println("Capacidade máxima de pessoas: ${evento.capacidadeMaximaPessoas}")
                            println("-------------------------")
                        }
                    }

                    else -> {
                        println("Torne-se organizador para poder criar eventos e listá-los.")
                    }
                }
            }

            "12" -> {
                println("======= FEED DE EVENTOS =======")

                if (listaFeedEventos.isEmpty()) {
                    println("Nenhum evento ativo no momento!")
                    continue
                }

                listaFeedEventos.forEach { evento ->
                    println("Título: ${evento.nomeEvento}")
                    println("Descrição: ${evento.descricaoEvento}")
                    println("Data: ${evento.periodoRealizacao}")
                }
            }

            "13" -> {
                println("======= COMPRAR INGRESSO =======")

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                }

                val eventosDisponiveis = listaFeedEventos.filter { it.estaAtivo }

                if (eventosDisponiveis.isEmpty()) {
                    println("Não há eventos ativos no momento.")
                    continue
                }

                eventosDisponiveis.forEach {
                    val vagas = it.capacidadeMaximaPessoas - it.ingressosVendidos
                    println("- ${it.nomeEvento} | R$ ${"%.2f".format(it.precoUnitarioIngresso)} | Vagas: $vagas")
                }

                print("\nDigite o nome do evento que deseja: ")
                val textoBusca = readln()

                val eventoSelecionado = eventosDisponiveis.find {
                    it.nomeEvento.equals(textoBusca, ignoreCase = true)
                }

                if (eventoSelecionado == null) {
                    println("Evento não encontrado. Verifique o nome e tente novamente.")
                    continue
                }

                val vagasDisponiveis = eventoSelecionado.capacidadeMaximaPessoas - eventoSelecionado.ingressosVendidos

                if (vagasDisponiveis <= 0) {
                    println("Esgotado! Não há mais ingressos para este evento.")
                    continue
                }

                print("Quantos ingressos deseja comprar? ")
                val quantidade = readln().toIntOrNull() ?: 0

                if (quantidade <= 0) {
                    println("Quantidade inválida.")
                    continue
                }

                if (quantidade > vagasDisponiveis) {
                    println("Não é possível comprar essa quantidade. Restam apenas $vagasDisponiveis vagas.")
                    continue
                }

                val valorTotal = quantidade * eventoSelecionado.precoUnitarioIngresso

                println("Resumo: $quantidade x ${eventoSelecionado.precoUnitarioIngresso}")
                println("Total a pagar: R$ ${"%.2f".format(valorTotal)}")
                print("Confirmar compra? (Sim/Não): ")

                val confirmacao = readln()

                if (confirmacao.equals("Sim", ignoreCase = true)) {
                    eventoSelecionado.ingressosVendidos += quantidade

                    repeat(quantidade) {
                        listaDeIngressos.add(Ingresso(eventoSelecionado, validarUsuario.email))
                    }

                    println("Pagamento aprovado! Ingressos enviados para a conta de ${validarUsuario.nome}.")
                } else {
                    println("Compra cancelada.")
                }
            }

            "14" -> {
                println("======= CANCELAR INGRESSO =======")

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                }

                val meusIngressosAtivos = listaDeIngressos.filter {
                    it.emailUsuario == validarUsuario.email && !it.cancelado && it.evento.estaAtivo
                }

                if (meusIngressosAtivos.isEmpty()) {
                    println("Você não possui ingressos ativos disponíveis para cancelamento.")
                    continue
                }

                meusIngressosAtivos.forEach {
                    println("- ${it.evento.nomeEvento}")
                }

                print("\nDigite o nome do evento que deseja cancelar o ingresso: ")
                val textoBusca = readln()

                val ingressoSelecionado = meusIngressosAtivos.find {
                    it.evento.nomeEvento.equals(textoBusca, ignoreCase = true)
                }

                if (ingressoSelecionado == null) {
                    println("Ingresso não encontrado. Verifique o nome e tente novamente.")
                    continue
                }

                print("Confirmar cancelamento do ingresso para ${ingressoSelecionado.evento.nomeEvento}? (Sim/Não): ")
                val confirmacao = readln()

                if (confirmacao.equals("Sim", ignoreCase = true)) {
                    ingressoSelecionado.cancelado = true
                    println("Cancelamento aprovado! O seu ingresso foi inativado com sucesso.")
                } else {
                    println("Operação cancelada. Seu ingresso foi mantido.")
                }
            }

            "15" -> {
                println("======= LISTAR MEUS INGRESSOS =======")

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find { it.email == validarEmail && it.senha == validarSenha }

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                }

                val meusIngressos = listaDeIngressos.filter { it.emailUsuario == validarUsuario.email }

                if (meusIngressos.isEmpty()) {
                    println("Você ainda não possui nenhum ingresso registrado.")
                    continue
                }

                val ingressosAtivos = meusIngressos
                    .filter { it.evento.estaAtivo && !it.cancelado }
                    .sortedBy { it.evento.nomeEvento.lowercase() }

                val ingressosInativos = meusIngressos
                    .filter { !it.evento.estaAtivo || it.cancelado }
                    .sortedBy { it.evento.nomeEvento.lowercase() }

                val ingressosOrdenados = ingressosAtivos + ingressosInativos

                ingressosOrdenados.forEach {
                    val status = when {
                        it.cancelado -> "CANCELADO"
                        !it.evento.estaAtivo -> "INATIVO"
                        else -> "ATIVO"
                    }

                    println("Evento: ${it.evento.nomeEvento}")
                    println("Status do Ingresso: $status")
                    println("-----------------------------------")
                }
            }

            "0" -> {
                println("Saindo do sistema...")
            }

            else -> {
                println("Opção inválida. Por favor, tente novamente.")
            }
        }
    } while (opcao != "0")
}