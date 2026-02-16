
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
        var precoUnitarioIngresso: Double? = null,
        var estonarValorIngresso: Boolean = false,
        var taxaEstorno: Double = 0.0,

        val emailOrganizador: String
    )

    open class Usuario(
        var nome: String,
        var dataNascimento: String,
        var sexo: String,
        val email: String,
        var senha: String
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

    val listaDeEventos: MutableList<Evento> = mutableListOf()

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
    
    --- INGRESSOS E VENDAS ---
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
            "1" ->{
                println("======= CADASTRO USUÁRIO =======")

                println("Digite seu nome: ")
                nomeUsuario = readln()

                print("Digite sua data de nascimento (dd/mm/aaaa): ")
                dataNascimentoUsuario = readln()

                println("Digite seu sexo: ")
                sexoUsuario = readln()

                println("Digite seu melhor email: ")
                emailUsuario = readln()

                val emailExiste = listaDeUsuarios.any {
                    usuario -> usuario.email == emailUsuario
                }

                if (emailExiste) {
                    println("ERRO: Este e-mail já está sendo usado por outra pessoa!")
                    continue

                } else {
                    print("Digite a senha: ")
                    senhaUsuario = readln()

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

                when(seEmpresa) {
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

                val emailExiste = listaDeUsuarios.any {
                        usuario -> usuario.email == emailUsuario
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

                val validarUsuario = listaDeUsuarios.find{it.email == validarEmail && it.senha == validarSenha}

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue

                } else {
                  println("teste")
                }
            }

            "4" -> {
                println("======= VISUALIZAR PERFIL DO USUÁRIO =======")

                if (listaDeUsuarios.isEmpty()) {
                    println("Nenhum usuário encontrado.")
                    println("Cadastre um usuário, que seja organizador, antes de cadastrar um evento.")
                    continue
                }

                println("Digite seu e-mail: ")
                val validarEmail = readln()

                println("Digite sua senha: ")
                val validarSenha = readln()

                val validarUsuario = listaDeUsuarios.find {it.email == validarEmail && it.senha == validarSenha}

                val encontrarUsuario = listaDeUsuarios.filter {it.email == validarEmail}

                if (validarUsuario == null) {
                    println("Usuário não encontrado.")
                    println("Verifique seu e-mail e senha novamente.")
                    continue
                } else {
                    encontrarUsuario.forEach {
                        usuario ->
                        println("Nome: ${usuario.nome}")

                        val separarDataNascimento = usuario.dataNascimento.split("/")
                        val diaNasc = separarDataNascimento[0].toInt()
                        val mesNasc = separarDataNascimento[1].toInt()
                        val anoNasc = separarDataNascimento[2].toInt()

                        var idade = 2026 - anoNasc

                        println("Data de Nascimento: ${usuario.dataNascimento} (Idade: %d)".format(idade))

                        println("E-mail: ${usuario.email}")

                        if(usuario is UsuarioOrganizador) {
                            println("CNPJ da empresa: ${usuario.cnpj}")
                            println("Razão social da empresa: ${usuario.razaoSocial}")
                            println("Nome fantasia da empresa: ${usuario.nomeFantasia}")
                        }
                    }
                }
            }

            "5" -> {
                println("Inativar Usuário")
            }

            "6" -> {
                println("Reativar Usuário")
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

                val validarUsuario = listaDeUsuarios.find{it.email == validarEmail && it.senha == validarSenha}

                when(validarUsuario) {
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
                        TipoEvento.values().forEach{
                            println("$i - $it")
                            i++
                        }

                        println("Escolha o tipo de acordo com as opções disponíveis: ")

                        val tipoEventoEscolhido = readln().uppercase()
                        tipoEvento = TipoEvento.valueOf(tipoEventoEscolhido)

                        i = 1
                        println("Escolha a modalidade do evento: ")
                        ModalidadeEvento.values().forEach{
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

                        when(liberarOpcaoEstorno) {
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
                println("Alterar Evento")
            }

            "9" -> {
                println("Ativar Evento")
            }

            "10" -> {
                println("Desativar Evento")
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

                val validarUsuario = listaDeUsuarios.find{it.email == validarEmail && it.senha == validarSenha}

                val listarEventos = listaDeEventos.filter{it.emailOrganizador == validarEmail}

                when(validarUsuario) {
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
                println("Feed de Eventos Ativos")
            }

            "13" -> {
                println("Comprar Ingresso")
            }

            "14" -> {
                println("Cancelar Ingresso")
            }

            "15" -> {
                println("Listar Meus Ingressos")
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