import kotlin.math.sqrt
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.PI

//conversão dos algoritmos 121 a 160 para Kotlin.
// Execute o programa e informe o número do algoritmo que deseja testar.

fun lerInt(mensagem: String): Int {
    print(mensagem)
    return readLine()!!.trim().toInt()
}

fun lerDouble(mensagem: String): Double {
    print(mensagem)
    return readLine()!!.trim().replace(",", ".").toDouble()
}

fun lerTexto(mensagem: String): String {
    print(mensagem)
    return readLine()!!.trim()
}

fun dinheiro(valor: Double) = "R$ %.2f".format(valor)


// 121 — Ler cinco números inteiros diferentes e identificar o maior e o menor.
fun algoritmo121() {
    val numeros = IntArray(5) { lerInt("Digite o ${it + 1}º número: ") }
    if (numeros.toSet().size != 5) {
        println("Os valores devem ser diferentes.")
    } else {
        println("Maior = ${numeros.maxOrNull()}")
        println("Menor = ${numeros.minOrNull()}")
    }
}

// 122 — Verificar se três números podem ser lados de um triângulo.
fun algoritmo122() {
    val a = lerDouble("Digite o 1º lado: ")
    val b = lerDouble("Digite o 2º lado: ")
    val c = lerDouble("Digite o 3º lado: ")
    if (a > 0 && b > 0 && c > 0 && a < b + c && b < a + c && c < a + b)
        println("Podem ser lados de um triângulo.")
    else println("Não podem ser lados de um triângulo.")
}

// 123 — Classificar um triângulo segundo os lados.
fun algoritmo123() {
    val a = lerDouble("Digite o 1º lado: ")
    val b = lerDouble("Digite o 2º lado: ")
    val c = lerDouble("Digite o 3º lado: ")
    if (a <= 0 || b <= 0 || c <= 0 || a >= b + c || b >= a + c || c >= a + b) {
        println("As medidas não formam um triângulo.")
    } else {
        when {
            a == b && b == c -> println("Triângulo equilátero.")
            a == b || a == c || b == c -> println("Triângulo isósceles.")
            else -> println("Triângulo escaleno.")
        }
    }
}

// 124 — Classificar um triângulo segundo os ângulos.
fun algoritmo124() {
    val lados = doubleArrayOf(
        lerDouble("Digite o 1º lado: "),
        lerDouble("Digite o 2º lado: "),
        lerDouble("Digite o 3º lado: ")
    )
    lados.sort()
    val (a, b, c) = lados
    if (a <= 0 || a + b <= c) {
        println("As medidas não formam um triângulo.")
    } else {
        val maior = c * c
        val soma = a * a + b * b
        when {
            kotlin.math.abs(maior - soma) < 1e-9 -> println("Triângulo retângulo.")
            maior > soma -> println("Triângulo obtusângulo.")
            else -> println("Triângulo acutângulo.")
        }
    }
}

// 125 — Informar se a pessoa é maior de idade, menor de idade ou maior de 65 anos.
fun algoritmo125() {
    val idade = lerInt("Digite sua idade: ")
    when {
        idade >= 65 -> println("Maior de 65 anos.")
        idade >= 18 -> println("Maior de idade.")
        else -> println("Menor de idade.")
    }
}

// 126 — Identificar valores específicos e o intervalo de 500 a 1000.
fun algoritmo126() {
    val x = lerDouble("Digite um valor: ")
    when {
        x == 5.0 -> println("O número é 5.")
        x == 200.0 -> println("O número é 200.")
        x == 400.0 -> println("O número é 400.")
        x in 500.0..1000.0 -> println("Intervalo 500-1000.")
        else -> println("Fora dos escopos anteriores.")
    }
}

// 127 — Calcular média e informar a situação do aluno.
fun algoritmo127() {
    val nome = lerTexto("Digite o nome do aluno: ")
    val nota1 = lerDouble("Digite a nota da PR1: ")
    val nota2 = lerDouble("Digite a nota da PR2: ")
    val media = (nota1 + nota2) / 2
    val situacao = when {
        media >= 7.0 -> "Aprovado"
        media < 3.0 -> "Reprovado"
        else -> "Em prova final"
    }
    println("Aluno: $nome | PR1: $nota1 | PR2: $nota2 | Média: %.2f | $situacao".format(media))
}

