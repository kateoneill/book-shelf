package utils

import java.util.*

object ValidateInput {

    fun readValidGenre(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (GenreValidation.isValidGenre(input))
                return input
            else {
                print("Invalid genre: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    fun readValidURL(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (URLValidation.isValidURL(input))
                return input
            else {
                print("Invalid URL: $input.  Please try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }
}