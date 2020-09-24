fun main() {
    //Criando os Livros da Biblioteca

    var teste = livroTeca("Vitor's Library", "05/12/1996")
    var livro1 = Livro(10, "codigo de conduta", "Carlos Azevedo", "1998", 3.0, 25.0, true)
    var livro2 = Livro(20, "batman dark knight", "Matt Reeves", "2004", 8.00, 25.0, true)
    var livro3 = Livro(30, "a lenda do zorro", "danilo xavier", "2015", 2.0, 25.0, true)
    var livro4 = Livro(40, "7 passos para o sucesso", "bill gates", "1997", 4.0, 25.0, true)
    var livro5 = Livro(50, "Como Realizar Sonhos", "Deus", "1982", 9.0, 25.0, true)

    //criando o cliente
    var cliente1 = Cliente("Vitor Soares", 52345789)

    //criando o funcionario
    var funcionario1 = Funcionario("Carlos paixao", 5452358)


//    teste.cadastrarLivro(livro1);teste.cadastrarLivro(livro2); teste.cadastrarLivro(livro3); teste.cadastrarLivro(livro4)
//    teste.cadastrarLivro(livro5)
//    teste.consultarLivro(null, 10)
//    println("O valor total do Acervo é ${teste.verificarEstoque()}")
//    teste.alugarLivro(null,20,funcionario1)
//    println(funcionario1.historicoDeAlugueis)
// teste.colecao("Carlos Azevedo")
}

open class livroTeca(var nome: String, val dataCriacao: String) {

    var listaDeLivros = mutableListOf<Livro>()
    var qtdLivros = 0
    var qtdLivrosAlugados = 0
    var qtdLivrosVendidos = 0


    fun cadastrarLivro(livro: Livro) {
        listaDeLivros.add(livro)
        qtdLivros++

    }

    fun colecao(autor: String?) {

        var colecao: MutableMap<Any, Any>? = mutableMapOf()
        listaDeLivros.forEach {
            it.autor == autor
            colecao = mutableMapOf(it.titulo to "autor")

        }

        colecao?.forEach { x, y ->
            println("colecao dos livros do autor $autor é $x")

        }

    }

    fun consultarLivro(tituloLivro: String?, codigo: Int?) {

        var encontrei = false
        listaDeLivros.forEach {
            if (it.titulo == tituloLivro || it.codigo == codigo && it.disponibilidade
            ) {
                encontrei = true
                println("O livro ${it.titulo} esta disponivel")

            }
        }
        if (!encontrei)
            println("Livro não disponivel ou não temos!")
    }

    fun alugarLivro(tituloLivro: String?, codigo: Int?, funcionario: Funcionario) {
        var encontrei = false
        listaDeLivros.forEach {
            if ((it.titulo == tituloLivro || it.codigo == codigo) && it.disponibilidade == true) {
                println("Livro Alugado Com sucesso${it.titulo}")
                it.disponibilidade = false
                encontrei = true
                qtdLivrosAlugados++
                funcionario.historicoDeAlugueis.add(it)
            }

        }
        if (!encontrei)
            println("Livro Não Disponivel para alugar")
    }


    fun venderLivro(tituloLivro: String?, codigo: Int?, cliente: Cliente) {
        var encontrei = false
        listaDeLivros.forEach {
            if ((it.titulo == tituloLivro || it.codigo == codigo) && it.disponibilidade == true) {
                println("Livro vendido Com sucesso ${it.titulo}")
                it.disponibilidade = false
                encontrei = true
                qtdLivrosAlugados++
                cliente.historicoDeCompra.add(it)
            }

        }
        if (!encontrei)
            println("Livro Não Disponivel para alugar")
    }


    fun vendaCodigo(codigo: Int) {
        listaDeLivros.forEach {
            it.codigo == codigo
            println("Venda do Livro realizada com sucesso do livro : ${it.titulo}, que possui o codigo ${it.codigo}")
            println(it)
            qtdLivrosVendidos++
        }

    }

    fun verificarEstoque(): Double {
        var acumula = 0.0
        println("A Quantidade de Livros no estoque é de ${qtdLivros}")
        println("A Quantidade de Livros vendidos é de ${qtdLivrosVendidos}")
        println("A quantidade de Livros Alugados é de ${qtdLivrosAlugados}")
        listaDeLivros.forEach {
            acumula = (acumula + it.precoDeVenda)


        }
        return acumula


    }

}


open class Livro(
    var codigo: Int, var titulo: String, var autor: String, var anoDeLancamento: String, var precoDeAluguel: Double
    , var precoDeVenda: Double, var disponibilidade: Boolean
) {

    override fun equals(other: Any?): Boolean {
        return other?.let {
            var livro = (it as Livro)
            livro?.let {
                it.codigo == codigo || it.titulo == titulo
            } ?: false
        } ?: false
    }

    override fun toString(): String {
        return "codigo=$codigo , titulo do livro='$titulo', O autor do livro é ='$autor'"
    }
}


open class Pessoa(val nome: String, val rg: Int) {

}

class Cliente(nome: String, rg: Int) : Pessoa(nome, rg) {
    var historicoDeCompra = mutableListOf<Livro>()
    var nomeDoClinte = nome
    var rgDoClinte = rg
    var historicoDeCompras = mutableListOf<Int>()


}

class Funcionario(nome: String, rg: Int) : Pessoa(nome, rg) {
    var nomeDoFuncionario = nome
    var rgDoFuncionario = rg
    var historicoDeAlugueis = mutableListOf<Livro>()
    var historicoDeVendas = mutableListOf<Int>()


}