// 128 — Identificar se um verbo está no infinitivo e sua conjugação.
fun algoritmo128() {
    val verbo = lerTexto("Digite um verbo: ")
    if (!verbo.endsWith("r", ignoreCase = true) || verbo.length < 3) {
        println("Não é um verbo no infinitivo.")
    } else {
        when (verbo[verbo.length - 2].lowercaseChar()) {
            'a' -> println("1ª conjugação.")
            'e', 'o' -> println("2ª conjugação.")
            'i' -> println("3ª conjugação.")
            else -> println("Não existe verbo com essa terminação.")
        }
    }
}

// 129 — Calcular o desconto do INSS conforme as faixas do exercício.
fun algoritmo129() {
    val salario = lerDouble("Digite o salário: ")
    val desconto = when {
        salario <= 600.0 -> 0.0
        salario <= 1200.0 -> salario * 0.20
        salario <= 2000.0 -> salario * 0.25
        else -> salario * 0.30
    }
    println("Desconto: ${dinheiro(desconto)}")
}

// 130 — Calcular o preço de venda com lucro de 45% ou 30%.
fun algoritmo130() {
    val valor = lerDouble("Digite o valor de compra do produto: ")
    val venda = if (valor < 20.0) valor * 1.45 else valor * 1.30
    println("Valor de venda: ${dinheiro(venda)}")
}

// 131 — Determinar a sala do aluno pela inicial do nome.
fun algoritmo131() {
    val nome = lerTexto("Digite o nome do aluno: ")
    val inicial = nome.firstOrNull()?.uppercaseChar()
    val sala = when (inicial) {
        in 'A'..'K' -> "101"
        in 'L'..'N' -> "102"
        in 'O'..'Z' -> "103"
        else -> null
    }
    if (sala == null) println("Nome inválido.")
    else println("$nome — sala: $sala, bloco F.")
}

// 132 — Converter reais para a moeda escolhida pelo usuário.
fun algoritmo132() {
    val reais = lerDouble("Digite o valor em reais: ")
    val moeda = lerTexto("Escolha f-franco, l-libra, d-dólar ou m-marco: ").lowercase()
    val nome = when (moeda) {
        "f" -> "francos suíços"
        "l" -> "libras esterlinas"
        "d" -> "dólares"
        "m" -> "marcos alemães"
        else -> null
    }
    if (nome == null) {
        println("Moeda desconhecida.")
    } else {
        val cotacao = lerDouble("Digite o valor de 1 $nome em reais: ")
        if (cotacao <= 0) println("Cotação inválida.")
        else println("Total de $nome: %.2f".format(reais / cotacao))
    }
}

// 133 — Calcular o peso ideal conforme altura e sexo.
fun algoritmo133() {
    val altura = lerDouble("Digite a altura em metros: ")
    val sexo = lerTexto("Digite o sexo (M/F): ")
    val peso = if (sexo.equals("M", true)) 72.7 * altura - 58
               else 62.1 * altura - 44.7
    println("Seu peso ideal é: %.2f kg".format(peso))
}

// 134 — Determinar a categoria de natação pela idade.
fun algoritmo134() {
    val idade = lerInt("Digite a idade do nadador: ")
    val categoria = when {
        idade < 5 -> "Não existe categoria para essa idade."
        idade <= 7 -> "Infantil A"
        idade <= 10 -> "Infantil B"
        idade <= 13 -> "Juvenil A"
        idade <= 17 -> "Juvenil B"
        else -> "Sênior"
    }
    println("Categoria: $categoria")
}

// 135 — Informar a classe eleitoral.
fun algoritmo135() {
    val idade = lerInt("Digite a idade: ")
    val classe = when {
        idade < 16 -> "Não eleitor."
        idade < 18 || idade > 65 -> "Eleitor facultativo."
        else -> "Eleitor obrigatório."
    }
    println(classe)
}

