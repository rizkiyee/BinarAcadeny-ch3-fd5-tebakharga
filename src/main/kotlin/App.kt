import generator.ItemPriceGenerator
import model.ItemPrice
import kotlin.math.absoluteValue
import kotlin.system.exitProcess


class App {
    private val items = ItemPriceGenerator.getItems()
    private lateinit var selectedItemPrice: ItemPrice
    private var inputPlaerOne = 0
    private var inputPlaerTwo = 0

    companion object {
        const val PRICE_EQUAL = 0
        const val PRICE_MORE_THAN = 1
        const val PRICE_LESS_THAN = 2

        @JvmStatic
        fun main(args: Array<String>) {
            App().runGame()
        }
    }

    fun runGame() {
        println("================")
        println("Game Tebak Harga")
        println("================")
        println("Start Game? (Y/N)")
        if (readLine().equals("Y", ignoreCase = true)) {
            startGame()
        } else {
            println("Game Closed")
            exitProcess(0)
        }
    }

    private fun startGame() {
        //mengambil salah satu item dari list items dengan index
        //0 until items.size , contoh items.size = 5
        //maka 0,1,2,3,4
        selectedItemPrice = items[(0 until items.size).random()]
        //print info name item
        printInfoItem(selectedItemPrice)
        //input user price
        inputPriceUser()
        //melakukan pengecekan nilai price
        checkWinner(checkPrice(inputPlaerOne), checkPrice(inputPlaerTwo))
    }

    private fun inputPriceUser() {
        println("Masukkan Harga Pemain Pertama = ")
        readLine()?.toInt()?.let {
            inputPlaerOne = it
        }
        println("Masukkan Harga Pemain Kedua = ")
        readLine()?.toInt()?.let {
            inputPlaerTwo = it
        }
    }

    private fun printInfoItem(selectedItemPrice: ItemPrice) {
        println("*************************************")
        println("Tebaklah harga dari = ${selectedItemPrice.itemName}")
        println("*************************************")
    }

    private fun checkPrice(userInput: Int): Int {
        return when {
            userInput == selectedItemPrice.price -> {
                PRICE_EQUAL
            }
            userInput > selectedItemPrice.price -> {
                PRICE_MORE_THAN
            }
            else -> {
                PRICE_LESS_THAN
            }

        }

    }

    private fun checkWinner(resullPlayerOne: Int, resultPlayerTwo: Int) {
        println("========================")
        println("Harga untuk barang ${selectedItemPrice.itemName}, adalah ${selectedItemPrice.price}")
        println("Hasilnya adalah....")
        println("=========================")
        if (resullPlayerOne == PRICE_EQUAL) {
            if (resultPlayerTwo == PRICE_EQUAL) {
                //both result is PRICE_EQUAL, means gak ada yang menang
                println("Keduanya Benar, Tidak ada yang menang")
            } else {
                //player two, PRICE_MORE_THAN atau PRICE_LESS_THAN
                println("Player 1 Menang")
            }

        } else {
            //result player one, PRICE_MORE_THAN atau PRICE_LESS_THAN
            if (resultPlayerTwo == PRICE_EQUAL) {
                println("Player 2 Menang")
            } else {
                val diffOne = (inputPlaerOne - selectedItemPrice.price).absoluteValue
                val diffTwo = (inputPlaerTwo - selectedItemPrice.price).absoluteValue
                when {
                    (diffOne < diffTwo) -> {
                        println("Player 1 Mendekati Benar, Player 1 MEnang")
                    }
                    (diffOne > diffTwo) -> {
                        println("Player 2 Mendekati Benar, Player 2 Menang")
                    }
                    else -> {
                        //diffOne == diffTwo
                        println("Keduanya hampir benar, tidak ada yang menang")
                    }
                }
            }
        }
    }
}