// 136 — Calcular mensalidade do plano de saúde conforme a idade.
fun algoritmo136() {
    val nome = lerTexto("Digite o nome: ")
    val idade = lerInt("Digite a idade: ")
    val valor = when {
        idade <= 10 -> 30
        idade <= 29 -> 60
        idade <= 45 -> 120
        idade <= 59 -> 150
        idade <= 65 -> 250
        else -> 400
    }
    println("$nome pagará ${dinheiro(valor.toDouble())}.")
}

// 137 — Resolver equação do segundo grau.
fun algoritmo137() {
    val a = lerDouble("Digite o valor de a: ")
    val b = lerDouble("Digite o valor de b: ")
    val c = lerDouble("Digite o valor de c: ")
    if (a == 0.0) {
        println("Não é equação do segundo grau.")
    } else {
        val delta = b * b - 4 * a * c
        if (delta < 0) println("Não há raízes reais.")
        else {
            val raizDelta = sqrt(delta)
            val x1 = (-b + raizDelta) / (2 * a)
            val x2 = (-b - raizDelta) / (2 * a)
            println("X1 = $x1")
            println("X2 = $x2")
        }
    }
}

// 138 — Escrever o mês correspondente ao número informado.
fun algoritmo138() {
    val numero = lerInt("Digite um número de 1 a 12: ")
    val meses = listOf(
        "janeiro", "fevereiro", "março", "abril", "maio", "junho",
        "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"
    )
    if (numero in 1..12) println(meses[numero - 1])
    else println("Não existe mês correspondente a esse número.")
}

// 139 — Verificar se haverá segundo turno na eleição municipal.
fun algoritmo139() {
    val municipio = lerTexto("Digite o nome do município: ")
    val eleitores = lerInt("Digite o número de eleitores aptos: ")
    val votos = lerInt("Digite os votos do candidato mais votado: ")
    if (eleitores > 20000 && votos <= eleitores / 2)
        println("$municipio terá segundo turno.")
    else println("$municipio não terá segundo turno.")
}

// 140 — Aplicar desconto de 30% conforme a inicial do nome.
fun algoritmo140() {
    val nome = lerTexto("Digite o primeiro nome: ")
    val conta = lerDouble("Digite o valor da conta: ")
    val inicial = nome.firstOrNull()?.uppercaseChar()
    if (inicial in listOf('A', 'O', 'M', 'S')) {
        println("$nome — valor com desconto: ${dinheiro(conta * 0.70)}")
    } else {
        println("Que pena. Nesta semana o desconto não é para seu nome; mas continue nos prestigiando que sua vez chegará.")
    }
}

// 141 — Ordenar três finalistas por pontuação, em ordem decrescente.
fun algoritmo141() {
    val finalistas = mutableListOf<Pair<String, Int>>()
    repeat(3) { i ->
        val nome = lerTexto("Digite o nome do ${i + 1}º finalista: ")
        val pontos = lerInt("Digite os pontos: ")
        finalistas.add(nome to pontos)
    }
    finalistas.sortedByDescending { it.second }.forEachIndexed { i, item ->
        val colocacao = listOf("Vencedor", "Segundo colocado", "Terceiro colocado")[i]
        println("$colocacao: ${item.first} — ${item.second} pontos")
    }
}

// 142 — Ordenar pontos de três arqueiros e verificar a classificação da equipe.
fun algoritmo142() {
    val pontos = IntArray(3) { lerInt("Digite os pontos do ${it + 1}º jogador: ") }
    pontos.sort()
    println("Pontuações em ordem decrescente: ${pontos.reversed().joinToString(", ")}")
    val soma = pontos.sum()
    if (soma > 100) println("Média = %.2f".format(soma / 3.0))
    else println("Equipe desclassificada!")
}

// 143 — Verificar letras centrais: vogal para palavra ímpar, rr ou ss para par.
fun algoritmo143() {
    val palavra = lerTexto("Digite uma palavra: ")
    if (palavra.isEmpty()) {
        println("Palavra vazia.")
    } else if (palavra.length % 2 == 0) {
        val meio = palavra.length / 2
        val centrais = palavra.substring(meio - 1, meio + 1)
        if (centrais == "rr" || centrais == "ss") println("É rr ou ss.")
        else println("Não é rr ou ss.")
    } else {
        val central = palavra[palavra.length / 2].lowercaseChar()
        if (central in "aeiou") println("É vogal.")
        else println("Não é vogal.")
    }
}

// 144 — Calcular crédito especial conforme o saldo médio.
fun algoritmo144() {
    val saldo = lerDouble("Digite o saldo médio: ")
    val credito = when {
        saldo <= 500 -> 0.0
        saldo <= 1000 -> saldo * 0.30
        saldo <= 3000 -> saldo * 0.40
        else -> saldo * 0.50
    }
    if (credito > 0) println("Saldo médio: ${dinheiro(saldo)} | Crédito: ${dinheiro(credito)}")
    else println("Como seu saldo médio era ${dinheiro(saldo)}, você não terá crédito.")
}

// 145 — Emitir recibo de empréstimo de livro.
fun algoritmo145() {
    val livro = lerTexto("Digite o nome do livro: ")
    val tipo = lerTexto("Digite o tipo de usuário (professor/aluno): ")
    val dias = when (tipo.lowercase()) {
        "professor" -> 10
        "aluno" -> 3
        else -> 0
    }
    if (dias == 0) println("Tipo de usuário desconhecido.")
    else {
        println("Nome do livro: $livro")
        println("Tipo de usuário: $tipo")
        println("Total de dias: $dias")
    }
}

// 146 — Estimar o consumo de combustível conforme o tipo de carro.
fun algoritmo146() {
    val tipo = lerTexto("Digite o tipo do carro (A/B/C): ").uppercase()
    val percurso = lerDouble("Digite o percurso em quilômetros: ")
    val rendimento = when (tipo) {
        "C" -> 12.0
        "B" -> 9.0
        "A" -> 8.0
        else -> 0.0
    }
    if (rendimento == 0.0) println("Modelo inexistente.")
    else println("Consumo estimado: %.2f litros".format(percurso / rendimento))
}

// 147 — Somar as calorias do prato, sobremesa e bebida escolhidos.
fun algoritmo147() {
    println("Prato: 1-Vegetariano(180) 2-Peixe(230) 3-Frango(250) 4-Carne(350)")
    val prato = lerInt("Escolha o prato: ")
    println("Sobremesa: 1-Abacaxi(75) 2-Sorvete diet(110) 3-Mousse diet(170) 4-Mousse chocolate(200)")
    val sobremesa = lerInt("Escolha a sobremesa: ")
    println("Bebida: 1-Chá(20) 2-Suco laranja(70) 3-Suco melão(100) 4-Refrigerante diet(65)")
    val bebida = lerInt("Escolha a bebida: ")
    val pratos = mapOf(1 to 180, 2 to 230, 3 to 250, 4 to 350)
    val sobremesas = mapOf(1 to 75, 2 to 110, 3 to 170, 4 to 200)
    val bebidas = mapOf(1 to 20, 2 to 70, 3 to 100, 4 to 65)
    if (prato !in pratos || sobremesa !in sobremesas || bebida !in bebidas)
        println("Uma ou mais opções são inválidas.")
    else println("Total de calorias: ${pratos.getValue(prato) + sobremesas.getValue(sobremesa) + bebidas.getValue(bebida)}")
}

// 148 — Calcular passagem conforme destino e tipo de viagem.
fun algoritmo148() {
    println("Destino: 1-Norte 2-Nordeste 3-Centro-Oeste 4-Sul")
    val destino = lerInt("Escolha o destino: ")
    val tipo = lerInt("Viagem: 1-Ida 2-Ida e volta: ")
    val precos = mapOf(
        1 to (500.0 to 950.0), 2 to (350.0 to 650.0),
        3 to (350.0 to 600.0), 4 to (300.0 to 550.0)
    )
    val regioes = mapOf(1 to "Norte", 2 to "Nordeste", 3 to "Centro-Oeste", 4 to "Sul")
    val valores = precos[destino]
    if (valores == null || tipo !in 1..2) println("Opção inexistente.")
    else {
        val valor = if (tipo == 1) valores.first else valores.second
        println("Passagem para a região ${regioes[destino]}: ${dinheiro(valor)}")
    }
}

// 149 — Calcular valor de venda com lucro conforme valor da compra.
fun algoritmo149() {
    val nome = lerTexto("Digite o nome do produto: ")
    val compra = lerDouble("Digite o valor da compra: ")
    val fator = when {
        compra < 10 -> 1.70
        compra < 30 -> 1.50
        compra < 50 -> 1.40
        else -> 1.30
    }
    println("$nome será vendido por ${dinheiro(compra * fator)}")
}

// 150 — Calcular seno em quadrantes pares e cosseno em quadrantes ímpares.
fun algoritmo150() {
    val angulo = lerDouble("Digite o ângulo em graus: ")
    val radianos = angulo * PI / 180.0
    val normalizado = ((angulo % 360) + 360) % 360
    val quadrantePar = (normalizado > 90 && normalizado <= 180) ||
                       (normalizado > 270 && normalizado < 360)
    if (quadrantePar) println("Seno: ${sin(radianos)}")
    else println("Cosseno: ${cos(radianos)}")
}

// 151 — Calcular IMC e apresentar a faixa de risco.
fun algoritmo151() {
    val peso = lerDouble("Digite o peso em kg: ")
    val altura = lerDouble("Digite a altura em metros: ")
    if (altura <= 0) {
        println("Altura inválida.")
        return
    }
    val imc = peso / (altura * altura)
    val faixa = when {
        imc < 20 -> "Abaixo do peso"
        imc <= 25 -> "Normal"
        imc <= 30 -> "Excesso de peso"
        imc <= 35 -> "Obesidade"
        else -> "Obesidade mórbida"
    }
    println("IMC: %.2f — $faixa".format(imc))
}

// 152 — Calcular a quantidade de gotas do medicamento conforme idade e peso.
// Conversão do enunciado: 500 mg por ml e 20 gotas por ml = 25 mg por gota.
fun algoritmo152() {
    val peso = lerDouble("Digite o peso em kg: ")
    val idade = lerInt("Digite a idade: ")
    if (peso < 5) {
        println("Não pode tomar o remédio porque não tem peso suficiente. Consulte um médico.")
        return
    }
    val doseMg = if (idade >= 12) {
        if (peso >= 60) 1000.0 else 875.0
    } else {
        when {
            peso <= 9 -> 125.0
            peso <= 16 -> 250.0
            peso <= 24 -> 375.0
            peso <= 30 -> 500.0
            else -> 750.0
        }
    }
    println("Tomar %.0f gotas por dose.".format(doseMg / 25.0))
}

// 153 — Informar quais grupos industriais devem suspender atividades.
fun algoritmo153() {
    val indice = lerDouble("Digite o índice de poluição: ")
    when {
        indice >= 0.5 -> println("Suspender atividades das indústrias dos grupos 1, 2 e 3.")
        indice >= 0.4 -> println("Suspender atividades das indústrias dos grupos 1 e 2.")
        indice >= 0.3 -> println("Suspender atividades das indústrias do grupo 1.")
        else -> println("Índice de poluição aceitável para todos os grupos.")
    }
}

// 154 — Informar o mês de renovação do emplacamento pelo último dígito da placa.
fun algoritmo154() {
    val placa = lerTexto("Digite a placa do carro: ")
    val ultimo = placa.lastOrNull()
    val mes = when (ultimo) {
        '1' -> "janeiro"; '2' -> "fevereiro"; '3' -> "março"
        '4' -> "abril"; '5' -> "maio"; '6' -> "junho"
        '7' -> "julho"; '8' -> "agosto"; '9' -> "setembro"
        '0' -> "outubro"
        else -> null
    }
    if (mes == null) println("Opção inválida.")
    else println("Renovação em $mes.")
}

// 155 — Formar nova palavra conforme a primeira letra.
fun algoritmo155() {
    val palavra = lerTexto("Digite uma palavra: ")
    if (palavra.isEmpty()) {
        println("Palavra vazia.")
        return
    }
    val nova = if (palavra.first().lowercaseChar() == 'l' ||
                  palavra.first().lowercaseChar() == 'd') {
        if (palavra.length >= 2) palavra.take(2) + palavra.last() else palavra
    } else palavra.drop(1)
    println("Palavra: $nova")
}

// 156 — Validar data informada em dia, mês e ano separados.
fun algoritmo156() {
    val dia = lerInt("Digite o dia: ")
    val mes = lerInt("Digite o mês: ")
    val ano = lerInt("Digite o ano: ")
    val bissexto = ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)
    val maxDia = when (mes) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (bissexto) 29 else 28
        else -> 0
    }
    println(if (ano >= 1 && dia in 1..maxDia) "DATA VÁLIDA" else "DATA INVÁLIDA")
}

// 157 — Validar data no formato ddmmaaaa.
fun algoritmo157() {
    val data = lerTexto("Digite a data no formato ddmmaaaa: ")
    if (data.length != 8 || !data.all { it.isDigit() }) {
        println("DATA INVÁLIDA")
        return
    }
    val dia = data.substring(0, 2).toInt()
    val mes = data.substring(2, 4).toInt()
    val ano = data.substring(4, 8).toInt()
    val bissexto = ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)
    val maxDia = when (mes) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (bissexto) 29 else 28
        else -> 0
    }
    println(if (ano >= 1 && dia in 1..maxDia) "DATA VÁLIDA" else "DATA INVÁLIDA")
}

// 158 — Calcular f(x) = 8 / (2 - x).
fun algoritmo158() {
    val x = lerDouble("Digite o valor de x: ")
    if (x == 2.0) println("Não pode ser feita: divisão por zero.")
    else println("f(x) = ${8.0 / (2.0 - x)}")
}

// 159 — Calcular f(x) = (5x + 3) / raiz(x² - 16), para x > 4 ou x < -4.
fun algoritmo159() {
    val x = lerDouble("Digite o valor de x: ")
    if (x > 4 || x < -4) {
        val resultado = (5 * x + 3) / sqrt(x * x - 16)
        println("f(x) = $resultado")
    } else println("Não pode ser feita.")
}

// 160 — Calcular a função por partes.
fun algoritmo160() {
    val x = lerDouble("Digite o valor de x: ")
    val y = when {
        x <= 1 -> 1.0
        x <= 2 -> 2.0
        x <= 3 -> x * x
        else -> x * x * x
    }
    println("Valor de f(x): $y")
}

fun main() {
    print("Digite o número do algoritmo que deseja executar (121 a 160): ")
    val numero = readLine()!!.trim().toIntOrNull()
    when (numero) {
        121 -> algoritmo121(); 122 -> algoritmo122(); 123 -> algoritmo123()
        124 -> algoritmo124(); 125 -> algoritmo125(); 126 -> algoritmo126()
        127 -> algoritmo127(); 128 -> algoritmo128(); 129 -> algoritmo129()
        130 -> algoritmo130(); 131 -> algoritmo131(); 132 -> algoritmo132()
        133 -> algoritmo133(); 134 -> algoritmo134(); 135 -> algoritmo135()
        136 -> algoritmo136(); 137 -> algoritmo137(); 138 -> algoritmo138()
        139 -> algoritmo139(); 140 -> algoritmo140(); 141 -> algoritmo141()
        142 -> algoritmo142(); 143 -> algoritmo143(); 144 -> algoritmo144()
        145 -> algoritmo145(); 146 -> algoritmo146(); 147 -> algoritmo147()
        148 -> algoritmo148(); 149 -> algoritmo149(); 150 -> algoritmo150()
        151 -> algoritmo151(); 152 -> algoritmo152(); 153 -> algoritmo153()
        154 -> algoritmo154(); 155 -> algoritmo155(); 156 -> algoritmo156()
        157 -> algoritmo157(); 158 -> algoritmo158(); 159 -> algoritmo159()
        160 -> algoritmo160()
        else -> println("Número inválido. Escolha um algoritmo de 121 a 160.")
    }
}
